package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Sensor_Distance
{
    private String name = "sensor_range";
    private DistanceSensor sensorRange;
    private OpMode opMode;

    public Sensor_Distance(OpMode setOpMode, String newName)
    {
        opMode = setOpMode;
        name = newName;
    }

    public void Init()
    {
        // you can use this as a regular Sensor_Distance.
        sensorRange = opMode.hardwareMap.get(DistanceSensor.class, name);

        // you can also cast this to a Rev2mDistanceSensor if you want to use added
        // methods associated with the Rev2mDistanceSensor class.
        //Rev2mDistanceSensor sensorTimeOfFlight = (Rev2mDistanceSensor)sensorRange;
    }

    public double GetRangeCM()
    {
        return sensorRange.getDistance(DistanceUnit.CM);
    }
}
