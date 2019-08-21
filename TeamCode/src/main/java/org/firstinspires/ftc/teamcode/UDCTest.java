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
        test1 = joystickCalc.leftStickX;
        telemetry.addData("X", test1);
        telemetry.update();
    }
}