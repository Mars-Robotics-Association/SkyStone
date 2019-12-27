package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;


@Autonomous(name = "simpleBlueZone", group = "Autonomous")
public class simpleBlueZone extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();
        nav.Start();
        waitForStart();
        nav.FoundationGrab(1);
        sleep(5000);
        nav.FoundationGrab(-1);
    }
}
