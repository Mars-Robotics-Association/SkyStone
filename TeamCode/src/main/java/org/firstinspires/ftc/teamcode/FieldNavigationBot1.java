package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec3F;

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
    private double StartAngle = 0;
    private double absoluteAngle = 0;

    private SkyStoneBot Bot;
    private SkystoneVuforiaWebcam Vuforia;

    public boolean Navigating = false;
    public boolean Rotating = false;

    private OpMode opmode;

    public boolean firstRound = true;

    public Vec3F GetLocationAndRotation()
    {
        return new Vec3F((float)CurrentX, (float)CurrentY, (float)CurrentRot);
    }

    public FieldNavigationBot1(OpMode setOpmode, double startAngle, Robot robot)
    {
        this.opmode = setOpmode;
        StartAngle = startAngle;
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode, false);
        Vuforia = new SkystoneVuforiaWebcam(opmode, "Webcam 1");
        Bot.Init();
        Vuforia.Init();
    }

    public void Start()
    {
        Bot.Start();
        opmode.telemetry.setMsTransmissionInterval(20);//update telemetry faster
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
        Bot.Loop();

        if(firstRound)
        {
            Bot.OffsetGyro();
            firstRound = false;
        }

        CurrentRot = Bot.GetRobotAngle();
        Vuforia.Loop();
        //update values
        CurrentX = Vuforia.GetRobotX();
        CurrentY = Vuforia.GetRobotY();


        if(Navigating)
        {
            //Calculate angle of movement
            double triY = (CurrentY - TargetY); //vertical length
            double triX = (CurrentX - TargetX); //horizontal length
            absoluteAngle = Math.atan2(triY,triX); //get measurement of move angle
            absoluteAngle = Math.toDegrees(absoluteAngle);
            absoluteAngle -= StartAngle + 180; //offsets by start angle of the robot
            if(absoluteAngle < 0)//convert degrees to positive if needed
            {
                absoluteAngle = 360 + absoluteAngle;
            }

            Bot.MoveAtAngle(absoluteAngle, 0.5, false);

            if (!CheckCloseEnoughDistance()) //If not close to target
            {
                opmode.telemetry.addData("Not close enough: ", true);
            }
            else { //Stop and rotate to target
                opmode.telemetry.addData("Not close enough: ", false);
                Bot.StopMotors();
                //Bot.RotateTowardsAngle(TargetRot, 0.5);
                //Rotating = true;
                //Navigating = false;
            }

            opmode.telemetry.update();
        }

        if(Rotating)
        {
            if (!CheckCloseEnoughRotation()) //if not at rotation target
            {
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
        //StopAll();
        //set values
        TargetX = x;
        TargetY = y;
        TargetRot = angle;
        CurrentX = Vuforia.GetRobotX();
        CurrentY = Vuforia.GetRobotY();
        CurrentRot = Bot.GetRobotAngle();

        //Calculate angle of movement (no obstacle avoidance)
        double triY = (CurrentY - TargetY); //vertical length
        double triX = (CurrentX - TargetX); //horizontal length
        absoluteAngle = Math.atan2(triY,triX); //get measurement of move angle
        absoluteAngle = Math.toDegrees(absoluteAngle);
        absoluteAngle -= StartAngle + 180; //offsets by start angle of the robot
        if(absoluteAngle < 0)//convert degrees to positive if needed
        {
            absoluteAngle = 360 + absoluteAngle;
        }

        Bot.MoveAtAngle(absoluteAngle, 0.5, true);
        Navigating = true;
        Rotating = false;
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
