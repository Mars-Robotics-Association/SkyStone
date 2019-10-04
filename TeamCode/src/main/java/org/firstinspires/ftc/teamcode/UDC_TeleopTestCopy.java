package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="UDC_TeleopTest", group="Iterative Opmode")
public class UDC_TeleopTestCopy extends OpMode {
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;

    private double BaseDriveSpeedMultiplier = 1;
    private double BaseTurnSpeedMultiplier = 0.4;
    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;
    private boolean normalMode = true;

    private double JoystickThreshold = 0.2;

    @Override
    public void init() {
        Jc = new JoystickCalc(this);
        Bot = new SkyStoneBot(this);
        Bot.Init();

        //set speeds:
        DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier;
    }

    @Override
    public void start() {
        Bot.Start();
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up) {}
        Bot.Loop();
        //Update telemetry and get joystick input
        Jc.calculate();

        //calculate the absolute value of the right x for turn speed
        double turnSpeed = Math.abs(Jc.rightStickX);

        //Reset Gyro if needed
        if (Jc.xButton) {
            Bot.OffsetGyro();
        }

        //switch between normal and slow mode
        if (Jc.yButton) {
            if (normalMode) {
                DriveSpeedMultiplier = BaseDriveSpeedMultiplier / 2;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier / 2;
                normalMode = false;
            }

        }
        if (Jc.bButton) {
            if (!normalMode) {
                DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier;
                normalMode = true;
            }
        }

        if (Jc.leftStickPower > JoystickThreshold) //Move
        {
            //if we need to turn while moving, choose direction
            boolean turnRight = false;
            if (Jc.rightStickX > JoystickThreshold) {
                turnRight = true;
            }
            if (Jc.rightStickX <= JoystickThreshold) {
                turnRight = false;
            }

            //Make robot move at the angle of the left joystick at the determined speed while applying a turn to the value of the right joystick
            Bot.MoveAtAngleTurning(Jc.leftStickBaring, DriveSpeedMultiplier * Jc.leftStickPower, turnRight, turnSpeed * TurnSpeedMultiplier);
            telemetry.addData("Moving", true);
        } else if (Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Bot.RawTurn(true, turnSpeed * TurnSpeedMultiplier);
        } else if (Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Bot.RawTurn(false, turnSpeed * TurnSpeedMultiplier);
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

