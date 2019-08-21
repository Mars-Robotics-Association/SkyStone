package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;




public class universalDriveControl extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();


    double leftStickY;
    double leftStickX;
    double rightStickX;
    double rightStickY;
    boolean xButton;
    boolean yButton;
    boolean aButton;
    boolean bButton;


//    public boolean

    // @Override

     //   telemetry.addData("Status", "Initialized");
       // telemetry.update();


    @Override
    public void init() {

    }

    @Override
    public void loop() {


        //telemetry.addData("LIFT POWER:", LIFT_POWER);
        //telemetry.update();
         leftStickY = gamepad1.left_stick_y;//controls left and right  motors
       leftStickX = gamepad1.left_stick_x;//controls rear and front  motors
        rightStickX = gamepad1.right_stick_x;// controls turning
        rightStickY = gamepad1.right_stick_y;
        xButton = gamepad1.x;
        yButton = gamepad1.y;
        bButton = gamepad1.b;
        aButton = gamepad1.a;
        telemetry.addData("X", "Initialized");
        telemetry.update();

    }


}