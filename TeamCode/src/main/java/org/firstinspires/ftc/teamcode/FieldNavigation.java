package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.navigation.MotionDetection;

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
    VuforiaTestWebcam Vuforia;
    boolean Navigating = false;
    boolean Rotating = false;

    @Override
    public void  init()
    {
        UDC = new UDCTest();
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
                CurrentRot = GetRotation(Vuforia);
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
                CurrentRot = GetRotation(Vuforia);
            }
            else
            {
                UDC.StopMotors();
                Rotating = false;
            }
        }
    }

    public void NavigateToLocation(float x, float y, float orientation, VuforiaTestWebcam vuforia)
    {
        //set values
        Vuforia = vuforia;
        TargetX = x;
        TargetY = y;
        TargetRot = orientation;
        CurrentX = Vuforia.RobotX;
        CurrentY = Vuforia.RobotY;
        CurrentRot = GetRotation(Vuforia);

        //Calculate angle of movement (no obstacle avoidance)
        double triY = Math.abs(CurrentY - TargetY); //vertical length
        double triX = Math.abs(CurrentX - TargetX); //horizontal length
        double absoluteAngle = Math.atan2(triY,triX); //get measurement of joystick angle
        absoluteAngle = Math.toDegrees(3.1415 - absoluteAngle);

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

    public float GetRotation(VuforiaTestWebcam vuforia)
    {
        float angle = vuforia.RobotAngle;
        return angle;
    }

}
