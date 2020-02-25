package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto_Master", group = "Iterative")
public class _Auto_Master extends LinearOpMode
{
    DigitalChannel ColorSwitch;
    DigitalChannel SkystoneSwitch;
    DigitalChannel FoundationSwitch;

    private boolean redBlueSwitch = false;
    private boolean foundationSwitch = false;
    private boolean skystoneSwitch = false;

    public SimpleFieldNavigation nav = null;
    private ColorSensor colorSensorSkystone;
    private ColorSensor colorSensorBot;
    private Sensor_Distance distanceSensor = null;
    public FoundationGrabber fgrabber = null;

    private double HueThreshold = 40;
    private double RedHue = 0;
    private double BlueHue = 180;

    @Override
    public void runOpMode()
    {
        ColorSwitch = hardwareMap.get(DigitalChannel.class, "ColorSwitch");
        SkystoneSwitch = hardwareMap.get(DigitalChannel.class, "SkystoneSwitch");
        FoundationSwitch = hardwareMap.get(DigitalChannel.class, "FoundationSwitch");

        ColorSwitch.setMode(DigitalChannel.Mode.INPUT);
        SkystoneSwitch.setMode(DigitalChannel.Mode.INPUT);
        FoundationSwitch.setMode(DigitalChannel.Mode.INPUT);



        nav = new SimpleFieldNavigation(this);
        nav.Init();

        fgrabber = new FoundationGrabber(this);
        fgrabber.Init();

        colorSensorSkystone = new ColorSensor(this, "colorSensorLeft");
        colorSensorSkystone.Init();

        colorSensorBot = new ColorSensor(this, "colorSensorGround");
        colorSensorBot.Init();

        distanceSensor = new Sensor_Distance(this, "ODSLeft");
        distanceSensor.Init();

        redBlueSwitch=ColorSwitch.getState();
        skystoneSwitch=SkystoneSwitch.getState();
        foundationSwitch=FoundationSwitch.getState();

        telemetry.addData("redBlueSwitch",redBlueSwitch);
        telemetry.addData("skystoneSwitch", skystoneSwitch);
        telemetry.addData("foundationSwitch", foundationSwitch);
        telemetry.update();


        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        if(foundationSwitch && skystoneSwitch)
        {
            if(redBlueSwitch)
            {

            }
            else
            {

            }
        }
        else if(foundationSwitch)
        {
            if(redBlueSwitch)
            {

            }
            else
            {

            }
        }
        else if(skystoneSwitch)
        {
            if(redBlueSwitch)
            {

            }
            else
            {

            }
        }
        else
        {

        }
    }
}
