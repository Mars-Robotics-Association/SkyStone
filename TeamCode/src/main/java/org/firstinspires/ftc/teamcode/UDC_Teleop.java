package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class UDC_Teleop
{
     SkyStoneBot Bot = null;
     PIDAngleFollower angleFollower = null;

     double BaseDriveSpeedMultiplier = 1;
     double BaseTurnSpeedMultiplier = 1;
     private double DriveSpeedMultiplier = 1 ;
     private double TurnSpeedMultiplier = 1;
     private double DriveAngle;
     private boolean DriveAngleReseted = false;

    private double JoystickThreshold = 0.2;
    double turnSpeed;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;

    OpMode opmode;
    public boolean headlessMode = false;

    boolean rotatedREVHub = false;

    public UDC_Teleop(OpMode thatopmode, boolean rotateREVHub)
    {
        opmode = thatopmode;
        rotatedREVHub = rotateREVHub;
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode, rotatedREVHub);
        Bot.Init();
        angleFollower = new PIDAngleFollower();
    }

    public void Start()
    {
        Bot.Start();
    }

    public void Loop()
    {
        Bot.Loop();
    }

    public void UpdateTurnSpeed(double input)
    {
        turnSpeed = input;
    }

    //Reset Gyro if needed

    public void gyroOffset()
    {
        Bot.OffsetGyro();
    }

    //switch between normal and slow modes
    public void fullSpeed()
    {
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier;
    }
    public void threeFourthsSpeed()
    {
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier*3/4;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier*3/4;
    }

    public void halfSpeed()
    {
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier/2;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier/2;
    }

    public void brake(double power)
    {
        Bot.Brake(power);
        DriveAngleReseted = false;
    }

    public void forthSpeed()
    {
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier/8;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier/8;
    }

    public void chooseDirection(double rightStickX, double leftStickBaring, double leftStickPower) //Move
    {
        if(!DriveAngleReseted)//reset the target drive angle
        {
            DriveAngle = Bot.GetRobotAngle();
            DriveAngleReseted = true;
        }

        //if we need to turn while moving, choose direction
        boolean turnRight = false;
        if (rightStickX > JoystickThreshold)
        {
            turnRight = true;
        }
        if (rightStickX <= JoystickThreshold)
        {
            turnRight = false;
        }

        //Get an offset of the robot so that it can stay on track:
        double offset = angleFollower.GetOffsetToAdd(DriveAngle, Bot.GetRobotAngle(), 0.01, 0, 0);
        opmode.telemetry.addData("Turn offset: ", offset);

        //Make robot move at the angle of the left joystick at the determined speed while applying a turn to the value of the right joystick
        Bot.MoveAtAngleTurning(leftStickBaring, DriveSpeedMultiplier * leftStickPower, turnRight, 0, headlessMode, 0);
    }

    public void RawForwards(double speed)
    {
        Bot.RawForwards(speed);
    }

    public void RawRight(double speed)
    {
        Bot.RawRight(speed);
    }

    public void turnRight() //Turn Right
    {
        Bot.RawTurn(true, turnSpeed*TurnSpeedMultiplier/2);
    }


    public void turnLeft() //Turn Left
    {
        Bot.RawTurn(false, turnSpeed*TurnSpeedMultiplier/2);
    }

    public void stopWheels() //STOP
    {
        Bot.StopMotors();
        Bot.SetBrakePos();
        DriveAngleReseted = false;
    }
   // public void FoundationGrab(double desiredAngle){ Bot.FoundationGrab(desiredAngle); }
    public int getfleftudc(){
        int fleft = Bot.getfleft();
        return fleft;
    }
    public int getfrightudc(){
        int fright = Bot.getfright();
        return fright;
    }
    public int getrleftudc(){
        int rleft = Bot.getrleft();
        return rleft;
    }
    public int getrrightudc(){
        int rright = Bot.getrright();
        return rright;
    }


}
