package com.example.TP2.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class TemperatureDetector implements SensorEventListener {

    private TemperatureDetector.OnTempListener mListener;

    public void setOnTempChangedListener(TemperatureDetector.OnTempListener listener) {
        this.mListener = listener;
    }

    public interface OnTempListener {
        public void onTempChanged(String temp);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // ignore
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        mListener.onTempChanged(Float.toString(event.values[0]));
    }
}
