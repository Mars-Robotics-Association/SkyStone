package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vec2F;
import com.vuforia.Vuforia;

public class GetSkystoneConfiguration
{
    private Vec2F[] RedUpper = {new Vec2F(-24, -44), new Vec2F(-24, -36), new Vec2F(-24, -28)};
    private Vec2F[] RedLower = {new Vec2F(-24, -68), new Vec2F(-24, -60), new Vec2F(-24, -52)};

    private VuforiaLookForSkystones vuforia;
    private OpMode opMode;
    private boolean redTeam;
    private double closeEnoughThresholdDist = 2;

    public GetSkystoneConfiguration(OpMode newOpMode, boolean redSide)
    {
        opMode = newOpMode;
        redTeam = redSide;
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
        Vec2F samplePos = vuforia.GetSkystonePos();
        Vec2F otherPos = new Vec2F();

        if(CheckCloseEnoughDistance(samplePos, RedUpper[0]))
        {
            otherPos = RedLower[0];
        }
        if(CheckCloseEnoughDistance(samplePos, RedUpper[1]))
        {
            otherPos = RedLower[1];
        }
        if(CheckCloseEnoughDistance(samplePos, RedUpper[2]))
        {
            otherPos = RedLower[2];
        }
        if(CheckCloseEnoughDistance(samplePos, RedUpper[0]))
        {
            otherPos = RedUpper[0];
        }
        if(CheckCloseEnoughDistance(samplePos, RedUpper[1]))
        {
            otherPos = RedUpper[1];
        }
        if(CheckCloseEnoughDistance(samplePos, RedUpper[2]))
        {
            otherPos = RedUpper[2];
        }
        Vec2F[] finalLocs = {samplePos, otherPos};

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
