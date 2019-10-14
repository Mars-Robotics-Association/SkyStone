package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="UDC_Teleop", group="Iterative Opmode")
public class UDC_Teleop extends OpMode
{
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;

    private double BaseDriveSpeedMultiplier = 1;
    private double BaseTurnSpeedMultiplier = 0.4;
    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;
    private boolean normalMode = true;
    private double gripperPosition = 0.5;

    private double JoystickThreshold = 0.2;

    public Gripper gripper;
    public ArmAttachment arm;


    @Override
    public void init()
    {
        gripperPosition = 0.5;
        Jc = new JoystickCalc(this);
        Bot = new SkyStoneBot(this);
        Bot.Init();
        gripper = new Gripper(this);
        gripper.Init();
        arm = new ArmAttachment(this);
        arm.Init();
        //set speeds:
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier;
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

        //calculate the absolute value of the right x for turn speed
        double turnSpeed = Math.abs(Jc.rightStickX);

        //Reset Gyro if needed
        if(Jc.xButton)
        {
            Bot.OffsetGyro();
        }

        //switch between normal and slow mode
        if(Jc.yButton)
        {
            if(normalMode)
            {
                DriveSpeedMultiplier = BaseDriveSpeedMultiplier/2;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier/2;
                normalMode = false;
            }

        }
        if(Jc.bButton)
        {
            if(!normalMode)
            {
                DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier;
                normalMode = true;
            }
        }

        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            //if we need to turn while moving, choose direction
            boolean turnRight = false;
            if (Jc.rightStickX > JoystickThreshold)
            {
                turnRight = true;
            }
            if (Jc.rightStickX <= JoystickThreshold)
            {
                turnRight = false;
            }

            //Make robot move at the angle of the left joystick at the determined speed while applying a turn to the value of the right joystick
            Bot.MoveAtAngleTurning(Jc.leftStickBaring, DriveSpeedMultiplier * Jc.leftStickPower, turnRight, turnSpeed*TurnSpeedMultiplier);
            telemetry.addData("Moving", true);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Bot.RawTurn(true, turnSpeed*TurnSpeedMultiplier);
        }

        else if(Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Bot.RawTurn(false, turnSpeed*TurnSpeedMultiplier);
        }

        /*if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            Bot.MoveAtAngle(Jc.leftStickBaring, DriveSpeedMultiplier * Jc.leftStickPower);
            telemetry.addData("Moving", true);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Bot.RawTurn(true, turnSpeed*TurnSpeedMultiplier);
        }

        else if(Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Bot.RawTurn(false, turnSpeed*TurnSpeedMultiplier);
        }*/

        else //STOP
        {
            Bot.StopMotors();
        }
        if(gamepad1.left_bumper){
            gripper.GripperClose();
        }
        if(gamepad1.right_bumper){
            gripper.GripperOpen();
        }

        if(gamepad1.dpad_up) {
            arm.LiftUp();}
        else if(gamepad1.dpad_down) {
                arm.LiftDown();}
        else {
            arm.LiftStopVertical();
        }

        if(gamepad2.dpad_left) {
            gripperPosition+=0.005;
            gripper.GripperRotatePosition(gripperPosition);
        }

        if(gamepad2.dpad_right) {
            gripperPosition -= 0.005;
            gripper.GripperRotatePosition(gripperPosition);
        }
        if(gripperPosition>1){ gripperPosition = 1;}
        if(gripperPosition<0){gripperPosition=0;}







        if(gamepad1.dpad_left) {
            arm.LiftLeft();}
         else if (gamepad1.dpad_right) {
            arm.LiftRight();}
        else{
            arm.LiftStopHorizontal();
    }







        //update telemetry
        telemetry.addData("Gyro Offset", Bot.GetGyroOffset());
        telemetry.addData("Gyro Final Rot", Bot.GetFinalGyro());
        telemetry.addData("Left Baring", Jc.leftStickBaring);
        telemetry.addData("Left Power", Jc.leftStickPower);
        telemetry.addData("Right X", Jc.rightStickX);
        telemetry.addData("Right Y", Jc.rightStickY);
        telemetry.addData("Controller ", gamepad1.left_stick_x);
        telemetry.update();

    }
}
