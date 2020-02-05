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
@Disabled
@Autonomous(name = "Auto_Testing", group = "Autonomous")
public class Auto_Testing extends LinearOpMode {

    public SimpleFieldNavigation nav = null;
    public ArmAttachmentCustom arm = null;
    public ColorSensor colorSensorSkystone = null;

    @Override
    public void runOpMode()
    {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        colorSensorSkystone = new ColorSensor(this, "colorSensorLeft");
        colorSensorSkystone.Init();

        waitForStart();

        nav.RotateTo(180, 0.5);
        while (!nav.CheckCloseEnoughRotation())
        {
            telemetry.addData("Turning to ", 180);
            telemetry.update();
        }
        nav.StopAll();

        sleep(1000);

        nav.RotateTo(270, 0.5);
        while (!nav.CheckCloseEnoughRotation())
        {
            telemetry.addData("Turning to ", 270);
            telemetry.update();
        }
        nav.StopAll();

        sleep(1000);

        nav.RotateTo(0, 0.5);
        while (!nav.CheckCloseEnoughRotation())
        {
            telemetry.addData("Turning to ", 0);
            telemetry.update();
        }
        nav.StopAll();

        sleep(1000);

        telemetry.update();

        //Stops all
        nav.StopAll();

        //Brakes
        nav.Brake(1);

        sleep(500);

    }
}
