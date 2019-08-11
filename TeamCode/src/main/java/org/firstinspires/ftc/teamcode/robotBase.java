package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;

//@Disabled

/**
 * Created by Matthew on 9/20/2018.
 */

public class robotBase {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor Rear = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
    private DcMotor Front = null;
    private DcMotor Left = null;
    private DcMotor Right = null;
    private DcMotor Lift = null;
    private DcMotor CollectorL;
    private DcMotor CollectorR;
    private DcMotor ArmMotorRetract;
    private DcMotor ArmMotorUpDown;
    private Servo servo90;
    private DcMotor ArmMotor;
    private getToDepot zoneIn = new getToDepot();
    private Telemetry talk = null;
    DigitalChannel upperGate;
    DigitalChannel lowerGate;


    static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 0.66;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double LINEAR_FUDGE = 1.6;
    static final double ANGULAR_FUDGE = 2.0;
    static final double DRIVE_SPEED = 0.8; //no.
    static final double TURN_SPEED = 0.2;
    static final double RAMP_SPEED = 0.7;
    static final double DEGREES_TO_INCHES = 0.133333;
    //changed from 0.1 for testing
    static final long CMDSLEEP = 100;
    static final double LIFT_MAX_ROTATIONS = 1;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private LinearOpMode lin_opmode;
    //VuforiaLocalizer vuforia;

    public robotBase(Telemetry telemetry, HardwareMap hardwareMap, LinearOpMode linopmode)   {

        lin_opmode=linopmode;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        talk=telemetry;

      /*  VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();
*/

        Rear = hardwareMap.dcMotor.get("Rear");
        Front = hardwareMap.dcMotor.get("Front");
        Left = hardwareMap.dcMotor.get("Left");
        Right = hardwareMap.dcMotor.get("Right");
        Lift = hardwareMap.dcMotor.get("Lift");
        upperGate = hardwareMap.get(DigitalChannel.class, "upperGate");
        lowerGate = hardwareMap.get(DigitalChannel.class, "lowerGate");
        upperGate.setMode(DigitalChannel.Mode.INPUT);
        lowerGate.setMode(DigitalChannel.Mode.INPUT);
        servo90 = hardwareMap.servo.get("servo90");
        ArmMotorRetract=hardwareMap.dcMotor.get("ArmMotorRetract");
        ArmMotorUpDown=hardwareMap.dcMotor.get("ArmMotorUpDown");



        //  SensorArm = hardwareMap.servo.get("SensorArm");

        Rear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        Rear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // Wait for the game to start (driver presses PLAY)
        runtime.reset();
    }

    public void GoForward(double inches) {


        encoderDrive(Right, Left, DRIVE_SPEED, -inches, 5.0);
    }

    public void GoBack(double inches) {
        encoderDrive(Right, Left, DRIVE_SPEED, inches, 5.0);
    }

    public void GoRight(double inches) {
        encoderDrive(Front, Rear, DRIVE_SPEED, inches, 5.0);
    }

    public void GoLeft(double inches) {
        encoderDrive(Front, Rear, DRIVE_SPEED, -inches, 5.0);
    }

    public void TurnRight( double degrees)
    {
        encoderTurn( Right, Left, Front, Rear, DRIVE_SPEED, -degrees * DEGREES_TO_INCHES, 5.0);
    }

