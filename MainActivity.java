package com.example.gyroscope0107uas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.gyroscope0107uas.R;

public class MainActivity extends AppCompatActivity {
        private SensorManager mSensorManager;
        private Sensor mSensor;
        private TextView mTextView;

        private SensorEventListener mSensorListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {
                        mTextView.setText(String.valueOf(event.values[0]));
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        //Nothing to do
                }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                mTextView = (TextView) findViewById(R.id.textView);
                mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
                mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }

        @Override
        protected void onResume() {
                super.onResume();
                mSensorManager.registerListener(mSensorListener, mSensor, 2000000, 2000000);
        }

        @Override
        protected void onPause() {
                super.onPause();
                mSensorManager.unregisterListener(mSensorListener);
        }
}