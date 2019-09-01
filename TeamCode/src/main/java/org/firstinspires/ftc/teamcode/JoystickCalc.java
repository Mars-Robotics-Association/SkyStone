package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class JoystickCalc
{
    private OpMode opmode;

    double leftStickY = 0;
    double leftStickX = 0;
    double leftStickBaring = 0;
    double leftStickPower = 0;
    double rightStickX = 0;
    double rightStickY = 0;
    double rightStickBaring = 0;
    double rightStickPower = 0;
    boolean xButton;
    boolean yButton;
    boolean aButton;
    boolean bButton;

    public JoystickCalc(OpMode opmode)
    {
        this.opmode = opmode;
    }

    public void calculate ()
    {

        leftStickY = (opmode.gamepad1.left_stick_y * -1 );
        leftStickX = opmode.gamepad1.left_stick_x;
        rightStickX = opmode.gamepad1.right_stick_x;
        rightStickY = (opmode.gamepad1.right_stick_y * -1 );
        xButton = opmode.gamepad1.x;
        yButton = opmode.gamepad1.y;
        bButton = opmode.gamepad1.b;
        aButton = opmode.gamepad1.a;

        //Calculate angle of joystick
        double Y = leftStickY; //X input
        double X = leftStickX; //Y input
        leftStickBaring = Math.atan2(Y,X); //get measurement of joystick angle
        if(leftStickBaring < 0)//convert degrees to positive if needed
        {
            leftStickBaring = 360 + leftStickBaring;
        }
        //Distance formula for calculating joystick power
        leftStickPower = Math.abs(Math.sqrt(Math.pow(leftStickX - 0, 2) + Math.pow(leftStickY - 0, 2)));

    }
}