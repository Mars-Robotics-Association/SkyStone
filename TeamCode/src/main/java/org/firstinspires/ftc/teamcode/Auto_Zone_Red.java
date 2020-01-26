package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "Auto_Zone_Red", group = "Autonomous")
public class Auto_Zone_Red extends LinearOpMode {

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
        GoRight(-16,0.2);
        GoForward(-30,0.2);
        sleep(500);
        fgrabber.FoundationGrabDown();
        sleep(500);
        GoForward(31,0.2);
        GoRight(-5,0.2);
        sleep(5000);



        //begin line finding

        nav.GoRight(1000, 0.2);
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
