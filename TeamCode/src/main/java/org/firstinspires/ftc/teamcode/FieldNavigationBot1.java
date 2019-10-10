package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class FieldNavigationBot1
{
    private double closeEnoughThresholdDist = 10; //in inches
    private double closeEnoughThresholdRot = 5; //in degrees

    private double TargetX = 0;
    private double TargetY = 0;
    private double TargetRot = 0;
    private double CurrentX = 0;
    private double CurrentY = 0;
    private double CurrentRot = 0;

    private double absoluteAngle = 0;

    private SkyStoneBot Bot;
    private SkystoneVuforiaPhone Vuforia;

    private boolean Navigating = false;
    private boolean Rotating = false;

    private OpMode opmode;

    public boolean firstRound = true;

    public FieldNavigationBot1(OpMode setOpmode)
    {
        this.opmode = setOpmode;
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode);
        Vuforia = new SkystoneVuforiaPhone(opmode);
        Bot.Init();
        Vuforia.Init();
    }

    public void Start()
    {
        Bot.Start();
    }

    public void Loop()
    {
        opmode.telemetry.addData("TargetX = ", TargetX);
        opmode.telemetry.addData("TargetY = ", TargetY);
        opmode.telemetry.addData("TargetRot = ", TargetRot);
        opmode.telemetry.addData("CurrentX = ", CurrentX);
        opmode.telemetry.addData("CurrentY = ", CurrentY);
        opmode.telemetry.addData("CurrentRot = ", CurrentRot);
        opmode.telemetry.addData("Travel Rot: ", absoluteAngle);
        opmode.telemetry.update();
        Bot.Loop();
        if(firstRound)
        {
            Bot.OffsetGyro();
            firstRound = false;
        }

        CurrentRot = Bot.GetFinalGyro();
        Vuforia.Loop();
        //update values
        CurrentX = Vuforia.GetRobotX();
        CurrentY = Vuforia.GetRobotY();


        if(Navigating)
        {
            if (!CheckCloseEnoughDistance()) //If not close to target
            {

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
        absoluteAngle = Math.atan2(triY,triX); //get measurement of joystick angle
        absoluteAngle = Math.toDegrees(absoluteAngle);
        absoluteAngle -= 180;
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
