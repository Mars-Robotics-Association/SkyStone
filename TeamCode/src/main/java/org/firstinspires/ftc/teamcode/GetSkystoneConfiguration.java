package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec2F;

public class GetSkystoneConfiguration
{
    private Vec2F[] RedUpper = {new Vec2F(-24, -44), new Vec2F(-24, -36), new Vec2F(-24, -28)};
    private Vec2F[] RedLower = {new Vec2F(-24, -68), new Vec2F(-24, -60), new Vec2F(-24, -52)};

    private VuforiaLookForSkystones vuforia;
    private OpMode opMode;
    private boolean redTeam;
    private double closeEnoughThresholdDist = 2;
    private double StartX;
    private double StartY;

    public GetSkystoneConfiguration(OpMode newOpMode, boolean redSide, double startX, double startY)
    {
        opMode = newOpMode;
        redTeam = redSide;
        StartX = startX;
        StartY = startY;
    }

    public void Init()
    {
        vuforia = new VuforiaLookForSkystones(opMode, "Webcam1");
        vuforia.Init();
    }

    public void Loop()
    {

    }

    public Vec2F[] GetSkystonePos()
    {
        Vec2F relativePos = vuforia.GetRelativeRobotPos();
        Vec2F firstSkystonePos = new Vec2F();
        Vec2F secondSkystonePos = new Vec2F();

        double firstX = StartX - relativePos.getData()[0];
        double firstY = StartY - relativePos.getData()[1];
        firstSkystonePos = new Vec2F((float)firstX, (float)firstY);

        if(CheckCloseEnoughDistance(firstSkystonePos, RedUpper[0]))
        {
            secondSkystonePos = RedLower[0];
        }
        if(CheckCloseEnoughDistance(firstSkystonePos, RedUpper[1]))
        {
            secondSkystonePos = RedLower[1];
        }
        if(CheckCloseEnoughDistance(firstSkystonePos, RedUpper[2]))
        {
            secondSkystonePos = RedLower[2];
        }
        if(CheckCloseEnoughDistance(firstSkystonePos, RedUpper[0]))
        {
            secondSkystonePos = RedUpper[0];
        }
        if(CheckCloseEnoughDistance(firstSkystonePos, RedUpper[1]))
        {
            secondSkystonePos = RedUpper[1];
        }
        if(CheckCloseEnoughDistance(firstSkystonePos, RedUpper[2]))
        {
            secondSkystonePos = RedUpper[2];
        }
        Vec2F[] finalLocs = {firstSkystonePos, secondSkystonePos};

        return finalLocs;
    }

    public boolean CheckCloseEnoughDistance(Vec2F labValue, Vec2F calculatedValue)
    {
        //use distance formula to check if in radius
        if(Math.sqrt(Math.pow(labValue.getData()[0] - calculatedValue.getData()[0], 2) + Math.pow(labValue.getData()[1] - calculatedValue.getData()[1], 2)) < closeEnoughThresholdDist)
        {
            return true;
        }
        else return false;
    }
}