    public void TurnLeft( double degrees)
    {
        encoderTurn( Right, Left, Front, Rear, DRIVE_SPEED, degrees * DEGREES_TO_INCHES, 10.0);
    }
    public void deposit(){
        servo90.setPosition(0.5);
    }
    public void retract(){
        servo90.setPosition(0);
    }
    public void CollectorExtend(){
        runtime.reset();
        //  while(digitalTouch.getstate==true){
        while(lin_opmode.opModeIsActive()&&runtime.seconds()<2){
            ArmMotorUpDown.setPower(-1);
        }
        ArmMotorUpDown.setPower(0);
    }
    public void LiftExtend (){


       //}
    }
    public void DiagonalSW(double inches) {
      //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
        diagonalDrive( Right, Left, Front, Rear, DRIVE_SPEED, inches, 6.0, -1,1,1,-1);
    }
    public void DiagonalSE(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
        diagonalDrive( Right, Left, Front, Rear, DRIVE_SPEED, inches, 6.0, -1,1,-1,1);
    }
    public void DiagonalNW(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
        diagonalDrive( Right, Left, Front, Rear, DRIVE_SPEED, inches, 5.0, 1,-1,1,-1);
    }
    public void DiagonalNE(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
        diagonalDrive( Right, Left, Front, Rear, DRIVE_SPEED, inches, 5, 1,-1,-1,1);
    }
   /** public void DiagonalDrive( DcMotor leftMotor, DcMotor rightMotor, DcMotor frontMotor, DcMotor rearMotor,
                              double speed, double inches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newFrontTarget;
        int newRearTarget;
        double leftInches = inches;
        double rightInches = -inches;
        double forwardInches = inches;
        double rearInches = -inches;
        // Ensure that the opmode is still active
        if (opModeIsActive) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int)(leftInches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int)(rightInches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newRearTarget = rearMotor.getCurrentPosition() + (int)(rearInches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newFrontTarget = frontMotor.getCurrentPosition() + (int)(forwardInches * LINEAR_FUDGE * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            rearMotor.setTargetPosition(newRearTarget);
            frontMotor.setTargetPosition(newFrontTarget);

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            leftMotor.setPower(Math.abs(speed));
            rightMotor.setPower(Math.abs(speed));
            frontMotor.setPower(Math.abs(speed));
            rearMotor.setPower(Math.abs(speed));
            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {

                // Display it for the driver.
                talk.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                talk.addData("Path2",  "Running at %7d :%7d",
                        leftMotor.getCurrentPosition(),
                        rightMotor.getCurrentPosition());
                talk.update();
            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            frontMotor.setPower(0);
            rearMotor.setPower(0);
            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

              sleep(250);   // optional pause after each move
        }
    }
*/


