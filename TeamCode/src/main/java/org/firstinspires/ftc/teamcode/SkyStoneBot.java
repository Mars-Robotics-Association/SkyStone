package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class SkyStoneBot implements Robot
{
    private double RobotAngle = 0;
    private double RobotAngleOffset = 0;
    private Orientation Angles;

    private DcMotor FrontRight = null;
    private DcMotor FrontLeft = null;
    private DcMotor RearRight = null;
    private DcMotor RearLeft = null;

//    private Servo LiftExtendLeft = null;


    //private Servo FoundationL;
    //private Servo FoundationR;


    private double FrontRightPower = 0;
    private double FrontLeftPower = 0;
    private double RearRightPower = 0;
    private double RearLeftPower = 0;

    //ENCODER MOVEMENT
    private double XRightTargetPos = 0;
    private double XLeftTargetPos = 0;
    private double YRightTargetPos = 0;
    private double YLeftTargetPos = 0;

    private double XRightCurrentPos = 0;
    private double XLeftCurrentPos = 0;
    private double YRightCurrentPos = 0;
    private double YLeftCurrentPos = 0;

    public double TargetSpeed = 0;
    public double TargetAngle = 0;

    //END ENCODER MOVEMENT

    private double EncoderTicks = 1120;//ticks for one rotation 40:1, divided by 2 for 20:1
    private double WheelCircumference = 2 * 3.14;//circumference of wheel in inches
    private int encodedDistance = 0;

    int MotorPositions[]={0,0,0,0};
    private IMU imu;
    private OpMode opmode;

    int FrontRightBrakePos = 0;
    int FrontLeftBrakePos = 0;
    int RearRightBrakePos = 0;
    int RearLeftBrakePos = 0;

    boolean rotatedREVHub = false;

    public SkyStoneBot(OpMode Opmode, boolean rotateREVHub)
    {
        opmode = Opmode;
        rotatedREVHub = rotateREVHub;
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
        FrontRight = opmode.hardwareMap.dcMotor.get("FrontRight");
        FrontLeft = opmode.hardwareMap.dcMotor.get("FrontLeft");
        RearRight = opmode.hardwareMap.dcMotor.get("RearRight");
        RearLeft = opmode.hardwareMap.dcMotor.get("RearLeft");

        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //FoundationL = opmode.hardwareMap.get(Servo.class, "FoundationL");
        //FoundationR = opmode.hardwareMap.get(Servo.class, "FoundationR");
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

        if(!rotatedREVHub)//use normal gyro heading
        {
            RobotAngle = Angles.firstAngle - RobotAngleOffset;
        }
        else //use other gyro orientation (for custom bot)
        {
            RobotAngle = Angles.secondAngle - RobotAngleOffset;
        }


        opmode.telemetry.addData("Target Encoder Distance ", encodedDistance);

        opmode.telemetry.addData("Front Right: ", FrontRight.getCurrentPosition());
        opmode.telemetry.addData("Front Left: ", FrontLeft.getCurrentPosition());
        opmode.telemetry.addData("Rear Right: ", RearRight.getCurrentPosition());
        opmode.telemetry.addData("Rear Left: ", RearLeft.getCurrentPosition());

        opmode.telemetry.addData("Front Right Brake: ", FrontRightBrakePos);
        opmode.telemetry.addData("Front Left Brake: ", FrontLeftBrakePos);
        opmode.telemetry.addData("Rear Right Brake: ", RearRightBrakePos);
        opmode.telemetry.addData("Rear Left Brake: ", RearLeftBrakePos);
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

    /*public void GoForwardWithEncoder(double speed, double distance)
    {
        StopAndResetEncoders();

        encodedDistance = (int)((EncoderTicks/ WheelCircumference)*distance);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

        TargetAngle = RobotAngle;
        TargetSpeed = speed;

        FrontRight.setTargetPosition(encodedDistance);
        FrontLeft.setTargetPosition(-encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(-encodedDistance);

        FrontRightPower = speed;
        FrontLeftPower = speed;
        RearRightPower = speed;
        RearLeftPower = speed;

        FrontRight.setPower(speed);
        FrontLeft.setPower(speed);
        RearRight.setPower(speed);
        RearLeft.setPower(speed);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void GoRightWithEncoder(double speed, double distance)
    {
        StopAndResetEncoders();

        encodedDistance = (int)((EncoderTicks/ WheelCircumference)*distance * Math.sqrt(2));//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

        TargetAngle = RobotAngle;
        TargetSpeed = speed;

        FrontRight.setTargetPosition(-encodedDistance);
        FrontLeft.setTargetPosition(-encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(encodedDistance);

        FrontRightPower = speed;
        FrontLeftPower = speed;
        RearRightPower = speed;
        RearLeftPower = speed;

        FrontRight.setPower(speed);
        FrontLeft.setPower(speed);
        RearRight.setPower(speed);
        RearLeft.setPower(speed);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }*/

   /* public void ApplyTurnOffsetUsingEncoders(double offset)
    {
        //FRONTRIGHT
        if(FrontRight.getCurrentPosition() < FrontRight.getTargetPosition())//going positive
        {
            FrontRight.setPower(FrontRightPower + offset);
        }
        else //going negative
        {
            FrontRight.setPower(FrontRightPower - offset);
        }

        //FRONTLEFT
        if(FrontLeft.getCurrentPosition() < FrontLeft.getTargetPosition())//going positive
        {
            FrontLeft.setPower(FrontLeftPower + offset);
        }
        else //going negative
        {
            FrontLeft.setPower(FrontLeftPower - offset);
        }

        //REARRIGHT
        if(RearRight.getCurrentPosition() < RearRight.getTargetPosition())//going positive
        {
            RearRight.setPower(RearRightPower + offset);
        }
        else //going negative
        {
            RearRight.setPower(RearRightPower - offset);
        }

        //REARLEFT
        if(RearLeft.getCurrentPosition() < RearLeft.getTargetPosition())//going positive
        {
            RearLeft.setPower(RearLeftPower + offset);
        }
        else //going negative
        {
            RearLeft.setPower(RearLeftPower - offset);
        }

        opmode.telemetry.addData("FR power", FrontRight.getPower());
        opmode.telemetry.addData("FL power", FrontLeft.getPower());
        opmode.telemetry.addData("RR power", RearRight.getPower());
        opmode.telemetry.addData("RL power", RearLeft.getPower());

        *//*opmode.telemetry.addData("FR offset", (TargetSpeed * (FrontRight.getPower() * Math.abs(FrontRight.getPower())) - offset));
        opmode.telemetry.addData("FL offset", (TargetSpeed * (FrontLeft.getPower() * Math.abs(FrontLeft.getPower())) - offset));
        opmode.telemetry.addData("RR offset", (TargetSpeed * (RearRight.getPower() * Math.abs(RearRight.getPower())) - offset));
        opmode.telemetry.addData("RL offset", (TargetSpeed * (RearLeft.getPower() * Math.abs(RearLeft.getPower())) - offset));*//*
    }*/

   /* public void MoveAtAngleTurningForEncoders(double angle, double speed, double turnSpeed)
    {
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //get relative angle and calculate wheel speeds
        double relativeAngle = angle;
        relativeAngle += RobotAngle;
        opmode.telemetry.addData("relative angle: ", relativeAngle);
        opmode.telemetry.addData("given angle: ", angle);

        boolean turnRight = false;

        if(relativeAngle > RobotAngle)
        {
            turnRight = true;
        }
        if(relativeAngle <= RobotAngle)
        {
            turnRight = false;
        }

        CalculateWheelSpeedsTurning(relativeAngle, speed, turnRight, turnSpeed);

        //set the powers of the motors
        FrontRight.setPower(FrontRightPower);
        FrontLeft.setPower(FrontLeftPower);
        RearRight.setPower(RearRightPower);
        RearLeft.setPower(RearLeftPower);

        //Update the values for breaking
        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontLeftBrakePos = FrontLeft.getCurrentPosition();
        RearRightBrakePos = RearRight.getCurrentPosition();
        RearLeftBrakePos = RearLeft.getCurrentPosition();
    }*/



    /*public boolean CheckIfEncodersCloseEnough()
    {
        //check if the motors are close enough to their target to move on
        boolean closeEnoughFR = Math.abs(FrontRight.getCurrentPosition() - FrontRight.getTargetPosition()) < 20;
        boolean closeEnoughFL = Math.abs(FrontLeft.getCurrentPosition() - FrontLeft.getTargetPosition()) < 20;
        boolean closeEnoughRR = Math.abs(RearRight.getCurrentPosition() - RearRight.getTargetPosition()) < 20;
        boolean closeEnoughRL = Math.abs(RearLeft.getCurrentPosition() - RearLeft.getTargetPosition()) < 20;
        if(closeEnoughFR && closeEnoughFL && closeEnoughRR && closeEnoughRL)//if all are, return true
        {
            return true;
        }
        else
        {
            return false;
        }
    }*/

    @Override
    public void RotateTowardsAngle(double angle, double speed)
    {
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    /*public void StopAndResetEncoders()
    {
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }*/

    //END ENCODER METHODS FOR SIMPLE AUTONOMOUS

    //allows robot to corkscrew
    public void MoveAtAngleTurning(double angle, double speed, boolean turnRight, double turnSpeed, boolean headlessMode, double PIDOffset)
    {
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //get relative angle and calculate wheel speeds
        double relativeAngle = angle;
        if(headlessMode){relativeAngle += RobotAngle;}
        opmode.telemetry.addData("move angle: ", angle);
        opmode.telemetry.addData("final angle: ", relativeAngle);
        CalculateWheelSpeedsTurning(relativeAngle, speed, turnRight, turnSpeed);
        ApplyTurnOffsetNonEncoder(PIDOffset);
        opmode.telemetry.addData("PID Offset: ", PIDOffset);
        //CalculateWheelSpeeds(relativeAngle, speed);

        //set the powers of the motors
        FrontRight.setPower(FrontRightPower);
        FrontLeft.setPower(FrontLeftPower);
        RearRight.setPower(RearRightPower);
        RearLeft.setPower(RearLeftPower);

        //Update the values for breaking
        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontLeftBrakePos = FrontLeft.getCurrentPosition();
        RearRightBrakePos = RearRight.getCurrentPosition();
        RearLeftBrakePos = RearLeft.getCurrentPosition();
    }

    public void ApplyTurnOffsetNonEncoder(double offset)
    {
        FrontRightPower += offset;
        FrontLeftPower += offset;
        RearRightPower += offset;
        RearLeftPower += offset;
    }

    public void SetTargetOdometry(double XR, double YR)
    {
        XRightTargetPos = XR;
        XLeftTargetPos = XR;
        YRightTargetPos = YR;
        YLeftTargetPos = YR;
    }

    public boolean CheckCloseEnoughOdometry()
    {
        XRightCurrentPos = 0;
        XLeftCurrentPos = 0;
        YRightCurrentPos = 0;
        YLeftCurrentPos = 0;

        //check if the motors are close enough to their target to move on
        boolean closeEnoughFR = Math.abs(XRightCurrentPos - XRightTargetPos) < 20;
        boolean closeEnoughFL = Math.abs(XLeftCurrentPos - XLeftTargetPos) < 20;
        boolean closeEnoughRR = Math.abs(YRightCurrentPos - YRightTargetPos) < 20;
        boolean closeEnoughRL = Math.abs(YLeftCurrentPos - YLeftTargetPos) < 20;
        if(closeEnoughFR && closeEnoughFL && closeEnoughRR && closeEnoughRL)//if all are, return true
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //Raw movement methods:

    public void RawForwards(double speed)
    {
        FrontRight.setPower(speed);
        FrontLeft.setPower(-speed);
        RearRight.setPower(speed);
        RearLeft.setPower(-speed);
    }

    public void RawRight(double speed)
    {
        FrontRight.setPower(-speed);
        FrontLeft.setPower(-speed);
        RearRight.setPower(speed);
        RearLeft.setPower(speed);
    }

    @Override
    public void RawTurn(boolean right, double speed)
    {
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

        //Update the values for breaking
        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontLeftBrakePos = FrontLeft.getCurrentPosition();
        RearRightBrakePos = RearRight.getCurrentPosition();
        RearLeftBrakePos = RearLeft.getCurrentPosition();
    }

    public void Brake(double power)
    {
        //Set the encoders to run to the breaking position
        FrontRight.setTargetPosition(FrontRightBrakePos);
        FrontLeft.setTargetPosition(FrontLeftBrakePos);
        RearRight.setTargetPosition(RearRightBrakePos);
        RearLeft.setTargetPosition(RearLeftBrakePos);

        FrontRight.setPower(power);
        FrontLeft.setPower(-power);
        RearRight.setPower(power);
        RearLeft.setPower(-power);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void SetBrakePos(){
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        FrontRightBrakePos = FrontRight.getCurrentPosition();
        FrontLeftBrakePos = FrontLeft.getCurrentPosition();
        RearRightBrakePos = RearRight.getCurrentPosition();
        RearLeftBrakePos = RearLeft.getCurrentPosition();
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
    public int getfleft(){
        int fleft = FrontLeft.getCurrentPosition();
        return fleft;
    }
    public int getfright(){
        int fright = FrontLeft.getCurrentPosition();
        return fright;
    }
    public int getrleft(){
        int rleft = FrontLeft.getCurrentPosition();
        return rleft;
    }
    public int getrright(){
        int rright = FrontLeft.getCurrentPosition();
        return rright;
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
        FrontRightPower = -Math.cos(Math.toRadians(degrees + 45)) * speed + turnOffset;
        FrontLeftPower = Math.cos(Math.toRadians(degrees - 45)) * speed + turnOffset;
        RearRightPower = -Math.cos(Math.toRadians(degrees - 45)) * speed + turnOffset;
        RearLeftPower = Math.cos(Math.toRadians(degrees + 45)) * speed + turnOffset;
    }

    @Override
    public double GetRobotAngle()
    {
        return RobotAngle;
    }

    /*public void FoundationGrab(double desiredAngle){
        if(desiredAngle>0){
            FoundationL.setPosition(0.75);
            FoundationR.setPosition(0.25);
        }
        else{
            FoundationL.setPosition(0.25);
            FoundationR.setPosition(0.75);
        }*/


}
