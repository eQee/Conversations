package de.eqee.pn.ui;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import de.eqee.pn.R;

public class SensorActivity extends Activity implements SensorEventListener {
    public static float vPressure = 0;
    public static float vLight = 0;
    public static float vTemperature = 0;
    public static float vHumidity = 0;
    public static float vAccelerometer = 0;
    public static float vLinearAcceleration = 0;
    public static float vGravitiy = 0;
    public static float vGyroscope = 0;
    public static float vProximity = 0;
    public static float vMagneticField = 0;
    public static float vGeomagneticRotationVector = 0;
    public static float vGameRotationVector = 0;
    public static float vRotationVector = 0;
    public static float vMotionDetect = 0;
    public static float vSignificantMotion = 0;
    public static float vStationaryDetect = 0;
    public static float vPose6D0F = 0;
    public static float vStepDetector = 0;
    public static float vStepCounter = 0;
    public static float vHeartBeat = 0;
    public static float vHeartRate = 0;
    public static float vLowLatencyOffbodyDetect = 0;

    private SensorManager mSensorManager;
    private Sensor mPressure;
    private Sensor mLight;
    private Sensor mTemperature;
    private Sensor mHumidity;
    private Sensor mAccelerometer;
    private Sensor mLinearAcceleration;
    private Sensor mGravity;
    private Sensor mGyroscope;
    private Sensor mProximity;
    private Sensor mMagneticField;
    private Sensor mGeomagneticRotationVector;
    private Sensor mGameRotationVector;
    private Sensor mRotationVector;
    private Sensor mMotionDetect;
    private Sensor mSignificantMotion;
    private Sensor mStationaryDetect;
    private Sensor mPose6D0F;
    private Sensor mStepDetector;
    private Sensor mStepCounter;
    private Sensor mHeartBeat;
    private Sensor mHeartRate;
    private Sensor mLowLatencyOffbodyDetect;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pn);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLinearAcceleration = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mMagneticField = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mGeomagneticRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
        mGameRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_GAME_ROTATION_VECTOR);
        mRotationVector = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mMotionDetect = mSensorManager.getDefaultSensor(Sensor.TYPE_MOTION_DETECT);
        mSignificantMotion = mSensorManager.getDefaultSensor(Sensor.TYPE_SIGNIFICANT_MOTION);
        mStationaryDetect = mSensorManager.getDefaultSensor(Sensor.TYPE_STATIONARY_DETECT);
        mPose6D0F = mSensorManager.getDefaultSensor(Sensor.TYPE_POSE_6DOF);
        mStepDetector = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mHeartBeat = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        mHeartRate = mSensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
        mLowLatencyOffbodyDetect = mSensorManager.getDefaultSensor(Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        switch (sensor.getType())
        {
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                vTemperature = event.values[0];
                break;
            case Sensor.TYPE_PRESSURE:
                vPressure = event.values[0];
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                vHumidity = event.values[0];
                break;
            case Sensor.TYPE_LIGHT:
                vLight = event.values[0];
                break;
            case Sensor.TYPE_ACCELEROMETER:
                vAccelerometer = event.values[0];
                break;
            case Sensor.TYPE_LINEAR_ACCELERATION:
                vLinearAcceleration = event.values[0];
                break;
            case Sensor.TYPE_GRAVITY:
                vGravitiy = event.values[0];
                break;
            case Sensor.TYPE_GYROSCOPE:
                vGyroscope = event.values[0];
                break;
            case Sensor.TYPE_PROXIMITY:
                vProximity = event.values[0];
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                vMagneticField = event.values[0];
                break;
            case Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR:
                vGeomagneticRotationVector = event.values[0];
                break;
            case Sensor.TYPE_GAME_ROTATION_VECTOR:
                vGameRotationVector = event.values[0];
                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                vRotationVector = event.values[0];
                break;
            case Sensor.TYPE_MOTION_DETECT:
                vMotionDetect = event.values[0];
                break;
            case Sensor.TYPE_SIGNIFICANT_MOTION:
                vSignificantMotion = event.values[0];
                break;
            case Sensor.TYPE_STATIONARY_DETECT:
                vStationaryDetect = event.values[0];
                break;
            case Sensor.TYPE_POSE_6DOF:
                vPose6D0F = event.values[0];
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                vStepDetector = event.values[0];
                break;
            case Sensor.TYPE_STEP_COUNTER:
                vStepCounter = event.values[0];
                break;
            case Sensor.TYPE_HEART_BEAT:
                vHeartBeat = event.values[0];
                break;
            case Sensor.TYPE_HEART_RATE:
                vHeartRate = event.values[0];
                break;
            case Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT:
                vLowLatencyOffbodyDetect = event.values[0];
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLinearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGeomagneticRotationVector, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mGameRotationVector, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mRotationVector, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMotionDetect, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mSignificantMotion, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mStationaryDetect, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPose6D0F, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mStepDetector, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHeartBeat, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mHeartRate, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLowLatencyOffbodyDetect, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
