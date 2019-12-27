package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;


@Autonomous(name = "simpleBlueDepot", group = "Autonomous")
public class simpleBlueDepot extends LinearOpMode {

    NormalizedColorSensor colorSensorGround;

    private DcMotor FrontRight;
    private DcMotor FrontLeft;
    private DcMotor RearRight;
    private DcMotor RearLeft;

    private double EncoderTicks = 1120;//ticks for one rotation
    private double WheelDiameter = 2;//diameter of wheel in inches
    private int encodedDistance = 0;




    @Override
    public void runOpMode() {
        FrontRight = hardwareMap.get(DcMotor.class, "FrontRight");
        FrontLeft = hardwareMap.get(DcMotor.class, "FrontLeft");
        RearRight = hardwareMap.get(DcMotor.class, "RearRight");
        RearLeft = hardwareMap.get(DcMotor.class, "RearLeft");

        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        colorSensorGround = hardwareMap.get(NormalizedColorSensor.class, "colorSensorGround");


        waitForStart();


        int encodedDistance = (int)((EncoderTicks/WheelDiameter)/25);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

        FrontRight.setTargetPosition(encodedDistance);
        FrontLeft.setTargetPosition(-encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(-encodedDistance);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        FrontRight.setPower(1);
        FrontLeft.setPower(1);
        RearRight.setPower(1);
        RearLeft.setPower(1);


        while(FrontRight.isBusy()){
            telemetry.addData("running",0);
            telemetry.update();
        }


        FrontRight.setPower(0);
        FrontLeft.setPower(0);
        RearRight.setPower(0);
        RearLeft.setPower(0);

        encodedDistance = (int)((EncoderTicks/WheelDiameter)/-50 * Math.sqrt(2));//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)
        
        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        
        FrontRight.setTargetPosition(-encodedDistance);
        FrontLeft.setTargetPosition(-encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(encodedDistance);

        FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        FrontRight.setPower(1);
        FrontLeft.setPower(1);
        RearRight.setPower(1);
        RearLeft.setPower(1);

        if (colorSensorGround instanceof SwitchableLight) {
            ((SwitchableLight)colorSensorGround).enableLight(true);
        }
        NormalizedRGBA colors = colorSensorGround.getNormalizedColors();

        while(colors.red>170||colors.blue>170){
        	colors = colorSensorGround.getNormalizedColors();
        	telemetry.addData("searching",0);
            telemetry.update();
        }
        FrontRight.setPower(0);
        FrontLeft.setPower(0);
        RearRight.setPower(0);
        RearLeft.setPower(0);
    }
}
