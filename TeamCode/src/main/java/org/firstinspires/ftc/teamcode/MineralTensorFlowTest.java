package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 *
 *
 * Created by Linda on 9/20/2018.
 *
 */
//@Autonomous(name="MineralTensorFlowTest", group="Autonomous")
public class MineralTensorFlowTest extends LinearOpMode{
    // private getToDepot redDepot = new getToDepot();
    double sq8 = 2.8284*12;
    double mineralLength = 4;
    BlueCraterVuforiaTest SL =null;
    tensorFlow TF = null;
    robotBase RB = null;
    getToDepot Depot = null;

    //private ElapsedTime runtime = new ElapsedTime();
  /*  OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
  */


    //private robotBase basics= new robotBase();
    @Override
    public void runOpMode()
    {   //int mirror controls the mirroring of the program. positive is red by default
        //robotBase.moveleft;
        // public void drive(){
        ////////////////////
 /*       int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.vuforiaLicenseKey = "AYx4WHb/////AAAAGdNm8Or+eEMksoJYY3Yb/IBw58qzcy+cEb/VpEnMQGNqXtH7nppS40WcX0+9QwjDgKMRyXrQlK+SwLFzun99YdyNz1Et6o4erVZa8GU1G8lyuURTiIasy3WxfZ5mHLXkyabiEwXEVwzcP/wQWXVi7wJY+efylYN75biEKUGewV5X9wgICp9Lzyiext1eiHpIup2jYBxtCM24i0Gdo73keMKGPA7d7MSqpLqKj/UcMjljm8qYXF3eG1IdppGv4OApmo9rUbLfIpB62UUfQ1nASiVKCaD/qYF5huHaFIqwH9fq3wshGqtx2W2Nlqmn4Ka0iTSgGutMOrbYVt915+qaOQ9tL5VL/ogerL5Q/EqHyYMe";
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
       parameters.cameraMonitorFeedback= VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
  //     this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
      VuforiaLocalizer vuforia=ClassFactory.createVuforiaLocalizer(parameters);
*/
        ///////////////////
        //RB = new robotBase(telemetry, hardwareMap);
        //TF = new tensorFlow();
        Depot = new getToDepot();
        //SL= new BlueCraterVuforiaTest();


            Depot.Mineral(telemetry, hardwareMap, true);




    }
}
