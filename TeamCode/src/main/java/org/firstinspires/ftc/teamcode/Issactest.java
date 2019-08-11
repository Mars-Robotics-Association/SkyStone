package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 *
 *
 * Created by Linda on 9/20/2018.
 */
public class Issactest {
    // private getToDepot redDepot = new getToDepot();
    double sq8 = 2.8284*12 -2;
    double mineralLength = 10;
    BlueCraterVuforiaTest SL =null;
    tensorFlow TF = null;
    robotBase RB = null;
    boolean middlePosition;
    double mineralposition;
    double mineralOffset=0;
    //private ElapsedTime runtime = new ElapsedTime();
  /*  OpenGLMatrix lastLocation = null;
    VuforiaLocalizer vuforia;
  */public void Mineral(Telemetry telemetry, HardwareMap hardwareMap, boolean depo){
        //RB = new robotBase(telemetry, hardwareMap);
        TF = new tensorFlow();

        int gold=1;
        boolean s;
        double mineralReturnLegnth=5;
        double offset=5;
        boolean found = false;
        while (gold==1){
          //if(gold==1){
              //it 1
              //RB.GoForward(sq8/2);
              RB.GoRight(mineralReturnLegnth);
              //TF.tensorFlow(telemetry, hardwareMap, true);
              s=TF.tensorFlow(telemetry, hardwareMap, true);
              found=s;
              if(found){
                  if(!depo){
                      RB.GoLeft(mineralReturnLegnth);
                      //RB.GoBack(offset);
                      RB.GoLeft(mineralLength);
                      RB.collectorExtend(2);
                      //RB.TurnRight(135);
                      //RB.DiagonalSE(mineralLength);
                  }
                  //RB.GoRight(mineralLength);
                  //RB.GoBack(sq8/2);
                  if(depo){
                    RB.GoLeft(mineralLength);
                      //RB.TurnLeft(90);
                      RB.TurnRight(90);
                      RB.DiagonalSE(14);
                      RB.DiagonalSW(20);
                      RB.deposit();

                  }
                  break;
              }
              RB.GoForward(sq8/2);



            s=TF.tensorFlow(telemetry, hardwareMap, true);
              found=s;
              if(found){
                  if(!depo) {
                      RB.GoLeft(mineralReturnLegnth);
                      //RB.GoBack(offset);
                      RB.GoLeft(mineralLength);
                      RB.collectorExtend(2);
                      /*   RB.GoBack(10);
                      RB.TurnRight(45);
                      RB.DiagonalSW(mineralLength);
                  */
                  }
                  if(depo){
                      RB.TurnRight(45);
                      RB.DiagonalSW(mineralLength);
                    RB.DiagonalSW(sq8+10);
                    RB.deposit();
                  }
                    //RB.GoRight(mineralLength);
                  break;
              }

              RB.GoForward(sq8/2);
              if (!depo) {
                  RB.GoLeft(mineralReturnLegnth);
                 // RB.GoBack(offset);
                  RB.GoLeft(mineralLength);
                  //RB.TurnRight(135);
                  //RB.DiagonalSE(mineralLength);
                  RB.collectorExtend(2);
              }
              else{
                  RB.GoLeft(mineralLength);
                  RB.DiagonalNW(14);
                  RB.DiagonalSW(20);
                  RB.deposit();

              }

              //  RB.GoRight(mineralLength);
             // RB.GoForward(sq8/2);
                break;


          }
          gold = 2;
      }
      /*if(gold==1){
            RB.GoLeft(sq8/2);
            RB.GoBack(mineralLength);
            RB.GoForward(mineralLength);
            RB.GoRight(sq8/2);a

        }
        if(gold==2){

            RB.GoBack(mineralLength);
            RB.GoForward(mineralLength);
        }
        if(gold==3){
            RB.GoLeft(sq8/2);
            RB.GoBack(mineralLength);
            RB.GoForward(mineralLength);
            RB.GoRight(sq8/2);
        }*/
    //}

