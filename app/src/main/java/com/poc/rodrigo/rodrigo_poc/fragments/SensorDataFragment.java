package com.poc.rodrigo.rodrigo_poc.fragments;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DigitalClock;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.poc.rodrigo.rodrigo_poc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SensorDataFragment extends Fragment implements SensorEventListener{
    private static final String TAG = "MapsFragment";

    private SensorManager mSensorManager;
    TextView accelerometerXValue, accelerometerYValue , accelerometerZValue;
    TextView gyroscopeXValue, gyroscopeYValue, gyroscopeZValue;
    TextView magnometerXValue, magnometerYValue, magnometerZValue;
    TextView pressureValue, lightValue, timeAndDate;
    TextClock textClock;



    Sensor accelerometer, gyroscope, magnometer,pressure,light;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager) this.getActivity().getSystemService(Activity.SENSOR_SERVICE);
    }

    @Override
    public void onStart() {
        super.onStart();

        if(this.getUserVisibleHint()) {
            this.registerSensorListener();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        this.unregisterSensorListener();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_sensor_data, container, false);

        accelerometerXValue = view.findViewById(R.id.accelerometerXValue);
        accelerometerYValue = view.findViewById(R.id.accelerometerYValue);
        accelerometerZValue = view.findViewById(R.id.accelerometerZValue);

        gyroscopeXValue = view.findViewById(R.id.gyroscopeXValue);
        gyroscopeYValue = view.findViewById(R.id.gyroscopeYValue);
        gyroscopeZValue = view.findViewById(R.id.gyroscopeZValue);

        magnometerXValue = view.findViewById(R.id.magnoMeterXValue);
        magnometerYValue = view.findViewById(R.id.magnoMeterYValue);
        magnometerZValue = view.findViewById(R.id.magnoMeterZValue);

        pressureValue = view.findViewById(R.id.pressure);
        lightValue = view.findViewById(R.id.light);
        timeAndDate = view.findViewById(R.id.clockTime);

        accelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        light = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        textClock = view.findViewById(R.id.textClock);

        timeAndDate.setText("Current Date and Time: " + textClock.getText());

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "Accelerometer dataonSensorChanged: X Value: " + sensorEvent.values[0] + "Y Value :" + sensorEvent.values[1] + "Z Value :" + sensorEvent.values[2]);
            accelerometerXValue.setText("xValue :" + sensorEvent.values[0]);
            accelerometerYValue.setText("yValue :" + sensorEvent.values[1]);
            accelerometerZValue.setText("ZValue :" + sensorEvent.values[2]);

        } else if(sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            Log.d(TAG, "Gyroscope dataonSensorChanged: X Value:" + sensorEvent.values[0] + "Y Value :" + sensorEvent.values[1] + "Z Value :" + sensorEvent.values[2]);
            gyroscopeXValue.setText("xValue :" + sensorEvent.values[0]);
            gyroscopeYValue.setText("yValue :" + sensorEvent.values[1]);
            gyroscopeZValue.setText("ZValue :" + sensorEvent.values[2]);

        } else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            Log.d(TAG, "Magnetic Field Sensor data onSensorChanged: X Value:" + sensorEvent.values[0] + "Y Value :" + sensorEvent.values[1] + "Z Value :" + sensorEvent.values[2]);
            magnometerXValue.setText("xValue :" + sensorEvent.values[0]);
            magnometerYValue.setText("yValue :" + sensorEvent.values[1]);
            magnometerZValue.setText("ZValue :" + sensorEvent.values[2]);
        } else if(sensor.getType() == Sensor.TYPE_PRESSURE) {
            Log.d(TAG, "Pressure data onSensorChanged Value :" + sensorEvent.values[0]);
            pressureValue.setText("Pressure: " + sensorEvent.values[0]);
        } else if(sensor.getType() == Sensor.TYPE_LIGHT) {
            Log.d(TAG, "Light data onSensorChanged: Value:" + sensorEvent.values[0]);
            lightValue.setText("Light: " + sensorEvent.values[0]);
        }
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        // First starts (gets called before everything else)
        if(mSensorManager == null) {
            return;
        }

        if(menuVisible) {
            this.registerSensorListener();
        } else {
            this.unregisterSensorListener();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void registerSensorListener() {

        if(accelerometer == null) {
            accelerometerXValue.setText("Not Supported");
            accelerometerYValue.setText("Not Supported");
            accelerometerZValue.setText("Not Supported");
        } else {
            mSensorManager.registerListener(this,
                    mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(gyroscope == null) {
            gyroscopeXValue.setText("Not Supported");
            gyroscopeYValue.setText("Not Supported");
            gyroscopeZValue.setText("Not Supported");
        } else {
            mSensorManager.registerListener(this,
                    mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE).get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(magnometer == null) {
            magnometerXValue.setText("Not Supported");
            magnometerYValue.setText("Not Supported");
            magnometerZValue.setText("Not Supported");

        } else {
            mSensorManager.registerListener(this,
                    mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(pressure == null) {
            pressureValue.setText("Not Supported");

        } else {
            mSensorManager.registerListener(this,
                    mSensorManager.getSensorList(Sensor.TYPE_PRESSURE).get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(light == null) {
            lightValue.setText("Not Supported");

        } else {
            mSensorManager.registerListener(this,
                    mSensorManager.getSensorList(Sensor.TYPE_LIGHT).get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }
}
