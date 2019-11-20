package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CustomChasisTeleop", group="Iterative Opmode")
public class CustomChasisTeleop extends OpMode
{
    private JoystickCalc Jc = null;
    private SkyStoneBot Bot = null;
    public UDC_Teleop Teleop = null;

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
        Teleop = new UDC_Teleop(this);

        //set speeds:
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
        if(gamepad1.x)
        {
            Teleop.gyroOffset();
        }





        //switch between normal and slow modes
        if(gamepad1.a)
        {
                Teleop.fullSpeed();


        }
        if(gamepad1.b)
        {

                Teleop.halfSpeed();

        }
        if(gamepad1.y)
        {

            Teleop.forthSpeed();

        }

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
            Teleop.leftTurn();
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
        if(gamepad2.left_bumper){
            Teleop.closeGripper();
        }
        if(gamepad2.right_bumper){
            Teleop.openGripper();
        }

        if(gamepad2.right_trigger == 1.0)
        {
            gripper.GripperOpenRight();
            do
            {
                gripper.GripperOpenRight();
            }
            while(gamepad2.right_trigger == 1.0);
            gripper.GripperCloseRight();
        }
        if(gamepad2.left_trigger == 1.0) 
        {
            gripper.GripperOpenLeft();
            do
            {
                gripper.GripperOpenLeft();
            }
            while(gamepad2.left_trigger == 1.0);
            gripper.GripperCloseLeft();
        }

        if(gamepad2.dpad_up) {
            Teleop.upLift();}
        else if(gamepad2.dpad_down) {
            Teleop.downLift();}
        else {
            Teleop.stopLift();
        }

        if(gamepad2.dpad_left) {
            Teleop.gripperLeft();
        }

        if(gamepad2.dpad_right) {
           Teleop.gripperRight();
        }
        if(gripperPosition>1){ Teleop.gripper1();}
        if(gripperPosition<0){Teleop.gripper0();}

        if(gamepad2.a){
            Teleop.stoneUp();
        }
        if(gamepad2.b){
            Teleop.stoneDown();
        }







        if(gamepad2.dpad_left) {
            Teleop.leftLift();}
        else if (gamepad1.dpad_right) {
            Teleop.rightLift();}
        else{
           Teleop.stopLifth();
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
