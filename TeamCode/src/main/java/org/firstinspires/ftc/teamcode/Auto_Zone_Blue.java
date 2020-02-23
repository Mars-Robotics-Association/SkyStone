package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "Auto_Zone_Blue", group = "Autonomous")
public class Auto_Zone_Blue extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    public FoundationGrabber fgrabber = null;
    private ColorSensor colorSensor;
    private double HueThreshold = 40;
    private double RedHue = 0;
    private double BlueHue = 180;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        fgrabber = new FoundationGrabber(this);
        nav.Init();
        fgrabber.Init();
        colorSensor = new ColorSensor(this, "colorSensorGround");
        colorSensor.Init();

        //forward 30 in
        // back 31
        //release grabber

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");


        //START
        fgrabber.FoundationGrabUp();
        GoRight(18,0.8, 0); //Line up with foundation
        GoForward(-30,0.6, 0); //Go to foundation
        fgrabber.FoundationGrabDown(); //Grab foundation
        sleep(1000);

        //PULL BACK FOUNDATION
        GoForward(31,0.3, 0);//Pull foundation back
        sleep(200);
        nav.GoForward(4,1, 0);//slam into wall
        nav.StopAll();//stop going

        //FREE ROBOT FROM FOUNDATION
        fgrabber.FoundationGrabUp();//move grabbers up

        GoRight(-20, 0.8, 0);


        //DRIVE TO THE LINE
        nav.GoRight(-100, 0.3, 0);//begin driving
        while (Math.abs(RedHue - colorSensor.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensor.returnHue()) > HueThreshold)//wait until the line is seen
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.addData("Hue: ", colorSensor.returnHue());
            telemetry.update();
        }

        //Stops all
        nav.StopAll();

        //Brakes
        nav.SetBrakePos();
        nav.Brake(1);
    }


    public void GoForward(double distance, double speed, double angle){
        nav.GoForward(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
    public void GoRight(double distance, double speed, double angle){
        nav.GoRight(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

}
