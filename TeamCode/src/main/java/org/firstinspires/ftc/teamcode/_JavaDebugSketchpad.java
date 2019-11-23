package org.firstinspires.ftc.teamcode;

public class _JavaDebugSketchpad
{
    private static double TargetX = 36;
    private static double TargetY = 36;
    private static double CurrentX = -36;
    private static double CurrentY = 36;
    private static double closeEnoughThresholdDist = 5;
    private static double StartAngle = 180;
    private static double absoluteAngle = 0;


    public static void main(String[] args) 
    {
        //Calculate angle of movement (no obstacle avoidance)
        double triY = (CurrentY - TargetY); //vertical length
        double triX = (CurrentX - TargetX); //horizontal length
        absoluteAngle = Math.atan2(triY,triX); //get measurement of move angle
        absoluteAngle = Math.toDegrees(absoluteAngle);
        System.out.println(absoluteAngle);
        absoluteAngle -= StartAngle + 180; //offsets by start angle of the robot
        System.out.println(absoluteAngle);
        if(absoluteAngle < 0)//convert degrees to positive if needed
        {
            absoluteAngle = 360 + absoluteAngle;
        }
        System.out.println(absoluteAngle);
        System.out.println(triY);
        System.out.println(triX);
    }

    // //Calculate angle of movement
    // double triY = (CurrentY - TargetY); //vertical length
    // double triX = (CurrentX - TargetX); //horizontal length
    // absoluteAngle = Math.atan2(triY,triX); //get measurement of move angle
    // absoluteAngle = Math.toDegrees(absoluteAngle);
    // absoluteAngle -= StartAngle; //offsets by start angle of the robot
    // if(absoluteAngle < 0)//convert degrees to positive if needed
    // {
    //     absoluteAngle = 360 + absoluteAngle;
    // }
}


