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
    private CRServo LiftExtendLeft = null;
    private CRServo LiftExtendRight = null;

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
        LiftExtendLeft = opmode.hardwareMap.crservo.get("LiftExtendLeft");
        LiftExtendRight = opmode.hardwareMap.crservo.get("LiftExtendRight");
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
        ArmVertical.setPower(0.45*Vratio);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void LiftDown () //Moves the lift down and resets the resting target for LiftStopVertical
    {
        ArmVertical.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmVertical.setPower(-0.45*Vratio);
        VerticalsRestingPos = ArmVertical.getCurrentPosition();
    }

    public void LiftStopVertical () //Sets the motors target position and tries to hold it
    {
        ArmVertical.setTargetPosition(VerticalsRestingPos);
        ArmVertical.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }






    public void LiftExtend () {
        if(GoOut) {
            LiftExtendLeft.setPower(1 * Hratio);
            LiftExtendRight.setPower(-1 * Hratio);
            GoIn=true;
        }
        else{
            LiftStopHorizontal();
        }
    }

    public void LiftRetract () {
        if(GoIn) {
            LiftExtendLeft.setPower(-1*Hratio);
            LiftExtendRight.setPower(1 * Hratio);
            GoOut=true;
        }
        else{
            LiftStopHorizontal();
        }
    }

    public void LiftStopHorizontal ()
    {
        LiftExtendLeft.setPower(0*Hratio);
        LiftExtendRight.setPower(0 * Hratio);
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
}