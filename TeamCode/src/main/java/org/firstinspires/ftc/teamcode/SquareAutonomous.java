package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.Vec3F;

@Disabled
//Test Program for using a linear system of navigation with the WaypointManager
@Autonomous(name = "SquareAutonomous", group = "Autonomous")
public class SquareAutonomous extends LinearOpMode {
    private NavigationManager nav;
    private SkyStoneBot bot;
    private ElapsedTime runtime = new ElapsedTime();
    private ExampleAttachment exampleAttachment;


    @Override
    public void runOpMode() {
        //==========INIT==========
        bot = new SkyStoneBot(this, false);
        nav = new NavigationManager(this, 180, bot);
        exampleAttachment = new ExampleAttachment(this);

        //Init objects
        nav.Init();
        bot.Init();
        exampleAttachment.Init();

        //put all your attachments into an array here for looping
        Attachment[] attachments = {exampleAttachment};

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //==========START==========
        runtime.reset();
        nav.Start();

        //==========LINEAR MAIN==========

        //***Corner 1***
        nav.NavigateToLocation(-36, -36, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Corner 2***
        nav.NavigateToLocation(-36, 36, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Corner 3***
        nav.NavigateToLocation(36, 36, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Corner 4***
        nav.NavigateToLocation(-36, 36, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Attachment***
        exampleAttachment.YourCustomMethod(1);//Run the attachment
        while (exampleAttachment.isRunning){LoopObjects(attachments);}//Loop the various objects in the OpMode
        exampleAttachment.Stop();//stop when done

    }

    public void LoopObjects(Attachment[] attachmentsToLoop)
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