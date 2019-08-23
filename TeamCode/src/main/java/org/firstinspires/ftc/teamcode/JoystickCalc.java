package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class JoystickCalc
{
    private OpMode opmode;

    double leftStickY;
    double leftStickX;
    double leftStickBaring;
    double leftStickPower;
    double rightStickX;
    double rightStickY;
    double rightStickBaring;
    double rightStickPower;
    boolean xButton;
    boolean yButton;
    boolean aButton;
    boolean bButton;

    public JoystickCalc(OpMode opmode)
    {
        this.opmode = opmode;
    }

    public void calculate ()
    {

        leftStickY = (opmode.gamepad1.left_stick_y * -1 );
        leftStickX = opmode.gamepad1.left_stick_x;
        rightStickX = opmode.gamepad1.right_stick_x;
        rightStickY = (opmode.gamepad1.right_stick_y * -1 );
        xButton = opmode.gamepad1.x;
        yButton = opmode.gamepad1.y;
        bButton = opmode.gamepad1.b;
        aButton = opmode.gamepad1.a;

if(leftStickY > 0){
//quadrants A and Dif()
          if(leftStickX > 0){

           leftStickBaring =  Math.toDegrees(Math.atan((leftStickX)/(leftStickY)));


            }
          if(leftStickX < 0){
              //quadrant D
          }
          if(leftStickX == 0){
              //along y axis
          }
}
if(leftStickY < 0){
//quadrants C and B
        if(leftStickX > 0){
                //quadrant B
        }
        if(leftStickX < 0){
                //quadrant C
       }
        if(leftStickX == 0){
             //along y axis
      }

}
if(leftStickY == 0){
//along x axis
    if(leftStickX > 0){
        //along right x axis
    }
    if(leftStickX < 0){
        //along left x axis
    }
    if(leftStickX == 0){
        //in the center
    }


}

    }



}