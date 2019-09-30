package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Gripper Test", group="Iterative Opmode")
public class GripperTeleOpTest extends OpMode
{


    boolean a;
    boolean b;


    @Override
    public void init()
    {


        a = gamepad1.a;
        b = gamepad1.b;



    }

    @Override
    public void start()
    {


    }

    @Override
    public void loop()
    {

        if(a){

        }
        if(b){

        }


    }
}
