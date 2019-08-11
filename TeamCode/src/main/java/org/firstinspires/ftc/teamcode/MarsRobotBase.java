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
import com.sun.tools.javac.tree.DCTree;
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

public class MarsRobotBase {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor LeftRear = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
    private DcMotor RightRear = null;
    private DcMotor LeftFront = null;
    private DcMotor RightFront = null;

    private DcMotor Bucket = null;


    static final double COUNTS_PER_MOTOR_REV = 1120;     // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 3.875;     // For figuring circumference
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
    private LinearOpMode lin_opmode=null;
    private Telemetry talk = null;

    //VuforiaLocalizer vuforia;

    public MarsRobotBase(Telemetry telemetry, HardwareMap hardwareMap,LinearOpMode linopmode) {

        lin_opmode=linopmode;
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        talk=telemetry;

        LeftRear = hardwareMap.dcMotor.get("LeftRear");
        RightRear = hardwareMap.dcMotor.get("RightRear");
        LeftFront = hardwareMap.dcMotor.get("LeftFront");
        RightFront = hardwareMap.dcMotor.get("RightFront");

        LeftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        LeftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        Bucket = hardwareMap.dcMotor.get("Bucket");

        // Wait for the game to start (driver presses PLAY)
        runtime.reset();
    }

    public void GoForward(double inches) {

        encoderDrive(LeftRear, RightFront, LeftFront, RightRear, DRIVE_SPEED, -inches, 5.0, -1,1, -1,1);
    }

    public void GoBack(double inches) {
        encoderDrive(LeftRear, RightFront, LeftFront, RightRear, DRIVE_SPEED, inches, 5.0, -1,1, -1,1);
    }

    public void GoRight(double inches) {
        encoderDrive(LeftRear, RightFront, LeftFront, RightRear, DRIVE_SPEED, inches, 5.0, -1,1, 1,-1);
    }

    public void GoLeft(double inches) {
        encoderDrive(LeftRear, RightFront, LeftFront, RightRear, DRIVE_SPEED, -inches, 5.0, -1,1, 1,-1);
    }


   /* public void TurnRight(double degrees) {
        encoderTurn(LeftRear, LeftFront, RightRear, RightFront, DRIVE_SPEED, -degrees * DEGREES_TO_INCHES, 5.0);
    }

    public void TurnLeft(double degrees) {
        encoderTurn(LeftRear, LeftFront, RightRear, RightFront, DRIVE_SPEED, degrees * DEGREES_TO_INCHES, 10.0);
    }*/

    public void DiagonalSW(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
    //    diagonalDrive(Right, Left, Front, Rear, DRIVE_SPEED, inches, 6.0, -1, 1, 1, -1);
    }

    public void DiagonalSE(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
      //  diagonalDrive(Right, Left, Front, Rear, DRIVE_SPEED, inches, 6.0, -1, 1, -1, 1);
    }

    public void DiagonalNW(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
      //  diagonalDrive(Right, Left, Front, Rear, DRIVE_SPEED, inches, 5.0, 1, -1, 1, -1);
    }

    public void DiagonalNE(double inches) {
        //  encoderDrive(Front, Right, DRIVE_SPEED, -inches, 60.0);
        //encoderDrive(Rear, Left, DRIVE_SPEED, inches, 60.0);
        //diagonalDrive(Right, Left, Front, Rear, DRIVE_SPEED, inches, 5, 1, -1, -1, 1);
    }
    public void bucketExtend(double time, double direction){
        double initial = runtime.seconds();
       while(runtime.seconds()<initial+time) {
           Bucket.setPower(0.8*direction);
       }
       Bucket.setPower(0);
    }


