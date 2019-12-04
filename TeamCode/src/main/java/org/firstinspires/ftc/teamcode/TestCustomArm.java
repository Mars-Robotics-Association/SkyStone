package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TestCustomArm", group="Iterative Opmode")
public class TestCustomArm extends OpMode
{


    private JoystickCalc Jc = null;
    public ArmAttachmentCustom arm;
    GripperTetrix gripper;

    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;
    private double gripperPosition = 0.5;

    private double JoystickThreshold = 0.2;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;

    @Override
    public void init()
    {
        gripperPosition = 0.5;

        Jc = new JoystickCalc(this, 180);

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        //gripper = new GripperTetrix(this);
        //gripper.Init();
    }

    @Override
    public void start()
    {
    }

    @Override
    public void loop()
    {
        //Update telemetry and get joystick input
        Jc.calculate();
        //calculate the absolute value of the right x for turn speed
        double turnSpeed = Math.abs(Jc.rightStickX);

        if(gamepad2.x)
        {
            //pick up stone
        }
        else if (gamepad2.y)
        {
            //put down stone
        }

        ManageArmMovement();
        //ManageGripperMovement();

        telemetry.update();
    }

    public void ManageArmMovement()//Manages the Arm/Lift
    {
/*        if(gamepad2.b)//turn the wheel intake on
        {
            arm.IntakeOn();
        }
        else//turn the intake off
        {
            arm.IntakeOff();
        }*/

        if(gamepad2.dpad_up)//move lift up
        {
            arm.LiftUp();
        }
        else if(gamepad2.dpad_down)//move lift down
        {
            arm.LiftDown();
        }
        else//stop the arm from moving up or down
        {
            arm.LiftStopVertical();
        }
        if(gamepad2.dpad_left)////extend arm
        {
            arm.LiftExtend();
        }

        else if (gamepad2.dpad_right)//retract arm
        {
            arm.LiftRetract();
        }

        else//stop the arm from moving left or right
        {
            arm.LiftStopHorizontal();
        }
    }
}
