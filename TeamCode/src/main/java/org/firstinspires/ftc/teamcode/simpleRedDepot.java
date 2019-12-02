package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "simpleRedDepot", group = "Autonomous")
public class simpleRedDepot extends LinearOpMode {

    private SimpleFieldNavigation nav;
    private SkyStoneBot SSB;

    public void Init() {
        nav.Init();
        SSB = new SkyStoneBot(this);
        nav = new SimpleFieldNavigation(this,SSB );
    }

    @Override
    public void runOpMode() {

        nav.GoForward(25,false);
        nav.GoRight(-1,true);
        while(nav.IsOnTheLine()){
            telemetry.addData("searching",0);
        }
        nav.StopAll();
    }

    public void LoopOpjects(Attachment[] attachmentsToLoop)
    {
        //loop navigation manager
        nav.Loop();
        //loop all the attachments
        for (Attachment a: attachmentsToLoop)
        {
            a.Loop();
        }
    }
}
