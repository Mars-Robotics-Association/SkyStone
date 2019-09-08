package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="UDC_TeleopTest", group="Iterative Opmode")
public class UDC_TeleopTest extends OpMode
{
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;

    private double DriveSpeed = 1;
    private double TurnSpeed = 0.5;
    private double JoystickThreshold = 0.2;

    @Override
    public void init()
    {
        Jc = new JoystickCalc(this);
        Bot = new SkyStoneBot(this);
        Bot.Init();
    }

    @Override
    public void start()
    {
        Bot.Start();
    }

    @Override
    public void loop()
    {
        Bot.Loop();
        //Update telemetry and get joystick input
        Jc.calculate();

        //Reset Gyro if needed
        if(Jc.xButton)
        {
            Bot.OffsetGyro();
        }

        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            Bot.MoveAtAngle(Jc.leftStickBaring, DriveSpeed * Jc.leftStickPower);
            telemetry.addData("Moving", true);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Bot.RawTurn(true, TurnSpeed);
        }

        else if(Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Bot.RawTurn(false, TurnSpeed);
        }

        else //STOP
        {
            Bot.StopMotors();
        }



        telemetry.addData("Left Baring", Jc.leftStickBaring);
        telemetry.addData("Left Power", Jc.leftStickPower);
        telemetry.addData("Right X", Jc.rightStickX);
        telemetry.addData("Right Y", Jc.rightStickY);
        telemetry.addData("Controller ", gamepad1.left_stick_x);
        telemetry.update();

    }
}
