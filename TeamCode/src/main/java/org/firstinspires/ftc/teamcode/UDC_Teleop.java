package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="UDC_Teleop", group="Iterative Opmode")
public class UDC_Teleop extends OpMode
{
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;

    private double BaseDriveSpeedMultiplier = 1;
    private double BaseTurnSpeedMultiplier = 0.4;
    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;
    private double gripperPosition = 0.5;

    private double JoystickThreshold = 0.2;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;

    public Gripper gripper;
    public ArmAttachment arm;


    @Override
    public void init()
    {
        telemetry.addData("start",5 );
        telemetry.update();


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
        telemetry.addData("endstart",5 );

        telemetry.update();
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
        if(gamepad2.x)
        {
            Bot.OffsetGyro();
        }





        //switch between normal and slow modes
        if(gamepad1.a)
        {
                DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier;


        }
        if(gamepad1.b)
        {

                DriveSpeedMultiplier = BaseDriveSpeedMultiplier/2;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier/2;

        }
        if(gamepad1.y)
        {

            DriveSpeedMultiplier = BaseDriveSpeedMultiplier/4;
            TurnSpeedMultiplier = BaseTurnSpeedMultiplier/4;

        }
        if(gamepad1.x)
        {

            DriveSpeedMultiplier = BaseDriveSpeedMultiplier/8;
            TurnSpeedMultiplier = BaseTurnSpeedMultiplier/8;

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
        if(gamepad2.left_bumper){
            gripper.GripperClose();
        }
        if(gamepad2.right_bumper){
            gripper.GripperOpen();
        }
        if(gamepad2.right_trigger == 1.0){
            gripper.GripperOpenRight();
            while(gamepad2.right_trigger == 1.0){
            }
            gripper.GripperCloseRight();
        }
        if(gamepad2.left_trigger == 1.0){
            gripper.GripperOpenLeft();
            while(gamepad2.left_trigger == 1.0){
            }
            gripper.GripperCloseLeft();
        }

        if(gamepad2.dpad_up) {
            arm.LiftUp();}
        else if(gamepad2.dpad_down) {
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

        if(gamepad2.a){
            arm.PickUpStone();
        }
        if(gamepad2.b){
            arm.PutDownStone();
        }







        if(gamepad2.dpad_left) {
            arm.LiftLeft();}
        else if (gamepad1.dpad_right) {
            arm.LiftRight();}
        else{
            arm.LiftStopHorizontal();
    }

        if(FirstRun){
            for(int i = 0; i<4; i++){

            }
        }
        for(int i = 0; i<4; i++){
            if(MaxMotorPositions[i]<Bot.GetMotorPositions()[i]){
                MaxMotorPositions[i]=Bot.GetMotorPositions()[i];
            }
        }




        telemetry.addData("FL total clicks",TotalMotorClicks[0]);
        telemetry.addData("FR total clicks",TotalMotorClicks[1]);
        telemetry.addData("RL total clicks",TotalMotorClicks[2]);
        telemetry.addData("RR total clicks",TotalMotorClicks[3]);
        telemetry.addData("Max encoder clicks",MaxMotorPositions[0]);
        telemetry.addData("Current Motor Position",Bot.GetMotorPositions()[0]);
        FirstRun=false;
        telemetry.update();
    }
}
