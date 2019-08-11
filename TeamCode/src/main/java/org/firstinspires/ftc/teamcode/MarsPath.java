package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
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
import com.sun.tools.javac.tree.DCTree;

import org.firstinspires.ftc.robotcore.external.Telemetry;
/**
 *
 *
 *
 * Created by Linda on 9/20/2018.
 */
//@Autonomous(name="getToDepot", group="Autonomous")
public class MarsPath {
    // private getToDepot redDepot = new getToDepot();
    private ElapsedTime runtime = new ElapsedTime();

    MarsRobotBase MRB = null;

    //precondition: |mirror| = 1
    //postcondition
  public void Path(double mirror, Telemetry telemetry, HardwareMap hardwareMap,LinearOpMode linopmode){
      MRB = new MarsRobotBase(telemetry,hardwareMap,linopmode);
     MRB.GoForward(10);
     MRB.GoLeft(20*mirror);
      MRB.GoForward(40);
      MRB.GoRight(20*mirror);
      MRB.GoForward(46);

     for(int i =0; i<15;i++) {
         MRB.bucketExtend(0.5, 1);
         double initial = runtime.seconds();
         while (runtime.seconds() < initial + 0.5) {

         }
         MRB.bucketExtend(0.5, -1);
         initial = runtime.seconds();
         while (runtime.seconds() < initial + 0.5) {

         }
     }
  }
}
