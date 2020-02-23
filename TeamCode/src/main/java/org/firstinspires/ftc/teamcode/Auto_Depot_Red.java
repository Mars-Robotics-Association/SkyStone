package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//@Disabled
@Autonomous(name = "Auto_Depot_Red", group = "Autonomous")
public class Auto_Depot_Red extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    private ColorSensor colorSensorSkystone;
    private ColorSensor colorSensorBot;
    private Sensor_Distance distanceSensor = null;
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

        colorSensorSkystone = new ColorSensor(this, "colorSensorLeft");
        colorSensorSkystone.Init();

        colorSensorBot = new ColorSensor(this, "colorSensorGround");
        colorSensorBot.Init();

        distanceSensor = new Sensor_Distance(this, "ODSLeft");
        distanceSensor.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //START
        fgrabber.FoundationGrabUp();//Make sure grabbers are up
        GoForward(-18, 0.8, 0);//Go to blocks

        nav.GoForward(-10, 0.1, 0);
        while (distanceSensor.GetRangeCM() > 12)
        {
            nav.Loop();
            telemetry.update();
        }
        nav.StopAll();

        //STRAFING RUN 1
        nav.GoRight(50, 0.2, 0);//start strafing along blocks
        while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
        GoRight(2, 0.2, 0);//align
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

        nav.GoRight(15, 0.8, 0);
        sleep(1000);
        nav.StopAll();


        //Line up to blocks
        nav.GoForward(-15, 0.1, 0);
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
        GoRight(-8, 0.8, -90);
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

    /*nav = new SimpleFieldNavigation(this);
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

            //START
            fgrabber.FoundationGrabUp();//Make sure grabbers are up
            GoForward(-18, 0.8, 0);//Go to blocks

            nav.GoForward(-10, 0.2, 0);
            while (distanceSensor.GetRangeCM() > 7)
            {
            nav.Loop();
            telemetry.update();
            }
            nav.StopAll();

            //STRAFING RUN 1
            nav.GoRight(50, 0.2, 0);//start strafing along blocks
            while (!(colorSensorSkystone.returnHue() > 100)) //wait until color sensor sees skystone
            {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
            }
            nav.StopAll();
            GoRight(2, 0.2, 0);//align
            GoForward(-3, 0.1, 0);//Go to block
            fgrabber.FoundationGrabDownR();//grab block
            GoForward(-2, 0.1, 0);//finish going
            sleep(200);

            //PULL OUT SKYSTONE
            GoForward(10, 1, 0);//Go backwards a few inches to pull block out
            RotateTo(-90, 0.4);//Rotate to -90 degrees


            //PLACE SKYSTONE 1
            GoForward(-44, 1, -90);
            fgrabber.FoundationGrabUp();
            //sleep(500);
            //RotateToPrecise(0);
            GoForward(10, 1, -90);


            //GO TO LINE
            nav.GoForward(100, 0.3, -90);
            while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
            {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
            }
            nav.StopAll();


            //ALIGNS FOR SECOND RUN
            //aligns with Y on skybridge
        *//*nav.GoRight(20, 1, -90);
        sleep(1500);
        nav.StopAll();
        //nav.ResetGyro();
        sleep(500);

        GoRight(-15, 0.4, -90);*//*

            RotateTo(-90, 0.4);

            //Goes to far wall by skystones
            GoForward(45, 1, -90);
*//*        nav.GoForward(15, 0.3, -90);
        sleep(2000);
        nav.StopAll();*//*


            //Turns back to 0 degrees
            //GoForward(-5, 0.8, -90);
            RotateTo(0, 0.4);


            nav.GoRight(10, 0.7, 0);
            sleep(1000);
            nav.StopAll();


            nav.GoForward(-15, 0.2, 0);
            while (distanceSensor.GetRangeCM() > 7)
            {
            nav.Loop();
            telemetry.update();
            }
            nav.StopAll();


            //STRAFING RUN 2
            nav.GoRight(-50, 0.2, 0);//start strafing along blocks
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
            GoForward(7, 1, 0);//Go backwards a few inches to pull block out
            RotateTo(-90, 0.4);//Rotate to -90 degrees


            //PLACE SKYSTONE 2
            GoForward(-65, 1, -90);
            fgrabber.FoundationGrabUp();
            //RotateToPrecise(0);
            //GoForward(5, 1, -90);


            //GO TO LINE
            nav.GoForward(100, 0.3, -90);
            while (Math.abs(RedHue - colorSensorBot.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensorBot.returnHue()) > HueThreshold) //wait until color sensor sees the line
            {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
            }
            nav.StopAll();


            //Brakes
            nav.SetBrakePos();
            nav.Brake(1);*/



