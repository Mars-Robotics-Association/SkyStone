

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */

public class tensorFlow {
    public boolean isVuforiaActive=false;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    public static int position = 0;
    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = "AYx4WHb/////AAAAGdNm8Or+eEMksoJYY3Yb/IBw58qzcy+cEb/VpEnMQGNqXtH7nppS40WcX0+9QwjDgKMRyXrQlK+SwLFzun99YdyNz1Et6o4erVZa8GU1G8lyuURTiIasy3WxfZ5mHLXkyabiEwXEVwzcP/wQWXVi7wJY+efylYN75biEKUGewV5X9wgICp9Lzyiext1eiHpIup2jYBxtCM24i0Gdo73keMKGPA7d7MSqpLqKj/UcMjljm8qYXF3eG1IdppGv4OApmo9rUbLfIpB62UUfQ1nASiVKCaD/qYF5huHaFIqwH9fq3wshGqtx2W2Nlqmn4Ka0iTSgGutMOrbYVt915+qaOQ9tL5VL/ogerL5Q/EqHyYMe";


    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;
    boolean objectDetected = false;
    boolean isGold = false;
    public boolean tensorFlow(Telemetry telemetry, HardwareMap hardwareMap, boolean opModeIsActive) {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        objectDetected=false;
        isGold=false;

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod(hardwareMap);
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin
         telemetry.addData(">", "Press Play to start  tracking");
         telemetry.update();
         waitForStart();
         */
        if (opModeIsActive) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }
            long x=System.currentTimeMillis();

            while (opModeIsActive&&System.currentTimeMillis()<x+1000) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {

                        //telemetry.addData("Recognitions Updated.  Recognitions = " , updatedRecognitions.size());
                        //
                        // telemetry.update();
                        //  telemetry.addData("# Object Detected", updatedRecognitions.size());

                        if(updatedRecognitions.size()==0){

                            if(System.currentTimeMillis()>=x+1000){

                                tfod.shutdown();
                            }
                        }

                        if (updatedRecognitions.size() == 1) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            int silverMineral2X = -1;

                            for (Recognition recognition : updatedRecognitions) {
                                //telemetry.addData("it sees stuff", recognition);
                                //telemetry.update();

                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();

                                  //  objectDetected = true;
                                    isGold = true;
                                    objectDetected = true;
                                    tfod.shutdown();
                                   // telemetry.addData("Gold", "Gold");
                                    //telemetry.update();
                              //      telemetry.addData("detected?", objectDetected );
                                    //tfod.shutdown();
                                    //break;
                                    //telemetry.update();
                                }
                                else if(recognition.getLabel().equals(LABEL_SILVER_MINERAL)) {
                                    //else{
                                    isGold = false;
                                   // telemetry.addData("Silver", "Sliver");
                                    //telemetry.update();
                                    objectDetected = true;
                                    tfod.shutdown();
                                }
                                else if(true){

                                }

                                    //break;
                                //}
                                      /*else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                } else {
                                    silverMineral2X = (int) recognition.getLeft();
                             *else if(recognition.getLabel().equals(LABEL_SILVER_MINERAL)){

                                    }
                                */

                            }
                            //telemetry.addData("tfod", position );
                            //telemetry.update();
                            return isGold;


                            /*
                            if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                                if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Left");
                                    position=1;
                                } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                                    telemetry.addData("Gold Mineral Position", "Right");
                                    position=2;
                                } else {
                                    telemetry.addData("Gold Mineral Position", "Center");
                                    position=3;
                                }
                            } else {

                            }*/
                        }
                        telemetry.update();
                    }
                }
            }
            telemetry.addData("tfod", position );
            telemetry.update();
            return objectDetected;
        }

       /* if (tfod != null) {
            return position;
            //tfod.shutdown();
        }
        else{*/
        telemetry.addData("tfod", position );
        telemetry.update();
        return objectDetected;

        //}
    }


    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        if(!isVuforiaActive) {
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             */
            isVuforiaActive = true;
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            parameters.vuforiaLicenseKey = "AYx4WHb/////AAAAGdNm8Or+eEMksoJYY3Yb/IBw58qzcy+cEb/VpEnMQGNqXtH7nppS40WcX0+9QwjDgKMRyXrQlK+SwLFzun99YdyNz1Et6o4erVZa8GU1G8lyuURTiIasy3WxfZ5mHLXkyabiEwXEVwzcP/wQWXVi7wJY+efylYN75biEKUGewV5X9wgICp9Lzyiext1eiHpIup2jYBxtCM24i0Gdo73keMKGPA7d7MSqpLqKj/UcMjljm8qYXF3eG1IdppGv4OApmo9rUbLfIpB62UUfQ1nASiVKCaD/qYF5huHaFIqwH9fq3wshGqtx2W2Nlqmn4Ka0iTSgGutMOrbYVt915+qaOQ9tL5VL/ogerL5Q/EqHyYMe";
            parameters.cameraDirection = CameraDirection.BACK;

            //  Instantiate the Vuforia engine
            vuforia = ClassFactory.getInstance().createVuforia(parameters);

        }// Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod(HardwareMap hardwareMap) {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
