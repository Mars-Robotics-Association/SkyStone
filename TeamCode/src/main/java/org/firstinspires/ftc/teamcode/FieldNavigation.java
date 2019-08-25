package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import org.firstinspires.ftc.robotcore.external.navigation.MotionDetection;

public class FieldNavigation
{
    double closeEnoughThreshold = 20;

    double TargetX = 0;
    double TargetY = 0;
    double TargetRot = 0;
    double CurrentX = 0;
    double CurrentY = 0;
    double CurrentRot = 0;

    public void NavigateToLocation(float x, float y, float orientation, VuforiaTestWebcam vuforia)
    {
        //set values
        TargetX = x;
        TargetY = y;
        TargetRot = orientation;
        CurrentX = vuforia.RobotX;
        CurrentY = vuforia.RobotY;
        CurrentRot = GetRotation(vuforia);

        //Calculate angle of movement (no obstacle avoidance)
        double triY = Math.abs(CurrentY - TargetY); //vertical length
        double triX = Math.abs(CurrentX - TargetX); //horizontal length
        double tanA = triY/triX; //tangent of the angle
        double absoluteAngle = Math.atan(tanA); //get measurement of angle relative to 0 degrees on the field

        //UDC.MoveAtAngle(absoluteAngle)

        NavigationLoop(vuforia);
    }

    public void NavigationLoop(VuforiaTestWebcam vuforia)
    {
        while(!CheckCloseEnough()) //If not close to target
        {
            //update values
            CurrentX = vuforia.RobotX;
            CurrentY = vuforia.RobotY;
            CurrentRot = GetRotation(vuforia);
        }
        //UDC.StopMoving()
        //UDC.RotateTo(TargetRot)
    }

    public boolean CheckCloseEnough()
    {
        //use distance formula to check if in radius
        if(Math.sqrt(Math.pow(TargetX - CurrentX, 2) + Math.pow(TargetY - CurrentY, 2)) < closeEnoughThreshold)
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
