package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;


@TeleOp(name="teleop", group="Iterative Opmode")
//@Disabled

public class teleop extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor Rear = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
    private DcMotor Front = null;
    private DcMotor Left = null;
    private DcMotor Right = null;
    public DcMotor Lift = null;
    //private Servo ClawA = null;
    //private Servo ClawB = null;
    private DcMotor Collector;
    // private NormalizedColorSensor colorSensor = nul
    private Servo servo90;
    private CRServo servo180;
    private DcMotor ArmMotorUpDown;
    private DcMotor ArmMotorRetract;
    private DigitalChannel upperGate;
    private DigitalChannel lowerGate;
    private DigitalChannel upperArmGate;
    private DigitalChannel lowerArmGate;
    double armrun1=0;
    double armrun2=0;
    double a;
    double b;
    double c;
    double d;
    double LiftClicks;
    boolean x;
    boolean y;
    boolean aa;
    boolean bb;
    boolean folded = true;
    boolean colorLight = false;
    static final double MAX_SPEED = 0.75;
    static final double TURN_SPEED = 0.25;
    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 0.66;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double LINEAR_FUDGE = 1.6;
    static final double ANGULAR_FUDGE = 2.0;
    static final double DRIVE_SPEED = 0.6;
    static final double RAMP_SPEED = 0.5;
    static final double DEGREES_TO_INCHES = 0.1;
    static final long CMDSLEEP = 100;
    static final double LIFT_INTERVAL = COUNTS_PER_MOTOR_REV*3.9;
    static final double LIFT_THRESHOLD = COUNTS_PER_INCH*2.5;
    //double LIFT_STARTING = Lift.getCurrentPosition();
    double LIFT_POWER = 1;
    boolean upperGateT;
    boolean lowerGateT;


