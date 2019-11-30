package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

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

    private Servo FoundationL;
    private Servo FoundationR;

    private double FrontRightPower = 0;
    private double FrontLeftPower = 0;
    private double RearRightPower = 0;
    private double RearLeftPower = 0;

    private double EncoderTicks = 1120;//ticks for one rotation
    private double WheelDiameter = 2;//diameter of wheel in inches
    private int encodedDistance = 0;

    int MotorPositions[]={0,0,0,0};
    private IMU imu;
    private OpMode opmode;

    int FrontRightBrakePos = 0;
    int FrontLeftBrakePos = 0;
    int RearRightBrakePos = 0;
    int RearLeftBrakePos = 0;

    public SkyStoneBot(OpMode Opmode)
    {
        opmode = Opmode;
    }

    public double GetGyroOffset()
    {
        return RobotAngleOffset;
    }
    public double GetFinalGyro()
    {
        return RobotAngle;
    }

    @Override
    public void Init()
    {
        opmode.telemetry.addData("SkyStoneStart", true);
        imu = new IMU(opmode);
        imu.Init();

        //Get hardware components
        FrontRight = opmode.hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = opmode.hardwareMap.get(DcMotor.class, "FrontLeft");
        RearRight = opmode.hardwareMap.get(DcMotor.class, "RearRight");
        RearLeft = opmode.hardwareMap.get(DcMotor.class, "RearLeft");

        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FoundationL = opmode.hardwareMap.get(Servo.class, "FoundationL");
        FoundationR = opmode.hardwareMap.get(Servo.class, "FoundationR");

        opmode.telemetry.update();
    }

    @Override
    public void Start()
    {
        imu.Start();
        OffsetGyro();
    }

    @Override
    public void Loop()
    {
        imu.Loop();
        Angles = imu.angles;
        MotorPositions = new int[]{FrontRight.getCurrentPosition(), FrontLeft.getCurrentPosition(), RearRight.getCurrentPosition(), RearLeft.getCurrentPosition()};
        RobotAngle = Angles.firstAngle - RobotAngleOffset;
    }

    public void OffsetGyro()
    {
        Orientation a = imu.angles;
        RobotAngleOffset = a.firstAngle;
    }

    @Override
    public void MoveAtAngle(double angle, double speed, boolean headlessMode)
    {
        //get relative angle and calculate wheel speeds
        double relativeAngle = angle;
        if(headlessMode){relativeAngle += RobotAngle;}
/*
        double relativeAngle = angle + RobotAngle + 90;
*/
        CalculateWheelSpeeds(relativeAngle, speed);
        //set the powers of the motors
        FrontRight.setPower(FrontRightPower);
        FrontLeft.setPower(FrontLeftPower);
        RearRight.setPower(RearRightPower);
        RearLeft.setPower(RearLeftPower);
    }

    //ENCODER METHODS FOR SIMPLE AUTONOMOUS

    public void GoForwardWithEncoder(double speed, double distance)
    {
        StopEncoders();

        encodedDistance = (int)((EncoderTicks/WheelDiameter)/distance);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

        FrontRight.setTargetPosition(encodedDistance);
        FrontLeft.setTargetPosition(-encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(-encodedDistance);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void GoRightWithEncoder(double speed, double distance)
    {
        StopEncoders();

        encodedDistance = (int)((EncoderTicks/WheelDiameter)/distance * Math.sqrt(2));//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

        FrontRight.setTargetPosition(encodedDistance);
        FrontLeft.setTargetPosition(encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(encodedDistance);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public boolean CheckIfEncodersCloseEnough()
    {
        int currentPos = FrontRight.getCurrentPosition();
        if(Math.abs(currentPos - encodedDistance) < 4)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void StopEncoders()
    {
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    //END ENCODER METHODS FOR SIMPLE AUTONOMOUS

    //allows robot to corkscrew
    public void MoveAtAngleTurning(double angle, double speed, boolean turnRight, double turnSpeed, boolean headlessMode)
    {
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //get relative angle and calculate wheel speeds
        double relativeAngle = angle;
        if(headlessMode){relativeAngle += RobotAngle;}
        CalculateWheelSpeedsTurning(relativeAngle, speed, turnRight, turnSpeed);
        //set the powers of the motors
        FrontRight.setPower(FrontRightPower);
        FrontLeft.setPower(FrontLeftPower);
        RearRight.setPower(RearRightPower);
        RearLeft.setPower(RearLeftPower);

        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontRightBrakePos = FrontRight.getCurrentPosition();

    }

    @Override
    public void RotateTo(double angle, double speed)
    {
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
        /*Wheel speed is calculated by getting the cosine wave (which we found matches the speed that
         * the wheels need to go) with a positive 45 or negative 45 shift, depending on the wheel. This works
         * so that no matter the degrees, it will always come out with the right value.*/
        FrontRightPower = -Math.cos(Math.toRadians(degrees + 45)) * speed;
        FrontLeftPower = Math.cos(Math.toRadians(degrees - 45)) * speed;
        RearRightPower = -Math.cos(Math.toRadians(degrees - 45)) * speed;
        RearLeftPower = Math.cos(Math.toRadians(degrees + 45)) * speed;
    }
    public int[] GetMotorPositions(){
        MotorPositions[0] = FrontRight.getCurrentPosition();
        MotorPositions[1] = FrontLeft.getCurrentPosition();
        MotorPositions[2] = RearRight.getCurrentPosition();
        MotorPositions[3] = RearLeft.getCurrentPosition();
        return MotorPositions;
    }

    //allows for corkscrewing
    public void CalculateWheelSpeedsTurning(double degrees, double speed, boolean turnRight, double turnSpeed)
    {
        //offset to be added to motor speeds
        double turnOffset = 0;
        if (!turnRight) //turn left
        {
            turnOffset = turnSpeed;
        }
        if (turnRight) //turn right
        {
            turnOffset = -turnSpeed;
        }

        /*Wheel speed is calculated by getting the cosine wave (which we found matches the speed that
        * the wheels need to go) with a positive 45 or negative 45 shift, depending on the wheel. This works
        * so that no matter the degrees, it will always come out with the right value. A turn offset is added
        * to the end for corkscrewing, or turning while driving*/
        FrontRightPower = (-Math.cos(Math.toRadians(degrees + 45)) * speed) + turnOffset;
        FrontLeftPower = (Math.cos(Math.toRadians(degrees - 45)) * speed) + turnOffset;
        RearRightPower = (-Math.cos(Math.toRadians(degrees - 45)) * speed) + turnOffset;
        RearLeftPower = (Math.cos(Math.toRadians(degrees + 45)) * speed) + turnOffset;
    }

    @Override
    public float GetRobotAngle()
    {
        return 0;
    }

    public void FoundationGrab(double desiredAngle){
        if(desiredAngle>0){
            FoundationL.setPosition(0.75);
            FoundationR.setPosition(0.25);
        }
        else{
            FoundationL.setPosition(0.25);
            FoundationR.setPosition(0.75);
        }

    }

}
