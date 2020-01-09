package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;


@Autonomous(name = "HS_Auto_BlueZone", group = "Autonomous")
public class HS_Auto_BlueZone extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    public ColorSensor color = null;
    public FoundationGrabber grab = null;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();
        color = new ColorSensor(this);
        color.Init();
        grab = new FoundationGrabber(this);
        grab.Init();

        waitForStart();
        nav.Start();
        telemetry.addData("Status", "Initialized");
        grab.Loop();
        nav.Loop();
        telemetry.update();




        grab.FoundationGrabUp();
        //Go forwards 39.5 inches
        telemetry.addData("about to drive ","");
        telemetry.update();
        GoForward(-39.5, 1);
        telemetry.addData("done with 1st movement","");
        telemetry.update();
        grab.FoundationGrabDown();
        sleep(2000);

        //Block: Go back 40 inches
        GoForward(40, 1);
        grab.FoundationGrabUp();
        telemetry.addData("done with 2nd movement","");
        telemetry.update();
        sleep(2000);

        //Block: Go left 28 inches
        GoRight(-28, 1);

        //Block: Go forward 38.5 inches
        GoForward(-38.5, 1);

        GoRight(28,1);

        GoForward(18,1);

        GoForward(-3,1);

        nav.GoRight(-42, 0.15);
        double sensorValue = 0;
        while(!(color.returnHue()>180)){
            nav.Loop();
            if(color.returnHue()>sensorValue){
                sensorValue=color.returnHue();
            }

            telemetry.addData("colorSensor", color.returnHue());
            telemetry.addData("max detection", sensorValue  );
            telemetry.update();
        }
        nav.StopAll();
        nav.SetBrakePos();
        nav.Brake(0.15);



    }
    public void GoForward(double distance, double speed){
        nav.GoForward(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
    public void GoRight(double distance, double speed){
        nav.GoRight(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
}
