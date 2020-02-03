package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ColorSensor
{
    NormalizedColorSensor colorSensor;
    DistanceSensor sensorDistance;

    View relativeLayout;
    OpMode opMode;
    String sensorName = "colorSensorCenter";

    // values is a reference to the hsvValues array.
    private float[] hsvValues = new float[3];
    private float values[] = hsvValues;

    public ColorSensor(OpMode currentOpMode, String name)
    {
        opMode = currentOpMode;
        sensorName = name;
    }

    public double returnHue()
    {
        runSample();
        return hsvValues[0];
    }

    public double returnLightness()
    {
        runSample();
        return hsvValues[2];
    }

    public double returnDistanceCM()
    {
        return sensorDistance.getDistance(DistanceUnit.CM);
    }

    public void Init()
    {
        // Get a reference to the RelativeLayout so we can later change the background
        // color of the Robot Controller app to match the hue detected by the RGB sensor.
        int relativeLayoutId = opMode.hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", opMode.hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) opMode.hardwareMap.appContext).findViewById(relativeLayoutId);

        // Get a reference to our sensor object.
        colorSensor = opMode.hardwareMap.get(NormalizedColorSensor.class, sensorName);
        sensorDistance = opMode.hardwareMap.get(DistanceSensor.class, sensorName);

        // If possible, turn the light on in the beginning (it might already be on anyway,
        // we just make sure it is if we can).
        if (colorSensor instanceof SwitchableLight) {
            ((SwitchableLight)colorSensor).enableLight(true);
        }
    }

    public void runSample()
    {
        // Read the sensor
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        /** Use telemetry to display feedback on the driver station. We show the conversion
         * of the colors to hue, saturation and value, and display the the normalized values
         * as returned from the sensor.
         * @see <a href="http://infohost.nmt.edu/tcc/help/pubs/colortheory/web/hsv.html">HSV</a>*/

        Color.colorToHSV(colors.toColor(), hsvValues);
        /*opMode.telemetry.addLine()
                .addData("H", "%.3f", hsvValues[0])
                .addData("S", "%.3f", hsvValues[1])
                .addData("V", "%.3f", hsvValues[2]);
        opMode.telemetry.addLine()
                .addData("a", "%.3f", colors.alpha)
                .addData("r", "%.3f", colors.red)
                .addData("g", "%.3f", colors.green)
                .addData("b", "%.3f", colors.blue);*/

        /** We also display a conversion of the colors to an equivalent Android color integer.
         * @see Color */
        int color = colors.toColor();
        /*opMode.telemetry.addLine("raw Android color: ")
                .addData("a", "%02x", Color.alpha(color))
                .addData("r", "%02x", Color.red(color))
                .addData("g", "%02x", Color.green(color))
                .addData("b", "%02x", Color.blue(color));*/

        // Balance the colors. The values returned by getColors() are normalized relative to the
        // maximum possible values that the sensor can measure. For example, a sensor might in a
        // particular configuration be able to internally measure color intensity in a range of
        // [0, 10240]. In such a case, the values returned by getColors() will be divided by 10240
        // so as to return a value it the range [0,1]. However, and this is the point, even so, the
        // values we see here may not get close to 1.0 in, e.g., low light conditions where the
        // sensor measurements don't approach their maximum limit. In such situations, the *relative*
        // intensities of the colors are likely what is most interesting. Here, for example, we boost
        // the signal on the colors while maintaining their relative balance so as to give more
        // vibrant visual feedback on the robot controller visual display.
        float max = Math.max(Math.max(Math.max(colors.red, colors.green), colors.blue), colors.alpha);
        colors.red   /= max;
        colors.green /= max;
        colors.blue  /= max;
        color = colors.toColor();

        /*opMode.telemetry.addLine("normalized color:  ")
                .addData("a", "%02x", Color.alpha(color))
                .addData("r", "%02x", Color.red(color))
                .addData("g", "%02x", Color.green(color))
                .addData("b", "%02x", Color.blue(color));*/

        // convert the RGB values to HSV values.
        Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsvValues);

        // change the background color to match the color detected by the RGB sensor.
        // pass a reference to the hue, saturation, and value array as an argument
        // to the HSVToColor method.
        relativeLayout.post(new Runnable() {
            public void run() {
                values = hsvValues;
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, values));
            }
        });
    }
}
