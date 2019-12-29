package org.firstinspires.ftc.teamcode;

/* This is a parent class for all robots. To interface to this class, simple add "extends Robot" to the end of the class line

 */
public interface Robot
{
    void Init();

    void Start();

    void Loop();

    void MoveAtAngle(double angle, double speed, boolean headlessMode);

    void RotateTowardsAngle(double angle, double speed);

    void RawTurn(boolean right, double speed);

    void  StopMotors();

    void CalculateWheelSpeeds(double degrees, double speed);

    double GetRobotAngle();

}
