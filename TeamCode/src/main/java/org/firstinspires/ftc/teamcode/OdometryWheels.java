package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class OdometryWheels
{
    private OpMode opMode;
    private DcMotor X;
    private DcMotor Y;
    private boolean dontDoAnything;

    public OdometryWheels(OpMode setOpMode, boolean deactivate)
    {
        opMode = setOpMode;
        dontDoAnything = deactivate;
    }

    public void Init()
    {
        if(!dontDoAnything)
        {
            X = opMode.hardwareMap.dcMotor.get("OdometryWheelX");
            Y = opMode.hardwareMap.dcMotor.get("OdometryWheelY");
        }
    }

    public double[] GetCurrentData()
    {
        double[] vals = {0,0};
        if(!dontDoAnything)
        {
            vals = new double[]{X.getCurrentPosition(), Y.getCurrentPosition()};
        }

        return vals;
    }
}
