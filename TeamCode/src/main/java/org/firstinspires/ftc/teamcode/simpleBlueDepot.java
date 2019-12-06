package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "simpleBlueDepot", group = "Autonomous")
public class simpleBlueDepot extends LinearOpMode {

    private SimpleFieldNavigation bot;



    @Override
    public void runOpMode() {
        bot.Init();
        bot = new SimpleFieldNavigation(this);
        waitForStart();
        bot.GoForward(25,false);
        bot.GoRight(1,true);
        while(bot.IsOnTheLine()){
            telemetry.addData("searching",0);
        }
        bot.StopAll();
    }
}