//    public boolean

  // @Override
    public void init() {

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        Rear = hardwareMap.dcMotor.get("Rear");
        Front = hardwareMap.dcMotor.get("Front");

        Left = hardwareMap.dcMotor.get("Left");
        Right = hardwareMap.dcMotor.get("Right");
        Lift = hardwareMap.dcMotor.get("Lift");
        Collector = hardwareMap.dcMotor.get("Collector");
        servo90 = hardwareMap.servo.get("servo90");
        servo180 = hardwareMap.crservo.get("servo180");
        ArmMotorUpDown = hardwareMap.dcMotor.get("ArmMotorUpDown");
        ArmMotorRetract = hardwareMap.dcMotor.get("ArmMotorRetract");
        // ClawA = hardwareMap.servo.get("ClawA");
        // ClawB = hardwareMap.servo.get("ClawB");
        // colorSensor = hardwareMap.get(NormalizedColorSensor.class, "ColorSensor");
       //Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       //Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       upperGate = hardwareMap.get(DigitalChannel.class, "upperGate");
       lowerGate = hardwareMap.get(DigitalChannel.class, "lowerGate");
       upperGate.setMode(DigitalChannel.Mode.INPUT);
       lowerGate.setMode(DigitalChannel.Mode.INPUT);
       upperArmGate = hardwareMap.get(DigitalChannel.class, "upperArmGate");
        lowerArmGate = hardwareMap.get(DigitalChannel.class, "lowerArmGate");
        upperArmGate.setMode(DigitalChannel.Mode.INPUT);
        lowerArmGate.setMode(DigitalChannel.Mode.INPUT);

        // LiftClicks=LIFT_STARTING;
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

        runtime.reset();
        servo90.setPosition(0);

    }

  /**  public void encoderDrive(double speed, double inches, double timeoutS) {

        int newTarget;
        double Inches = inches*1120;
        Lift.setDirection(DcMotor.Direction.FORWARD);
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        newTarget = Lift.getCurrentPosition() + (int)(Inches);
        Lift.setTargetPosition(newTarget);
        telemetry.addData("active?", "yes");
        telemetry.update();
        Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //runtime.reset();
        Lift.setPower(Math.abs(speed));
        //hile (Lift.isBusy()) {
        //Thread.sleep(5000);
        //}
        //Lift.setPower(0);
        //Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }*/
  //DigitalChannel digitalTouch;  // Hardware Device Object

    @Override
    public void loop() {


        //telemetry.addData("LIFT POWER:", LIFT_POWER);
        //telemetry.update();
        a = gamepad1.left_stick_y;//controls left and right  motors
        b = -gamepad1.left_stick_x;//controls rear and front  motors
        c = gamepad1.right_stick_x;// controls turning
        d = gamepad2.right_stick_y;
        x = gamepad2.x;
        y = gamepad2.y;
        //aa = gamepad2.left_bumper;
        //bb = gamepad2.right_bumper;
        double threshold = 0.05;
        //telemetry.addData("Right",Right.getCurrentPosition());
        //telemetry.addData("Rear", Rear.getCurrentPosition());
        //telemetry.addData("Front",Right.getCurrentPosition());
        //telemetry.addData("Left",Left.getCurrentPosition());
        if (c < -threshold) {
            Rear.setPower(c * c * TURN_SPEED);
            Front.setPower(c * c * TURN_SPEED);
            Left.setPower(-c * -c * TURN_SPEED);
            Right.setPower(-c * -c * TURN_SPEED);
        } else if (c > threshold) {
            Rear.setPower(c * -c * TURN_SPEED);
            Front.setPower(c * -c * TURN_SPEED);
            Left.setPower(-c * c * TURN_SPEED);
            Right.setPower(-c * c * TURN_SPEED);
        }
        else if(gamepad1.dpad_up){
            Front.setPower(DRIVE_SPEED);
            Right.setPower(DRIVE_SPEED);
            Left.setPower(-DRIVE_SPEED);
            Rear.setPower(-DRIVE_SPEED);
        }
        else if(gamepad1.dpad_down){
            Front.setPower(-DRIVE_SPEED);
            Right.setPower(-DRIVE_SPEED);
            Left.setPower(DRIVE_SPEED);
            Rear.setPower(DRIVE_SPEED);
        }else if(gamepad1.dpad_left){
            Front.setPower(DRIVE_SPEED);
            Right.setPower(-DRIVE_SPEED);
            Left.setPower(DRIVE_SPEED);
            Rear.setPower(-DRIVE_SPEED);
        }else if(gamepad1.dpad_right){
            Front.setPower(-DRIVE_SPEED);
            Right.setPower(DRIVE_SPEED);
            Left.setPower(-DRIVE_SPEED);
            Rear.setPower(DRIVE_SPEED);
        }

        else {
            if (a > threshold) {
                //telemetry.addData("gp1_lstick_y", "value %f", a);

                //Rear.setPower(0);
                //Front.setPo no.wer(0);
                Left.setPower(-a * -a * MAX_SPEED);
                Right.setPower(a * -a * MAX_SPEED);
            } else if (a < -threshold) {
                //Rear.setPower(0);
                //Front.setPower(0);
                Left.setPower(-a * a * MAX_SPEED);
                Right.setPower(a * a * MAX_SPEED);
            } else {
                Left.setPower(0);
                Right.setPower(0);
            }
            if (b > threshold) {
                Rear.setPower(-b * b * MAX_SPEED);
                Front.setPower(b * b * MAX_SPEED);
                //Left.setPower (0);
                //Right.setPower(0);
            } else if (b < -threshold) {
                Rear.setPower(-b * -b * MAX_SPEED);
                Front.setPower(b * -b * MAX_SPEED);
                //Left.setPower(0);
                //Right.setPower(0);
            } else {
                Rear.setPower(0);
                Front.setPower(0);
            }
        }
        telemetry.addData("up",gamepad1.dpad_up);
        telemetry.addData("down",gamepad1.dpad_down);
        telemetry.addData("right",gamepad1.dpad_right);
        telemetry.addData("left",gamepad1.dpad_left);

       /* if(gamepad2.x){
            LIFT_POWER=LIFT_POWER/2;
        }
        if(gamepad2.y){
            LIFT_POWER=LIFT_POWER*2;
        }*/

        if (gamepad2.dpad_down)
        {
            servo180.setPower(-1);
           /* runtime.reset();

           while (lowerGate.getState() && runtime.seconds() < 2) {
            Lift.setPower(1);
            }*/
           //Lift.setPower(0);
            //encoderDrive(LIFT_POWER, LIFT_INTERVAL-Lift.getCurrentPosition(),5);
            //LiftClicks+=LIFT_INTERVAL;

        }

        else if (gamepad2.dpad_up)
        {

            servo180.setPower(1);
            /*runtime.reset();

            while (upperGate.getState() &&runtime.seconds() < 2) {
                Lift.setPower(-1);

            }*/
            //}
            //Lift.setPower(0);

            // encoderDrive(LIFT_POWER, -Lift.getCurrentPosition(),5);
            //Lift.getCurrentPosition()-=LIFT_INTERVAL;

        }
        else {
            servo180.setPower(0);

        }
        if(!lowerGate.getState()){
            Lift.setPower(0);
        }
        if(!upperGate.getState()){
            Lift.setPower(0);
        }
        /*telemetry.addData("Lift.isBusy", Lift.isBusy());
        telemetry.addData("upper gate", upperGate.getState());
        telemetry.update();*/
        if (gamepad2.x && !(Lift.isBusy())) {

            runtime.reset();//double lifttemp = Lift.getCurrentPosition();
            //while (gamepad2.left_stick_y < -threshold && lowerGate.getState()/*&&Lift.getCurrentPosition()>-COUNTS_PER_MOTOR_REV*0.1*/) {
               if(lowerGate.getState()) {
                   Lift.setPower(LIFT_POWER);
               }
                //  lifttemp = Lift.getCurrentPosition()+lifttemp;
            //}
            //Lift.setPower(0);
            //     Lift.set
            //LiftClicks+=lifttemp;
        }
        else if (gamepad2.y && !(Lift.isBusy())) {
            runtime.reset();
            //while (gamepad2.left_stick_y > threshold && upperGate.getState()/*Lift.getCurrentPosition()<COUNTS_PER_MOTOR_REV*4*/) {
            if(upperGate.getState()) {
                Lift.setPower(-LIFT_POWER);
            }
            // /}
            //Lift.setPower(0);
        }
        else{
            Lift.setPower(0);
        }





        // else if(){
        //  Lift.setPower(0);
        //}
        if (gamepad2.left_bumper)

        {
            Collector.setPower(1);
        } else if (gamepad2.right_bumper)

        {
            Collector.setPower(-1);
        } else

        {
            Collector.setPower(0);
        }

        if (gamepad1.left_bumper)

        {
            servo90.setPosition(0);
        }
        if (gamepad1.right_bumper)

        {
            servo90.setPosition(0.5);
        }




        if (gamepad2.right_stick_y > threshold)

        {
            ArmMotorUpDown.setPower(1);
            armrun1=-0.25;
        }
        else if (gamepad2.right_stick_y < -threshold) {
            ArmMotorUpDown.setPower(-1);
            armrun1=0.25;
        }
        else if (armrun1==0.25||armrun1==-0.25) {
            runtime.reset();
            while (runtime.seconds()<0.05) {
                ArmMotorUpDown.setPower(armrun1);
            }
            armrun1=0;
        }
        else{
            ArmMotorUpDown.setPower(0);
        }


        if (gamepad2.left_stick_y>threshold/*&&!lowerArmGate.getState()*/) {
            ArmMotorRetract.setPower(0.5 );
            armrun2=-0.25;
        }

        else if (gamepad2.left_stick_y<-threshold/*&&!upperArmGate.getState()*/) {
            ArmMotorRetract.setPower(-1);
            armrun2=0.25;
        }
        else if(armrun2==0.25||armrun2==-0.25){
            runtime.reset();
            while (runtime.seconds()<0.05) {
                ArmMotorRetract.setPower(armrun2);
            }
            armrun2=0;
        }
        else {
            ArmMotorRetract.setPower(0);
        }

/*    if(gamepad2.x){
        double speed= DRIVE_SPEED;
        double inches=1;
        double timeoutS;
            int newTarget;
            double Inches = inches*1120;
            RotationalMotor.setDirection(DcMotor.Direction.FORWARD);
            RotationalMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RotationalMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            newTarget = RotationalMotor.getCurrentPosition() + (int)(Inches);
            RotationalMotor.setTargetPosition(newTarget);

            RotationalMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            runtime.reset();
            RotationalMotor.setPower(Math.abs(speed));
            while (Lift.isBusy()) {
            }
            Lift.setPower(0);
            Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }*/
            /**   if(folded)

             {
             telemetry.addData("clamps", "folded");
             }
             else

             {
             double leftTrigger = gamepad2.left_trigger;
             telemetry.addData("left trigger", "value %f", leftTrigger);
             double leftClose = 0.0;//0.145, 0.2,v  correct 0.0;
             double leftOpen = 0.60;//0.60, correct 0.6;
             double leftCmd = (1.0 - leftTrigger) * leftClose + leftTrigger * leftOpen;
             //      ClawB.setPosition( leftCmd);
             telemetry.addData("left claw", "value %f", leftCmd);
             double rightTrigger = gamepad2.right_trigger;
             telemetry.addData("right trigger", "value %f", rightTrigger);
             double rightClose = 1;//0.6, 0.7, correct 1;
             double rightOpen = 0.40;//0.2, correct 0.4;
             double rightCmd = (1.0 - rightTrigger) * rightClose + rightTrigger * rightOpen;
             //        ClawA.setPosition(rightCmd);
             telemetry.addData("right claw", "value %f", rightCmd);
             }*/
            telemetry.update();
        }

/*
        public void encoderDrive ( double speed, double rotations, double timeoutS){
            int newLiftTarget;
            double LiftRotations = rotations;

            // Ensure that the opmode is still active
            if (true) {

                // Determine new target position, and pass to motor controller
                newLiftTarget = Lift.getCurrentPosition() + (int) (LiftRotations);
                Lift.setTargetPosition(newLiftTarget);

                // Turn On RUN_TO_POSITION
                Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

                // reset the timeout time and start motion.
                runtime.reset();
                Lift.setPower(Math.abs(speed));

                // keep looping while we are still active, and there is time left, and both motors are running.
                while (
                        (runtime.seconds() < timeoutS + 1) &&
                                (Lift.isBusy())) {

                    // Display it for the driver.
                    // leftMotor.getCurrentPosition(),
                }

                // Stop all motion;
                Lift.setPower(0);

                // Turn off RUN_TO_POSITION
                Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            }
        }*/
    }