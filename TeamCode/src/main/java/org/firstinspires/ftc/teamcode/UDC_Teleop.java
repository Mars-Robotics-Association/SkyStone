package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class UDC_Teleop
{
     JoystickCalc Jc = null;
     SkyStoneBot Bot = null;
     Gripper gripper;

     double BaseDriveSpeedMultiplier = 1;
     double BaseTurnSpeedMultiplier = 0.4;
     double DriveSpeedMultiplier = 0 ;
     double TurnSpeedMultiplier = 0;
     double gripperPosition = 0.5;

    private double JoystickThreshold = 0.2;
    double turnSpeed;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;

    OpMode opmode;

    public UDC_Teleop(OpMode thatopmode) {
        opmode = thatopmode;
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode);
        Bot.Init();

        gripper = new Gripper(opmode);
        gripper.Init();
    }

    public void Start(){Bot.Start();}

    public void Loop(){
        Bot.Loop();
    }

    public void UpdateTurnSpeed(double input){
    turnSpeed = input;
}

        //calculate the absolute value of the right x for turn speed


    //Reset Gyro if needed

    public void gyroOffset()
        {
            Bot.OffsetGyro();
        }





        //switch between normal and slow modes
        public void fullSpeed() {
            DriveSpeedMultiplier = BaseDriveSpeedMultiplier;
            TurnSpeedMultiplier = BaseTurnSpeedMultiplier;

        }
        public void twoThirdsSpeed()
        {

        DriveSpeedMultiplier = BaseDriveSpeedMultiplier*2/3;
        TurnSpeedMultiplier = BaseTurnSpeedMultiplier*2/3;

        }

        public void halfSpeed()
        {

                DriveSpeedMultiplier = BaseDriveSpeedMultiplier/2;
                TurnSpeedMultiplier = BaseTurnSpeedMultiplier/2;

        }

        public void thirdSpeed(){
            DriveSpeedMultiplier = BaseDriveSpeedMultiplier/3;
            TurnSpeedMultiplier = BaseTurnSpeedMultiplier/3; }

        public void forthSpeed()
        {

            DriveSpeedMultiplier = BaseDriveSpeedMultiplier/4;
            TurnSpeedMultiplier = BaseTurnSpeedMultiplier/4;

        }

        public void chooseDirection(double rightStickX) //Move
        {
            //if we need to turn while moving, choose direction
            boolean turnRight = false;
            if (rightStickX > JoystickThreshold)
            {
                turnRight = true;
            }
            if (rightStickX <= JoystickThreshold)
            {
                turnRight = false;
            }

            //Make robot move at the angle of the left joystick at the determined speed while applying a turn to the value of the right joystick
            Bot.MoveAtAngleTurning(Jc.leftStickBaring, DriveSpeedMultiplier * Jc.leftStickPower, turnRight, turnSpeed*TurnSpeedMultiplier);
        }

        public void turnRight() //Turn Right
        {
            Bot.RawTurn(true, turnSpeed*TurnSpeedMultiplier);
        }


        public void turnLeft() //Turn Left
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

        public void stopWheels() //STOP
        {
            Bot.StopMotors();
        }
        public void closeGripper(){
            gripper.GripperClose();
        }
        public void openGripper(){
            gripper.GripperOpen();
        }

//        public void openGripperRight()
//        {
//            gripper.GripperOpenRight();
//            do
//            {
//                gripper.GripperOpenRight();
//            }
//            while(gamepad2.right_trigger == 1.0);
//            gripper.GripperCloseRight();
//        }
//        public void openGripperLeft()
//        {
//            gripper.GripperOpenLeft();
//            do
//            {
//                gripper.GripperOpenLeft();
//            }
//            while(gamepad2.left_trigger == 1.0);
//            gripper.GripperCloseLeft();
        //}



        public void gripperLeft() {
            gripperPosition+=0.005;
            gripper.GripperRotatePosition(gripperPosition);
        }

        public void gripperRight() {
            gripperPosition -= 0.005;
            gripper.GripperRotatePosition(gripperPosition);
        }
        public void gripper1(){ gripperPosition = 1;}
        public void gripper0(){gripperPosition=0;}

        public void gripperOpenLeft(){
            gripper.GripperOpenLeft();
        }
        public void gripperOpenRight(){
            gripper.GripperOpenRight();
        }







}
