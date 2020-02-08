package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

@Autonomous(name = "Auto_Depot_Blue", group = "Autonomous")
public class Auto_Depot_Blue extends LinearOpMode {

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

        colorSensorSkystone = new ColorSensor(this, "colorSensorRight");
        colorSensorSkystone.Init();

        colorSensorBot = new ColorSensor(this, "colorSensorGround");
        colorSensorBot.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //START
        fgrabber.FoundationGrabUp();//Make sure grabbers are up
        GoForward(-28, 0.3);//Go to blocks
        //STRAFING RUN 1
        nav.GoRight(-50, 0.2);//start strafing along blocks
        while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
        GoRight(-2, 0.2);
        GoForward(-2, 0.4);
        fgrabber.FoundationGrabDownL();//grab block
        sleep(2000);
        GoForward(8, 0.4);//Go backwards a few inches to pull block out
        //GoRight(20, 0.8);
        RotateTo(0, 0.4);
        nav.GoRight(100, 0.4);//Start towards the line
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
        {
            nav.Loop();
            telemetry.addData("looping2: ", true);
            telemetry.update();
        }
        nav.StopAll();
        //The robot is now at the line
        RotateTo(0, 0.4);
        //PLACE SKYSTONE 1
        GoRight(12, 0.4);
        fgrabber.FoundationGrabUp();
        sleep(1000);

        //GO TO LINE
        nav.GoRight(-100, 0.4);
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
        nav.GoForward(distance, speed);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

    public void GoRight(double distance, double speed) {
        nav.GoRight(distance, speed);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

    public void RotateTo(double angle, double speed)
    {
        nav.RotateTo(angle, speed);
        while (!nav.CheckCloseEnoughRotation())
        {
            nav.Loop();
            telemetry.addData("Turning to ", angle);
            telemetry.update();
        }
        nav.StopAll();

        nav.RotateTo(angle, 0.1);
        while (!nav.CheckCloseEnoughRotationPrecise())
        {
            nav.Loop();
            telemetry.addData("Turning to precise", angle);
            telemetry.update();
        }
        nav.StopAll();
    }
}
