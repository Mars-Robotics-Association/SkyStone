package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="UDC_TeleopTest", group="Iterative Opmode")
public class UDC_TeleopTest extends OpMode
{
    JoystickCalc Jc;
    SkyStoneBot Bot;

    double DriveSpeed = 1;
    double TurnSpeed = 0.5;
    double JoystickThreshold = 0.2;

    @Override
    public void init()
    {
        Jc = new JoystickCalc(this);
        Bot = new SkyStoneBot(this);
        Bot.Start();
    }

    @Override
    public void loop()
    {
        Bot.Loop();
        //Update telemetry and get joystick input
        Jc.calculate();
        telemetry.addData("Bot", Bot);
        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            Bot.MoveAtAngle(Jc.leftStickBaring, DriveSpeed * Jc.leftStickPower);
            telemetry.addData("Moving", true);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Bot.RawTurn(true, TurnSpeed);
        }

        else if(Jc.rightStickX < JoystickThreshold) //Turn Left
        {
            //Bot.RawTurn(false, TurnSpeed);
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
