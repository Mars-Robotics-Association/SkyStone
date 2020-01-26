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

    @Override
    public void runOpMode()
    {
        nav = new SimpleFieldNavigation(this);
        nav.Init();

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        arm.VerticalGoToPosition(-300);
        while (!arm.VerticalIsAtTargetPos())
        {
            telemetry.addData("Arm moving: ", true);
            arm.VerticalUpdateBreakPos();
        }
        arm.LiftStopVertical();

        telemetry.update();

        //Stops all
        nav.StopAll();

        //Brakes
        nav.Brake(1);

        sleep(500);

    }
}
