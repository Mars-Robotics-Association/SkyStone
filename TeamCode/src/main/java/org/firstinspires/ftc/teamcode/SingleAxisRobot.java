package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SingleAxisRobot implements Robot
{
    static double RobotAngle = 0;

    static DcMotor Right;
    static DcMotor Left;

    static double RightPower = 0;
    static double LeftPower = 0;

    static IMU imu;

    static double TargetRot = 0;
    static double CloseEnoughThresholdRot = 5;
    static boolean isRotating = false;
    static double Speed = 0;
    static boolean FacingForwards = true; //whether robot is going to move forwards or backwards

    OpMode opmode;

    public SingleAxisRobot(OpMode opMode){this.opmode = opMode;}

    @Override
    public void Init()
    {
        imu = new IMU(opmode);
        imu.Init();
        //Get hardware components
        Right = opmode.hardwareMap.get(DcMotor.class, "Right");
        Left = opmode.hardwareMap.get(DcMotor.class, "Left");
    }

    @Override
    public void Start()
    {
        imu.Start();
    }

    @Override
    public void Loop()
    {
        imu.Loop();
        //get robot angle
        RobotAngle = GetRobotAngle();

        if(isRotating)
        {
            if(CheckCloseEnoughRotation()) //if time to stop rotating
            {
                StopMotors();
                isRotating = false;
            }
            CalculateWheelSpeeds(RobotAngle, 1);
        }

        //set the powers of the motors
        Right.setPower(RightPower);
        Left.setPower(LeftPower);
    }

    @Override
    public void MoveAtAngle(double angle, double speed, boolean headlessMode)
    {
        TargetRot = angle;
        isRotating = true;
        Speed = speed;
    }

    @Override
    public void RotateTowardsAngle(double angle, double speed)
    {
        Speed = speed;
        if (angle > RobotAngle) //turn left
        {
            RightPower = -Speed;
            LeftPower = -Speed;
        }
        if (angle < RobotAngle) //turn right
        {
            RightPower = Speed;
            LeftPower = Speed;
        }
    }

    @Override
    public void RawTurn(boolean right, double speed)
    {
        Speed = speed;
        if (!right) //turn left
        {
            RightPower = -Speed;
            LeftPower = -Speed;
        }
        if (right) //turn right
        {
            RightPower = Speed;
            LeftPower = Speed;
        }
    }

    @Override
    public void StopMotors()
    {
        isRotating = false;
        RightPower = 0;
        LeftPower = 0;
    }

    @Override
    public void CalculateWheelSpeeds(double degrees, double speed)
    {
        if(CheckCloseEnoughRotation()) //if the robot is on track
        {
            if(FacingForwards)//go forwards
            {
                RightPower = -Speed; //because motors are opposite eachother
                LeftPower = Speed;
            }
            else//go backwards
            {
                RightPower = Speed; //because motors are opposite eachother
                LeftPower = -Speed;
            }
        }

        else //robot isn't on track
        {
            boolean turnToFront = CheckIfForwardIsCloser();
            double amountOff = TargetRot - GetRobotAngle(); // get amount off
            if(amountOff < 0) //if negative, make positive
            {
                amountOff = 360 - amountOff;
            }

            if(turnToFront) //which way to turn
            {
                if(amountOff >= 0 && amountOff <= 180) //turn left
                {
                    RightPower = -Speed;
                    LeftPower = -Speed;
                }

                if(amountOff > 180 && amountOff < 360) //turn right
                {
                    RightPower = Speed;
                    LeftPower = Speed;
                }
            }
            else //if we want to go backwards, reverse the turn direction
            {
                if(amountOff >= 0 && amountOff <= 180) //turn right
                {
                    RightPower = Speed;
                    LeftPower = Speed;
                }

                if(amountOff > 180 && amountOff < 360) //turn left
                {
                    RightPower = -Speed;
                    LeftPower = -Speed;
                }
            }
        }
    }

    @Override
    public double GetRobotAngle()
    {
        return 0;
    }

    public boolean CheckCloseEnoughRotation()
    {
        double amountOff = TargetRot - GetRobotAngle(); // get amount off
        if(amountOff < 0) //if negative, make positive
        {
            amountOff = 360 - amountOff;
        }

        if(amountOff <  CloseEnoughThresholdRot && amountOff > CloseEnoughThresholdRot ) //if front or back is on track
        {
            FacingForwards = true;
            return true;
        }
        else if(amountOff - 180 <  CloseEnoughThresholdRot && amountOff - 180 > CloseEnoughThresholdRot)
        {
            FacingForwards = false;
            return true;
        }
        else return false;
    }

    public boolean CheckIfForwardIsCloser()//checks which way robot should turn
    {
        double amountOff = TargetRot - GetRobotAngle(); // get amount off
        if(amountOff < 0) //if negative, make positive
        {
            amountOff = 360 - amountOff;
        }

        if(amountOff <=  270 && amountOff >= 90 ) //if back is closer
        {
            return false;
        }
        else if((amountOff < 90  && amountOff >= 0) || (amountOff < 360  && amountOff > 270))
        {
            return true;
        }
        else
        {
            return true;
        }
    }
}
