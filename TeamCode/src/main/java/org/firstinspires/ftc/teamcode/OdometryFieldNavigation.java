package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class OdometryFieldNavigation
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

    PIDAngleFollower PID = null;
    private double pCoefficient = 0.006;

    //ENCODER MOVEMENT
    private double XTargetPos = 0;
    private double YTargetPos = 0;

    private double XCurrentPos = 0;
    private double YCurrentPos = 0;


    public OdometryFieldNavigation(OpMode setOpmode)
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

    public void Init()
    {
        Bot = new SkyStoneBot(opmode, false);
        Bot.Init();
        PID = new PIDAngleFollower();
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
            NavigationControl();
        }

        if(Rotating)
        {
            RotationControl();
        }

        if(!Navigating && !Rotating)//stop if shouldn't do anything
        {
            Bot.Brake(1);
        }
    }

    void NavigationControl()
    {
        boolean closeEnough = false;
        closeEnough = CheckCloseEnoughOdometry(odometryWheels.GetCurrentData()[0], odometryWheels.GetCurrentData()[1]);

        opmode.telemetry.addData("Close enough: ", closeEnough);

        if (!closeEnough) //If not close to target
        {
            opmode.telemetry.addData("Not close enough: ", true);
            opmode.telemetry.addData("Odometry X: ", odometryWheels.GetCurrentData()[0]);
            opmode.telemetry.addData("Odometry Y: ", odometryWheels.GetCurrentData()[1]);

            //CODE FOR PID DRIVE CORRECTION
            double offset = PID.GetOffsetToAdd(Bot.TargetAngle, Bot.GetRobotAngle(), pCoefficient, 0, 0); //good
            Bot.MoveAtAngleTurning(Bot.TargetAngle, Bot.TargetSpeed, true, 0, false, offset);

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

    public void MoveXandY(double XDistance, double YDistance, double speed)
    {
        double angle = CalculateMoveAngle(XDistance, YDistance);
        Bot.TargetAngle = angle;
        Bot.TargetSpeed = speed;
        double offset = PID.GetOffsetToAdd(Bot.TargetAngle, Bot.GetRobotAngle(), 0.01, 0, 0);
        XTargetPos = XDistance;
        YTargetPos = YDistance;
        Bot.MoveAtAngleTurning(angle, speed, true, 0, false, offset);
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

    public boolean CheckCloseEnoughOdometry(double CurrentX, double CurrentY)
    {
        XCurrentPos = CurrentX;
        YCurrentPos = CurrentY;

        //check if the motors are close enough to their target to move on
        boolean closeEnoughX = Math.abs(XCurrentPos - XTargetPos) < 20;
        boolean closeEnoughY = Math.abs(YCurrentPos - YTargetPos) < 20;
        if(closeEnoughX && closeEnoughY)//if all are, return true
        {
            return true;
        }
        else
        {
            return false;
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

    public void Brake(double power){
        Bot.SetBrakePos();
        Bot.Brake(power);
    }
    public void SetBrakePos(){
        Bot.SetBrakePos();
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
