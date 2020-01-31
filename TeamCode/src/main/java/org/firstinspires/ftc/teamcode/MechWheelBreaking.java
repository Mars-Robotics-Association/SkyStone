package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
//Test Program for using a linear system of navigation with the WaypointManager
@Autonomous(name = "MechWheelBreaking", group = "Autonomous")
public class MechWheelBreaking extends LinearOpMode {
    private DcMotor FrontRight = null;
    private DcMotor FrontLeft = null;
    private DcMotor RearRight = null;
    private DcMotor RearLeft = null;

    int FrontRightBrakePos = 0;
    int FrontLeftBrakePos = 0;
    int RearRightBrakePos = 0;
    int RearLeftBrakePos = 0;

    @Override
    public void runOpMode() {

        FrontRight = hardwareMap.dcMotor.get("FrontRight");
        FrontLeft = hardwareMap.dcMotor.get("FrontLeft");
        RearRight = hardwareMap.dcMotor.get("RearRight");
        RearLeft = hardwareMap.dcMotor.get("RearLeft");

        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        RearLeft.setPower(0);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearRight.setPower(0);

        waitForStart();

        while (!gamepad1.x){

        }
        RearLeft.setPower(0.8);
        FrontLeft.setPower(0.8);
        FrontRight.setPower(-0.8);
        RearRight.setPower(-0.8);
        while (!gamepad1.y){

        }
        FrontLeftBrakePos=FrontLeft.getCurrentPosition();
        FrontRightBrakePos=FrontRight.getCurrentPosition();
        RearLeftBrakePos=RearLeft.getCurrentPosition();
        RearRightBrakePos=RearRight.getCurrentPosition();



        FrontRight.setTargetPosition(FrontRightBrakePos);
        FrontLeft.setTargetPosition(FrontLeftBrakePos);
        RearRight.setTargetPosition(RearRightBrakePos);
        RearLeft.setTargetPosition(RearLeftBrakePos);

        FrontRight.setPower(0.5);
        FrontLeft.setPower(0.5);
        RearRight.setPower(0.5);
        RearLeft.setPower(0.5);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }


}