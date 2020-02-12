package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name = "MS Simple", group = "Autonomous")
public class MiddleSchoolAutonomous2 extends LinearOpMode {

    public SimpleFieldNavigation nav = null;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();


        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");
        double time = getRuntime();
        //Block: Go forwards to the line
        nav.GoForward(5, 0.1, 0);
        while (getRuntime()-time<3)
        {
            nav.Loop();
            telemetry.addData("looping: ", true);
            telemetry.update();
        }
        nav.StopAll();

    }
}
