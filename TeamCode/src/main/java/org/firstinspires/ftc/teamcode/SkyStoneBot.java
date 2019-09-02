package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp(name="SkyStoneBase", group="DEBUG")
public class SkyStoneBot extends OpMode implements Robot
{
    double RobotAngle = 90;
    Orientation Angles;

    DcMotor FrontRight;
    DcMotor FrontLeft;
    DcMotor RearRight;
    DcMotor RearLeft;

    double FrontRightPower = 0;
    double FrontLeftPower = 0;
    double RearRightPower = 0;
    double RearLeftPower = 0;

    IMU imu;

    @Override
    public void init()
    {

    }

    @Override
    public void loop()
    {

    }

    public SkyStoneBot()
    {

    }

    public void Start()
    {
        telemetry.addData("SkyStoneStart", true);
        telemetry.update();
        imu = new IMU();
        //Angles = imu.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        //Get hardware components
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        RearRight = hardwareMap.get(DcMotor.class, "RearRight");
        RearLeft = hardwareMap.get(DcMotor.class, "RearLeft");
    }

    public void Loop()
    {
        //RobotAngle = Angles.firstAngle;
        telemetry.addData("IMU: ", imu);
        telemetry.update();
    }

    @Override
    public void MoveAtAngle(double angle, double speed)
    {
        //RobotAngle = GetRobotAngle();
        //get relative angle and calculate wheel speeds
        double relativeAngle = angle + RobotAngle;
        CalculateWheelSpeeds(relativeAngle);
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
        if (angle < RobotAngle) //turn right
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
    public void CalculateWheelSpeeds(double degrees)
    {
        //RobotAngle = GetRobotAngle();
        //Wheel speeds are calculated using cosine with a shift
        FrontRightPower = -Math.cos(Math.toRadians(degrees + 45));
        FrontLeftPower = Math.cos(Math.toRadians(degrees - 45));
        RearRightPower = -Math.cos(Math.toRadians(degrees - 45));
        RearLeftPower = Math.cos(Math.toRadians(degrees + 45));
    }

    @Override
    public float GetRobotAngle()
    {
        return imu.angles.firstAngle;
    }
}
