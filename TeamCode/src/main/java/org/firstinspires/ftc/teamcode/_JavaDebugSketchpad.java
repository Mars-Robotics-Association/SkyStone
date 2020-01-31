// package org.firstinspires.ftc.teamcode;

// //import org.graalvm.compiler.core.common.FieldsScanner.CalcOffset;

// public class _JavaDebugSketchpad
// {
//     static private double robotAngle = 0;
//     static private double leftStickBaring = 0;

//     public static void main(String[] args) 
//     {
//         double offset = GetOffsetToAdd(leftStickBaring, robotAngle, 0.01, 0, 0);
//     }

//     static public double GetOffsetToAdd(double targetAngle, double currentAngle, double pCoefficient, double iCoefficient, double dCoefficient)
//     {
//         double error = targetAngle - currentAngle; //difference between the target and actual angle (positive if going right)
//         double correction = error * pCoefficient + dCoefficient * (error/Math.abs(error)); //Proportional control

//         return correction;
//     }
// } a