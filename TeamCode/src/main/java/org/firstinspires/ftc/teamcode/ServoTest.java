package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@TeleOp(name="ServoTesting", group="Iterative Opmode")
public class ServoTest extends OpMode
{

    private Servo GripperL = null;





    @Override
    public void init()
    {

        GripperL= hardwareMap.servo.get("GripperL");

    }

    @Override
    public void start()
    {

    }

    @Override
    public void loop()
    {
        telemetry.addData("inital position", GripperL.getPosition());


        if(gamepad1.a){
            GripperL.setPosition(0.3);
        }
        if(gamepad1.b){GripperL.setPosition(0.5);}
        telemetry.update();

    }
}

