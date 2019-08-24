package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class UDCTest extends OpMode
{
    JoystickCalc joystickCalc;
    double test1;

    @Override
    public void init()
    {
        joystickCalc = new JoystickCalc(this);
    }

    @Override
    public void loop() {

        joystickCalc.calculate();

        telemetry.addData("left X", joystickCalc.leftStickX);
        telemetry.addData("left Y", joystickCalc.leftStickY);
        telemetry.addData("Left Baring", joystickCalc.leftStickBaring);
        telemetry.addData("Left Power", joystickCalc.leftStickPower);
        telemetry.addData("right X", joystickCalc.rightStickX);
        telemetry.addData("right Y", joystickCalc.rightStickY);
        telemetry.addData("Right Baring", joystickCalc.rightStickBaring);
        telemetry.addData("Right Power", joystickCalc.rightStickPower);
        telemetry.update();
    }
}