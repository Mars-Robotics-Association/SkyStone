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

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();
        waitForStart();
        nav.Start();
        telemetry.addData("Status", "Initialized");


    }
}
