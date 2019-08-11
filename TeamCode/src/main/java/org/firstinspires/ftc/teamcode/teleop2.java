package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


//@TeleOp(name="teleop2", group="Iterative Opmode")
//@Disabled

public class teleop2 extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor Rear = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
    private DcMotor Front = null;
    private DcMotor Left = null;
    private DcMotor Right = null;
    private DcMotor Lift = null;
    //private Servo ClawA = null;
    //private Servo ClawB = null;
    private DcMotor Collector;
    // private NormalizedColorSensor colorSensor = nul
    private Servo servo90;
    private Servo servo180;
    private DcMotor ArmMotor;
    private DcMotor RotationalMotor;
    double a;
    double b;
    double c;
    double d;
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
        servo180 = hardwareMap.servo.get("servo180");
        ArmMotor = hardwareMap.dcMotor.get("ArmMotor");
        RotationalMotor = hardwareMap.dcMotor.get("RotationalMotor");
        // ClawA = hardwareMap.servo.get("ClawA");
        // ClawB = hardwareMap.servo.get("ClawB");
        // colorSensor = hardwareMap.get(NormalizedColorSensor.class, "ColorSensor");
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

    @Override
    public void loop() {

        telemetry.addData("Status", "Running: " + runtime.toString());

        a = -gamepad1.left_stick_y;//controls left and right  motors
        b = -gamepad1.left_stick_x;//controls rear and front  motors
        c = -gamepad1.right_stick_x;// controls turning
        d = gamepad2.right_stick_y;
        x = gamepad2.x;
        y = gamepad2.y;
        //aa = gamepad2.left_bumper;
        //bb = gamepad2.right_bumper;
        double threshold = 0.05;

        if(c < -threshold)
        {
            telemetry.addData("gp1_rstick_x", "value %f", c);
            Rear.setPower(c * -c * TURN_SPEED);
            Front.setPower(c * -c * TURN_SPEED);
            Left.setPower(c * -c * TURN_SPEED);
            Right.setPower(c * -c * TURN_SPEED);
        } else if(c > threshold)
        {
            telemetry.addData("gp1_rstick_x", "value %f", c);
            Rear.setPower(c * c * TURN_SPEED);
            Front.setPower(c * c * TURN_SPEED);
            Left.setPower(c * c * TURN_SPEED);
            Right.setPower(c * c * TURN_SPEED);
        } else
        {
            if(a > threshold)
            {
                telemetry.addData("gp1_lstick_y", "value %f", a);
                //Rear.setPower(0);
                //Front.setPower(0);
                telemetry.addData("orientation",Left.getCurrentPosition() );

                Left.setPower(-a * -a * MAX_SPEED);
                Right.setPower(a * -a * MAX_SPEED);
            }
            else if(a < -threshold)
            {
                telemetry.addData("gp1_lstick_y", "value %f", a);
                //Rear.setPower(0);
                //Front.setPower(0);
                Left.setPower(-a * a * MAX_SPEED);
                Right.setPower(a * a * MAX_SPEED);
            }
            else
            {
                Left.setPower(0);
                Right.setPower(0);
            }
            if(b > threshold)
            {
                telemetry.addData("gp1_lstick_x", "value %f", b);
                Rear.setPower(-b * b * MAX_SPEED);
                Front.setPower(b * b * MAX_SPEED);
                //Left.setPower (0);
                //Right.setPower(0);
            } else if(b < -threshold)
            {
                telemetry.addData("gp1_lstick_x", "value %f", b);
                Rear.setPower(-b * -b * MAX_SPEED);
                Front.setPower(b * -b * MAX_SPEED);
                //Left.setPower(0);
                //Right.setPower(0);
            }
            else
            {
                Rear.setPower(0);
                Front.setPower(0);
            }
        }


        if(gamepad2.left_stick_y>threshold)

        {
            Lift.setPower(1);

        }
        else if(gamepad2.left_stick_y<-threshold){
            Lift.setPower(-1);
        }
        else{
            Lift.setPower(0);
        }



        if(gamepad2.left_bumper)

            {
        Collector.setPower(1);
    }

        else if(gamepad2.right_bumper)

        {
           Collector.setPower(-1);
        }
        else

        {
           Collector.setPower(0);
        }

        if(gamepad1.left_bumper)

            {
        servo90.setPosition(0);
    }
        if(gamepad1.right_bumper)

            {
        servo90.setPosition(0.5);
    }
        if(gamepad1.x)

            {
        servo180.setPosition(0);
    }
        if(gamepad1.y)

            {
        servo180.setPosition(1);
    }
        if(gamepad2.right_stick_y>threshold)

            {
        ArmMotor.setPower(0.5);
    }
    else if(gamepad2.right_stick_y<-threshold){
            ArmMotor.setPower(-0.5);
        }
        else

            {
        ArmMotor.setPower(0);
    }
    if (!gamepad2.a){
            RotationalMotor.setPower(0);
        }
        else if (!gamepad2.b){
            RotationalMotor.setPower(0);
        }

    if (gamepad2.a){
            RotationalMotor.setPower(1);
    }


        if (gamepad2.b){
            RotationalMotor.setPower(-1);
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
        }

}