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
    private OdometryWheels odometryWheels;

    public boolean firstRound = true;

    private double TargetRot;
    private double CurrentRot;

    private double TurnSpeed = 0;

    PIDAngleFollower angleFollower = null;
    private double pCoefficient = 0.006;

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
        return Bot.CheckCloseEnoughOdometry(odometryWheels.GetCurrentData()[0], odometryWheels.GetCurrentData()[1]);
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode, false);
        Bot.Init();
        angleFollower = new PIDAngleFollower();
        odometryWheels = new OdometryWheels(opmode);
    }

    public void Start()
    {
        Bot.Start();
        opmode.telemetry.setMsTransmissionInterval(20);//update telemetry faster
    }

    public void Loop()
    {
        Bot.Loop();

        if(firstRound)
        {
            Bot.OffsetGyro();
            firstRound = false;
        }

        if(Navigating)
        {
            if (!Bot.CheckCloseEnoughOdometry(odometryWheels.GetCurrentData()[0], odometryWheels.GetCurrentData()[1])) //If not close to target
            {
                opmode.telemetry.addData("Not close enough: ", true);

                //CODE FOR PID DRIVE CORRECTION
                double offset = angleFollower.GetOffsetToAdd(Bot.TargetAngle, Bot.GetRobotAngle(), pCoefficient, 0, 0); //good
                Bot.MoveAtAngleTurning(Bot.TargetAngle, Bot.TargetSpeed, false, 0, false, offset);
                //Bot.ApplyTurnOffsetUsingEncoders(offset);

                opmode.telemetry.addData("Robot Angle ", Bot.GetRobotAngle());
                opmode.telemetry.addData("Target Angle ", Bot.TargetAngle);
                opmode.telemetry.addData("Offset ", offset);
            }
            else //Stop
            {
                opmode.telemetry.addData("Not close enough: ", false);
                //Bot.StopAndResetEncoders();
                //Bot.RotateTowardsAngle(TargetRot, 0.5);
                //Rotating = true;
                Navigating = false;
            }
        }

        if(Rotating)
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

        if(!Navigating && !Rotating)//stop if shouldn't do anything
        {
            //Bot.StopMotors();
        }
    }

    public void MoveXandY(double XDistance, double YDistance, double speed)
    {
        pCoefficient = 0.01;
        double angle = CalculateMoveAngle(XDistance, YDistance);
        Bot.TargetAngle = angle;
        double offset = angleFollower.GetOffsetToAdd(Bot.TargetAngle, Bot.GetRobotAngle(), pCoefficient, 0, 0); //good
        Bot.SetTargetOdometry(XDistance, YDistance);
        Bot.MoveAtAngleTurning(angle, speed, false, 0, false, offset);
        Navigating = true;
        Rotating = false;
    }

    public double CalculateMoveAngle(double X, double Y)
    {
        //Calculate angle of joystick
        double baring = Math.atan2(Y, X); //get measurement of joystick angle
        baring = Math.toDegrees(baring);
        baring -= 90;
        if(baring < 0)//convert degrees to positive if needed
        {
            baring = 360 + baring;
        }
        return baring;
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

    /*public boolean CheckCloseEnoughDistance()
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
    }*/

    public void GoRight(double distance, double speed)
    {
        //Bot.GoRightWithEncoder(speed, distance);
        pCoefficient = 0.05;
        Navigating = true;
        Rotating = false;
    }

    public void GoForward(double distance, double speed)
    {
        //Bot.GoForwardWithEncoder(speed, distance);
        pCoefficient = 0.01;
        Navigating = true;
        Rotating = false;
    }

    public void Brake(double power){
        Bot.Brake(power);
    }
    public void SetBrakePos(){
        Bot.SetBrakePos();
    }

    /*public void FoundationGrab(double desiredAngle){
        Bot.FoundationGrab(desiredAngle);
    }*/

    public boolean CheckCloseEnoughRotation()
    {
        if(CurrentRot < TargetRot + closeEnoughThresholdRot && CurrentRot > TargetRot - closeEnoughThresholdRot)
        {
            return true;
        }
        else return false;
    }

}
