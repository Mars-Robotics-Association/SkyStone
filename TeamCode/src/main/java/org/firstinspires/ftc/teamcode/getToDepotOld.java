package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;



/**
 *
 *
 *
 * Created by Linda on 9/20/2018.
 */
//@Autonomous(name="getToDepot", group="Autonomous")

public class getToDepotOld {
    // private getToDepot redDepot = new getToDepot();
    tensorFlow TF = null;
    robotBase RB = null;
    //private ElapsedTime runtime = new ElapsedTime();
    //OpenGLMatrix lastLocation = null;
    //VuforiaLocalizer vuforia;
    //private robotBase basics= new robotBase();
    //@Override
    public void drive(Telemetry telemetry, HardwareMap hardwareMap, boolean isDepot, boolean opModeIsActive)
    {   //int mirror controls the mirroring of the program. positive is red by default
        //robotBase.moveleft;
        // public void drive(){
      //  RB = new robotBase(telemetry, hardwareMap,true);
       //  TF = new tensorFlow(telemetry, hardwareMap, opModeIsActive);
        if(isDepot){
           /* RB.TurnLeft(135);
            RB.GoForward(15);
            RB.GoRight(15);
            RB.TurnLeft(45);
            RB.GoRight(23);
            RB.GoForward(28.5);
        */            RB.TurnRight (180);

        }
        else{
            RB.TurnLeft(90);



           /*
           //this is the proper code
            RB.TurnLeft(135);
            RB.GoForward(11);
            RB.TurnLeft(135);
            RB.DiagonalNE(56.125);
            RB.GoForward(20.314 );
*/


          /* RB.TurnLeft(180);

             RB.TurnRight(180); */
        }
    }
}
