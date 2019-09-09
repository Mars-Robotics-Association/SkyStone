package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class SkyStoneBot implements Robot
{
    private double RobotAngle = 0;
    private double RobotAngleOffset = 0;
    private Orientation Angles;

    private DcMotor FrontRight;
    private DcMotor FrontLeft;
    private DcMotor RearRight;
    private DcMotor RearLeft;

    private double FrontRightPower = 0;
    private double FrontLeftPower = 0;
    private double RearRightPower = 0;
    private double RearLeftPower = 0;

    private IMU imu;
    private OpMode opmode;

    public SkyStoneBot(OpMode opmode)
    {
        this.opmode = opmode;
    }

    @Override
    public void Init()
    {
        opmode.telemetry.addData("SkyStoneStart", true);
        opmode.telemetry.update();
        imu = new IMU(this.opmode);
        imu.Init();

        //Get hardware components
        FrontRight = opmode.hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = opmode.hardwareMap.get(DcMotor.class, "FrontLeft");
        RearRight = opmode.hardwareMap.get(DcMotor.class, "RearRight");
        RearLeft = opmode.hardwareMap.get(DcMotor.class, "RearLeft");
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
        Angles = imu.angles;
        RobotAngle = Angles.firstAngle - RobotAngleOffset;
        opmode.telemetry.addData("IMU: ", imu);
        opmode.telemetry.update();
    }

    public void OffsetGyro()
    {
        RobotAngleOffset = RobotAngle;
    }

    @Override
    public void MoveAtAngle(double angle, double speed)
    {
        //RobotAngle = GetRobotAngle();
        //get relative angle and calculate wheel speeds
        double relativeAngle = angle + RobotAngle;
        CalculateWheelSpeeds(relativeAngle, speed);
        //set the powers of the motors
        FrontRight.setPower(FrontRightPower);
        FrontLeft.setPower(FrontLeftPower);
        RearRight.setPower(RearRightPower);
        RearLeft.setPower(RearLeftPower);
    }

    @Override
    public void RotateTo(double angle, double speed)
    {
        //RobotAngle = GetRobotAngle();
        if (angle > RobotAngle) //turn left
        {
            RearLeft.setPower(speed);
            FrontLeft.setPower(speed);
            FrontRight.setPower(speed);
            RearRight.setPower(speed);
        }
        else //turn right
        {
            RearLeft.setPower(-speed);
            FrontLeft.setPower(-speed);
            FrontRight.setPower(-speed);
            RearRight.setPower(-speed);
        }
    }

    @Override
    public void RawTurn(boolean right, double speed)
    {
        //RobotAngle = GetRobotAngle();
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

    @Override
    public void StopMotors()
    {
        FrontRight.setPower(0);
        FrontLeft.setPower(0);
        RearRight.setPower(0);
        RearLeft.setPower(0);
    }

    @Override
    public void CalculateWheelSpeeds(double degrees, double speed)
    {
        //RobotAngle = GetRobotAngle();
        //Wheel speeds are calculated using cosine with a shift
        FrontRightPower = -Math.cos(Math.toRadians(degrees + 45)) * speed;
        FrontLeftPower = Math.cos(Math.toRadians(degrees - 45)) * speed;
        RearRightPower = -Math.cos(Math.toRadians(degrees - 45)) * speed;
        RearLeftPower = Math.cos(Math.toRadians(degrees + 45)) * speed;
    }

    @Override
    public float GetRobotAngle()
    {
        return 0;
    }
}
