package org.firstinspires.ftc.teamcode;

public class PIDAngleFollower
{
    double timeOffOfTarget = 0;

    public double GetOffsetToAdd(double targetAngle, double currentAngle, double pCoefficient, double iCoefficient, double dCoefficient)
    {
        double error = targetAngle - currentAngle; //difference between the target and actual angle (positive if going right)
        double correction = error * pCoefficient + dCoefficient * (error/Math.abs(error)); //Proportional control

        if(!(correction > 0) && !(correction < 0))
        {
            correction = 0;
        }

        return correction;
    }
}
