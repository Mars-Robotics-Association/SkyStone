package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@Disabled
@TeleOp(name="MechWheelTest", group="Iterative Opmode")
public class MechWheelTest extends OpMode
{
    private DcMotor FrontRight = null;
    private DcMotor FrontLeft = null;
    private DcMotor RearRight = null;
    private DcMotor RearLeft = null;

    private double FrontRightPower = 0;
    private double FrontLeftPower = 0;
    private double RearRightPower = 0;
    private double RearLeftPower = 0;

    private double EncoderTicks = 1120;//ticks for one rotation was 1120
    private double WheelDiameter = 2;//diameter of wheel in inches
    private int encodedDistance = 0;

    private double speed = 1;

    @Override
    public void init()
    {
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

    }

    @Override
    public void start()
    {
        RearLeft.setPower(0);
        FrontLeft.setPower(0);
        FrontRight.setPower(0);
        RearRight.setPower(0);
    }

    @Override
    public void loop() {
       telemetry.addData("Front Right: ", FrontRight.getCurrentPosition());
       telemetry.addData("Front Left: ", FrontLeft.getCurrentPosition());
       telemetry.addData("Rear Right: ", RearRight.getCurrentPosition());
       telemetry.addData("Rear Left: ", RearLeft.getCurrentPosition());

        if(gamepad1.dpad_up){
            RearLeft.setPower(speed);
            FrontLeft.setPower(speed);
            FrontRight.setPower(-speed);
            RearRight.setPower(-speed);
        }
        else if(gamepad1.dpad_down){
            RearLeft.setPower(-speed);
            FrontLeft.setPower(-speed);
            FrontRight.setPower(speed);
            RearRight.setPower(speed);
        }
        else if(gamepad1.dpad_left){
            RearLeft.setPower(speed);
            FrontLeft.setPower(-speed);
            FrontRight.setPower(speed);
            RearRight.setPower(-speed);
        }
        else if(gamepad1.dpad_right){
            RearLeft.setPower(-speed);
            FrontLeft.setPower(speed);
            FrontRight.setPower(-speed);
            RearRight.setPower(speed);
        }
        else{
            RearLeft.setPower(0);
            FrontLeft.setPower(0);
            FrontRight.setPower(0);
            RearRight.setPower(0);
        }
       telemetry.update();

    }

}