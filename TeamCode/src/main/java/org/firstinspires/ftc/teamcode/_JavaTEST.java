package org.firstinspires.ftc.teamcode;

public class _JavaTEST
{

//    static private double robotAngle = 10;
 //   static private double DriveAngle = -10;

    static private double robotAngle = 179.8125;
    static private double DriveAngle = -120.25;

    static private double leftStickBaring = 0;
    static private double rightStickX = 0;
    static private double JoystickThreshold = 0;

    public static void main(String[] args)
    {
        boolean turnRight = false;
        if (rightStickX > JoystickThreshold)//turn right
        {
            turnRight = true;
            DriveAngle -= 6 * Math.abs(rightStickX);
        }
        else//turn left
        {
            turnRight = false;
            DriveAngle += 6 * Math.abs(rightStickX);
        }

        //Get the Drive angle over the 180 degree wall
        if(DriveAngle > 180)//go to negative side
        {
            DriveAngle = -360 + DriveAngle;
        }
        if(DriveAngle < -180)//go to positive side
        {
            DriveAngle = 360 + DriveAngle;
        }

        double [] vals = GetUsableRotsForOffset(DriveAngle, robotAngle);
        double offset = GetOffsetToAdd(vals[0], vals[1], 0.01, 0, 0);//gets an offset to keep the robot on track

        System.out.println("DriveAngle: " + DriveAngle);
        System.out.println("robotAngle: " + robotAngle);
        System.out.println("vals[0]: " + vals[0]);
        System.out.println("vals[1]: " + vals[1]);
        System.out.println("Offset: " + offset);
    }

    static private double GetOffsetToAdd(double targetAngle, double currentAngle, double pCoefficient, double iCoefficient, double dCoefficient)
    {
        double error = targetAngle - currentAngle; //difference between the target and actual angle (positive if going right)
        double correction = error * pCoefficient + dCoefficient * (error/Math.abs(error)); //Proportional control

        if(!(correction > 0) && !(correction < 0))
        {
            correction = 0;
        }

        return correction;
    }

    static private double[] GetUsableRotsForOffset(double targetAbsRot, double currentAbsRot)//detects if the two numbers are
    {
        double newTargetRot = 0;
        double newCurrentRot = 0;

        double normalDistance = Math.abs(targetAbsRot - currentAbsRot);
        double overTheLineDistance = Math.abs(-targetAbsRot - currentAbsRot);
        boolean isInRange = (Math.abs(targetAbsRot) > 90 && Math.abs(currentAbsRot)>90);

        if(overTheLineDistance < normalDistance && isInRange)//if need to go over the line
        {
            newTargetRot = (targetAbsRot - (180 * (Math.abs(targetAbsRot)/targetAbsRot)));//offset by 180 towards 0. The abs thing is to detect whether to add or subtract 170 -> -10
            newCurrentRot = (currentAbsRot - (180 * (Math.abs(currentAbsRot)/currentAbsRot)));//offset by 180 towards 0. The abs thing is to detect whether to add or subtract -170 -> 10
        }
        else //everything is normal
        {
            newTargetRot = targetAbsRot;
            newCurrentRot = currentAbsRot;
        }

        return new double [] {newTargetRot, newCurrentRot};//return values
    }
}

