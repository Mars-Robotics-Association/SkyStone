package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

/*
Goes forwards to a blue or red line and then brakes
 */
@Autonomous(name = "Auto_Testing", group = "Autonomous")
public class Auto_Testing extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    public ArmAttachmentCustom arm = null;
    public ColorSensor colorSensorSkystone = null;
    private Sensor_Distance distanceSensor = null;

    @Override
    public void runOpMode()
    {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        colorSensorSkystone = new ColorSensor(this, "colorSensorLeft");
        colorSensorSkystone.Init();

        distanceSensor = new Sensor_Distance(this, "ODSLeft");
        distanceSensor.Init();

        waitForStart();

        nav.GoForward(-10, 0.2, 0);
        while (distanceSensor.GetRangeCM() > 7)
        {
            nav.Loop();
            telemetry.update();
        }
        nav.StopAll();



        nav.StopAll();
    }

    public void GoForward(double distance, double speed, double angle) {
        nav.GoForward(distance, speed, angle);
        while (!nav.CheckIfAtTargetDestination()) {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

    public void GoRight(double distance, double speed, double angle) {
        nav.GoRight(distance, speed, angle);
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

/*nav.RotateTo(180, 0.4);
        while (!nav.CheckCloseEnoughRotation())
        {
        nav.Loop();
        telemetry.addData("Turning to ", 180);
        telemetry.update();
        }
        nav.StopAll();

        sleep(1000);

        nav.RotateTo(270, 0.4);
        while (!nav.CheckCloseEnoughRotation())
        {
        nav.Loop();
        telemetry.addData("Turning to ", 270);
        telemetry.update();
        }
        nav.StopAll();

        sleep(1000);

        nav.RotateTo(0, 0.4);
        while (!nav.CheckCloseEnoughRotation())
        {
        nav.Loop();
        telemetry.addData("Turning to ", 0);
        telemetry.update();
        }

        sleep(1000);

        nav.RotateTo(-60, 0.2);
        while (!nav.CheckCloseEnoughRotation())
        {
        nav.Loop();
        telemetry.addData("Turning to ", -60);
        telemetry.update();
        }

        sleep(1000);

        nav.RotateTo(-200, 0.2);
        while (!nav.CheckCloseEnoughRotation())
        {
        nav.Loop();
        telemetry.addData("Turning to ", -200);
        telemetry.update();
        }*/
