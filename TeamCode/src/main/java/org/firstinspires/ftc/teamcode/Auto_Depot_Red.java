package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "Auto_Depot_Red", group = "Autonomous")
public class Auto_Depot_Red extends LinearOpMode {

    public SimpleFieldNavigation nav = null;

    private ColorSensor colorSensor;
    private double HueThreshold = 40;
    private double RedHue = 0;
    private double BlueHue = 180;

    @Override
    public void runOpMode() {
        nav = new SimpleFieldNavigation(this, true);
        nav.Init();

        colorSensor = new ColorSensor(this, "colorSensorGround");
        colorSensor.Init();

        waitForStart();

        nav.Start();
        telemetry.addData("Status", "Initialized");

        GoRight(-20,0.2);

        //Block: Go forwards to the line
        nav.GoForward(10, 0.2);
        while (Math.abs(RedHue - colorSensor.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensor.returnHue()) > HueThreshold)
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.addData("Hue: ", colorSensor.returnHue());
            telemetry.update();
        }

        //Stops all
        nav.StopAll();

        //Brakes
        nav.SetBrakePos();
        nav.Brake(1);



    }


/*
        grab.FoundationGrabUp();
        //Go forwards 39.5 inches
        GoForward(-39.5, 1);
        grab.FoundationGrabDown();
        sleep(500);

        //Block: Go back 40 inches
        GoForward(45, 1);
        grab.FoundationGrabUp();
        sleep(500);
        GoForward(-4,1);

        //Block: Go left 28 inches
        GoRight(28, 1);

        //Block: Go forward 38.5 inches
        GoForward(-38.5, 1);

        GoRight(-28,1);

        GoForward(18,1);

        GoForward(-3,1);

        nav.GoRight(42, 0.5);
        double sensorValue = 0;
        while(!(color.returnHue()>180)){
            nav.Loop();
            if(color.returnHue()>sensorValue){
                sensorValue=color.returnHue();
            }

            telemetry.addData("colorSensor", color.returnHue());
            telemetry.addData("max detection", sensorValue  );
            telemetry.update();
        }
        nav.StopAll();
        nav.SetBrakePos();
        nav.Brake(0.5);


*/

    public void GoForward(double distance, double speed){
        nav.GoForward(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }
    public void GoRight(double distance, double speed){
        nav.GoRight(distance, 0.3);
        while (!nav.CheckIfAtTargetDestination())
        {
            nav.Loop();
            telemetry.addData("looping1: ", true);
            telemetry.update();
        }
        nav.StopAll();
    }

    /*private ElapsedTime runtime = new ElapsedTime();

    private DcMotor FrontRight;
    private DcMotor FrontLeft;
    private DcMotor RearRight;
    private DcMotor RearLeft;

    private double EncoderTicks = 103.6; //ticks for one rotation 1120, 103.6
    private double WheelDiameter = 2;//diameter of wheel in inches
    private int encodedDistance = 0;

    private ColorSensor colorSensor;
    private double HueThreshold = 20;
    private double RedHue = 0;
    private double BlueHue = 207;


    @Override
    public void runOpMode() {
        FrontRight = hardwareMap.dcMotor.get("FrontRight");
        FrontLeft = hardwareMap.dcMotor.get("FrontLeft");
        RearRight = hardwareMap.dcMotor.get("RearRight");
        RearLeft = hardwareMap.dcMotor.get("RearLeft");

        colorSensor = new ColorSensor(this);
        colorSensor.Init();

/*        FrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/


        /*waitForStart();
        runtime.reset();

        encodedDistance = (int)((EncoderTicks/WheelDiameter)*5);//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

/*        FrontRight.setTargetPosition(encodedDistance);
        FrontLeft.setTargetPosition(-encodedDistance);
        RearRight.setTargetPosition(encodedDistance);
        RearLeft.setTargetPosition(-encodedDistance);*/
/*
        FrontRight.setPower(-0.5);
        FrontLeft.setPower(0.5);
        RearRight.setPower(-0.5);
        RearLeft.setPower(0.5);

        /*FrontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        FrontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        RearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);*/

        /*double startTime = runtime.time();
        double targetTime = 0.4;

        while(startTime + targetTime > runtime.time())//startTime + targetTime > runtime.time()
        {
            telemetry.addData("Encoded Distance: ", encodedDistance);
            telemetry.addData("FrontRight Target Pos: ", FrontRight.getTargetPosition());
            telemetry.addData("FrontRight Current Pos: ", FrontRight.getCurrentPosition());
            telemetry.addData("running",0);
            telemetry.update();
        }*/

  /*      telemetry.addData("Before line: ", true);

        while (Math.abs(RedHue - colorSensor.returnHue()) > HueThreshold && Math.abs(BlueHue - colorSensor.returnHue()) > HueThreshold)
        {
            telemetry.addData("Hue: ", colorSensor.returnHue());
            telemetry.update();
        }
        telemetry.addData("Before line: ", false);

        FrontRight.setPower(0);
        FrontLeft.setPower(0);
        RearRight.setPower(0);
        RearLeft.setPower(0);
    }
}

/*encodedDistance = (int)((EncoderTicks/WheelDiameter)/50 * Math.sqrt(2));//find ticks for distance: ticks per inch = (encoderTicks/wheelDiameter)

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
        }*/
        }
