package org.firstinspires.ftc.teamcode;

/* This is a parent class for all robots. To interface to this class, simple add "extends Robot" to the end of the class line

 */
public interface Robot
{
    public abstract void MoveAtAngle(double angle, double speed);

    public void RotateTo(double angle, double speed);

    public void RawTurn(boolean right, double speed);

    public void  StopMotors();

    void CalculateWheelSpeeds(double degrees);

    public float GetRobotAngle();

}
