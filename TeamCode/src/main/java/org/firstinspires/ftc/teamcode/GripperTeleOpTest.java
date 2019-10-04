package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Gripper Test", group="Iterative Opmode")
public class GripperTeleOpTest extends OpMode
{
    public Gripper gripper;



    boolean a;
    boolean b;


    @Override
    public void init()
    {
        gripper = new Gripper();

        a = gamepad1.left_bumper;
        b = gamepad1.right_bumper;   // might wanna change this to triggers



    }

    @Override
    public void start()
    {


    }

    @Override
    public void loop()
    {

        if(a){
gripper.GripperClose();
        }
        if(b){
            gripper.GripperOpen();
        }


    }
}
