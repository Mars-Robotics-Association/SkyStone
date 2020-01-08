package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;


@Autonomous(name = "MiddleSchoolAutonomous", group = "Autonomous")
public class MiddleSchoolAutonomous extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    public ColorSensor color = null;
    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        color = new ColorSensor(this);
        nav.Init();
        color.Init();
        waitForStart();
        nav.Start();
        telemetry.addData("Status", "Initialized");

        nav.Loop();
        telemetry.update();  


        while(color.returnHue()!=207||color.returnHue()!=0){
            nav.GoForward(1,1);
        }


        //Block: Go forwards 5 inches
        nav.GoForward(5, 1);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }

        //Block: Go left 10 inches
        nav.GoForward(-10, 1);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping2: ", true);
            telemetry.update();
        }

        //Block: Rotate to 90 degrees
        nav.RotateTo(90, 1);
        while (!nav.CheckCloseEnoughRotation())
        {
            nav.Loop();
            telemetry.addData("looping3: ", true);
            telemetry.update();
        }

        nav.StopAll();
    }

}
