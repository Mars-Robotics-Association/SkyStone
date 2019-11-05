package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec2F;
import com.vuforia.Vec3F;

import java.util.List;


//Program should allow robot to move in a square pattern using a list of destinations



@Autonomous(name = "depotDouble", group = "Autonomous")
public class depotDouble extends OpMode
{
    float mirrorx = 1;
    float mirrory = 1;
    private FieldNavigationBot1 nav;
    private ExampleAttachment attachment;

    //Vec3F is a location structured as (x,y,rotation)
    private Vec3F[] navPoints = {
            //max value is 1828.8
            new Vec3F(mirrorx*-914,mirrory*-48,0),
            new Vec3F(mirrorx*-914,mirrory*48,0),
            new Vec3F(mirrorx*60,mirrory*48,0),
            new Vec3F(mirrorx*60,0,-90)

    };
    private int waypointIndex = 0;
    private boolean reachedDestination = true;
    @Override
    public void init()
    {
        //nav = new FieldNavigationBot1(this);
        attachment = new ExampleAttachment(this);
        nav.Init();
        attachment.Init();
    }
    @Override
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

