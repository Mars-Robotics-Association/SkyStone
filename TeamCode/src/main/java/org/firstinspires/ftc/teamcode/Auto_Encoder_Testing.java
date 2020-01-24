package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

@Autonomous(name = "Auto_Testing", group = "Autonomous")
public class Auto_Encoder_Testing extends LinearOpMode
{
    public SimpleFieldNavigation nav = null;

    @Override
    public void runOpMode()
    {
        nav = new SimpleFieldNavigation(this, true);
        nav.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //Block: Go sideways 10 inches
        nav.GoRight(10, 0.4);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }

        //Stops all
        nav.StopAll();

        //Brakes
        nav.SetBrakePos();
        nav.Brake(1);

        sleep(500);

    }
}
