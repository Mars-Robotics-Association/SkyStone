package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CustomChasisTeleop", group="Iterative Opmode")
public class CustomChasisTeleop extends OpMode
{
    private JoystickCalc Jc = null;
    public UDC_Teleop Teleop = null;
    public ArmAttachmentCustom arm;
    Gripper gripper;

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

        Jc = new JoystickCalc(this);

        Teleop = new UDC_Teleop(this);
        Teleop.Init();

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        gripper = new Gripper(this);
        gripper.Init();
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
        if(gamepad1.right_bumper) { Teleop.twoThirdsSpeed(); }
        if(gamepad1.left_trigger>0.2) { Teleop.halfSpeed(); }
        if(gamepad1.right_trigger>0.2) { Teleop.thirdSpeed(); }

        if(gamepad2.x)
        {
            //pick up stone
        }
        else if (gamepad2.y)
        {
            //put down stone
        }

        if(gamepad2.left_stick_y>JoystickThreshold){
            Teleop.FoundationGrab(gamepad2.left_stick_y);
        }
        else if(gamepad2.left_stick_y<-JoystickThreshold){
            Teleop.FoundationGrab(gamepad2.left_stick_y);
        }

        ManageArmMovement();
        ManageGripperMovement();

        telemetry.update();
    }

    public void ManageDriveMovement()//Manages general drive input
    {
        if(Jc.leftStickPower > JoystickThreshold) //Move
        {
            Teleop.chooseDirection(Jc.rightStickX, Jc.leftStickBaring, Jc.leftStickPower);
        }

        else if(Jc.rightStickX > JoystickThreshold) //Turn Right
        {
            Teleop.turnRight();
        }


        else if(Jc.rightStickX < -JoystickThreshold) //Turn Left
        {
            Teleop.turnLeft();
        }

        else //STOP
        {
            Teleop.stopWheels();
        }
    }

    public void ManageArmMovement()//Manages the Arm/Lift
    {
        /*if(gamepad2.b)//turn the wheel intake on
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

    public void ManageGripperMovement()//Manages the Gripper
    {
        /*if(gamepad2.left_stick_x>JoystickThreshold)//rotate the gripper right
        {
            gripperRight();
        }
        else if(gamepad2.left_stick_x<JoystickThreshold)//rotate the gripper left
        {
            gripperLeft();
        }*/
        if(gamepad2.left_bumper)//open the gripper
        {
            openGripper();
        }
        if(gamepad2.right_bumper)//close the gripper
        {
            closeGripper();
        }
        if(gamepad2.left_trigger>0.2)//open the left side of the gripper
        {
            gripperOpenLeft();
        }
        if(gamepad2.right_trigger>0.2)//open the right side of the gripper
        {
            gripperOpenRight();
        }
    }

    //Gripper Management Methods
    public void closeGripper(){
        gripper.GripperClose(2);
    }
    public void openGripper(){
        gripper.GripperOpen(2);
    }

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
        gripper.GripperOpenLeft(2);
    }
    public void gripperOpenRight(){
        gripper.GripperOpenRight(2);
    }
    public void gripperCloseLeft(){gripper.GripperCloseLeft(2);}
    public void gripperCloseRight(){gripper.GripperCloseRight(2);}


}