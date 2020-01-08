package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "MiddleSchoolAutonomous", group = "Autonomous")
public class MiddleSchoolAutonomous extends LinearOpMode {

    public SimpleFieldNavigation nav = null;

    private ColorSensor colorSensor;
    private double HueThreshold = 20;
    private double RedHue = 0;
    private double BlueHue = 207;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        colorSensor = new ColorSensor(this);
        colorSensor.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //Block: Go forwards to the line
        nav.GoForward(10, 1);
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
        nav.Brake(0.1);
    }
}

    /*public SimpleFieldNavigation nav = null;
    public ColorSensor color = null;
    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this);
        color = new ColorSensor(this);
        nav.Init();
        color.Init();
        waitForStart();
        nav.Start();
        telemetry.addData("Status", "Initialized");

        nav.Loop();
        telemetry.update();



        nav.GoForward(1,0.1);
        double sensorValue=0;
        while(!(color.returnHue()>180)){
            nav.Loop();
            if(color.returnHue()>sensorValue){
                sensorValue=color.returnHue();
            }

            telemetry.addData("colorSensor", color.returnHue());
            telemetry.addData("max detection", sensorValue  );
            telemetry.update();
        }
        telemetry.addData("detechted", sensorValue  );
        // telemetry.update();
        nav.SetBrakePos();


        nav.StopAll();


        nav.Brake(0.1);



        nav.StopAll();

    }*/
