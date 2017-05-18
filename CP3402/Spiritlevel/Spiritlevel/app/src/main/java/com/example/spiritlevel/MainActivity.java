package com.example.spiritlevel;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    public Vibrator vibrating;
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Bitmap xBitmap,yBitmap;
    private float deltaX = 0;
    private float deltaY = 0;
    private float initialX, initialY;
    private float vibrateThreshold = 0;
    private TextView currentX, currentY;
    private Display display;
    private String Xoutput,Youtput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentX = (TextView) findViewById(R.id.currentX);
        currentY = (TextView) findViewById(R.id.currentY);
        display=(Display) findViewById(R.id.display);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if(sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)!= null){
            //accelarometer is set. does our device have accelerometer
            //we initialize our sensorManager in order to bind the sensor to listen to the accelerometer events.
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
            sensorManager.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            vibrateThreshold = accelerometer.getMaximumRange() /2;
        }else{
            //in case device doesnt have accelerometer
        }
        vibrating = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
    }
    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // clean current values
        ResetValues();

        // get the change of the x,y,z values of the accelerometer
        deltaX = event.values[0];
        deltaY = event.values[1];
        BigDecimal X=round(deltaX,2);
        BigDecimal Y=round(deltaY,2);
        Xoutput=X.toString();
        Youtput=Y.toString();
        // display the current x,y,z accelerometer values
        displayCurrentCoordinates();
        display.setPoints(deltaX,deltaY);

        display.invalidate();

        // device will calculate the slightest difference as well, so need to set the low threshold
        // if the change is below 2, it is just plain noise(not to be considered)
//        if (deltaX < 2)
//            deltaX = 0;
//        if (deltaY < 2)
//            deltaY = 0;


        //threshold can be set as max/2 or max/3
        if ((deltaY > vibrateThreshold) ) {
            vibrating.vibrate(40);
        }
    }

    public void ResetValues() {
        currentX.setText("0.0");
        currentY.setText("0.0");
    }

    // display the current x,y,z accelerometer values
    public void displayCurrentCoordinates() {
        currentX.setText("X:"+Xoutput);
        currentY.setText("Y"+Youtput);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
