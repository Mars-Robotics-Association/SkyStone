package org.firstinspires.ftc.teamcode;

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

public class ArmAttachment implements Attachment {
    private DcMotor ArmForward = null;
    private DcMotor ArmBackward = null;
    private DcMotor ArmLeft = null;
    private DcMotor ArmRight = null;
    OpMode opmode;

    public Gripper gripper;

    public ArmAttachment(OpMode thatopmode) {
        opmode = thatopmode;
    }
    public void Init() {

        ArmForward= opmode.hardwareMap.dcMotor.get("ArmForward");
        ArmBackward= opmode.hardwareMap.dcMotor.get("ArmBackward");
        ArmLeft= opmode.hardwareMap.dcMotor.get("ArmLeft");
        ArmRight= opmode.hardwareMap.dcMotor.get("ArmRight");
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
        ArmForward.setPower(0.2);
        ArmBackward.setPower(0.2);
    }
    public void LiftDown () {
        ArmForward.setPower(-0.2);
        ArmBackward.setPower(-0.2);
    }
    public void LiftStopVertical () {
        ArmForward.setPower(0);
        ArmBackward.setPower(0);
    }
    public void LiftLeft () {
        ArmLeft.setPower(0.2);
        ArmRight.setPower(-0.2);
    }

    public void LiftRight () {
        ArmLeft.setPower (-0.2);
        ArmRight.setPower (0.2); }

    public void LiftStopHorizontal () {
        ArmLeft.setPower (0);
        ArmRight.setPower (0);
    }
    public void PickUpStone(){
        do{
            LiftDown();
        }while(ArmLeft.getCurrentPosition() == 0 && ArmRight.getCurrentPosition() == 0);
        //0 is down for arm motors
        LiftStopVertical();
        gripper.GripperClose();
    }

    public void PutDownStone(){
        do{
            LiftDown();
        }while(ArmLeft.getCurrentPosition() == 0 && ArmRight.getCurrentPosition() == 0);
        LiftStopHorizontal();
        gripper.GripperOpen();
    }




}
