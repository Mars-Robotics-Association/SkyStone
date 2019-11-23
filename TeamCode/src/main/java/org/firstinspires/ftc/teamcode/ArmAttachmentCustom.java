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

    public Gripper gripper;

    public ArmAttachmentCustom(OpMode thatopmode) {
        opmode = thatopmode;
    }

    @Override
    public void Init() {
        ArmHorizontal = opmode.hardwareMap.dcMotor.get("ArmVertical");
        ArmVertical = opmode.hardwareMap.dcMotor.get("ArmHorizontal");
        LeftIntake = opmode.hardwareMap.dcMotor.get("LeftIntake");
        RightIntake = opmode.hardwareMap.dcMotor.get("RightIntake");
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
    public void LiftUp ()
    {
        ArmVertical.setPower(0.9*Vratio);
    }
    public void LiftDown ()
    {
        ArmVertical.setPower(-0.9*Vratio);
    }
    public void LiftStopVertical ()
    {
        ArmVertical.setPower(0*Vratio);
    }
    public void LiftExtend () {
        ArmHorizontal.setPower(0.9*Hratio);
    }

    public void LiftRetract ()
    {
        ArmHorizontal.setPower(-0.9*Hratio);

    }
    public void LiftStopHorizontal () {
        ArmHorizontal.setPower(0*Hratio);
    }

    public void IntakeOn()
    {
        LeftIntake.setPower(1);
        RightIntake.setPower(-1);
    }
    public void IntakeOff()
    {
        LeftIntake.setPower(-1);
        RightIntake.setPower(1);
    }
}