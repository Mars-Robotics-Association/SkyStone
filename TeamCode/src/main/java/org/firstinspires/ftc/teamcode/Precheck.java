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
    public ArmAttachmentCustom arm = null;
    public FoundationGrabber grab = null;
    public GripperCustom gripper = null;
    public TapeMeasure tape =null;
    public CapstoneDeployer Deployer = null;

    private RevTouchSensor ArmRetractStop;
    private RevTouchSensor ArmUpStop;
    private Sensor_Distance distanceSensor = null;
    private ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        colorSensor = new ColorSensor(this, "colorSensorGround");
        colorSensor.Init();

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

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //Run everything needed
        

        //Stops all
        nav.StopAll();

        //Brakes
        nav.SetBrakePos();
        nav.Brake(1);
    }
}
