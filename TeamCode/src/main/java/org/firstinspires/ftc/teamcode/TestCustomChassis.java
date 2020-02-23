package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@Disabled
@TeleOp(name="TEST-CustomChasisTeleop", group="Iterative Opmode")
public class TestCustomChassis extends OpMode
{
    private JoystickCalc Jc = null;
    public UDC_Teleop Teleop = null;


    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;

    private double JoystickThreshold = 0.2;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;
    boolean stopping = false;


    @Override
    public void init()
    {
        Jc = new JoystickCalc(this, 180);//was 180

        Teleop = new UDC_Teleop(this, false);
        Teleop.Init();
    }

    @Override
    public void start()
    {
        Teleop.Start();
    }

    @Override
    public void loop()
    {
        Teleop.Loop();
        //Update telemetry and get joystick input
        Jc.calculate();
        Teleop.UpdateTurnSpeed(Math.abs(Jc.rightStickX));
        //calculate the absolute value of the right x for turn speed
        double turnSpeed = Math.abs(Jc.rightStickX);

        //Reset Gyro if needed
        if(gamepad1.x)
        {
            Teleop.gyroOffset();
        }

        //SwitchModes
        if(gamepad1.y)
        {
            Teleop.headlessMode = true;
        }
        if(gamepad1.b)
        {
            Teleop.headlessMode = false;
        }

        ManageDriveMovement();

        //switch between normal and slow modes
        if(gamepad1.left_bumper) { Teleop.fullSpeed(); }
        if(gamepad1.right_bumper) { Teleop.halfSpeed(); }
        if(gamepad1.left_trigger>0.2) { Teleop.quarterSpeed(); }
/*
        if(gamepad1.right_trigger>0.2) { Teleop.forthSpeed(); }
*/

        if(gamepad2.x)
        {
            //pick up stone
        }
        else if (gamepad2.y)
        {
            //put down stone.
        }
        if(gamepad2.a)
        {
        }
        if (gamepad2.b)
        {
        }

        if(gamepad1.dpad_up){
            int fleft = Teleop.getfleftudc();
            int fright = Teleop.getfrightudc();
            int rleft = Teleop.getrleftudc();
            int rright = Teleop.getrrightudc();
            telemetry.addData("front left wheel: ",fleft);
            telemetry.addData("front right wheel: ",fright);
            telemetry.addData("rear left wheel: ",rleft);
            telemetry.addData("rear right wheel: ",rright);
            telemetry.update();
        }


        telemetry.update();
    }

    public void ManageDriveMovement()//Manages general drive input
    {
        //Raw D-pad stuff
        /*if(gamepad1.dpad_up)
        {
            Teleop.RawForwards(1);
        }
        if(gamepad1.dpad_down)
        {
            Teleop.RawForwards(-1);
        }
        if(gamepad1.dpad_right)
        {
            Teleop.RawRight(1);
        }
        if(gamepad1.dpad_left)
        {
            Teleop.RawRight(-1);
        }*/

        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            Teleop.chooseDirection(Jc.rightStickX, Jc.leftStickBaring, Jc.leftStickPower);
            stopping = false;
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Teleop.turnRight();
            stopping = false;
        }


        else if(Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Teleop.turnLeft();
            stopping = false;
        }

        else if(gamepad1.right_trigger>0.2)
        {
            Teleop.brake(0.5);
            telemetry.addData("Brake: ", true);
            stopping = true;
        }
        else if(gamepad1.dpad_up){
            Teleop.RawForwards(1);
        }
        else if(gamepad1.dpad_down){
            Teleop.RawForwards(-1);
        }
        else if(gamepad1.dpad_right){
            Teleop.RawRight(1);
        }
        else if(gamepad1.dpad_left){
            Teleop.RawRight(-1);
        }
        else //STOP
        {
            Teleop.stopWheels();
        }
        /*else //STOP
        {
            if(!stopping)
            {
                Teleop.brake();
                stopping = true;
            }
            else
            {

            }
        }*/
    }


}