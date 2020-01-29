package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;


public class SimpleFieldNavigation
{
    private double closeEnoughThresholdDist = 10; //in inches
    private double closeEnoughThresholdRot = 5; //in degrees

    private double HueThreshold = 20;
    private double RedHue = 0;
    private double BlueHue = 207;
    private SkyStoneBot Bot;

    public boolean Navigating = false;
    public boolean Rotating = false;

    private OpMode opmode;

    public boolean firstRound = true;

    private double TargetRot;
    private double CurrentRot;

    private double TurnSpeed = 0;

    PIDAngleFollower PID = null;
    private double pCoefficient = 0.006;

    //ENCODER MOVEMENT
    private double XTargetPos = 0;
    private double YTargetPos = 0;

    private double XCurrentPos = 0;
    private double YCurrentPos = 0;


    public SimpleFieldNavigation(OpMode setOpmode)
    {
        this.opmode = setOpmode;
    }

    public boolean isNavigating()
    {
        if(!Navigating && !Rotating)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean CheckIfAtTargetDestination()
    {
        return Bot.CheckIfEncodersCloseEnough();
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode, false);
        Bot.Init();
        PID = new PIDAngleFollower();
    }

    public void Start()
    {
        Bot.Start();
        opmode.telemetry.setMsTransmissionInterval(20);//update telemetry faster
    }

    public void Loop()
    {
        Bot.Loop();
        CurrentRot = Bot.GetRobotAngle();

        if(firstRound)
        {
            Bot.OffsetGyro();
            firstRound = false;
        }

        if(Navigating)
        {
            NavigationControl();
        }

        if(Rotating)
        {
            RotationControl();
        }

        if(!Navigating && !Rotating)//stop if shouldn't do anything
        {
            //Bot.Brake(1);
        }
    }

    void NavigationControl()
    {
        boolean closeEnough = CheckIfAtTargetDestination();
        opmode.telemetry.addData("Close enough: ", closeEnough);

        if (!closeEnough) //If not close to target
        {
            opmode.telemetry.addData("Not close enough: ", true);

            //CODE FOR PID DRIVE CORRECTION
            double offset = PID.GetOffsetToAdd(Bot.TargetAngle, Bot.GetRobotAngle(), 0.01 , 0, 0); //good
            Bot.ApplyTurnOffsetUsingEncoders(offset);

            opmode.telemetry.addData("Robot Angle ", Bot.GetRobotAngle());
            opmode.telemetry.addData("Target Angle ", Bot.TargetAngle);
            opmode.telemetry.addData("Offset ", offset);
        }
        else //Stop
        {
            opmode.telemetry.addData("Not close enough: ", false);
            //StopAll();
            //Bot.RotateTowardsAngle(TargetRot, 0.5);
            //Rotating = true;
            Navigating = false;
        }
    }

    void RotationControl()
    {
        if (!CheckCloseEnoughRotation()) //if not at rotation target
        {
            Bot.RotateTowardsAngle(TargetRot, TurnSpeed);
        }
        else //Stop
        {
            Bot.StopMotors();
            Rotating = false;
        }
    }

    public void RotateTo(double angle, double speed)
    {
        StopAll();
        TurnSpeed = speed;
        TargetRot = angle;
        Rotating = true;
    }

    public void StopAll()//Stops everything movement-wise
    {
        Bot.StopMotors();
        Navigating = false;
        Rotating = false;
    }

    public void GoRight(double distance, double speed)
    {
        Bot.GoRightWithEncoder(speed, distance);
        pCoefficient = 0.05;
        Navigating = true;
        Rotating = false;
    }

    public void GoForward(double distance, double speed)
    {
        Bot.GoForwardWithEncoder(speed, distance);
        pCoefficient = 0.01;
        Navigating = true;
        Rotating = false;
    }

    public void Brake(double power){
        Bot.SetBrakePos();
        Bot.Brake(power);
    }
    public void SetBrakePos(){
        Bot.SetBrakePos();
    }

    public void ResetGyro()
    {
        Bot.OffsetGyro();
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
