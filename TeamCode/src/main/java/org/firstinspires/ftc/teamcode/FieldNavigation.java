package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class FieldNavigation
{
    private double closeEnoughThresholdDist = 20; //in mm
    private double closeEnoughThresholdRot = 5; //in degrees

    private double TargetX = 0;
    private double TargetY = 0;
    private double TargetRot = 0;
    private double CurrentX = 0;
    private double CurrentY = 0;
    private double CurrentRot = 0;

    private SkyStoneBot Bot;
    private SkystoneVuforiaPhone Vuforia;

    private boolean Navigating = false;
    private boolean Rotating = false;

    private OpMode opmode;

    public FieldNavigation (OpMode setOpmode)
    {
        this.opmode = setOpmode;
    }

    public void  Init()
    {
        Bot = new SkyStoneBot(opmode);
        Vuforia = new SkystoneVuforiaPhone(opmode);
        Bot.Init();
        Bot.OffsetGyro();
        Vuforia.Init();
    }

    public void  Loop()
    {
        Bot.Loop();
        Vuforia.Loop();
        CurrentRot = Bot.GetRobotAngle();
        if(Navigating)
        {
            if (!CheckCloseEnoughDistance()) //If not close to target
            {
                //update values
                CurrentX = Vuforia.GetRobotX();
                CurrentY = Vuforia.GetRobotY();
            }
            else { //Stop and rotate to target
                Bot.StopMotors();
                Bot.RotateTo(TargetRot, 0.5);
                Rotating = true;
                Navigating = false;
            }
        }

        if(Rotating)
        {
            if (!CheckCloseEnoughRotation()) //if not at rotation target
            {
                //update values
                CurrentX = Vuforia.GetRobotX();
                CurrentY = Vuforia.GetRobotY();
            }
            else //Stop
            {
                Bot.StopMotors();
                Rotating = false;
            }
        }

        if(!Navigating && !Rotating)//stop if shouldn't do anything
        {
            Bot.StopMotors();
        }
    }

    public void NavigateToLocation(double x, double y, double angle)
    {
        StopAll();
        //set values
        TargetX = x;
        TargetY = y;
        TargetRot = angle;
        CurrentX = Vuforia.GetRobotX();
        CurrentY = Vuforia.GetRobotY();
        CurrentRot = Bot.GetRobotAngle();

        //Calculate angle of movement (no obstacle avoidance)
        double triY = Math.abs(CurrentY - TargetY); //vertical length
        double triX = Math.abs(CurrentX - TargetX); //horizontal length
        double absoluteAngle = Math.atan2(triY,triX); //get measurement of joystick angle
        if(absoluteAngle < 0)//convert degrees to positive if needed
        {
            absoluteAngle = 360 + absoluteAngle;
        }

        Bot.MoveAtAngle(absoluteAngle, 1);
        Navigating = true;
    }

    public void RotateTo(double angle)
    {
        StopAll();
        TargetRot = angle;
        Rotating = true;
    }

    public void StopAll()//Stops everything movement-wise
    {
        Bot.StopMotors();
        Navigating = false;
        Rotating = false;
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
        if(CurrentRot < TargetRot + closeEnoughThresholdRot && CurrentRot > TargetRot - closeEnoughThresholdRot)
        {
            return true;
        }
        else return false;
    }



}
