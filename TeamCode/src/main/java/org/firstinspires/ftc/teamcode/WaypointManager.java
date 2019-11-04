package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec3F;

import java.util.Vector;

public class WaypointManager
{
    private FieldNavigationBot1 nav;
    private OpMode opMode;
    private double startAngleOffset;
    private Robot bot;

    public WaypointManager(OpMode newOpMode, double startAngle, Robot robot)
    {
        opMode = newOpMode;
        startAngleOffset = startAngle;
        bot = robot;
    }

    public void Init()
    {
        nav = new FieldNavigationBot1(opMode, startAngleOffset, bot);
        
    }
}
