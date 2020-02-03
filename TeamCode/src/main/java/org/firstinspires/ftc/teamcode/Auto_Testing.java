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

        boolean run = true;
        while (run)
        {
            telemetry.addData("Detected Skystone: ", true);
            telemetry.addData("Detected Color: ", colorSensorSkystone.returnHue());
            telemetry.addData("Detected Lightness: ", colorSensorSkystone.returnLightness());
            telemetry.addData("Detected Distance: ", colorSensorSkystone.returnDistanceCM());
            telemetry.update();
        }

        telemetry.update();

        //Stops all
        nav.StopAll();

        //Brakes
        nav.Brake(1);

        sleep(500);

    }
}
