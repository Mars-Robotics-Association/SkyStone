package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class JoystickCalcOld
{
    private OpMode opmode;

    double leftStickY;
    double leftStickX;
    double rightStickX;
    double rightStickY;
    boolean xButton;
    boolean yButton;
    boolean aButton;
    boolean bButton;

    public JoystickCalcOld(OpMode opmode)
    {
        this.opmode = opmode;
    }
    double leftStickBaringTest = 0;
    double leftStickPower = 0;

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

        //EXPERIMENTAL CODE
        //Calculate angle of joystick
        double Y = leftStickY; //X input
        double X = leftStickX; //Y input
        leftStickBaringTest = Math.atan2(Y,X); //get measurement of joystick angle
        leftStickBaringTest = Math.toDegrees(3.1415 - leftStickBaringTest);
        leftStickPower = (leftStickX + leftStickY)/2;
        //END EXPERIMENTAL

    } //closes calculate







}// closes class "JoystickCalc"