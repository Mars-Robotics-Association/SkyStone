
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="ISDDeaths_Head", group="Iterative Opmode")  // @Autonomous(...) is the other common choice
@Disabled
public class ISDDeaths_Head extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();

    private DcMotor drivel= null;


    private double length = 0;
    private double a ;
    private double b = 0.1;
    static final double MAX_POS     =  1.0;     // Maximum rotational position
    static final double MIN_POS     =  0.0;     // Minimum rotational position
    @Override
    public void init() {

        telemetry.addData("Status", "Initialized");

        drivel  = hardwareMap.dcMotor.get("Left");

        drivel.setDirection(DcMotor.Direction.FORWARD);


    }

    //double  servolpos = 0.50;
    @Override
    public void init_loop(){

    }
    @Override
    public void start(){
        runtime.reset();

    }

    @Override
    public void loop() {

        telemetry.addData("Status", "Running: " + runtime.toString());
        double c=1;
        a=1;
        //drivel.setPower(-gamepad1.left_stick_y);
        drivel.setPower(gamepad1.left_stick_y *a);
        //driver.setPower(-gamepad1.right_stick_y);


      //  if("button is pressed"){
            //move servo 180 deg
        //}




        //     servolpos = servolpos - .01;
        // }
        // if (gamepad2.b){
        //     servolpos = servolpos + .01;
        // Code Adjustment to work Matt's beacon pusher


    }
}

