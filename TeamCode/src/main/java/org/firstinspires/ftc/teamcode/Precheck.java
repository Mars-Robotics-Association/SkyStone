package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
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

@Autonomous(name = "PreCheck", group = "Autonomous")
public class Precheck extends LinearOpMode {

    public SimpleFieldNavigation nav = null;

    private ColorSensor colorSensor;
    private double HueThreshold = 40;
    private double RedHue = 0;
    private double BlueHue = 180;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();
        ArmAttachmentCustom arm = null;
        FoundationGrabber grab = null;
        GripperCustom gripper = null;
        TapeMeasure tape =null;
        CapstoneDeployer Deployer = null;

        RevTouchSensor ArmRetractStop;
        RevTouchSensor ArmUpStop;
        Sensor_Distance distanceSensor = null;

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        gripper = new GripperCustom(this);
        gripper.Init();

        grab = new FoundationGrabber(this);
        grab.Init();

        tape = new TapeMeasure(this);
        tape.Init();

        Deployer = new CapstoneDeployer(this);
        Deployer.Init();

        ArmRetractStop = hardwareMap.get(RevTouchSensor.class, "ArmRetractStop");
        ArmUpStop = hardwareMap.get(RevTouchSensor.class, "ArmUpStop");

        distanceSensor = new Sensor_Distance(this, "ODSLeft");
        distanceSensor.Init();
        colorSensor = new ColorSensor(this, "colorSensorGround");
        colorSensor.Init();


        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //Block: Go forwards to the line


        //Stops all
        nav.StopAll();

        //Brakes
        nav.SetBrakePos();
        nav.Brake(1);
    }
}
