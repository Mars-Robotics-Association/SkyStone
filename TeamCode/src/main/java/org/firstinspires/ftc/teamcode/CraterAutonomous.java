package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 *
 *
 * Created by Linda on 9/20/2018.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="DepotAutonomous", group="Autonomous")
public class CraterAutonomous extends LinearOpMode {
    // private getToDepot redDepot = new getToDepot();
    double sq8 = 2.8284*12 -2;
    double mineralLength = 10;
    BlueCraterVuforiaTest SL =null;
    tensorFlow TF = null;
    //robotBase RB = null;
    boolean middlePosition;
    double mineralposition;
    double mineralOffset=0;
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


    /*    Tim: uncomment this section to make a constructor for your class
    public getToDepot()
    {
        TF = new tensorFlow();
    }
    */

    //private ElapsedTime runtime = new ElapsedTime();
  /*  OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
  */

      /*if(gold==1){
            RB.GoLeft(sq8/2);
            RB.GoBack(mineralLength);
            RB.GoForward(mineralLength);
            RB.GoRight(sq8/2);a

        }
        if(gold==2){

            RB.GoBack(mineralLength);
            RB.GoForward(mineralLength);
        }
        if(gold==3){
            RB.GoLeft(sq8/2);
            RB.GoBack(mineralLength);
            RB.GoForward(mineralLength);
            RB.GoRight(sq8/2);
        }*/
    //}

    //    //private robotBase basics= new robotBase();
    //@Override

