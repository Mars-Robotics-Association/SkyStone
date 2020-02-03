package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class FoundationGrabber implements Attachment {
    private double number;
    private DcMotor motor;
    private OpMode Opmode;
    public boolean isRunning = false;
    private double timeToMove = 500;
    private double currentTime = 0;

    private Servo FoundationL;
    private Servo FoundationR;

    public FoundationGrabber(OpMode opMode)
    {
        Opmode = opMode;
    }

    @Override
    public void Init()
    {
        FoundationL = Opmode.hardwareMap.get(Servo.class, "FoundationL");
        FoundationR = Opmode.hardwareMap.get(Servo.class, "FoundationR");
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
    public void FoundationGrab(double desiredAngle){
        if(desiredAngle>0){
            FoundationL.setPosition(0.75);
            FoundationR.setPosition(0.25);
        }
        else{
            FoundationL.setPosition(0.25);
            FoundationR.setPosition(0.75);
        }

    }
    public void FoundationGrabUp()
    {

        FoundationL.setPosition(1);
        FoundationR.setPosition(0);
    }

    public void FoundationGrabDown()
    {
        FoundationL.setPosition(0);
        FoundationR.setPosition(1);
    }

    public void FoundationGrabUpR()
    {

        FoundationR.setPosition(0);
    }

    public void FoundationGrabDownR()
    {
        FoundationR.setPosition(1);
    }

    public void FoundationGrabUpL()
    {

        FoundationL.setPosition(1);
    }

    public void FoundationGrabDownL()
    {
        FoundationL.setPosition(0);
    }

}
