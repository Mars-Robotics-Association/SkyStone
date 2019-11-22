package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.security.cert.TrustAnchor;
import java.sql.Time;
import java.util.Timer;

public class ExampleAttachment implements Attachment {
    private double number;
    private DcMotor motor;
    private OpMode Opmode;
    public boolean isRunning = false;
    private double timeToMove = 500;
    private double currentTime = 0;

    public ExampleAttachment(OpMode opMode)
    {
        Opmode = opMode;
    }

    @Override
    public void Init()
    {

    }

    @Override
    public void Loop()
    {
        if(isRunning)
        {
            currentTime += 1;
            if(currentTime > timeToMove)
            {
                isRunning = false;
            }
        }
    }

    @Override
    public void Run()
    {
        isRunning = true;
        currentTime = 0;
    }

    @Override
    public void Stop()
    {
        isRunning = false;

    }

    public void YourCustomMethod(double variable)
    {
        //put code here
        Run();//this just runs the Run() function...
    }
}
