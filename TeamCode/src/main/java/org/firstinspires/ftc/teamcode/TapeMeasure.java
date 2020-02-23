package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TapeMeasure implements Attachment {
    private DcMotor TapeMeasureMotor = null;



    OpMode opmode;

    public TapeMeasure(OpMode thatopmode) {
        opmode = thatopmode;
    }

    @Override
    public void Init() {

        TapeMeasureMotor = opmode.hardwareMap.dcMotor.get("TapeMeasureMotor");

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

    public void TapeMeasureMotorOut() //Moves the lift up and resets the resting target for LiftStopVertical
    {
        TapeMeasureMotor.setPower(1);
    }

    public void TapeMeasureMotorIn() //Moves the lift up and resets the resting target for LiftStopVertical
    {
        TapeMeasureMotor.setPower(-1);
    }


    public void TapeMeasureMotorOff () //Moves the lift down and resets the resting target for LiftStopVertical
    {
        TapeMeasureMotor.setPower(0);
    }
}