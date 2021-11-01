package com.example.myapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class steps extends AppCompatActivity implements SensorEventListener{

    TextView stepCountView;
    private static final int SHAKE_THRESHOLD = 800;
    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;
    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;


    int kcal22=0;

    // 현재 걸음 수
    public static int cnt = 0;

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;

    @Override
    protected void onCreate(Bundle savedIntanceState) {

        EditText edit;
        TextView text;
        super.onCreate(savedIntanceState);

        setContentView(R.layout.steps);
        Intent intent = getIntent();

        edit= (EditText)findViewById(R.id.edit);
        //text= (TextView)findViewById(R.id.result); //resetButton

        stepCountView = findViewById(R.id.stepCountView);


        Button resetButton = (Button) findViewById(R.id.resetButton);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        stepCountView = (TextView) findViewById(R.id.stepCountView);
        resetButton = (Button) findViewById(R.id.resetButton);

        stepCountView.setText("" + cnt);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        TextView kcal2= findViewById(R.id.kcal2);

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    stepCountView.setText("" + (++cnt));
                    kcal22 = cnt/30;
                    kcal2.setText(String.valueOf(kcal22));
                }

                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void resetnClick(View v) {
        switch (v.getId()) {
            case R.id.resetButton:
                cnt = 0;
                stepCountView.setText("" + cnt);
                break;
        }
    }
}
