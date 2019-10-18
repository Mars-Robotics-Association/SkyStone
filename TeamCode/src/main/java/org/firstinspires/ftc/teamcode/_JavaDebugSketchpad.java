package org.firstinspires.ftc.teamcode;

public class _JavaDebugSketchpad
{
    private static double TargetX = -36;
    private static double TargetY = -36;
    private static double CurrentX = 36;
    private static double CurrentY = -36;
    private static double closeEnoughThresholdDist = 5;

    public static void main(String[] args) 
    {
        double out = Math.sqrt(Math.pow(TargetX - CurrentX, 2) + Math.pow(TargetY - CurrentY, 2));
        System.out.println(out);
        boolean b = out < closeEnoughThresholdDist;
        System.out.println(b);
    }
}