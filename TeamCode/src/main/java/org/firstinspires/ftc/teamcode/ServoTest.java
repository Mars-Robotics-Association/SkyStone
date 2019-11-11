package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="ServoTesting", group="Iterative Opmode")
public class ServoTest extends OpMode
{
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;

    private Servo GripperL = null;





    @Override
    public void init()
    {

        GripperL= hardwareMap.servo.get("GripperL");

    }

    @Override
    public void start()
    {
        Bot.Start();
    }

    @Override
    public void loop()
    {
        telemetry.addData("inital position", GripperL.getPosition());


        if(gamepad1.a){
            GripperL.setPosition(0.3);
        }
        telemetry.update();

    }
}

