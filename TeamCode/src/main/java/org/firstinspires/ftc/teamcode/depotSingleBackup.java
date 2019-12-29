package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.Vec3F;



@Disabled
@Autonomous(name = "depotSingleBackup", group = "Autonomous")
public class depotSingleBackup extends LinearOpMode {

    private DcMotor FrontRight;
    private DcMotor FrontLeft;
    private DcMotor RearRight;
    private DcMotor RearLeft;



    private SkyStoneBot skystonebot;



    @Override
    public void runOpMode() {




        skystonebot.MoveAtAngle(0,1,false);


    }
}
