package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import java.io.File;
@Disabled
@TeleOp(name="roger", group="Iterative Opmode")
public class roger extends OpMode {
    private File silverFile = null;
    private File goldFile = null;
    private File bronzeFile = null;
    private File cobaltFile = null;

    private String soundPath = "/FIRST/blocks/sounds";





    @Override
    public void init() {
        silverFile = new File("/sdcard" + soundPath + "/silver.wav");
     //   goldFile = new File("/sdcard" + soundPath + "/gold.wav");
        bronzeFile = new File("/sdcard" + soundPath + "/bronze.wav");
        cobaltFile = new File("/sdcard" + soundPath + "/cobalt.wav");

    }

    @Override
    public void start()
    {

    }
    @Override
    public void loop()
    {
        if(gamepad1.dpad_down){
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, silverFile);

            telemetry.addData("playing sound","Silver");
            telemetry.update();
        }

        if(gamepad1.dpad_up){
           SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, bronzeFile);

            telemetry.addData("playing sound","Bronze");
            telemetry.update();
        }
        if(gamepad1.dpad_left){
           // SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, goldFile);

            telemetry.addData("playing sound","Gold");
            telemetry.update();
        }
        if(gamepad1.dpad_right){
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, cobaltFile);

            telemetry.addData("playing sound","Cobalt");
            telemetry.update();
        }





    }

    public void dependencies(){


         File silverFile = null;
         File goldFile = null;
         File bronzeFile = null;
         File cobaltFile = null;

         String soundPath = "/FIRST/blocks/sounds";

            silverFile = new File("/sdcard" + soundPath + "/silver.wav");
            //   goldFile = new File("/sdcard" + soundPath + "/gold.wav");
            bronzeFile = new File("/sdcard" + soundPath + "/bronze.wav");
            cobaltFile = new File("/sdcard" + soundPath + "/cobalt.wav");



    }


    public void playSilver(){
        dependencies();
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, silverFile);
    }
    public void playGold(){
        dependencies();
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, goldFile);
    }
    public void playCobalt(){
        dependencies();
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, cobaltFile);
    }
    public void playBronze(){
        dependencies();
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, bronzeFile);
    }
}