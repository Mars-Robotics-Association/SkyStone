package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TetrixChasisTeleop", group="Iterative Opmode")
public class TetrixChasisTeleop extends OpMode
{
    private JoystickCalc Jc = null;
    public UDC_Teleop Teleop = null;
    public ArmAttachment arm;


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
        telemetry.addData("start",5 );
        telemetry.update();
        gripperPosition = 0.5;

        Jc = new JoystickCalc(this);



        Teleop = new UDC_Teleop(this);
        Teleop.Init();

        arm = new ArmAttachment(this,1);
        arm.Init();
        //set speeds:
        telemetry.addData("endstart",5 );

        telemetry.update();
    }

    @Override
    public void start()
    {

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





        //switch between normal and slow modes

        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            Teleop.chooseDirection(Jc.rightStickX);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Teleop.turnRight();
        }


        else if(Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Teleop.turnLeft();
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
            Teleop.stopWheels();
        }


        if(gamepad1.left_bumper) { Teleop.fullSpeed(); }
        if(gamepad1.right_bumper) { Teleop.twoThirdsSpeed(); }
        if(gamepad1.left_trigger>0.2) { Teleop.halfSpeed(); }
        if(gamepad1.right_trigger>0.2) { Teleop.thirdSpeed(); }


        if(gamepad2.x){
            //pick up stone
        }
        else if (gamepad2.y){
            //put down stone
        }

        if(gamepad2.dpad_up) {
            arm.LiftUp();}
        else if(gamepad2.dpad_down) {
            arm.LiftDown();}
        else {
            arm.LiftStopVertical();
        }
        if(gamepad2.dpad_left)
        {arm.LiftLeft();}

        else if (gamepad1.dpad_right)
        { arm.LiftRight();}

        else{ arm.LiftStopHorizontal(); }





        if(gamepad2.left_stick_x>JoystickThreshold){
            Teleop.gripperRight();
        }
        else if(gamepad2.left_stick_x<JoystickThreshold){
            Teleop.gripperLeft();
        }

        if(gamepad2.left_bumper){
            Teleop.gripperOpenLeft();
        }
        if(gamepad2.right_bumper){
            Teleop.gripperOpenRight();
        }
        if(gamepad2.left_trigger>0.2){
            Teleop.closeGripper();
        }
        if(gamepad2.right_trigger>0.2){
            Teleop.openGripper();
        }
        telemetry.update();
    }
}
