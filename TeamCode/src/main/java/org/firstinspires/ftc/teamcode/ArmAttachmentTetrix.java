package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ArmAttachmentTetrix implements Attachment {
    private DcMotor ArmHorizontal = null;
    private DcMotor ArmVertical = null;
    private DcMotor ArmLeft = null;
    private DcMotor ArmRight = null;
    private DcMotor LeftIntake = null;
    private DcMotor RightIntake = null;

    int ArmLeftResting;
    int ArmRightResting;
    int ArmHorizontalResting;

    double ArmLeftPower=0.8;
    double ArmRightPower=0.8;

    double Vratio;
    double Hratio;
    OpMode opmode;

    public Gripper gripper;

    public ArmAttachmentTetrix(OpMode thatopmode) {
        opmode = thatopmode;
    }

    public void Init() {
        ArmLeft = opmode.hardwareMap.dcMotor.get("ArmLeft");
        ArmRight = opmode.hardwareMap.dcMotor.get("ArmRight");
        ArmHorizontal = opmode.hardwareMap.dcMotor.get("ArmHorizontal");
        Vratio=1;
        Hratio=1;


        ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ArmRightResting=ArmRight.getCurrentPosition();
        ArmLeftResting=ArmLeft.getCurrentPosition();

        ArmRight.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        ArmLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    //@Override
    public void Loop() {

    }

    @Override
    public void Run() {

    }

    @Override
    public void Stop() {
//test
    }
    public void LiftUp () {
        ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setPower(ArmRightPower);
        ArmLeft.setPower(ArmLeftPower);
        ArmRightResting=ArmRight.getCurrentPosition();
        ArmLeftResting=ArmLeft.getCurrentPosition();
    }
    public void LiftDown () {
        ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setPower(-ArmRightPower);
        ArmLeft.setPower(-ArmLeftPower);
        ArmRightResting=ArmRight.getCurrentPosition();
        ArmLeftResting=ArmLeft.getCurrentPosition();
    }
    public void LiftStopVertical () {
        ArmRight.setPower(0*Vratio);
        ArmLeft.setPower(0*Vratio);
        if(ArmRight.getCurrentPosition()<ArmRightResting){
            ArmRight.setTargetPosition(ArmRightResting);
            ArmLeft.setTargetPosition(ArmLeftResting);
            ArmRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ArmLeft.setPower(0.6);
            ArmRight.setPower(0.6);
        }
        else if(ArmRight.getCurrentPosition()>ArmRightResting){
            ArmRight.setTargetPosition(ArmRightResting);
            ArmLeft.setTargetPosition(ArmLeftResting);
            ArmRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            ArmLeft.setPower(-0.6);
            ArmRight.setPower(-0.6);
        }
    }
    public void LiftExtend () {
        ArmHorizontal.setPower(0.5*Hratio);
    }

    public void LiftRetract () {
        ArmHorizontal.setPower(-0.2*Hratio);

    }
    public void LiftStopHorizontal () {
        ArmHorizontal.setPower(0*Hratio);
    }
}