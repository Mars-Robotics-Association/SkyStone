package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec3F;

import java.util.Vector;

public class NavigationManager
{
    public boolean isNavigating = false;
    
    private FieldNavigationBot1 nav;
    private OpMode opMode;
    private double startAngleOffset;
    private Robot bot;

    public NavigationManager(OpMode newOpMode, double startAngle, Robot robot)
    {
        opMode = newOpMode;
        startAngleOffset = startAngle;
        bot = robot;
    }

    public void Init()
    {
        nav = new FieldNavigationBot1(opMode, startAngleOffset, bot);
    }

    public void Loop()
    {
        if(!nav.Navigating && !nav.Rotating)
        {
            isNavigating = false;
        }
        else
        {
            isNavigating = true;
        }
    }
    
    public void NavigateToLocation(double x, double y, double rot)
    {
        nav.NavigateToLocation(x, y, rot);
    }
    
    public void RotateTo(double angle)
    {
        nav.RotateTo(angle);
    }

    public void StopMoving()
    {
        nav.StopAll();
    }

    public Vec3F GetLocationAndRotation()
    {
        return nav.GetLocationAndRotation();
    }
    
}
