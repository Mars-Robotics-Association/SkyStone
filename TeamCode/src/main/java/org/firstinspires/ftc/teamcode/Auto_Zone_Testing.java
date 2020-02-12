package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "Auto_Zone_TESTING", group = "Autonomous")
public class Auto_Zone_Testing extends LinearOpMode {

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

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        //robot starts facing red side (east)

        /*//Go to foundation and flick it aside
        GoForward(-48, 0.8);//go to foundation
        RotateTo(90,1);//Hit corner of foundation
        RotateTo(90, 0.2);//correct any offset of robot
        nav.GoForward(48, 1);//push foundation into wall
        sleep(2000);
        nav.GoRight(-60, 1);//go to the red side wall
        sleep(2000);
        //robot is now against the red side wall at a set distance from the other wall facing the build site (north)

        //Go the the other side of the field
        GoRight(24, 0.6);//Go past other imaginary robot
        RotateTo(90, 0.3);//make sure is still at 90 degrees
        GoForward(-100, 1);//go to other side

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
        nav.Brake(1);*/


        //Block: Go forwards to the line


        //Stops all




    }


    public void GoForward(double distance, double speed, double angle){
        nav.GoForward(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("Forward: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
    public void GoRight(double distance, double speed, double angle){
        nav.GoRight(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("Right: ", true);
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
            telemetry.addData("Turning: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

}
