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

public class GripperCustom implements Attachment{


    static double GripperLAngle = 0;
    static double GripperRAngle = 0;

    static double GripperAngle = 0;
    private Servo GripperL = null;
    private Servo GripperR = null;
    private Servo GripperRotate = null;
    private Servo GripperUpDownR = null;
    private Servo GripperUpDownL = null;
    private double gripperPosition = 0.5;
    private double gripperVRPosition = 0.5;
    private double gripperVLPosition = 0.5;

    OpMode opmode;

    public GripperCustom(OpMode thatopmode){
        opmode = thatopmode;
    }

    public void Init() {

        GripperL= opmode.hardwareMap.servo.get("GripperL");
        GripperR= opmode.hardwareMap.servo.get("GripperR");
        GripperRotate= opmode.hardwareMap.servo.get("GripperRotate");
        GripperUpDownR= opmode.hardwareMap.servo.get("GripperUpDownRotateR");
        GripperUpDownL= opmode.hardwareMap.servo.get("GripperUpDownRotateL");
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
        GripperR.setPosition(.35);//.35
        GripperL.setPosition(.75);//.85
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

    public void GripperRotateParallel()
    {
        GripperRotate.setPosition(0.5);
    }

    public void GripperUpDownRotate(double percentToAdd)
    {
        gripperVRPosition += percentToAdd;
        gripperVLPosition -= percentToAdd;
        if(gripperVRPosition > 1)
        {
            gripperVRPosition = 1;
        }
        if(gripperVRPosition < 0)
        {
            gripperVRPosition = 0;
        }
        if(gripperVLPosition > 1)
        {
            gripperVLPosition = 1;
        }
        if(gripperVLPosition < 0)
        {
            gripperVLPosition = 0;
        }

        GripperUpDownR.setPosition(gripperVRPosition);
        GripperUpDownL.setPosition(gripperVLPosition);
    }

    public void GripperUpDownSetPosition(double pos)
    {
        GripperUpDownR.setPosition(pos);
        GripperUpDownL.setPosition(1-pos);
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
