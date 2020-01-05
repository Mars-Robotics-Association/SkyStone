package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="CustomChasisTeleop", group="Iterative Opmode")
public class CustomChasisTeleop extends OpMode
{
    private JoystickCalc Jc = null;
    public UDC_Teleop Teleop = null;
    public ArmAttachmentCustom arm = null;
    public FoundationGrabber grab = null;
    public GripperCustom gripper = null;



    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;

    private double JoystickThreshold = 0.2;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;
    boolean stopping = false;

    private RevTouchSensor armRetractStop;

    @Override
    public void init()
    {
        Jc = new JoystickCalc(this, 180);//was 180

        Teleop = new UDC_Teleop(this, false);
        Teleop.Init();

        arm = new ArmAttachmentCustom(this);
        arm.Init();

        gripper = new GripperCustom(this);
        gripper.Init();

        grab = new FoundationGrabber(this);
        grab.Init();

        //armRetractStop = hardwareMap.touchSensor.get("ArmRetractStop");
        armRetractStop = hardwareMap.get(RevTouchSensor.class, "ArmRetractStop");
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
        gripper.Loop();
        grab.Loop();
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
        if(gamepad1.right_bumper) { Teleop.threeFourthsSpeed(); }
        if(gamepad1.left_trigger>0.2) { Teleop.halfSpeed(); }
/*
        if(gamepad1.right_trigger>0.2) { Teleop.forthSpeed(); }
*/

        if(gamepad2.x)
        {
            //pick up stone
        }
        else if (gamepad2.y)
        {
            //put down stone
        }

<<<<<<< HEAD
        if(gamepad2.a)
        {

        }
        if (gamepad2.b)
        {

        }

        if(gamepad2.left_stick_y>JoystickThreshold){
=======
        /*if(gamepad2.left_stick_y>JoystickThreshold){
>>>>>>> master
            grab.FoundationGrab(gamepad2.left_stick_y);
        }
        if(gamepad2.left_stick_y<-JoystickThreshold){
            grab.FoundationGrab(gamepad2.left_stick_y);
        }*/
        if(gamepad1.dpad_up){
            int fleft = Teleop.getfleftudc();
            int fright = Teleop.getfrightudc();
            int rleft = Teleop.getrleftudc();
            int rright = Teleop.getrrightudc();
            int armval = arm.getarmval();
            telemetry.addData("front left wheel: ",fleft);
            telemetry.addData("front right wheel: ",fright);
            telemetry.addData("rear left wheel: ",rleft);
            telemetry.addData("rear right wheel: ",rright);
            telemetry.addData("arm vertical: ",armval);
            telemetry.update();
        }

        ManageArmMovement();
        ManageGripperMovement();

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
            Teleop.brake();
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

    public void ManageArmMovement()//Manages the Arm/Lift
    {
        if(gamepad2.right_trigger > 0.2)//turn the wheel intake on
        {
            arm.IntakeOn();
        }
        else if(gamepad2.left_trigger > 0.2)//turn the wheel intake on
        {
            arm.IntakeReverse();
        }
        else//turn the intake off
        {
            arm.IntakeOff();
        }

        if(gamepad2.right_stick_y > 0.4)//move lift up
        {
            arm.LiftUp();
        }
        else if(gamepad2.right_stick_y < -0.4)//move lift down
        {
            arm.LiftDown();
        }
        else//stop the arm from moving up or down
        {
            arm.LiftStopVertical();
        }

        if(gamepad2.left_stick_y > 0.4 && !armRetractStop.isPressed())////extend arm
        {
            arm.LiftRetract();
        }

        else if (gamepad2.left_stick_y < -0.4 )//retract arm
        {
            arm.LiftExtend();
        }

        else//stop the arm from moving left or right
        {
            arm.LiftStopHorizontal();
        }
    }

    public void ManageGripperMovement()//Manages the Gripper
    {
        if(gamepad2.dpad_right)//rotate the gripper right
        {
            gripper.GripperRotatePosition(-0.05);
        }
        else if(gamepad2.dpad_left)//rotate the gripper left
        {
            gripper.GripperRotatePosition(0.05);
        }
        if(gamepad2.left_bumper)//open the gripper
        {
            gripper.GripperClose();
        }
        if(gamepad2.right_bumper)//close the gripper
        {
            gripper.GripperOpen();
        }
        if(gamepad2.dpad_up)
        {
            grab.FoundationGrabUp();
        }
        if(gamepad2.dpad_down)
        {
            grab.FoundationGrabUp();
        }
    }
}