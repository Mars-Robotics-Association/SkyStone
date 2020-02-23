package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto_Combined", group = "Autonomous")
public class Auto_Combined extends LinearOpMode
{
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

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");



        fgrabber.FoundationGrabUp();
        GoRight(-15,0.8, 0); //Line up with foundation
        GoForward(-25,0.6, 0); //Go to foundation
        GoForward(-5, 0.3, 0);//Go slower
        fgrabber.FoundationGrabDown(); //Grab foundation
        sleep(1000);

        //PULL BACK FOUNDATION
        GoForward(31,0.4, 0);//Pull foundation back
        sleep(200);
        /*nav.GoForward(4,1, 0);//slam into wall
        nav.StopAll();//stop going*/

        //FREE ROBOT FROM FOUNDATION
        fgrabber.FoundationGrabUp();//move grabbers up

        GoRight(33, 1, 0);

        GoForward(-12, 1, 0);


        //DRIVE TO THE LINE
        nav.GoRight(100, 0.3, 0);//begin driving
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold)//wait until the line is seen
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.addData("Hue: ", colorSensorBot.returnHue());
            telemetry.update();
        }

        RotateToPrecise(0);

        //START SKYSTONE RETRIEVAL
        fgrabber.FoundationGrabUp();//Make sure grabbers are up
        GoRight(24, 1, 0);//Go to blocks

        nav.GoForward(-10, 0.2, 0);
        while (distanceSensor.GetRangeCM() > 12)
        {
            nav.Loop();
            telemetry.update();
        }
        nav.StopAll();

        //STRAFING RUN 1
        nav.GoRight(50, 0.3, 0);//start strafing along blocks
        while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
        GoRight(2, 0.3, 0);//align
        GoForward(-3, 0.3, 0);//Go to block
        fgrabber.FoundationGrabDownR();//grab block
        GoForward(-3, 0.1, 0);//finish going
        sleep(250);


        //PULL OUT SKYSTONE
        GoForward(7, 1, 0);//Go backwards a few inches to pull block out
        RotateTo(-90, 0.4);//Rotate to -90 degrees


        //GO TO LINE
        GoForward(-12, 1, -90);
        nav.GoForward(-30, 0.3, -90);
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();

        //PLACE SKYSTONE 1
        GoForward(-12, 1, -90);
        fgrabber.FoundationGrabUp();
        GoForward(22, 1, -90);

        RotateTo(-90, 0.4);

        //move towards middle a little
        GoRight(-3, 0.8, -90);

        RotateToPrecise(-90);

        //Goes to far wall by skystones
        GoForward(35, 1, -90);

        RotateTo(0, 0.4);

        nav.GoRight(15, 1, 0);
        sleep(1000);
        nav.StopAll();


        //Line up to blocks
        nav.GoForward(-15, 0.2, 0);
        while (distanceSensor.GetRangeCM() > 7)
        {
            nav.Loop();
            telemetry.update();
        }
        nav.StopAll();


        //STRAFING RUN 2
        nav.GoRight(-50, 0.3, 0);//start strafing along blocks
        while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
        GoRight(-2, 0.2, 0);//align
        GoForward(-3, 0.1, 0);//Go to block
        fgrabber.FoundationGrabDownR();//grab block
        GoForward(-2, 0.1, 0);//finish going
        sleep(200);


        //PULL OUT SKYSTONE
        GoForward(5, 1, 0);//Go backwards a few inches to pull block out
        RotateTo(-90, 0.4);//Rotate to -90 degrees


        //GO TO LINE
        GoForward(-25, 1, -90);
        GoRight(-8, 1, -90);
        nav.GoForward(-40, 0.3, -90);
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();


        //PLACE SKYSTONE 2
        GoForward(-12, 1, -90);
        fgrabber.FoundationGrabUp();
        GoForward(12, 1, -90);


        //Brakes
        nav.SetBrakePos();
        nav.Brake(1);
    }
    public void GoForward(double distance, double speed, double angle) {
        nav.GoForward(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }

        nav.StopAll();
        nav.SetBrakePos();
        nav.Brake(1);
    }

    public void GoRight(double distance, double speed, double angle) {
        nav.GoRight(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        /*nav.SetMoveSpeed(1);
        while (!nav.CheckIfAtTargetDestinationPrecise())
        {
            nav.Loop();
            telemetry.addData("looping2: ", true);
            telemetry.update();
        }*/
        nav.StopAll();
        nav.SetBrakePos();
        nav.Brake(1);
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
    public void RotateToPrecise(double angle)
    {
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