    /**
     * public void DiagonalDrive( DcMotor leftMotor, DcMotor rightMotor, DcMotor frontMotor, DcMotor rearMotor,
     * double speed, double inches, double timeoutS) {
     * int newLeftTarget;
     * int newRightTarget;
     * int newFrontTarget;
     * int newRearTarget;
     * double leftInches = inches;
     * double rightInches = -inches;
     * double forwardInches = inches;
     * double rearInches = -inches;
     * // Ensure that the opmode is still active
     * if (opModeIsActive) {
     * <p>
     * // Determine new target position, and pass to motor controller
     * newLeftTarget = leftMotor.getCurrentPosition() + (int)(leftInches * LINEAR_FUDGE * COUNTS_PER_INCH);
     * newRightTarget = rightMotor.getCurrentPosition() + (int)(rightInches * LINEAR_FUDGE * COUNTS_PER_INCH);
     * newRearTarget = rearMotor.getCurrentPosition() + (int)(rearInches * LINEAR_FUDGE * COUNTS_PER_INCH);
     * newFrontTarget = frontMotor.getCurrentPosition() + (int)(forwardInches * LINEAR_FUDGE * COUNTS_PER_INCH);
     * leftMotor.setTargetPosition(newLeftTarget);
     * rightMotor.setTargetPosition(newRightTarget);
     * rearMotor.setTargetPosition(newRearTarget);
     * frontMotor.setTargetPosition(newFrontTarget);
     * <p>
     * // Turn On RUN_TO_POSITION
     * leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     * rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     * frontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     * rearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
     * // reset the timeout time and start motion.
     * runtime.reset();
     * leftMotor.setPower(Math.abs(speed));
     * rightMotor.setPower(Math.abs(speed));
     * frontMotor.setPower(Math.abs(speed));
     * rearMotor.setPower(Math.abs(speed));
     * // keep looping while we are still active, and there is time left, and both motors are running.
     * while (opModeIsActive &&
     * (runtime.seconds() < timeoutS) &&
     * (leftMotor.isBusy() && rightMotor.isBusy())) {
     * <p>
     * // Display it for the driver.
     * talk.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
     * talk.addData("Path2",  "Running at %7d :%7d",
     * leftMotor.getCurrentPosition(),
     * rightMotor.getCurrentPosition());
     * talk.update();
     * }
     * <p>
     * // Stop all motion;
     * leftMotor.setPower(0);
     * rightMotor.setPower(0);
     * frontMotor.setPower(0);
     * rearMotor.setPower(0);
     * // Turn off RUN_TO_POSITION
     * leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     * rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
     * <p>
     * sleep(250);   // optional pause after each move
     * }
     * }
     */



