package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ArmAttachmentCustom implements Attachment {
    private DcMotor ArmHorizontal = null;
    private DcMotor ArmVertical = null;
    private DcMotor ArmLeft = null;
    private DcMotor ArmRight = null;
    private DcMotor LeftIntake = null;
    private DcMotor RightIntake = null;

    double Vratio;
    double Hratio;
    OpMode opmode;

    int VerticalsRestingPos;

    public GripperTetrix gripper;

    public ArmAttachmentCustom(OpMode thatopmode) {
        opmode = thatopmode;
    }

    @Override
    public void Init() {
        ArmHorizontal = opmode.hardwareMap.dcMotor.get("ArmHorizontal");
        ArmVertical = opmode.hardwareMap.dcMotor.get("ArmVertical");

        LeftIntake = opmode.hardwareMap.dcMotor.get("LeftIntake");
        RightIntake = opmode.hardwareMap.dcMotor.get("RightIntake");

        ArmVertical.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Vratio=1;
        Hratio=1;
    }

    @Override
    public void Loop() {

    }

    @Override
    public void Run() {

    }

    @Override
    public void Stop()
    {
//test
    }

    public void LiftUp () //Moves the lift up and resets the resting target for LiftStopVertical
    {
        ArmVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmVertical.setPower(0.9*Vratio);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void LiftDown () //Moves the lift down and resets the resting target for LiftStopVertical
    {
        ArmVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmVertical.setPower(-0.9*Vratio);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void LiftStopVertical () //Sets the motors target position and tries to hold it
    {
        ArmVertical.setTargetPosition(VerticalsRestingPos);
        ArmVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void LiftExtend () {
        ArmHorizontal.setPower(0.9*Hratio);
    }

    public void LiftRetract ()
    {
        ArmHorizontal.setPower(-0.9*Hratio);

    }

    public void LiftStopHorizontal ()
    {
        ArmHorizontal.setPower(0*Hratio);
    }

    public void IntakeOn()
    {
        LeftIntake.setPower(-1);
        RightIntake.setPower(1);
    }

    public void IntakeReverse()
    {
        LeftIntake.setPower(1);
        RightIntake.setPower(-1);
    }

    public void IntakeOff()
    {
        LeftIntake.setPower(0);
        RightIntake.setPower(0);
    }
}