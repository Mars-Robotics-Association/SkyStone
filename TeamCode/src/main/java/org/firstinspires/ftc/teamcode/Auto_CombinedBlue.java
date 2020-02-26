package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto_Combined_Blue", group = "Autonomous")
public class Auto_CombinedBlue extends LinearOpMode
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

        colorSensorSkystone = new ColorSensor(this, "colorSensorRight");
        colorSensorSkystone.Init();

        colorSensorBot = new ColorSensor(this, "colorSensorGround");
        colorSensorBot.Init();

        distanceSensor = new Sensor_Distance(this, "ODSRight");
        distanceSensor.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");



        //START
        fgrabber.FoundationGrabUp();
        GoRight(18,1, 0); //Line up with foundation
        nav.GoForward(10, 0.7, 0);
        sleep(500);
        nav.StopAll();
        GoForward(-29,0.6, 0); //Go to foundation
        fgrabber.FoundationGrabDown(); //Grab foundation
        sleep(800);


        //PULL BACK FOUNDATION
        GoForward(3,0.3, 0);//Pull foundation back
        GoForward(28,0.5, 0);//Pull foundation back

        //FREE ROBOT FROM FOUNDATION
        fgrabber.FoundationGrabUp();//move grabbers up

        GoRight(-33, 1, 0);

        GoForward(-16, 1, 0);


        //DRIVE TO THE LINE
        nav.GoRight(-100, 0.3, 0);//begin driving
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold)//wait until the line is seen
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.addData("Hue: ", colorSensorBot.returnHue());
            telemetry.update();
        }

        RotateToPrecise(0);

        //GoForward(-5, 0.5, 0);

        //START SKYSTONE RETRIEVAL
        fgrabber.FoundationGrabUp();//Make sure grabbers are up
        GoRight(-20, 1, 0);//Go to blocks

        nav.GoForward(-15, 0.2, 0);
        while (distanceSensor.GetRangeCM() > 15)
        {
            nav.Loop();
            telemetry.update();
        }
        nav.StopAll();

        //STRAFING RUN 1
        nav.GoRight(-50, 0.3, 0);//start strafing along blocks
        while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
        GoRight(-2, 0.3, 0);//align
        GoForward(-3, 0.3, 0);//Go to block
        fgrabber.FoundationGrabDownL();//grab block
        GoForward(-3, 0.1, 0);//finish going
        sleep(250);


        //PULL OUT SKYSTONE
        GoForward(7, 1, 0);//Go backwards a few inches to pull block out
        RotateTo(90, 0.4);//Rotate to 90 degrees


        //GO TO LINE
        GoForward(-12, 1, 90);
        nav.GoForward(-30, 0.3, 90);
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();

        //PLACE SKYSTONE 1
        fgrabber.FoundationGrabUp();
        GoForward(-12, 1, 90);
        GoForward(22, 1, 90);

        RotateTo(90, 0.4);

        //move towards middle a little
        GoRight(3, 0.8, 90);

        RotateToPrecise(90);

        //Goes to far wall by skystones
        GoForward(35, 1, 90);

        RotateTo(0, 0.4);

        nav.GoRight(-15, 1, 0);
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
        nav.GoRight(50, 0.3, 0);//start strafing along blocks
        while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
        GoRight(2, 0.2, 0);//align
        GoForward(-3, 0.1, 0);//Go to block
        fgrabber.FoundationGrabDownL();//grab block
        GoForward(-3, 0.1, 0);//finish going
        sleep(200);


        //PULL OUT SKYSTONE
        GoForward(10, 1, 0);//Go backwards a few inches to pull block out //13
        RotateTo(90, 0.6);//Rotate to 90 degrees //was 0.4


        //GO TO LINE
        GoForward(-25, 1, 90);
        //GoRight(-8, 1, 90);
        nav.GoForward(-40, 0.4, 90);
        while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();


        //PLACE SKYSTONE 2
        fgrabber.FoundationGrabUp();
        GoForward(-10, 1, 90);
        GoForward(8, 1, 90);


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
