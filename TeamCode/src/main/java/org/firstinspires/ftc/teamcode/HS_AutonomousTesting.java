package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

/*
Goes forwards to a blue or red line and then brakes
 */

@Autonomous(name = "HS_Auto_Testing", group = "Autonomous")
public class HS_AutonomousTesting extends LinearOpMode {

    public SimpleFieldNavigation nav = null;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //Block: Go forwards to the line
        nav.GoForward(100000, 0.2);
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
    }
}
