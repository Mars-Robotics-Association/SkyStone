package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="UDC_TeleopTest", group="Iterative Opmode")
public class UDC_TeleopTest extends OpMode
{
    JoystickCalc Jc;
    UDCTest UDC;

    double DriveSpeed = 1;
    double TurnSpeed = 0.5;
    double JoystickThreshold = 0.2;

    @Override
    public void init()
    {
        Jc = new JoystickCalc(this);
        UDC = new UDCTest();
    }

    @Override
    public void loop()
    {
        //Update telemetry and get joystick input
        Jc.calculate();

        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            UDC.MoveAtAngle(Jc.leftStickBaring, DriveSpeed * Jc.leftStickPower);
            telemetry.addData("Moving", Jc.leftStickBaring);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
           UDC.RawTurn(true, TurnSpeed);
        }

        else if(Jc.rightStickX < JoystickThreshold) //Turn Left
        {
            UDC.RawTurn(false, TurnSpeed);
        }

        else //STOP
        {
            UDC.StopMotors();
        }



        telemetry.addData("Left Baring", Jc.leftStickBaring);
        telemetry.addData("Left Power", Jc.leftStickPower);
        telemetry.addData("Right X", Jc.rightStickX);
        telemetry.addData("Right Y", Jc.rightStickY);
        telemetry.update();

    }
}
