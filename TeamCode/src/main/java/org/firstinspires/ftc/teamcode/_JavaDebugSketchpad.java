/*
package org.firstinspires.ftc.teamcode;

//import org.graalvm.compiler.core.common.FieldsScanner.CalcOffset;

public class _JavaDebugSketchpad
{
    private static double TargetX = 36;
    private static double TargetY = 36;
    private static double CurrentX = -36;
    private static double CurrentY = 36;
    private static double closeEnoughThresholdDist = 5;
    private static double StartAngle = 180;
    private static double absoluteAngle = 0;

    private static double EncoderTicks = 1120;//ticks for one rotation
    private static double WheelDiameter = 2;//diameter of wheel in inches
    private static int encodedDistance = 0;

    static double leftStickY = 0;
    static double leftStickX = 0;
    static double leftStickBaring = 0;
    static double leftStickPower = 0;
    static double rightStickX = 0;
    static double rightStickY = 0;
    static double rightStickBaring = 0;
    static double rightStickPower = 0;
    static boolean xButton;
    static boolean yButton;
    static boolean aButton;
    static boolean bButton;
    static float leftJS = 0;
    static float rightJS = 0;

    private double rotationOffset = 0;


    public static void main(String[] args) 
    {
        calculate();
        

        System.out.println(leftStickBaring);
        System.out.println(leftStickPower);

    }

    public static void calculate ()
    {

        leftStickY = 0;
        leftStickX = -1;
        rightStickX = 0;
        rightStickY = 0;
        xButton = false;
        yButton =false ;
        bButton = false;
        aButton = false;
        leftJS = 0;
        rightJS = 0;


        //Calculate angle of joystick
        double Y = leftStickY; //X input
        double X = leftStickX; //Y input
        leftStickBaring = Math.atan2(Y,-X); //get measurement of joystick angle
        leftStickBaring = Math.toDegrees(leftStickBaring);
        leftStickBaring -= 90 - 180;
        if(leftStickBaring < 0)//convert degrees to positive if needed
        {
            leftStickBaring = 360 + leftStickBaring;
        }
        //Distance formula for calculating joystick power
        leftStickPower = Math.abs(Math.sqrt(Math.pow(leftStickX - 0, 2) + Math.pow(leftStickY - 0, 2)));

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

    
        // //Calculate angle of movement (no obstacle avoidance)
        // double triY = (CurrentY - TargetY); //vertical length
        // double triX = (CurrentX - TargetX); //horizontal length
        // absoluteAngle = Math.atan2(triY,triX); //get measurement of move angle
        // absoluteAngle = Math.toDegrees(absoluteAngle);
        // System.out.println(absoluteAngle);
        // absoluteAngle -= StartAngle + 180; //offsets by start angle of the robot
        // System.out.println(absoluteAngle);
        // if(absoluteAngle < 0)//convert degrees to positive if needed
        // {
        //     absoluteAngle = 360 + absoluteAngle;
        // }
        // System.out.println(absoluteAngle);
        // System.out.println(triY);
        // System.out.println(triX);
}


*/
