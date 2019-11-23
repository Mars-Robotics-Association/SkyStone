package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class UDC_Teleop
{
     SkyStoneBot Bot = null;

     double BaseDriveSpeedMultiplier = 1;
     double BaseTurnSpeedMultiplier = 1;
     double DriveSpeedMultiplier = 1 ;
     double TurnSpeedMultiplier = 1;

    private double JoystickThreshold = 0.2;
    double turnSpeed;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;

    OpMode opmode;
    public boolean headlessMode = false;

    public UDC_Teleop(OpMode thatopmode) {
        opmode = thatopmode;
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode);
        Bot.Init();
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
    public void twoThirdsSpeed()
    {

        DriveSpeedMultiplier = BaseDriveSpeedMultiplier*2/3;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier*2/3;

    }

    public void halfSpeed()
    {

        DriveSpeedMultiplier = BaseDriveSpeedMultiplier/2;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier/2;

    }

    public void thirdSpeed()
    {
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier/3;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier/3;
    }

    public void forthSpeed()
    {

        DriveSpeedMultiplier = BaseDriveSpeedMultiplier/4;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier/4;

    }

    public void chooseDirection(double rightStickX, double leftStickBaring, double leftStickPower) //Move
    {
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

        //Make robot move at the angle of the left joystick at the determined speed while applying a turn to the value of the right joystick
        Bot.MoveAtAngleTurning(leftStickBaring, DriveSpeedMultiplier * leftStickPower, turnRight, turnSpeed*TurnSpeedMultiplier, headlessMode);
    }

    public void turnRight() //Turn Right
    {
        Bot.RawTurn(true, turnSpeed*TurnSpeedMultiplier);
    }


    public void turnLeft() //Turn Left
    {
        Bot.RawTurn(false, turnSpeed*TurnSpeedMultiplier);
    }

    public void stopWheels() //STOP
    {
        Bot.StopMotors();
    }
}
