package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.Vec3F;


//Test Program for using a linear system of navigation with the WaypointManager
@Autonomous(name = "SquareAutonomous", group = "Autonomous")
public class SquareAutonomous extends LinearOpMode
{
    private NavigationManager navigationManager;
    private SkyStoneBot bot;
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode()
    {
        //INIT
        bot = new SkyStoneBot(this);
        navigationManager = new NavigationManager(this, 180, bot);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        //START
        runtime.reset();

        //Start LOOP thread
        LoopThread R1 = new LoopThread( navigationManager);
        R1.start();

        //LOOP


    }

    //this loop runs in the backround
    public class LoopThread implements Runnable {
        private Thread t;
        NavigationManager n;

        LoopThread( NavigationManager setNavigationManager)
        {
            n = setNavigationManager;
        }

        public void run()
        {
            //LOOP
            while(opModeIsActive())
            {
                n.Loop();
            }
            n.StopMoving();
        }

        public void start () {
            System.out.println("Starting " +  "LoopThread" );
            if (t == null) {
                t = new Thread (this, "LoopThread");
                t.start ();
            }
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