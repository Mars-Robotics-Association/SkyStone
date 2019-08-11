package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name="MarsTeleop", group="Iterative Opmode")
//@Disabled

public class MarsTeleop extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor LeftRear = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
    private DcMotor LeftFront = null;
    private DcMotor RightRear = null;
    private DcMotor RightFront = null;
    private DcMotor Bucket = null;
    private DcMotor Collector = null;

    //private Servo ClawA = null;
    //private Servo ClawB = null;
    // private NormalizedColorSensor colorSensor = nul
    double armrun1=0;
    double armrun2=0;
    double a;
    double b;
    double c;
    double d;
    double LiftClicks;
    boolean xButton;
    boolean yButton;
    boolean aButton;
    boolean bButton;
    boolean aa;
    boolean bb;
    boolean folded = true;
    boolean colorLight = false;
    static final double MAX_SPEED = 0.75;
    static final double TURN_SPEED = 0.5;
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

        LeftRear = hardwareMap.dcMotor.get("LeftRear");
        LeftFront = hardwareMap.dcMotor.get("LeftFront");
        RightFront = hardwareMap.dcMotor.get("RightFront");
        RightRear = hardwareMap.dcMotor.get("RightRear");

        Bucket = hardwareMap.dcMotor.get("Bucket");
        Collector=hardwareMap.dcMotor.get("Collector");
        // ClawA = hardwareMap.servo.get("ClawA");
        // ClawB = hardwareMap.servo.get("ClawB");
        // colorSensor = hardwareMap.get(NormalizedColorSensor.class, "ColorSensor");
       //Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       //Lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        // LiftClicks=LIFT_STARTING;
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

        runtime.reset();

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
        b = gamepad1.left_stick_x;//controls rear and front  motors
        c = gamepad1.right_stick_x;// controls turning
        d = gamepad1.right_stick_y;
        xButton = gamepad1.x;
        yButton = gamepad1.y;
        bButton = gamepad1.b;
        aButton = gamepad1.a;
        //x = gamepad2.x;
        //y = gamepad2.y;
        //aa = gamepad2.left_bumper;
        //bb = gamepad2.right_bumper;
        double threshold = 0.05;

        //telemetry.addData("Right",Right.getCurrentPosition());
        //telemetry.addData("Rear", Rear.getCurrentPosition());
        //telemetry.addData("Front",Right.getCurrentPosition());
        //telemetry.addData("Left",Left.getCurrentPosition());

        if(xButton || yButton || bButton || aButton) {
            //Buttons for motor testing
            if (xButton) {
                LeftFront.setPower(MAX_SPEED);
            }
            if (yButton) {
                RightFront.setPower(MAX_SPEED);
            }
            if (bButton) {
                RightRear.setPower(MAX_SPEED);
            }
            if (aButton) {
                LeftRear.setPower(MAX_SPEED);
            }
        }

        else if (c < -threshold) {
            LeftRear.setPower(c * c * TURN_SPEED);
            LeftFront.setPower(c * c * TURN_SPEED);
            RightFront.setPower(-c * -c * TURN_SPEED);
            RightRear.setPower(-c * -c * TURN_SPEED);
        } else if (c > threshold) {
            LeftRear.setPower(c * -c * TURN_SPEED);
            LeftFront.setPower(c * -c * TURN_SPEED);
            RightFront.setPower(-c * c * TURN_SPEED);
            RightRear.setPower(-c * c * TURN_SPEED);
        }


        else {
            if (a > threshold) {
                //telemetry.addData("gp1_lstick_y", "value %f", a);

                //Rear.setPower(0);
                //Front.setPo no.wer(0);
                RightFront.setPower(a * -a * MAX_SPEED);
                LeftFront.setPower(-a * -a * MAX_SPEED);
                LeftRear.setPower(-a * -a * MAX_SPEED);
                RightRear.setPower(a * -a * MAX_SPEED);
            } else if (a < -threshold) {
                RightFront.setPower(-a * -a * MAX_SPEED);
                LeftFront.setPower(a * -a * MAX_SPEED);
                LeftRear.setPower(a * -a * MAX_SPEED);
                RightRear.setPower(-a * -a * MAX_SPEED);
            }
            else if (b > threshold) {
                RightFront.setPower(-b * b * MAX_SPEED);
                LeftFront.setPower(-b * b * MAX_SPEED);
                LeftRear.setPower(b * b * MAX_SPEED);
                RightRear.setPower(b * b * MAX_SPEED);

            } else if (b < -threshold) {
                RightFront.setPower(-b * -b * MAX_SPEED);
                LeftFront.setPower(-b * -b * MAX_SPEED);
                LeftRear.setPower(b * -b * MAX_SPEED);
                RightRear.setPower(b * -b * MAX_SPEED);
            } else {
                RightFront.setPower(0);
                LeftFront.setPower(0);
                LeftRear.setPower(0);
                RightRear.setPower(0);
            }
        }

        telemetry.addData("RightRear pos ",  RightRear.getCurrentPosition());
        telemetry.addData("RightFront pos ", RightFront.getCurrentPosition());
        telemetry.addData("LeftRear pos ",  LeftRear.getCurrentPosition());
        telemetry.addData("LeftFront pos ", LeftFront.getCurrentPosition());

        telemetry.update();

        if (gamepad1.dpad_down)
        {

           Bucket.setPower(0.6);

        }

        else if (gamepad1.dpad_up)
        {

            Bucket.setPower(-0.6);

        }
        else {
            Bucket.setPower(0);
        }

        if(gamepad1.a){
            Collector.setPower(0.4);

        }
        else if(gamepad1.b){
            Collector.setPower(-0.4);

        }
        else{
            Collector.setPower(0);

        }
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