package org.firstinspires.ftc.teamcode;

import android.opengl.Matrix;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class UDCTest extends OpMode
{
    //JoystickCalc joystickCalc;
    VuforiaTest vuforia;
    double robotAngle;

    DcMotor FrontRight;
    DcMotor FrontLeft;
    DcMotor RearRight;
    DcMotor RearLeft;

    double FrontRightPower = 0;
    double FrontLeftPower = 0;
    double RearRightPower = 0;
    double RearLeftPower = 0;


    @Override
    public void init()
    {
        //Get hardware components
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        RearRight = hardwareMap.get(DcMotor.class, "RearRight");
        RearLeft = hardwareMap.get(DcMotor.class, "RearLeft");
    }

    @Override
    public void loop()
    {
        //get robot angle TODO: USE GYRO!
        robotAngle = vuforia.RobotAngle;
    }

    public  void  MoveAtAngle(double angle, double speed)
    {
        //get relative angle and calculate wheel speeds
        double relativeAngle = angle + robotAngle;
        CalculateWheelSpeeds(relativeAngle);
        //set the powers of the motors
        FrontRight.setPower(FrontRightPower);
        FrontLeft.setPower(FrontLeftPower);
        RearRight.setPower(RearRightPower);
        RearLeft.setPower(RearLeftPower);
    }

    public void RotateTo(double angle, double speed)
    {
         if (angle > robotAngle) //turn left
         {
            RearLeft.setPower(speed);
            FrontLeft.setPower(speed);
            FrontRight.setPower(speed);
            RearRight.setPower(speed);
         }
         if (angle < robotAngle) //turn right
         {
             RearLeft.setPower(-speed);
             FrontLeft.setPower(-speed);
             FrontRight.setPower(-speed);
             RearRight.setPower(-speed);
         }
    }

    public void RawTurn(boolean right, double speed)
    {
        if (!right) //turn left
        {
            RearLeft.setPower(speed);
            FrontLeft.setPower(speed);
            FrontRight.setPower(speed);
            RearRight.setPower(speed);
        }
        if (right) //turn right
        {
            RearLeft.setPower(-speed);
            FrontLeft.setPower(-speed);
            FrontRight.setPower(-speed);
            RearRight.setPower(-speed);
        }
    }

    public void  StopMotors()
    {
        FrontRight.setPower(0);
        FrontLeft.setPower(0);
        RearRight.setPower(0);
        RearLeft.setPower(0);
    }



    void CalculateWheelSpeeds(double degrees)
    {
        //Wheel speeds are calculated using cosine with a shift
        FrontRightPower = Math.cos(Math.toRadians(degrees + 45));
        FrontLeftPower = Math.cos(Math.toRadians(degrees - 45));
        RearRightPower = Math.cos(Math.toRadians(degrees - 45));
        RearLeftPower = Math.cos(Math.toRadians(degrees + 45));
    }
}