    public void encoderDrive(DcMotor LeftRear, DcMotor RightFront, DcMotor LeftFront, DcMotor RightRear,
                             double speed, double inches, double timeoutS, double LeftFrontDirection,
                             double RightRearDirection, double LeftRearDirection, double RightFrontDirection  ) {

        int newLeftRearTarget;
        int newRightRearTarget;
        int newLeftFrontTarget;
        int newRightFrontTarget;


        // Ensure that the opmode is still active
        if (lin_opmode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftRearTarget = LeftRear.getCurrentPosition() + (int) (LeftRearDirection* inches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newLeftFrontTarget = LeftFront.getCurrentPosition() + (int) (LeftFrontDirection* inches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newRightFrontTarget = RightFront.getCurrentPosition() + (int) (RightFrontDirection* inches * LINEAR_FUDGE * COUNTS_PER_INCH);
            newRightRearTarget = RightRear.getCurrentPosition() + (int) (RightRearDirection* inches * LINEAR_FUDGE * COUNTS_PER_INCH);

            LeftRear.setTargetPosition(newLeftRearTarget);
            RightFront.setTargetPosition(newRightFrontTarget);
            LeftFront.setTargetPosition(newLeftFrontTarget);
            RightRear.setTargetPosition(newRightRearTarget);

            // Turn On RUN_TO_POSITION
            LeftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LeftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);






            // reset the timeout time and start motion.
            runtime.reset();
            LeftRear.setPower(LeftRearDirection*Math.abs(speed));
            RightFront.setPower(RightFrontDirection*Math.abs(speed));
            RightRear.setPower(RightRearDirection*Math.abs(speed));
            LeftFront.setPower(LeftFrontDirection*Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (lin_opmode.opModeIsActive()&&(runtime.seconds() < timeoutS) &&
                    LeftRear.isBusy() && RightFront.isBusy()&&RightRear.isBusy()&&LeftFront.isBusy()) {
                    talk.addData("RightRear ",  "Running to %7d :%7d", newRightRearTarget,  RightRear.getCurrentPosition());
                talk.addData("RightFront ",  "Running to %7d :%7d", newRightFrontTarget,  RightFront.getCurrentPosition());
                talk.addData("LeftRear ",  "Running to %7d :%7d", newLeftRearTarget,  LeftRear.getCurrentPosition());
                talk.addData("LeftFront ",  "Running to %7d :%7d", newLeftFrontTarget,  LeftFront.getCurrentPosition());
                talk.addData("Motors are busy: ", true);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                //      leftMotor.getCurrentPosition(),
                //    rightMotor.getCurrentPosition());
                talk.update();
            }
            talk.addData("Motors are busy: ", false);

            // Stop all motion;
            LeftRear.setPower(0);
            RightFront.setPower(0);
            RightRear.setPower(0);
            LeftFront.setPower(0);



            // Turn off RUN_TO_POSITION
            LeftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //  sleep(250);   // optional pause after each move
         }
    }




    public void encoderTurn(DcMotor LeftRear, DcMotor LeftFront,
                            DcMotor RightRear, DcMotor RightFront,
                            double speed, double inches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (lin_opmode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = LeftRear.getCurrentPosition() + (int) (inches * ANGULAR_FUDGE * COUNTS_PER_INCH);
            newRightTarget = RightFront.getCurrentPosition() + (int) (inches * ANGULAR_FUDGE * COUNTS_PER_INCH);


            LeftRear.setTargetPosition(newLeftTarget);
            RightFront.setTargetPosition(newRightTarget);


            // Turn On RUN_TO_POSITION
            LeftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            RightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            double absspeed = Math.abs(speed);
            LeftFront.setPower(absspeed);
            RightFront.setPower(absspeed);
            LeftRear.setPower(absspeed);
            RightRear.setPower(absspeed);

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (lin_opmode.opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (LeftRear.isBusy() && RightFront.isBusy())) {
                 //Display it for the driver.
                //telemetry.addData("Path1",  "Running to0 %7d :%7d", newLeftTarget,  newRightTarget);
                //telemetry.addData("Path2",  "Running at %7d :%7d",
                //      leftMotor.getCurrentPosition(),
                //    rightMotor.getCurrentPosition());
                //telemetry.update();
            }

            // Stop all motion;
            LeftFront.setPower(0);
            RightFront.setPower(0);
            LeftRear.setPower(0);
            RightRear.setPower(0);

            // Turn off RUN_TO_POSITION
            LeftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


            //  sleep(250);   // optional pause after each move
        }
    }

    public void diagonalDrive(DcMotor leftMotor, DcMotor rightMotor,
                              DcMotor frontMotor, DcMotor backMotor,
                              double speed, double inches, double timeoutS, double leftNeg, double rightNeg, double frontNeg, double backNeg) {
        int newLeftTarget;
        int newRightTarget;
        int newFrontTarget;
        inches = inches / (1.414);
        //1.414 is the conversion factor for diagonal movement
        int newBackTarget;

        // Ensure that the opmode is still active
        if (lin_opmode.opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = leftMotor.getCurrentPosition() + (int) (inches * LINEAR_FUDGE * COUNTS_PER_INCH * leftNeg);
            newRightTarget = rightMotor.getCurrentPosition() + (int) (inches * LINEAR_FUDGE * COUNTS_PER_INCH * rightNeg);
            newFrontTarget = frontMotor.getCurrentPosition() + (int) (inches * LINEAR_FUDGE * COUNTS_PER_INCH * frontNeg);
            newBackTarget = backMotor.getCurrentPosition() + (int) (inches * LINEAR_FUDGE * COUNTS_PER_INCH * backNeg);

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
      //  Lift.setPower(-1);
        //Lift.setPower(0);
    //liftKill();
        //encoderDrive(LIFT_POWER, LIFT_INTERVAL-Lift.getCurrentPosition(),5);hhh
        //LiftClicks+=LIFT_INTERVAL;
    }



    /////////////////////////////////////////////////////////
}
