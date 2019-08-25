package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class JoystickCalcTest
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
    double leftStickBaringTest;

    public JoystickCalcTest(OpMode opmode)
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

        //EXPERIMENTAL CODE
        //Calculate angle of joystick
        double Y = leftStickY; //X input
        double X = leftStickX; //Y input
        double tanA = Y/X; //tangent of the angle
        leftStickBaringTest = Math.atan(tanA); //get measurement of joystick angle
        leftStickBaringTest = Math.toDegrees(3.1415 - leftStickBaringTest);
        //END EXPERIMENTAL

    } //closes calculate







}// closes class "JoystickCalc"