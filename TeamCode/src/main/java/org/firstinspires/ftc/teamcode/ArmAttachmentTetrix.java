package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class ArmAttachmentTetrix implements Attachment {
    private DcMotor ArmHorizontal = null;
    private DcMotor ArmVertical = null;
   // private DcMotor ArmLeft = null;
    private DcMotor ArmRight = null;
    private DcMotor LeftIntake = null;
    private DcMotor RightIntake = null;

    int ArmLeftResting;
    int ArmRightResting;
    int ArmHorizontalResting;

    double ArmLeftPower=-0.5;//reverse????
    double ArmRightPower=0.5;

    double Vratio;
    double Hratio;
    OpMode opmode;

    public GripperTetrix gripper;

    public ArmAttachmentTetrix(OpMode thatopmode) {
        opmode = thatopmode;
    }

    public void Init() {
     //   ArmLeft = opmode.hardwareMap.dcMotor.get("ArmLeft");
        ArmRight = opmode.hardwareMap.dcMotor.get("ArmRight");
        ArmHorizontal = opmode.hardwareMap.dcMotor.get("ArmHorizontal");
        Vratio=1;
        Hratio=1;


       // ArmLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        ArmHorizontal.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmHorizontal.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ArmRightResting=ArmRight.getCurrentPosition();
       // ArmLeftResting=ArmLeft.getCurrentPosition();

        ArmRight.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
       // ArmLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    //@Override
    public void Loop()
    {
        //opmode.telemetry.addData("Left Current: ", ArmLeft.getCurrentPosition());
        opmode.telemetry.addData("Left Resting: ", ArmLeftResting);
        opmode.telemetry.addData("Right Current: ", ArmRight.getCurrentPosition());
        opmode.telemetry.addData("Right Resting: ", ArmRightResting);
        opmode.telemetry.update();
    }

    @Override
    public void Run() {

    }

    @Override
    public void Stop() {
//test
    }

    public void LiftUp () //Moves the lift up and resets the resting target for LiftStopVertical
    {
        //ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setPower(ArmRightPower);
      //  ArmLeft.setPower(ArmLeftPower);
        ArmRightResting=ArmRight.getCurrentPosition();
       // ArmLeftResting=ArmLeft.getCurrentPosition();
    }

    public void LiftDown () //Moves the lift down and resets the resting target for LiftStopVertical
    {
       // ArmLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ArmRight.setPower(-ArmRightPower);
       // ArmLeft.setPower(-ArmLeftPower);
        ArmRightResting=ArmRight.getCurrentPosition();
       // ArmLeftResting=ArmLeft.getCurrentPosition();
    }

    public void LiftStopVertical ()//Sets the motors target position and tries to hold it
    {
        ArmRight.setTargetPosition(ArmRightResting);
        ArmRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       // ArmLeft.setTargetPosition(ArmLeftResting);
       // ArmLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void LiftExtend ()
    {
        ArmHorizontal.setPower(0.4*Hratio);
    }

    public void LiftRetract ()
    {
        ArmHorizontal.setPower(-0.4*Hratio);

    }

    public void LiftStopHorizontal ()
    {
        ArmHorizontal.setPower(0*Hratio);
    }

    public int getarmval(){
        int armval = ArmVertical.getCurrentPosition();
        return armval;
    }
}