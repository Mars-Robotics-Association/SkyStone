package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;


@TeleOp(name="Universal Drive Control PBR Test", group="Iterative Opmode")
//@Disabled

public class UDCTest extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();


    public void init() {

    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {


    }


    @Override
    public void loop() {


        telemetry.addData("X", "joystickcalc.leftStickX");
        telemetry.update();
    }


}