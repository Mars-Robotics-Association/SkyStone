package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class UDCTest extends OpMode
{
    //JoystickCalc joystickCalc;
    JoystickCalcOld jcTest;
    VuforiaTestWebcam vuforia;
    double robotAngle;

    @Override
    public void init()
    {
        //joystickCalc = new JoystickCalc(this);
        jcTest = new JoystickCalcOld(this);

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

    double GetRelativeDegrees(double JoystickAngle, double RobotAngle)
    {
        return (JoystickAngle - RobotAngle);
    }
}