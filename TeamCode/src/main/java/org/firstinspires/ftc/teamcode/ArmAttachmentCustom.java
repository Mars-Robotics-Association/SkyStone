package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

public class ArmAttachmentCustom implements Attachment {
    private DcMotor ArmVertical = null;
    private DcMotor ArmLeft = null;
    private DcMotor ArmRight = null;
    private DcMotor LeftIntake = null;
    private DcMotor RightIntake = null;
    private Servo LiftExtendLeft = null;
    private Servo LiftExtendRight = null;

    double Vratio;
    double Hratio;
    boolean GoIn = true;
    boolean GoOut = true;


    OpMode opmode;

    int VerticalsRestingPos;

    public GripperTetrix gripper;

    public ArmAttachmentCustom(OpMode thatopmode) {
        opmode = thatopmode;
    }

    @Override
    public void Init() {
        LiftExtendLeft = opmode.hardwareMap.servo.get("LiftExtendLeft");
        LiftExtendRight = opmode.hardwareMap.servo.get("LiftExtendRight");
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
        ArmVertical.setPower(0);
    }

    public void LiftUp (double speed) //Moves the lift up and resets the resting target for LiftStopVertical
    {
        ArmVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmVertical.setPower(1* speed);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void LiftDown (double speed) //Moves the lift down and resets the resting target for LiftStopVertical
    {
        ArmVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmVertical.setPower(-1 * speed);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void LiftStopVertical () //Sets the motors target position and tries to hold it
    {
        ArmVertical.setTargetPosition(VerticalsRestingPos);
        ArmVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public int VerticalPosition ()
    {
        return ArmVertical.getCurrentPosition();
    }

    public void VerticalGoToPosition(int target)
    {
        ArmVertical.setTargetPosition(target);
        ArmVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ArmVertical.setPower(1);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void VerticalUpdateBreakPos()
    {
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public boolean VerticalIsAtTargetPos()
    {
        if(Math.abs(ArmVertical.getTargetPosition() - ArmVertical.getCurrentPosition()) < 10)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void LiftExtend () {
        if(GoOut) {
            LiftExtendLeft.setPosition(0.9);
            LiftExtendRight.setPosition(0.1);
            GoIn=true;
        }
        else{
            LiftStopHorizontal();
        }
    }

    public void LiftRetract () {
        if(GoIn) {
            LiftExtendLeft.setPosition(0.1);
            LiftExtendRight.setPosition(0.9);
            GoOut=true;
        }
        else{
            LiftStopHorizontal();
        }
    }

    public void LiftStopHorizontal ()
    {
        LiftExtendLeft.setPosition(0.5);
        LiftExtendRight.setPosition(0.5);
    }

    public void LiftStopIn(){
        GoIn=false;
    }

    public void LiftStopOut(){
        GoOut=false;
    }

    public void IntakeReverse()
    {
        LeftIntake.setPower(-1);
        RightIntake.setPower(1);
    }

    public void IntakeOn()
    {
        LeftIntake.setPower(1);
        RightIntake.setPower(-1);
    }

    public void IntakeOff()
    {
        LeftIntake.setPower(0);
        RightIntake.setPower(0);
    }

    public int getarmval(){
        int armval = ArmVertical.getCurrentPosition();
        return armval;
    }

    public boolean isArmInRange(double targetPos)
    {
        if(Math.abs(ArmVertical.getCurrentPosition() - targetPos) < 50)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}