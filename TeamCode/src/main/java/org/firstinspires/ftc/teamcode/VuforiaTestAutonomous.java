package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec3F;


//Program should allow robot to move in a square pattern using a list of destinations
@Autonomous(name = "Vuforia Test Auto", group = "Autonomous")
public class VuforiaTestAutonomous extends OpMode
{
    private FieldNavigationBot1 nav;
    private ExampleAttachment attachment;

    //Vec3F is a location structured as (x,y,rotation)
    private Vec3F[] navPoints = {
            new Vec3F(-2000, -2000, 0),
            new Vec3F(2000, -2000, 90),
            new Vec3F(2000, 2000, 180),
            new Vec3F(-2000, 2000, 270)
    };
    private int waypointIndex = 0;
    private boolean reachedDestination = true;

    public void init()
    {
        nav = new FieldNavigationBot1(this);
        attachment = new ExampleAttachment(this);
        nav.Init();
        attachment.Init();
    }
    public void loop()
    {
        nav.Loop();
        attachment.Loop();
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
                attachment.Run();
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
}
