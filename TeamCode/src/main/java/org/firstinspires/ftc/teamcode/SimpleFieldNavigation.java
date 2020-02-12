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
    private double closeEnoughThresholdRot = 40; //in degrees
    private double closeEnoughThresholdRotPrecise = 3; //in degrees

    private double HueThreshold = 20;
    private double RedHue = 0;
    private double BlueHue = 207;
    private SkyStoneBot Bot;

    private boolean Navigating = false;
    private boolean Rotating = false;

    private OpMode opmode;

    public boolean firstRound = true;

    private double TargetRot;
    private double CurrentRot;

    private double TurnSpeed = 0;
    private double StartRot = 0;

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
    public boolean CheckIfAtTargetDestinationPrecise()
    {
        return Bot.CheckIfEncodersCloseEnoughPrecise();
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

        opmode.telemetry.addData("Current Angle: ", CurrentRot);
        opmode.telemetry.addData("Target Rot: ", TargetRot);
        opmode.telemetry.addData("Navigating: ", Navigating);
        opmode.telemetry.addData("Rotating: ", Rotating);

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
            Bot.StopMotors();
        }
    }

    void NavigationControl()
    {
        boolean closeEnough = CheckIfAtTargetDestinationPrecise();
        opmode.telemetry.addData("Close enough: ", closeEnough);

        if (!closeEnough) //If not close to target
        {
            opmode.telemetry.addData("Not close enough: ", true);

            //CODE FOR PID DRIVE CORRECTION
            double offset = PID.GetOffsetToAdd(Bot.TargetAngle, Bot.GetRobotAngle(), 0.0125, 0, 0); //good
            Bot.ApplyTurnOffsetUsingEncoders(offset);

            opmode.telemetry.addData("Robot Angle ", Bot.GetRobotAngle());
            opmode.telemetry.addData("Target Angle ", Bot.TargetAngle);
            opmode.telemetry.addData("Offset ", offset);
        }
        else //Stop
        {
            opmode.telemetry.addData("Not close enough: ", false);
            if(!Rotating)
            {
                Bot.StopMotors();
            }
            Navigating = false;
        }
    }

    void RotationControl()
    {
        opmode.telemetry.addData("turning: ", TargetRot);
        if (!CheckCloseEnoughRotationPrecise()) //if not at rotation target
        {
            Bot.RotateTowardsAngle(TargetRot, TurnSpeed);
        }
        else //Stop
        {
            if(!Navigating)
            {
                Bot.StopMotors();
            }
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

    public void GoRight(double distance, double speed, double angleToMoveAt)
    {
        Bot.GoRightWithEncoder(speed, distance, true, angleToMoveAt);
        pCoefficient = 0.05;
        Navigating = true;
        Rotating = false;
    }

    public void GoForward(double distance, double speed, double angleToMoveAt)
    {
        Bot.GoForwardWithEncoder(speed, distance, true, angleToMoveAt);
        pCoefficient = 0.01;
        Navigating = true;
        Rotating = false;
    }

    public void SetMoveSpeed(double speed)
    {
        Bot.SetSpeed(speed);
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
        if(CurrentRot < GetUsableRot() + closeEnoughThresholdRot && CurrentRot > GetUsableRot() - closeEnoughThresholdRot)
        {
            return true;
        }
        else
        {
            //opmode.telemetry.addData("Close Enough Rotation: ", false);
            return false;
        }
    }
    public boolean CheckCloseEnoughRotationPrecise()
    {
        if(CurrentRot < GetUsableRot() + closeEnoughThresholdRotPrecise && CurrentRot > GetUsableRot() - closeEnoughThresholdRotPrecise)
        {
            return true;
        }
        else
        {
            //opmode.telemetry.addData("Close Enough Rotation: ", false);
            return false;
        }
    }

    private double GetUsableRot()
    {
        double newRot = 0;
        if(TargetRot > 180)
        {
            newRot = -360 + TargetRot;
        }
        else if(TargetRot < -180)
        {
            newRot = 360 + TargetRot;
        }
        else
        {
            newRot = TargetRot;
        }
        return newRot;
    }

}
