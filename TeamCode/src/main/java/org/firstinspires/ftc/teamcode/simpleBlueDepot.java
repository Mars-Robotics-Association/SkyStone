package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;


@Autonomous(name = "simpleBlueDepot", group = "Autonomous")
public class simpleBlueDepot extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();
        nav.Start();
        nav.Loop();
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        nav.StopAll();
    }
}
