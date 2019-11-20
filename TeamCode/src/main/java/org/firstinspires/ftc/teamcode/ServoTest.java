package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ServoTest", group="Iterative Opmode")
public class ServoTest extends OpMode
{
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;

    private Servo GripperR = null;





    @Override
    public void init()
    {

        GripperR= hardwareMap.servo.get("GripperR");

    }

    @Override
    public void start()
    {
        Bot.Start();
    }

    @Override
    public void loop()
    {
        telemetry.addData("inital position", GripperR.getPosition());


        if(gamepad1.a){
            GripperR.setPosition(0.3);
        }
        telemetry.update();

    }
}

