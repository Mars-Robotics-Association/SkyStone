package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class Sensor_Webcam
{
    private WebcamName webcamName = null;
    private OpMode opMode;

    public Sensor_Webcam(OpMode setOpMode)
    {
        opMode = setOpMode;
    }

    public void Init()
    {
        webcamName = opMode.hardwareMap.get(WebcamName.class, "Webcam1");
    }
}
