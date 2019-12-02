package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


@Autonomous(name = "simpleRedDepot", group = "Autonomous")
public class simpleRedDepot extends LinearOpMode {

    private SimpleFieldNavigation nav;
    private SkyStoneBot SSB;
    private ExampleAttachment exampleAttachment;

    @Override
    public void runOpMode()
    {
        nav.Init();
        SSB = new SkyStoneBot(this);
        nav = new SimpleFieldNavigation(this,SSB );
        exampleAttachment = new ExampleAttachment(this);
        exampleAttachment.Init();

        Attachment[] attachments = {exampleAttachment};

        waitForStart();

        nav.GoForward(25, false);
        while (nav.isNavigating()){LoopOpjects(attachments);}

        nav.GoRight(-1000, false);
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
