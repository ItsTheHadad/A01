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

    private long timestampMove = 0;
    private long timestampTilt = 0;


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
                float y = event.values[2];

                changeSensor(x,y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

        };


    }

    private void changeSensor(float x, float y){

        if(System.currentTimeMillis() - timestampMove > 300){
            timestampMove = System.currentTimeMillis();

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


        }

        if(System.currentTimeMillis() - timestampTilt > 1000){
            timestampTilt = System.currentTimeMillis();

            if(y<-1){ //different values since its harder to move back
                if(sensorCallback != null){
                    sensorCallback.moveForw();
                }

            }
            if(y>7){
                if(sensorCallback != null){
                    sensorCallback.moveBack();
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
