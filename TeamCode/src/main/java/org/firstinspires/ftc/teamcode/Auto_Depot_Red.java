package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Disabled
@Autonomous(name = "Auto_Depot_Red", group = "Autonomous")
public class Auto_Depot_Red extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    private ColorSensor colorSensorSkystone;
    private ColorSensor colorSensorBot;
    public FoundationGrabber fgrabber = null;

    private double HueThreshold = 40;
    private double RedHue = 0;
    private double BlueHue = 180;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        fgrabber = new FoundationGrabber(this);
        fgrabber.Init();

        colorSensorSkystone = new ColorSensor(this, "colorSensorSkystone");
        colorSensorSkystone.Init();

        colorSensorBot = new ColorSensor(this, "colorSensorGround");
        colorSensorBot.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //START
        fgrabber.FoundationGrabUp();//Make sure grabbers are up
        GoForward(-28, 0.4);//Go to blocks

        //STRAFING RUN 1
        nav.GoRight(48, 0.4);//start strafing along blocks
        while (!(colorSensorSkystone.returnLightness() < 50)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        fgrabber.FoundationGrabDown();//grab block
        GoForward(5, 0.4);//Go backwards a few inches to pull block out
        nav.GoRight(-100, 0.6);//Start towards the line
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        //The robot is now at the line

        //PLACE SKYSTONE 1
        GoRight(-24, 0.4);
        fgrabber.FoundationGrabUp();

        //GO TO LINE
        nav.GoRight(100, 0.6);
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
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

    public void GoForward(double distance, double speed) {
        nav.GoForward(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

    public void GoRight(double distance, double speed) {
        nav.GoRight(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
}
