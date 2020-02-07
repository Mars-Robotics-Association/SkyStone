package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class CapstoneDeployer implements Attachment {
    private Servo Deploy = null;

    OpMode opmode;

    public CapstoneDeployer(OpMode thatopmode) {
        opmode = thatopmode;
    }

    @Override
    public void Init() {

        Deploy = opmode.hardwareMap.servo.get("CapstoneDeployer");

    }

    @Override
    public void Loop() {

    }

    @Override
    public void Run() {

    }

    @Override
    public void Stop()
    {
//test
    }

    public void DeployCapstone () //Moves the lift up and resets the resting target for LiftStopVertical
    {
        Deploy.setPosition(180);
    }

    public void RetractCapstone () //Moves the lift down and resets the resting target for LiftStopVertical
    {
        Deploy.setPosition(0);
    }
}