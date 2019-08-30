package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
This is an example script that outlines an idea for managing attachments in a cleaner manner.
 */
public class ExampleAttachment extends OpMode implements Attachment
{

    boolean IsRunning = false;
    DcMotor Motor;

    @Override
    public void init()
    {
        Motor = hardwareMap.get(DcMotor.class, "ExampleAttachment");
    }

    @Override
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
