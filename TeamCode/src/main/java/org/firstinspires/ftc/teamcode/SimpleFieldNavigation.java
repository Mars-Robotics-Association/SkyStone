package org.firstinspires.ftc.teamcode;

/*
Class that completes the following goals:
    >Allows code to set destinations for the robot
    >Contains a coroutine for navigating to destination
    >Contains a function/method for getting the rotation at game start
    >Eventually allows for obstacle avoidance
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.vuforia.Vec3F;


public class SimpleFieldNavigation
{
    private double closeEnoughThresholdDist = 10; //in inches
    private double closeEnoughThresholdRot = 5; //in degrees

    NormalizedColorSensor colorSensorGround;
    private SkyStoneBot Bot;

    public boolean Navigating = false;
    public boolean Rotating = false;

    private OpMode opmode;

    public boolean firstRound = true;

    private double TargetRot;
    private double CurrentRot;

    public SimpleFieldNavigation(OpMode setOpmode, Robot robot)
    {
        this.opmode = setOpmode;
    }

    public boolean isNavigating()
    {
        if(!Navigating && !Rotating)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void Init()
    {
        Bot = new SkyStoneBot(opmode);
        Bot.Init();
        colorSensorGround = opmode.hardwareMap.get(NormalizedColorSensor.class, "colorSensorGround");

    }

    public void Start()
    {
        Bot.Start();
        opmode.telemetry.setMsTransmissionInterval(20);//update telemetry faster
    }

    public void Loop()
    {
        Bot.Loop();

        if(firstRound)
        {
            Bot.OffsetGyro();
            firstRound = false;
        }

        if(Navigating)
        {
            if (!Bot.CheckIfEncodersCloseEnough()) //If not close to target
            {
                opmode.telemetry.addData("Not close enough: ", true);
            }
            else //Stop
            {
                opmode.telemetry.addData("Not close enough: ", false);
                Bot.StopEncoders();
                //Bot.RotateTo(TargetRot, 0.5);
                //Rotating = true;
                Navigating = false;
            }
            opmode.telemetry.update();
        }

        if(Rotating)
        {
            if (!CheckCloseEnoughRotation()) //if not at rotation target
            {
                Bot.RotateTo(TargetRot, 1);
            }
            else //Stop
            {
                Bot.StopMotors();
                Rotating = false;
            }
        }

        if(!Navigating && !Rotating)//stop if shouldn't do anything
        {
            Bot.StopMotors();
        }
    }

    public void RotateTo(double angle)
    {
        StopAll();
        Rotating = true;
    }

    public void StopAll()//Stops everything movement-wise
    {
        Bot.StopMotors();
        Navigating = false;
        Rotating = false;
    }

    /*public boolean CheckCloseEnoughDistance()
    {
        //use distance formula to check if in radius
        if(Math.sqrt(Math.pow(TargetX - CurrentX, 2) + Math.pow(TargetY - CurrentY, 2)) < closeEnoughThresholdDist)
        {
            return true;
        }
        else return false;
    }

    public boolean CheckCloseEnoughRotation()
    {
        if(CurrentRot < TargetRot + closeEnoughThresholdRot && CurrentRot > TargetRot - closeEnoughThresholdRot)
        {
            return true;
        }
        else return false;
    }*/

    public void GoRight(double distance, boolean infinite)
    {
        Bot.MoveAtAngle(90*(distance/Math.abs(distance)), 1, false);
        Navigating = true;
        Rotating = false;
    }

    public void GoForward(double distance, boolean infinite)
    {
        Bot.GoForwardWithEncoder(1, distance);
        Navigating = true;
        Rotating = false;
    }

    public boolean IsOnTheLine(){

        if (colorSensorGround instanceof SwitchableLight) {
            ((SwitchableLight)colorSensorGround).enableLight(true);
        }
        NormalizedRGBA colors = colorSensorGround.getNormalizedColors();
        opmode.telemetry.addLine()
                .addData("a", "%.3f", colors.alpha)
                .addData("r", "%.3f", colors.red)
                .addData("g", "%.3f", colors.green)
                .addData("b", "%.3f", colors.blue);
        opmode.telemetry.update();

        if(colors.red>170||colors.blue>170){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean CheckCloseEnoughRotation()
    {
        if(CurrentRot < TargetRot + closeEnoughThresholdRot && CurrentRot > TargetRot - closeEnoughThresholdRot)
        {
            return true;
        }
        else return false;
    }

}
