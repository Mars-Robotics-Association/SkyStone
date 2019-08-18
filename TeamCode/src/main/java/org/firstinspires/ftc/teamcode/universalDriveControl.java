package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name="universalDriveControl", group="Iterative Opmode")
//@Disabled

public class universalDriveControl extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();


    double a;
    double b;
    double c;
    double d;
    boolean xButton;
    boolean yButton;
    boolean aButton;
    boolean bButton;


//    public boolean

    // @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

        runtime.reset();

    }


    @Override
    public void loop() {


        //telemetry.addData("LIFT POWER:", LIFT_POWER);
        //telemetry.update();
        a = gamepad1.left_stick_y;//controls left and right  motors
        b = gamepad1.left_stick_x;//controls rear and front  motors
        c = gamepad1.right_stick_x;// controls turning
        d = gamepad1.right_stick_y;
        xButton = gamepad1.x;
        yButton = gamepad1.y;
        bButton = gamepad1.b;
        aButton = gamepad1.a;

    }


}