package org.firstinspires.ftc.teamcode;

public class _JavaDebugSketchpad
{
    private static double TargetX = 36;
    private static double TargetY = -36;
    private static double CurrentX = -36;
    private static double CurrentY = -36;
    private static double closeEnoughThresholdDist = 5;
    private static double StartAngle = 180;
    private static double absoluteAngle = 0;

    public static void main(String[] args) 
    {
        //Calculate angle of movement (no obstacle avoidance)
        double triY = Math.abs(CurrentY + TargetY); //vertical length
        double triX = Math.abs(CurrentX + TargetX); //horizontal length
        absoluteAngle = Math.atan2(triY,triX); //get measurement of joystick angle
        absoluteAngle = Math.toDegrees(absoluteAngle);
        absoluteAngle -= StartAngle;
        if(absoluteAngle < 0)//convert degrees to positive if needed
        {
            absoluteAngle = 360 + absoluteAngle;
        }
        System.out.println(absoluteAngle);
    }

    // public static void main(String[] args) 
    // {
    //     double out = Math.sqrt(Math.pow(TargetX - CurrentX, 2) + Math.pow(TargetY - CurrentY, 2));
    //     System.out.println(out);
    //     boolean b = out < closeEnoughThresholdDist;
    //     System.out.println(b);
    // }
}