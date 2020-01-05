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
        public FoundationGrabber grab = null;
        UDC_Teleop UDC = null
        ColorSensor colorsensor = null;
        FoundationGrabber Grab = null;
        nav.Init();
        nav.Start();
        nav.Loop();
        telemetry.addData("Status", "Initialized");
          telemetry.update();
        waitForStart();
        Grab.FoundationGrab(1);
        sleep(5000);
        Grab.FoundationGrab(-1);
        nav.StopAll();
    }
    //Makes the Bot MOve to the Red or Blue Line/Skybridge
    while(colorsensor.returnHue()!=207||colorsensor.returnHue()!=0){
        UDC.RawForwards (1);
    }
    UDC.RawForwards (0);
    grab.FoundationGrab(-1)



    nav.GoForward(5, 1);
        while (!nav.CheckIfAtTargetDestination())
    {
        nav.Loop();
        telemetry.addData("looping1: ", true);
        telemetry.update();
    }
}
