package com.example.l01redo.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.l01redo.Interfaces.SensorCallback;

public class SensorsDetector {

    private Sensor sensor;

    private SensorManager sensorManager;

    private SensorCallback sensorCallback;

    private SensorEventListener sensorEventListener;

    private long timestamp = 0;


    public SensorsDetector(Context context, SensorCallback sensorCallback){

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.sensorCallback = sensorCallback;
        initEventListener();
    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];

                changeSensor(x,y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };


    }

    private void changeSensor(float x, float y){

        if(System.currentTimeMillis() - timestamp > 300){
            timestamp = System.currentTimeMillis();

            if(x>2){
                if (sensorCallback != null){
                    sensorCallback.moveLeft();
                }
            }
            if(x<-2){
                if(sensorCallback != null){
                    sensorCallback.moveRight();
                }

            }
            if(y>2.0){
                if(sensorCallback != null){
                   sensorCallback.moveForw(); //not working
                }

            }
            if(y<-2){
                if(sensorCallback != null){
                    sensorCallback.moveBack(); //not working
                }
            }

        }

    }

   public void start() {
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
   }

   public void stop() {
        sensorManager.unregisterListener(sensorEventListener,sensor);
   }
}
