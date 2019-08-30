package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class FieldNavigation extends OpMode
{
    double closeEnoughThresholdDist = 20; //in mm
    double closeEnoughThresholdRot = 5; //in degrees

    double TargetX = 0;
    double TargetY = 0;
    double TargetRot = 0;
    double CurrentX = 0;
    double CurrentY = 0;
    double CurrentRot = 0;

    UDCTest UDC;
    VuforiaTest Vuforia;

    boolean Navigating = false;
    boolean Rotating = false;

    @Override
    public void  init()
    {
        UDC = new UDCTest();
        Vuforia = new VuforiaTest();
    }

    @Override
    public void  loop()
    {
        if(Navigating)
        {
            if (!CheckCloseEnoughDistance()) //If not close to target
            {
                //update values
                CurrentX = Vuforia.RobotX;
                CurrentY = Vuforia.RobotY;
                CurrentRot = UDC.GetRotation();
            }
            else {
                UDC.StopMotors();
                UDC.RotateTo(TargetRot, 0.5);
                Rotating = true;
                Navigating = false;
            }
        }

        if(Rotating)
        {
            if (!CheckCloseEnoughRotation())
            {
                //update values
                CurrentX = Vuforia.RobotX;
                CurrentY = Vuforia.RobotY;
                CurrentRot = UDC.GetRotation();
            }
            else
            {
                UDC.StopMotors();
                Rotating = false;
            }
        }
    }

    public void NavigateToLocation(float x, float y, float orientation)
    {
        //set values
        TargetX = x;
        TargetY = y;
        TargetRot = orientation;
        CurrentX = Vuforia.RobotX;
        CurrentY = Vuforia.RobotY;
        CurrentRot = UDC.GetRotation();

        //Calculate angle of movement (no obstacle avoidance)
        double triY = Math.abs(CurrentY - TargetY); //vertical length
        double triX = Math.abs(CurrentX - TargetX); //horizontal length
        double absoluteAngle = Math.atan2(triY,triX); //get measurement of joystick angle
        absoluteAngle = Math.toDegrees(3.1415 - absoluteAngle);
        if(absoluteAngle < 0)//convert degrees to positive if needed
        {
            absoluteAngle = 360 - absoluteAngle;
        }

        UDC.MoveAtAngle(absoluteAngle, 1);
        Navigating = true;
    }

    public boolean CheckCloseEnoughDistance()
    {
        //use distance formula to check if in radius
        if(Math.sqrt(Math.pow(TargetX - CurrentX, 2) + Math.pow(TargetY - CurrentY, 2)) < closeEnoughThresholdDist)
        {
            return true;
        }
        else return false;
    }

    public boolean CheckCloseEnoughRotation()
    {
        if(Vuforia.RobotAngle < TargetRot + closeEnoughThresholdRot && Vuforia.RobotAngle > TargetRot - closeEnoughThresholdRot)
        {
            return true;
        }
        else return false;
    }



}
