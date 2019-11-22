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
    private DcMotor LeftIntake = null;
    private DcMotor RightIntake = null;

    int chasis=0;
    double Vratio;
    double Hratio;
    OpMode opmode;

    public Gripper gripper;

    public ArmAttachment(OpMode thatopmode, int currentChasis) {
        opmode = thatopmode;
        chasis=currentChasis;
    }
    //chasis = number of current drive chasis

    public void Init() {
        if(chasis==1||chasis==3) {
            ArmForward = opmode.hardwareMap.dcMotor.get("ArmForward");
            ArmBackward = opmode.hardwareMap.dcMotor.get("ArmBackward");
            ArmLeft = opmode.hardwareMap.dcMotor.get("ArmLeft");
            ArmRight = opmode.hardwareMap.dcMotor.get("ArmRight");
            Vratio=1;
            Hratio=1;
        }
        else if(chasis==2||chasis==4) {
            ArmForward = opmode.hardwareMap.dcMotor.get("ArmVertical");
            ArmLeft = opmode.hardwareMap.dcMotor.get("ArmHorizontal");
            LeftIntake = opmode.hardwareMap.dcMotor.get("LeftIntake");
            RightIntake = opmode.hardwareMap.dcMotor.get("RightIntake");
            Vratio=1;
            Hratio=1;
        }
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
        ArmForward.setPower(0.2*Vratio);
        if(chasis==1||chasis==3) {
            ArmBackward.setPower(0.2*Vratio);
        }
    }
    public void LiftDown () {
        ArmForward.setPower(-0.2*Vratio);
        if(chasis==1||chasis==3) {
            ArmBackward.setPower(-0.2*Vratio);
        }
    }
    public void LiftStopVertical () {
        ArmForward.setPower(0);
        if(chasis==1||chasis==3) {
            ArmBackward.setPower(0);
        }
    }
    public void LiftLeft () {
        ArmLeft.setPower(0.2*Hratio);
        if(chasis==1||chasis==3) {
            ArmRight.setPower(-0.2*Hratio);
        }
    }

    public void LiftRight () {
        ArmLeft.setPower(-0.2*Hratio);
        if (chasis == 1 || chasis == 3) {
            ArmRight.setPower(0.2*Hratio);
        }
    }
    public void LiftStopHorizontal () {
        ArmLeft.setPower(0);
        if(chasis==1||chasis==3) {
            ArmRight.setPower(0);
        }
    }

    public void IntakeOn(){
        LeftIntake.setPower(1);
        RightIntake.setPower(-1);
    }
    public void IntakeOff(){
        LeftIntake.setPower(-1);
        RightIntake.setPower(1);
    }





}
