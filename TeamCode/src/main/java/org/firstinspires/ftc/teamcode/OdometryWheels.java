package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class OdometryWheels
{
    OpMode opMode;
    DcMotor X;
    DcMotor Y;

    public OdometryWheels(OpMode setOpMode)
    {
        opMode = setOpMode;
    }

    public void Init()
    {
        X = opMode.hardwareMap.dcMotor.get("OdometryWheelX");
    }

    public double[] GetCurrentData()
    {

    }
}
