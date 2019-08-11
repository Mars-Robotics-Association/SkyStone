package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;

//@Disabled

//@Autonomous(name="DiagonalTest1414 ", group="Autonomous")

public class DiagonalTest1414 extends LinearOpMode {
    //private getToDepot redDepot = new getToDepot();
    // Declare OpMode members.
    getToDepot Depot = null;
    robotBase RB = null;

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
    //VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        /*

*/
        Depot = new getToDepot();
         RB = new robotBase(telemetry, hardwareMap,this);
        waitForStart();

        /////////////////// Start Commands ////////////////

        RB.DiagonalNW(192/1.414);
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