package org.firstinspires.ftc.teamcode;

import android.opengl.Matrix;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class UDCTest extends OpMode
{
    //JoystickCalc joystickCalc;
    JoystickCalcOld jcTest;
    VuforiaTestWebcam vuforia;
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
        //joystickCalc = new JoystickCalc(this);
        jcTest = new JoystickCalcOld(this);
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        RearRight = hardwareMap.get(DcMotor.class, "RearRight");
        RearLeft = hardwareMap.get(DcMotor.class, "RearLeft");
    }

    @Override
    public void loop() {

        //joystickCalc.calculate();
        jcTest.calculate();
        //telemetry.addData("left X", joystickCalc.leftStickX);
        //telemetry.addData("left Y", joystickCalc.leftStickY);
        //telemetry.addData("Left Baring", joystickCalc.leftStickBaring);
        telemetry.addData("Left Baring TEST", jcTest.leftStickBaringTest);
        telemetry.addData("Left Power", jcTest.leftStickPower);
        //telemetry.addData("right X", joystickCalc.rightStickX);
        //telemetry.addData("right Y", joystickCalc.rightStickY);
        //telemetry.addData("Right Baring", joystickCalc.rightStickBaring);
        //telemetry.addData("Right Power", joystickCalc.rightStickPower);
        telemetry.update();

        robotAngle = vuforia.RobotAngle;
    }

    public  void  MoveAtAngle(double angle, double speed)
    {
        double relativeAngle = angle + robotAngle;
        CalculateWheelSpeeds(relativeAngle);
    }

    double GetRelativeDegrees(double JoystickAngle, double RobotAngle)
    {
        return (JoystickAngle - RobotAngle);
    }

    void CalculateWheelSpeeds(double degrees)
    {
        FrontRightPower = Math.cos(Math.toRadians(degrees + 45));
        FrontLeftPower = Math.cos(Math.toRadians(degrees - 45));
        RearRightPower = Math.cos(Math.toRadians(degrees - 45));
        RearLeftPower = Math.cos(Math.toRadians(degrees + 45));
    }
}