    //private robotBase basics= new robotBase();
    //@Override
    public void drive(Telemetry telemetry, HardwareMap hardwareMap, boolean isDepot, boolean legal)
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
        TF = new tensorFlow();
        SL= new BlueCraterVuforiaTest();
        RB.liftUp();
        RB.DiagonalSE(8);
        RB.TurnLeft(45);
        RB.GoForward(3);
        //tweaked from 5 to better align with minerals.
        RB.GoLeft(12);
        middlePosition=TF.tensorFlow(telemetry, hardwareMap, true);
        if(middlePosition){
            RB.GoLeft(14);
            RB.GoRight(13);
            mineralOffset=0;
            //RB.GoBack(45);

        }
        else
        {
 //           RB.GoLeft(12);
            RB.TurnLeft(45);
            middlePosition=TF.tensorFlow(telemetry, hardwareMap, true);
            if(middlePosition){
                RB.TurnLeft(10);
                RB.GoLeft(24);
                RB.TurnRight(55);
                RB.GoRight(12);
                mineralOffset=-sq8;
                //RB.GoBack(45-sq8);
            }
            else{
                RB.TurnRight(100);
                RB.GoLeft(24 );
                RB.TurnLeft(55);
                RB.GoRight(12);
                mineralOffset=sq8;
                //RB.GoBack(45+sq8);
            }
        }

        //the same for both

        if (isDepot){
            if(mineralOffset==0) {
                //false condition added to nullify code

                RB.TurnRight(90);
                RB.GoRight(48 + mineralOffset);
                RB.DiagonalNW(6);
                RB.collectorExtend(1);
                RB.DiagonalSW(50);
                RB.deposit();
                SL.sleep(1000);
                RB.DiagonalNE(10);
                RB.retract();
            }
            if(mineralOffset==0){

            }
           // RB.TurnLeft(80);
            //RB.CollectorExtend();

            //RB.DiagonalSE(10);

        }
        else {
            if(mineralOffset==0){
                RB.TurnRight(135);
            }
            else if (mineralOffset>0){
                RB.TurnRight(180);
            }
            else{
                RB.TurnRight(90);
            }
            RB.DiagonalSE(5);
            RB.collectorExtend(2);


            if(mineralOffset==0&&false) {
            //false condition added to nullify code
                RB.TurnLeft(90);
            RB.GoLeft(48+mineralOffset);
            RB.DiagonalNW(5);
            RB.DiagonalSW(35);
            RB.DiagonalNW(5);
            RB.deposit();
            SL.sleep(1000);
            RB.DiagonalNE(34);
            RB.retract();

               // RB.TurnLeft(80);
                //RB.CollectorExtend();

//                RB.DiagonalSE(34);
            }
            }


/*

        RB.DiagonalSE(5); b
        //RB.liftDown();
        //SL.sleep(2000);
        RB.DiagonalSW(12.5);//RB.DiagonalNE(3);
        RB.DiagonalSE(11);
        RB.TurnLeft(45);
        // RB.GoRight(3);
        //RB.GoLeft(12.5);
            //RB.GoForward(2);
            //RB.TurnLeft(45);
            //TF.tensorFlow(telemetry, hardwareMap, true);
            //boolean gold=TF.tensorFlow(telemetry, hardwareMap, true);

//        Mineral(telemetry, hardwareMap, isDepot);

        if(isDepot) {
            RB.retract();
            SL.sleep(5000);
            RB.TurnRight(45);
            //Mineral(11);
            //up to here

            /*if(legal){
                RB.GoBack();
                RB.TurnRight(45);
                RB.GoLeft();
            }
            else{
               /* RB.GoBack(18);
                RB.TurnRight(45);
                RB.GoBack(20);
                RB.GoLeft(30);
            //}
            RB.TurnRight(45);
            RB.deposit();
            RB.retract();
            if(legal){
                RB.TurnLeft(45);
                RB.GoRight(58);
            }
        }
        else{
            //up to here
           RB.DiagonalSE(0);
            /* RB.TurnLeft(135);
            RB.DiagonalNW(54);
            RB.GoForward(3.5);
            RB.GoBack(3.5);
            RB.GoLeft(36);
            RB.TurnRight(45);
            RB.deposit();
            RB.retract();
            RB.TurnLeft(45);
            //RB.TurnLeft(45);
            RB.GoRight(56);*/

            /* RB.TurnLeft(180);
*/
  //           RB.TurnRight(180); */
        //}
    }
}
