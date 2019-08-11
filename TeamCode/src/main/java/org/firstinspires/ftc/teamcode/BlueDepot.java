package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

//@Disabled

//@Autonomous(name="BlueDepot", group="Autonomous")

public class BlueDepot extends LinearOpMode {
    //private getToDepot redDepot = new getToDepot();
    // Declare OpMode members.
    getToDepot Depot = null;
    //robotBase RB = null;

    /**
     * private ElapsedTime runtime = new ElapsedTime();
     * private DcMotor Rear = null; //main drive program should NOT be modified. implement claw/shooting systems from existing.
     * private DcMotor Front = null;
     * private DcMotor Left = null;
     * private DcMotor Right = null;
     * private DcMotor Lift = null;
     * private getToDepo zoneIn = new getToDepo();
     * <p>
     * static final double COUNTS_PER_MOTOR_REV = 1120;    // eg: TETRIX Motor Encoder
     * static final double DRIVE_GEAR_REDUCTION = 0.66;     // This is < 1.0 if geared UP
     * static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
     * static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
     * (WHEEL_DIAMETER_INCHES * 3.1415);
     * static final double LINEAR_FUDGE = 1.6;
     * static final double ANGULAR_FUDGE = 2.0;
     * static final double DRIVE_SPEED = 0.2;
     * static final double TURN_SPEED = 0.2;
     * static final double RAMP_SPEED = 0.5;
     * static final double DEGREES_TO_INCHES = 0.1;
     * static final long CMDSLEEP = 100;
     * <p>
     * OpenGLMatrix lastLocation = null;
     * /**
     * {@link } is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
//    OpenGLMatrix lastLocation = null;
  //  VuforiaLocalizer vuforia;


    @Override
    public void runOpMode() {
        hardwareMap.dcMotor.get("Right").setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareMap.dcMotor.get("Rear").setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareMap.dcMotor.get("Front").setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        hardwareMap.dcMotor.get("Left").setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        hardwareMap.dcMotor.get("Right").setPower(1);
        hardwareMap.dcMotor.get("Rear").setPower(1);
        hardwareMap.dcMotor.get("Front").setPower(1);
        hardwareMap.dcMotor.get("Left").setPower(1);

        telemetry.addData("Status", "Initialized");
        telemetry.addData("Right Motor", hardwareMap.dcMotor.get("Right").getCurrentPosition());
        telemetry.addData("Front Motor", hardwareMap.dcMotor.get("Front").getCurrentPosition());
        telemetry.addData("Left Motor", hardwareMap.dcMotor.get("Left").getCurrentPosition());
        telemetry.addData("Rear Motor", hardwareMap.dcMotor.get("Rear").getCurrentPosition());

        telemetry.update();
        hardwareMap.dcMotor.get("Right").setPower(0);
        hardwareMap.dcMotor.get("Rear").setPower(0);
        hardwareMap.dcMotor.get("Front").setPower(0);
        hardwareMap.dcMotor.get("Left").setPower(0);

        telemetry.update();
        /*

*/
        Depot = new getToDepot();
        //RB = new robotBase(telemetry, hardwareMap);
        waitForStart();

        /////////////////// Start Commands ////////////////

        Depot.drive(telemetry, hardwareMap, true, true,this);

        //RB.GoForward(6);
        //RB.GoLeft(17);
        //RB.TurnRight(45);
        //RB.GoLeft(24);
        //RB.GoForward(48);

        //RB.GoForward(5);
        //RB.GoLeft(5);
        //RB.GoBack(5);
        //RB.GoRight(5);

    }
}