package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ExampleAttachment implements Attachment {
    private double number;
    private DcMotor motor;
    private OpMode Opmode;

    public ExampleAttachment(OpMode opMode)
    {
        Opmode = opMode;
    }

    @Override
    public void Init() {

    }

    @Override
    public void Loop() {

    }

    @Override
    public void Run() {

    }

    @Override
    public void Stop() {


    }

    public void YourCustomMethod(double variable)
    {
        //put code here
    }
}