    public void runOpMode(/*Telemetry telemetry, HardwareMap hardwareMap, boolean isDepot, boolean legal, LinearOpMode linopmode*/) {   //int mirror controls the mirroring of the program. positive is red by default
        //robotBase.moveleft;
        // public void drive(){
        ////////////////////
 /*       int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AYx4WHb/////AAAAGdNm8Or+eEMksoJYY3Yb/IBw58qzcy+cEb/VpEnMQGNqXtH7nppS40WcX0+9QwjDgKMRyXrQlK+SwLFzun99YdyNz1Et6o4erVZa8GU1G8lyuURTiIasy3WxfZ5mHLXkyabiEwXEVwzcP/wQWXVi7wJY+efylYN75biEKUGewV5X9wgICp9Lzyiext1eiHpIup2jYBxtCM24i0Gdo73keMKGPA7d7MSqpLqKj/UcMjljm8qYXF3eG1IdppGv4OApmo9rUbLfIpB62UUfQ1nASiVKCaD/qYF5huHaFIqwH9fq3wshGqtx2W2Nlqmn4Ka0iTSgGutMOrbYVt915+qaOQ9tL5VL/ogerL5Q/EqHyYMe";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
       parameters.cameraMonitorFeedback= VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
  //     this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
      VuforiaLocalizer vuforia=ClassFactory.createVuforiaLocalizer(parameters);
*/
        hardwareMap.dcMotor.get("Right").setPower(1);
        hardwareMap.dcMotor.get("Rear").setPower(1);
        hardwareMap.dcMotor.get("Front").setPower(1);
        hardwareMap.dcMotor.get("Left").setPower(1);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Right Motor", hardwareMap.dcMotor.get("Right").getCurrentPosition());
        telemetry.addData("Front Motor", hardwareMap.dcMotor.get("Front").getCurrentPosition());
        telemetry.addData("Left Motor", hardwareMap.dcMotor.get("Left").getCurrentPosition());
        telemetry.addData("Rear Motor", hardwareMap.dcMotor.get("Rear").getCurrentPosition());
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        hardwareMap.dcMotor.get("Right").setPower(0);
        hardwareMap.dcMotor.get("Rear").setPower(0);
        hardwareMap.dcMotor.get("Front").setPower(0);
        hardwareMap.dcMotor.get("Left").setPower(0);



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
        ArmMotorRetract = hardwareMap.dcMotor.get("ArmMotorRetract");
        ArmMotorUpDown = hardwareMap.dcMotor.get("ArmMotorUpDown");


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
        waitForStart();
        runtime.reset();



        ///////////////////////////Autonomous Sequence/////////////////////////



        if(!opModeIsActive()) return;

//        RB = new robotBase(telemetry, hardwareMap, linopmode);
        TF = new tensorFlow();//Tim: If you make the constructor you can remove or comment out this line
        //      SL= new BlueCraterVuforiaTest();
        retract();
        liftUp();
        //DiagonalNE(2);
        TurnRight(10);// detach from handle

        // get to the minerals
        DiagonalSE(6);
        DiagonalNE(3);
        DiagonalSW(5);

        TurnLeft(50);
        //tweaked from 5 to better
        //        GoForward(4);align with minerals.
        GoForward(5);
        GoLeft(7);

        if(!opModeIsActive()) return;

        middlePosition = TF.tensorFlow(telemetry, hardwareMap, true);
        if (middlePosition) {
            GoLeft(17);
            GoRight(13);
            mineralOffset = 0;
            //GoBack(45);

        } else {
            //           GoLeft(12);
            TurnLeft(40);// was 45
            GoLeft(5);

            if(!opModeIsActive()) return;

            middlePosition = TF.tensorFlow(telemetry, hardwareMap, true);
            GoRight(5);
            if (middlePosition) {
                TurnLeft(10);
                GoLeft(26);
                TurnRight(55);
                GoRight(14);
                mineralOffset = -sq8;
                //GoBack(45-sq8);
            } else {
                TurnRight(100);
                GoLeft(24);
                TurnLeft(45);// was 55
                GoRight(12);
                mineralOffset = sq8;
                //GoBack(45+sq8);
            }
        }

        //the same for both

        boolean isDepot=false;

        if (isDepot) {
            if (mineralOffset == 0) {
                //false condition added to nullify code
                /*
                RB.TurnRight(90);
                GoRight(48 + mineralOffset);
                RB.DiagonalNW(6);
                collectorExtend(1);
                DiagonalSW(50);
                deposit();
                SL.sleep(1000);
                DiagonalNE(10);
                retract();
                */
                TurnRight(45);
                DiagonalSW((3 * sq8) / 2);
                deposit();
                DiagonalNE(sq8);
                retract();
            }
            if (mineralOffset == sq8) {
                GoLeft(sq8 * 2);
                DiagonalSW(sq8);
                deposit();
                DiagonalNE(sq8);
                retract();
            }
            if (mineralOffset == -sq8) {
                GoLeft(sq8 * 2);
                TurnRight(90);
                DiagonalSW(sq8);
                deposit();
                DiagonalNE(sq8);
                retract();
            }

            // TurnLeft(80);
            //CollectorExtend();

            //DiagonalSE(10);

        } else {
            if (mineralOffset == 0) {
                TurnRight(135);
                DiagonalSE(5);

            } else if (mineralOffset > 0) {
                TurnRight(135);

            } else {
                TurnRight(135);
                DiagonalSE(5);

            }
            //TurnRight(45);
            collectorExtend(2);
            DiagonalSE(5);


            if (mineralOffset == 0 && false) {
                //false condition added to nullify code
                TurnLeft(90);
                GoLeft(48 + mineralOffset);
                DiagonalNW(5);
                DiagonalSW(35);
                DiagonalNW(5);
                deposit();
                DiagonalNE(34);
                retract();

                // TurnLeft(80);
                //CollectorExtend();

//                DiagonalSE(34);
            }
        }

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
        while(opModeIsActive()&&runtime.seconds()<2){
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

         //  sleep(250);   // optional pause after each move
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
        if (opModeIsActive()) {

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
            while (opModeIsActive() &&
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
        double speed, double inches, double timeoutS) {
        int newLeftTarget;
        int newRightTarget;
        int newFrontTarget;

        int newBackTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

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
            while (opModeIsActive() &&
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
        if (opModeIsActive()) {

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
            while (opModeIsActive() &&
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

            while (lowerGate.getState() && runtime.seconds() < 10&&opModeIsActive()) {
                Lift.setPower(1);
            }
            Lift.setPower(0);
        }
    public void collectorExtend(int length)
    {
        boolean run = true;
        if(length==2&&run) {
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 4) {
                if (opModeIsActive() && runtime.seconds() < 4) {
                    ArmMotorUpDown.setPower(-1);
                } else {
                    ArmMotorUpDown.setPower(0);
                }
            }
            ArmMotorUpDown.setPower(0);
        }
        else if(length==1&&run){
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 4) {
                if (opModeIsActive() && runtime.seconds() < 4) {
                    ArmMotorUpDown.setPower(-1);
                } else {
                    ArmMotorUpDown.setPower(0);
                }
            }
            ArmMotorUpDown.setPower(0);
        }
    }
}
