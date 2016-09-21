package com.codekul.sensors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private float cx,cy, rad;
    private OurView ov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ov = new OurView(this);
        setContentView(ov);
        cx = cy = 10;
        rad = 30;

        accelerometer();
    }

    private class OurView extends TextView {

        private Paint paint;
        public OurView(Context context) {
            super(context);

            paint = new Paint();
            paint.setColor(Color.BLUE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            canvas.drawCircle(cx,cy,rad,paint);
        }
    }

    private void changeColor(int color){
        findViewById(R.id.relativeRoot).setBackgroundColor(color);
    }

    private void listSensors(){
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        for (Sensor sensor : manager.getSensorList(Sensor.TYPE_ALL)) {
            Log.i("@codekul","Name - "+sensor.getName());
            Log.i("@codekul","Vendor - "+sensor.getVendor());
        }
    }

    private void proximity() {
        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                Log.i("@codeku","Value is - "+event.values[0]);
                if(event.values[0] < 100)
                    changeColor(Color.RED);
                else changeColor(Color.WHITE);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor ,SensorManager.SENSOR_DELAY_UI);
    }

    private void light(){

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                changeLight(event.values[0]);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        }, sensor ,SensorManager.SENSOR_DELAY_UI);
    }

    private void changeLight(float light){
        ((TextView)findViewById(R.id.textLight)).setText(""+light);
    }

    private void accelerometer(){

        SensorManager manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        manager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                cx = event.values[0];
                cy = event.values[1];
                ov.invalidate();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {
            }
        }, sensor ,SensorManager.SENSOR_DELAY_UI);
    }
}
