package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name="demoTeleop", group="Iterative Opmode")
//@Disabled

public class MarsNewYearDemoTeleop extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor DriveL = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
    private DcMotor DriveR = null;
    private DcMotor ShooterL = null;
    private DcMotor ShooterR = null;
    private DcMotor Intake = null;
    private Servo  Gate= null;
    private Servo Feeder = null;
    // private NormalizedColorSensor colorSensor = null
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
    static final double TURN_SPEED = 0.60;
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
    double speed;

//    public boolean

    // @Override
    public void init() {

        DriveL = hardwareMap.dcMotor.get("left_drive");
        DriveR = hardwareMap.dcMotor.get("right_drive");

        ShooterL = hardwareMap.dcMotor.get("shooterl");
        ShooterR = hardwareMap.dcMotor.get("shooterr");
        Intake = hardwareMap.dcMotor.get("intake_drive                                                                                                                                                                                  ");
        Feeder = hardwareMap.servo.get("feeder");
        Gate = hardwareMap.servo.get("stopper");

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

        a = gamepad1.left_stick_y;//controls left and right  motors
        b = gamepad1.left_stick_x;//controls rear and front  motors
        c = -gamepad1.right_stick_x;// controls turning
        d = gamepad2.right_stick_y;
        x = gamepad2.x;
        y = gamepad2.y;
        //aa = gamepad2.left_bumper;
        //bb = gamepad2.right_bumper;
        double threshold = 0.05;
        telemetry.addData("gp1_lstick_x", "value %f", b);
        telemetry.addData("gp1_lstick_y", "value %f", a);

        /*if (b < -threshold)//Turn left
        {

            DriveR.setPower(-b * TURN_SPEED);
            DriveL.setPower(-b * TURN_SPEED);
            telemetry.addData("Movement: ", "Turn left");

        } 
        if (b > threshold)//Turn right
        {
            DriveR.setPower(-b * TURN_SPEED);
            DriveL.setPower(-b * TURN_SPEED);
            telemetry.addData("Movement: ", "Turn right");

        }*/

        if(Math.abs(b) > threshold * 4) {
            DriveR.setPower(-b * TURN_SPEED);
            DriveL.setPower(-b * TURN_SPEED);
        }

        else
        {
            if (a > threshold)//Go forwards
            {
                telemetry.addData("Going forwards: ", true);
                DriveL.setPower(a * MAX_SPEED);
                DriveR.setPower(-a * MAX_SPEED);
            }
            else if (a < -threshold)//Go backwards
            {
                telemetry.addData("Going forwards: ", false);
                DriveL.setPower(a * MAX_SPEED);
                DriveR.setPower(-a * MAX_SPEED);
            }
            else//Stop
            {
                DriveR.setPower(0);
                DriveL.setPower(0);
            }


            //Operates intake system
            if (gamepad1.left_bumper) {
                Intake.setPower(0.3);

            } else if (gamepad1.right_bumper) {
                Intake.setPower(-1);
            } else {
                Intake.setPower(0);
            }
        }

    /*
        if(getRuntime()>2){

        }
        if(gamepad1.x){
            Feeder.setPosition(45);
        }
        else{
            Feeder.setPosition(0);
        }

        if(gamepad1.y){
            ShooterL.setPower(0.9);
            ShooterR.setPower(-0.9);
        }
        else{
            ShooterR.setPower(0);
            ShooterL.setPower(0);
        }
      */

        //Operates the shooter
        telemetry.addData("runtime",getRuntime());

        //Fire!
        if (gamepad1.x) {
            ShooterL.setPower(0.9);
            ShooterR.setPower(-0.9);
            Feeder.setPosition(0);
            speed = getRuntime();
            Feeder.setPosition(0);

        }
        else 
        {
            //Stop firing if time is up
            if (getRuntime() >= speed + 2) {
                Feeder.setPosition(45);
                ShooterR.setPower(0);
                ShooterL.setPower(0);
            }
        }
          /*do{
                Feeder.setPosition(45);
                ShooterR.setPower(0);
                ShooterL.setPower(0);
            }while(getRuntime() >= speed + 2);*/


        if (gamepad1.right_trigger > 0.5) {
            Gate.setPosition(30);
        }
        if (gamepad1.left_trigger > 0.5) {
            Gate.setPosition(0);
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


