package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "simpleBlueZone", group = "Autonomous")
public class simpleBlueZone extends LinearOpMode {

    private SimpleFieldNavigation bot;
    private SkyStoneBot SSB;

    public void Init() {
        bot.Init();
        SSB = new SkyStoneBot(this);
        bot = new SimpleFieldNavigation(this,SSB );
    }

    @Override
    public void runOpMode() {

        bot.GoForward(25,false);
        bot.GoRight(-1,true);
        while(bot.IsOnTheLine()){
            telemetry.addData("searching",0);
        }
        bot.StopAll();
    }
}
