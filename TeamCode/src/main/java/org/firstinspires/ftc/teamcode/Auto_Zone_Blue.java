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



        fgrabber.FoundationGrabUp();
        GoRight(18,0.8); //Line up with foundation
        GoForward(-30,0.6); //Go to foundation
        fgrabber.FoundationGrabDown(); //Grab foundation
        sleep(500);

        GoForward(31,0.2);//Pull foundation back
        sleep(200);
        nav.GoForward(4,0.6);//slam into wall
        sleep(500);//wait half a second
        nav.StopAll();//stop going
        nav.ResetGyro();
        GoRight(2, 0.6);

        fgrabber.FoundationGrabUp();//move grabbers up
        GoForward(-3, 0.6);//go forwards a bit


        GoRight(-32, 0.8);//go just beyond foundation
        GoForward(-19, 0.8);//go in front of foundation
        nav.GoRight(15, 1);//push it into the wall
        sleep(1000);//wait 1 second
        nav.StopAll();//stop


        //begin line finding

        nav.GoRight(-100, 0.4);
        while (Math.abs(RedHue - colorSensor.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensor.returnHue()) > HueThreshold)
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


        //Block: Go forwards to the line


        //Stops all




    }


    public void GoForward(double distance, double speed){
        nav.GoForward(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
    public void GoRight(double distance, double speed){
        nav.GoRight(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

}
