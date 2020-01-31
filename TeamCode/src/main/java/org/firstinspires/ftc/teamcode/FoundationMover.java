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

public class FoundationMover implements Attachment{


    static double GripperLAngle = 0;
    static double GripperRAngle = 0;

    static double GripperAngle = 0;
    private Servo GripperL = null;
    private Servo GripperR = null;
    private Servo GripperRotate = null;
    private double gripperPosition = 0.5;

    OpMode opmode;

    public FoundationMover(OpMode thatopmode){
        opmode = thatopmode;
    }

    public void Init() {

        GripperL= opmode.hardwareMap.servo.get("GripperL");
        GripperR= opmode.hardwareMap.servo.get("GripperR");
        GripperRotate= opmode.hardwareMap.servo.get("GripperRotate");
    }

    public void updateGrippers(){
        //  GripperLAngle = (GripperAngle * 1);
        //  GripperRAngle = (GripperAngle * -1);

        //    GripperL.setPosition(GripperLAngle);
        //   GripperR.setPosition(GripperRAngle);



    }


    @Override
    public void Loop() {
        opmode.telemetry.addData("Rotate Gripper Pos: ", gripperPosition);
    }

    @Override
    public void Run() {

    }

    @Override
    public void Stop() {

    }

    public void GripperOpen(){
        GripperR.setPosition(0.6);//.6 //.8
        GripperL.setPosition(0.5);//.5 //.3

    }

    public void GripperClose(){
        GripperR.setPosition(.45);//.35
        GripperL.setPosition(.65);//.85
    }

    public void GripperCloseLeft(){
        GripperL.setPosition(0.85);
    }

    public void GripperOpenLeft(){
        GripperL.setPosition(0.1);
    }

    public void GripperCloseRight(){
        GripperR.setPosition(0.35);

    }

    public void GripperOpenRight(){
        GripperR.setPosition(0.6);
    }

    public void GripperRotatePosition(double percentToAdd)
    {
        gripperPosition += percentToAdd;
        if(gripperPosition > 1)
        {
            gripperPosition = 1;
        }
        if(gripperPosition < 0)
        {
            gripperPosition = 0;
        }

        GripperRotate.setPosition(gripperPosition);
    }


    /*public void GripperOpen(){

        GripperR.setPosition(0.75);
        GripperL.setPosition(0.055);

    }
    public void GripperClose(){

        GripperL.setPosition(0.416);
        GripperR.setPosition(0.483);
    }*/

}
