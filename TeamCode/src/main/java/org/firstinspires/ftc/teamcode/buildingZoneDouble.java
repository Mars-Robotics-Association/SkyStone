package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.Vec3F;

@Disabled
//Test Program for using a linear system of navigation with the WaypointManager
@Autonomous(name = "buildingZoneDouble", group = "Autonomous")
public class buildingZoneDouble extends LinearOpMode {
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
        nav.NavigateToLocation(-36, 48, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Corner 2***
        nav.NavigateToLocation(60, 48, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Corner 3***
        nav.NavigateToLocation(-36, -48, 0);//Go to a location
        while (nav.isNavigating){LoopObjects(attachments);}//Loop the various objects in the OpMode
        nav.StopMoving();//stop when done

        //***Corner 4***
        nav.NavigateToLocation(-36, 36, -90);//Go to a location
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



    /*private FieldNavigationBot1 nav;
    //private ExampleAttachment attachment;

    //Vec3F is a location structured as (x,y,rotation)
    private Vec3F[] navPoints;
    private int waypointIndex = 0;
    private boolean reachedDestination = true;

    @Override
    public void init()
    {
        telemetry.addData("SA Line: ", 28);
        nav = new FieldNavigationBot1(this, 180);
        telemetry.addData("SA Line: ", 30);
        //attachment = new ExampleAttachment(this);
        nav.Init();
        telemetry.addData("SA Line: ", 33);
        //attachment.Init();

        navPoints  = new Vec3F[]{
                new Vec3F(-36, -36, 0),
                new Vec3F(-36, 36, 90),
                new Vec3F(36, 36, 180),
                new Vec3F(36, -36, 270)
        };
    }

    @Override
    public void start()
    {
        nav.Start();
    }

    @Override
    public void loop()
    {
        nav.Loop();
        telemetry.addData("SA Line: ", 48);
        telemetry.update();
        //attachment.Loop();
        telemetry.addData("index: ", waypointIndex);
        if(reachedDestination) //navigate to next waypoint
        {
            //get vector data and set destination
            Vec3F target = navPoints[waypointIndex];
            float[] data = target.getData();
            nav.NavigateToLocation(data[0],data[1],data[2]);
            reachedDestination = false;

            if(waypointIndex >= navPoints.length -1)//reached end
            {
                waypointIndex = 0; //reset
            }
            else waypointIndex +=1; //add to index

            if(waypointIndex == 1)
            {
                //attachment.Run();
            }
        }
        else //check if done navigating
        {
            if(nav.CheckCloseEnoughDistance() && nav.CheckCloseEnoughRotation())
            {
                reachedDestination = true;
            }
        }
    }
*/