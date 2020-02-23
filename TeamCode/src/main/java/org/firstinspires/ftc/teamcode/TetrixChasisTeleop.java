package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.File;

@TeleOp(name="TetrixChasisTeleop", group="Iterative Opmode")
public class TetrixChasisTeleop extends OpMode
{
    private JoystickCalc Jc = null;
    public UDC_Teleop Teleop = null;
    public ArmAttachmentTetrix arm = null;
    public GripperTetrix gripper = null;
    public FoundationGrabber grab = null;
    public CapstoneDeployer Deployer = null;



    private RevTouchSensor armUpStop;


    private double DriveSpeedMultiplier;
    private double TurnSpeedMultiplier;
    private double gripperPosition = 0.5;
    private double gripperUpDownPositionLeft = 0.5;
    private double gripperUpDownPositionRight = 0.5;

    private double JoystickThreshold = 0.2;

    private int[] MaxMotorPositions = {0,0,0,0};
    private int[] PreviousMotorPositions = {0,0,0,0};
    private int[] TotalMotorClicks = {0,0,0,0};
    boolean FirstRun = true;
    private File silverFile;
    private String soundPath = "/FIRST/blocks/sounds";


    @Override
    public void init()
    {

        File silverFile = new File("/sdcard" + soundPath + "/silver.wav");


        telemetry.addData("start",5 );
        telemetry.update();
        gripperPosition = 0.25;
        gripperUpDownPositionLeft = 0.25;
        gripperUpDownPositionRight = 0.75;

        Jc = new JoystickCalc(this, 0);

        Teleop = new UDC_Teleop(this, false);
        Teleop.Init();

        grab = new FoundationGrabber(this);
        grab.Init();

        arm = new ArmAttachmentTetrix(this);
        arm.Init();
        //set speeds:
        telemetry.addData("endstart",5 );

        telemetry.update();

        gripper = new GripperTetrix(this);
        gripper.Init();

        Deployer = new CapstoneDeployer(this);
        Deployer.Init();

        armUpStop = hardwareMap.get(RevTouchSensor.class, "ArmRetractStop");
    }
        // test
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
        if(gamepad1.dpad_down){
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, silverFile);

            telemetry.addData("playing sound","");
        }

        if(gamepad1.x)
        {
            Teleop.gyroOffset();
        }

        //lift test
        if(gamepad1.a)
        {

        }

        //lift test
        if(gamepad1.b)
        {

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
        if(gamepad1.right_bumper) { Teleop.halfSpeed();}
        if(gamepad1.left_trigger>0.2) { Teleop.quarterSpeed();  }
        if(gamepad1.right_trigger>0.2) {Teleop.brake(0.2); }


        if(gamepad2.x){
            //pick up stone
        }
        if (gamepad2.y){
            //put down stone
        }
        if(gamepad2.left_stick_y>0.5){
            grab.FoundationGrab(gamepad2.left_stick_y);
        }
        if(gamepad2.left_stick_y<-0.5){
            grab.FoundationGrab(gamepad2.left_stick_y);
        }
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


        arm.Loop();
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

    public void ManageArmMovement()//Manages the Arm
    {
        if(gamepad2.dpad_up&& !armUpStop.isPressed())//move lift up //&&!motorGate.isPressed()
        {
            arm.LiftUp();

        }
        if(gamepad2.dpad_down)//move lift down
        {
            arm.LiftDown();
        }
        if(!gamepad2.dpad_down && !gamepad2.dpad_up)//stop the arm from moving up or down
        {
            arm.LiftStopVertical();
        }

        if(gamepad2.dpad_left )////extend arm
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

    public void ManageGripperMovement()//Manages the GripperTetrix
    {
        if(gamepad2.right_stick_x>JoystickThreshold)//rotate the gripper right
        {
            gripperRight();
        }
        else if(gamepad2.right_stick_x<-JoystickThreshold)//rotate the gripper left
        {
            gripperLeft();
        }
        if(gamepad2.right_stick_x>JoystickThreshold)//rotate the gripper right
        {
            gripperRight();
        }
        else if(gamepad2.right_stick_x<-JoystickThreshold)//rotate the gripper left
        {
            gripperLeft();
        }
        if(gamepad2.left_bumper)//Open the gripper
        {
            openGripper();
        }
        if(gamepad2.right_bumper)//close the gripper
        {
            closeGripper();
        }
        if(gamepad2.left_trigger>0.2)//Open the left side of the gripper
        {
            gripperOpenLeft();
        }
        if(gamepad2.right_trigger>0.2)//Open the right side of the gripper
        {
            gripperOpenRight();
        }
        if(gamepad2.left_stick_y>JoystickThreshold){
            gripperUp();
        }
        if(gamepad2.left_stick_y<-JoystickThreshold){
            gripperDown();
        }
        if(gamepad1.dpad_right){
            Deployer.DeployCapstone();
        }
        else if(gamepad1.dpad_left){
            Deployer.RetractCapstone();
        }
    }

    //GripperTetrix Management Methods
    public void closeGripper(){
        gripper.GripperClose();
    }
    public void openGripper(){
        gripper.GripperOpen();
    }

    public void gripperLeft() {
        gripperPosition+=0.01;
        gripper.GripperRotatePosition(gripperPosition);
    }

    public void gripperRight() {
        gripperPosition -= 0.01;
        gripper.GripperRotatePosition(gripperPosition);
    }

    public void gripperUp() {
        gripperUpDownPositionLeft+=0.005;
        gripperUpDownPositionRight-=0.005;
        gripper.GripperRotateUpDown(gripperUpDownPositionLeft,gripperUpDownPositionRight);
    }

    public void gripperDown() {
        gripperUpDownPositionLeft -= 0.005;
        gripperUpDownPositionRight += 0.005;
        gripper.GripperRotateUpDown(gripperUpDownPositionLeft,gripperUpDownPositionRight);
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