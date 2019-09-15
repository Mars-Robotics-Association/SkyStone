package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/*
This is an example script that outlines an idea for managing attachments in a cleaner manner.
 */
public class ExampleAttachment implements Attachment
{

    boolean IsRunning = false;
    DcMotor Motor;
    OpMode Opmode;

    public ExampleAttachment(OpMode opMode)
    {
        Opmode = opMode;
    }

    public void init()
    {
        Motor = Opmode.hardwareMap.get(DcMotor.class, "ExampleAttachment");
    }

    public void loop()
    {
        if(IsRunning)
        {
            //put code for running here
        }
    }

    @Override
    public void Run()
    {
        IsRunning = true;
    }

    @Override
    public void Stop()
    {
        //Put code for stopping here
    }
}
