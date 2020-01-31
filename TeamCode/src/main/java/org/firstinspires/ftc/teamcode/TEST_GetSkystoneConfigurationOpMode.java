package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
@Disabled
public class TEST_GetSkystoneConfigurationOpMode extends OpMode
{
    GetSkystoneConfiguration getConfig;

    @Override
    public void init()
    {
        getConfig = new GetSkystoneConfiguration(this, true, 48, 0);
        getConfig.Init();
    }

    @Override
    public void loop()
    {
        getConfig.Loop();
        telemetry.addData("Skystone Config: ", getConfig.GetSkystonePos());
    }
}
