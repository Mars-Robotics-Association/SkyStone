package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class JoystickCalc
{
    private OpMode opmode;

    double leftStickY;
    double leftStickX;
    double leftStickBaring;
    double leftStickPower;
    double rightStickX;
    double rightStickY;
    double rightStickBaring;
    double rightStickPower;
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
        leftStickBaring = Math.toDegrees(3.1415 - leftStickBaring);
        if(leftStickBaring < 0)//convert degrees to positive if needed
        {
            leftStickBaring = 360 - leftStickBaring;
        }
        leftStickPower = (leftStickX + leftStickY)/2;

    } //closes calculate







}// closes class "JoystickCalc"