package clover.andriod_learner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Accelormeter extends AppCompatActivity implements SensorEventListener{
    SensorManager mSensorManager;
    List<Sensor> deviceSensors;
    TextView textView;
    Sensor s,ps;
    LinearLayout myLinear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelormeter);
        LinearLayout myLinear= findViewById(R.id.accel_LL1);
        mSensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE);  //Initialization of Sensor manager to tell system it needs a Sensor Service
        deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);      //Get list of All the available sensors
        Iterator<Sensor> i = deviceSensors.iterator();
        s=i.next();
//        while(i.hasNext())
//        {
//            CheckedTextView ctt=new CheckedTextView(this);
//            ctt.setCheckMarkDrawable(R.color.bright_pink);
//
//            ctt.setText(s.getName());
//            ctt.setId(cttID++);
//            if (mSensorManager.getDefaultSensor(s.getType()) != null){
//                //Sensor Found
//                ctt.setChecked(true);
//                ctt.setTextColor(Color.MAGENTA);
//            }
//            else {
//                // Failure! No magnetometer.
//                ctt.setChecked(false);
//                ctt.setTextColor(Color.RED);
//            }
//            myLinear.addView(ctt);
//            s=i.next();
//        }

        final Spinner spinner =findViewById(R.id.s1221);
        final int[] slistID=new int[deviceSensors.size()];int ind=1;
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item) ;
        arrayAdapter.add(s.getName());
        slistID[0]=s.getId();
        while(i.hasNext())
        {
            arrayAdapter.add(s.getName());
            slistID[ind]=s.getId();
            s=i.next();ind++;
        }
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast toast = Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(),Toast.LENGTH_SHORT);
                toast.show();
                onspinnclick();
                s=deviceSensors.get(spinner.getSelectedItemPosition());
                ViewSensorData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//              ViewSensorData();
            }
        });
    }

    public void onspinnclick()
    {
        mSensorManager.unregisterListener(this);
    }

    private void ViewSensorData() {

        mSensorManager.registerListener(this,s,SensorManager.SENSOR_DELAY_FASTEST);
        TextView tv=findViewById(R.id.SName_tv);
        tv.setText(s.getName());
        tv=findViewById(R.id.SVendor_tv);
        tv.setText(s.getVendor());
        tv=findViewById(R.id.SVersion_tv);
        tv.setText(String.valueOf(s.getVersion()));
        tv=findViewById(R.id.SDelay_tv);
        tv.setText(String.valueOf(s.getMinDelay()));
        tv=findViewById(R.id.SResolution_tv);
        tv.setText(String.valueOf(s.getResolution()));
        tv=findViewById(R.id.SPower_tv);
        tv.setText(String.valueOf(s.getPower()));
        tv=findViewById(R.id.SMax_tv);
        tv.setText(String.valueOf(s.getMaximumRange()));
        tv=findViewById(R.id.SMDelay_tv);
        tv.setText(String.valueOf(s.getMaxDelay()));
        tv=findViewById(R.id.STYpe_tv);
        tv.setText(String.valueOf(s.getType()));

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float[] lux=sensorEvent.values;
        textView=findViewById(R.id.ValueChange_tv);
        textView.setText(Arrays.toString(lux));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
            textView=findViewById(R.id.Sacc_tv);
            textView.setText(Integer.toString(i));
    }


    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


}