    public void encoderDrive( DcMotor leftMotor, DcMotor rightMotor,
                              double speed, double inches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        double leftInches = -inches;
        double rightInches = inches;

        // Ensure that the opmode is still active
        if (lin_opmode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int)(leftInches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int)(rightInches * LINEAR_FUDGE * COUNTS_PER_INCH);
            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftMotor.setPower(Math.abs(speed));
            rightMotor.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (lin_opmode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy())) {
                liftKill();
                // Display it for the driver.

            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void encoderTurn( DcMotor leftMotor, DcMotor rightMotor,
                             DcMotor frontMotor, DcMotor backMotor,
                             double speed, double inches , double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newFrontTarget;
        int newBackTarget;

        // Ensure that the opmode is still active
        if (lin_opmode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int)(inches * ANGULAR_FUDGE * COUNTS_PER_INCH);
            newRightTarget = rightMotor.getCurrentPosition() + (int)(inches * ANGULAR_FUDGE * COUNTS_PER_INCH);
            newFrontTarget = frontMotor.getCurrentPosition() + (int)(inches * ANGULAR_FUDGE * COUNTS_PER_INCH);
            newBackTarget = backMotor.getCurrentPosition() + (int)(inches * ANGULAR_FUDGE * COUNTS_PER_INCH);

            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            frontMotor.setTargetPosition(newFrontTarget);
            backMotor.setTargetPosition(newBackTarget);

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            double absspeed = Math.abs(speed);
            leftMotor.setPower(absspeed);
            rightMotor.setPower(absspeed);
            frontMotor.setPower(absspeed);
            backMotor.setPower(absspeed);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (lin_opmode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy() && frontMotor.isBusy() && backMotor.isBusy())) {
                liftKill();
                // Display it for the driver.
                //telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                //      leftMotor.getCurrentPosition(),
                //    rightMotor.getCurrentPosition());
                //telemetry.update();
            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            frontMotor.setPower(0);
            backMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

    public void diagonalDrive(DcMotor leftMotor, DcMotor rightMotor,
                              DcMotor frontMotor, DcMotor backMotor,
                              double speed, double inches, double timeoutS, double leftNeg, double rightNeg, double frontNeg, double backNeg) {
        int newLeftTarget;
        int newRightTarget;
        int newFrontTarget;
        inches=inches/(1.414);
        //1.414 is the conversion factor for diagonal movement
        int newBackTarget;

        // Ensure that the opmode is still active
        if (lin_opmode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int)(inches * LINEAR_FUDGE * COUNTS_PER_INCH*leftNeg);
            newRightTarget = rightMotor.getCurrentPosition() + (int)(inches * LINEAR_FUDGE * COUNTS_PER_INCH*rightNeg);
            newFrontTarget = frontMotor.getCurrentPosition() + (int)(inches * LINEAR_FUDGE * COUNTS_PER_INCH*frontNeg);
            newBackTarget = backMotor.getCurrentPosition() + (int)(inches * LINEAR_FUDGE * COUNTS_PER_INCH*backNeg);

            leftMotor.setTargetPosition(newLeftTarget);
            rightMotor.setTargetPosition(newRightTarget);
            frontMotor.setTargetPosition(newFrontTarget);
            backMotor.setTargetPosition(newBackTarget);

            // Turn On RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            double absspeed = Math.abs(speed);
            leftMotor.setPower(absspeed);
            rightMotor.setPower(absspeed);
            frontMotor.setPower(absspeed);
            backMotor.setPower(absspeed);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (lin_opmode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftMotor.isBusy() && rightMotor.isBusy() && frontMotor.isBusy() && backMotor.isBusy())) {
                liftKill();
                // Display it for the driver.
                //telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                //      leftMotor.getCurrentPosition(),
                //    rightMotor.getCurrentPosition());
                //telemetry.update();
            }

            // Stop all motion;
            leftMotor.setPower(0);
            rightMotor.setPower(0);
            frontMotor.setPower(0);
            backMotor.setPower(0);

            // Turn off RUN_TO_POSITION
            leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
///////////////////////////////////////////////

  public void liftKill(){
    if(!lowerGate.getState()){
        Lift.setPower(0);
    }
}
     //if (gamepad2.a && !(Lift.isBusy()))
public void liftDown()
   /* upperGate = hardwareMap.get(DigitalChannel.class, "upperGate");
    lowerGate = hardwareMap.get(DigitalChannel.class, "lowerGate");
       upperGate.setMode(DigitalChannel.Mode.INPUT);
       lowerGate.setMode(DigitalChannel.Mode.INPUT);
    */{
        runtime.reset();

        /*while (upperGate.getState() && runtime.seconds() < 10) {
           Lift.setPower(-1);
        }*/
        Lift.setPower(-1);
        //Lift.setPower(0);
    liftKill();
        //encoderDrive(LIFT_POWER, LIFT_INTERVAL-Lift.getCurrentPosition(),5);hhh
        //LiftClicks+=LIFT_INTERVAL;
    }


    public void liftUp()

    {
        runtime.reset();

        while (lowerGate.getState() && runtime.seconds() < 10&&lin_opmode.opModeIsActive()) {
             Lift.setPower(1);
        }
        Lift.setPower(0);
    }
    public void collectorExtend(int length)
    {
        if(length==2) {
            runtime.reset();
            while (lin_opmode.opModeIsActive() && runtime.seconds() < 3) {
                if (lin_opmode.opModeIsActive() && runtime.seconds() < 3) {
                    ArmMotorUpDown.setPower(-1);
                } else {
                    ArmMotorUpDown.setPower(0);
                }
            }
            ArmMotorUpDown.setPower(0);
        }
        else if(length==1){
            runtime.reset();
            while (lin_opmode.opModeIsActive() && runtime.seconds() < 3) {
                if (lin_opmode.opModeIsActive() && runtime.seconds() < 2.25) {
                    ArmMotorUpDown.setPower(-1);
                } else {
                    ArmMotorUpDown.setPower(0);
                }
            }
            ArmMotorUpDown.setPower(0);
        }
    }
    /////////////////////////////////////////////////////////
}
