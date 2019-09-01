package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class SingleAxisRobot extends OpMode implements Robot
{
    double RobotAngle = 0;

    DcMotor Right;
    DcMotor Left;

    double RightPower = 0;
    double LeftPower = 0;

    IMU imu;

    double TargetRot = 0;
    double CloseEnoughThresholdRot = 5;
    boolean isRotating = false;
    double Speed = 0;
    boolean FacingForwards = true; //whether robot is going to move forwards or backwards

    @Override
    public void init()
    {
        imu = new IMU();
        //Get hardware components
        Right = hardwareMap.get(DcMotor.class, "Right");
        Left = hardwareMap.get(DcMotor.class, "Left");
    }

    @Override
    public void loop()
    {
        //get robot angle
        RobotAngle = GetRobotAngle();

        if(isRotating)
        {
            if(CheckCloseEnoughRotation()) //if time to stop rotating
            {
                StopMotors();
                isRotating = false;
            }
            CalculateWheelSpeeds(RobotAngle);
        }

        //set the powers of the motors
        Right.setPower(RightPower);
        Left.setPower(LeftPower);
    }

    @Override
    public void MoveAtAngle(double angle, double speed)
    {
        TargetRot = angle;
        isRotating = true;
        Speed = speed;
    }

    @Override
    public void RotateTo(double angle, double speed)
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
        
    }

    @Override
    public void StopMotors()
    {
        isRotating = false;
    }

    @Override
    public void CalculateWheelSpeeds(double degrees)
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

            if(turnToFront)
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
            else
            {

            }
        }
    }

    @Override
    public float GetRobotAngle()
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
