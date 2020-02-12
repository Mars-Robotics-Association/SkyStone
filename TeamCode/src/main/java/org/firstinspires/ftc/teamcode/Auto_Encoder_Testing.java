package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@Autonomous(name = "Auto_Encoder_Testing", group = "Autonomous")
public class Auto_Encoder_Testing extends LinearOpMode
{
    public SimpleFieldNavigation nav = null;

    @Override
    public void runOpMode()
    {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //Block: Go forwards 20 inches
        nav.GoForward(20, 1, 0);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        telemetry.update();

        //Stops all
        nav.StopAll();

        //Brakes
        nav.Brake(1);

        sleep(5000);

    }
}
