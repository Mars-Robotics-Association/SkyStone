package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


public class FieldNavigation
{
    float TargetX = 0;
    float TargetY = 0;
    float TargetRot = 0;

    public void NavigateToLocation(float x, float y, float orientation, VuforiaTestWebcam vuforia)
    {
        TargetX = x;
        TargetY = y;
        TargetRot = orientation;
    }

    public void NavigationLoop()
    {

    }

    public void GetRotation(VuforiaTestWebcam vuforia)
    {
        float angle = vuforia.RobotAngle;
    }

}
