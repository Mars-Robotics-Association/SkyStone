package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec2F;
import com.vuforia.Vec3F;

import java.util.List;


//Program should allow robot to move in a square pattern using a list of destinations
@Autonomous(name = "SquareAutonomous", group = "Autonomous")
public class SquareAutonomous extends OpMode
{
    static FieldNavigation nav;

    //Vec3F is a location structured as (x,y,rotation)
    static Vec3F[] navPoints = {
            new Vec3F(-2000, -2000, 0),
            new Vec3F(2000, -2000, 90),
            new Vec3F(2000, 2000, 180),
            new Vec3F(-2000, 2000, 270)
    };
    static int waypointIndex = 0;
    static boolean reachedDestination = true;

    public void init()
    {
        nav = new FieldNavigation();
    }
    public void loop()
    {
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
