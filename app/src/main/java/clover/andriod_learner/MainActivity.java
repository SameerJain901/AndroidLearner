package clover.andriod_learner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class MainActivity extends AppCompatActivity  {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button chs=findViewById(R.id.myCountryHash);
        Button dms=findViewById(R.id.myDisplayMess);
        Button apr=findViewById(R.id.myAppearance);
        Button loc=findViewById(R.id.myLocat);
        Button sen=findViewById(R.id.mySensors);
        Button drag=findViewById(R.id.myDrag);
        final Intent intent_shm=new Intent(this,CounrtyHashMaps.class);
        final Intent intent_dis=new Intent(this,DisplayMessageActivity.class);
        final Intent intent_Apr=new Intent(this,Appearance.class);
        final Intent intent_loc=new Intent(this,Location_test.class);
        final Intent intent_sen=new Intent(this,Accelormeter.class);
        final Intent intent_drag=new Intent(this,DragmDrop.class);

        chs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_shm);
            }
        });
        dms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_dis);
            }
        });
        apr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_Apr);
            }
        });
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_loc);
            }
        });
        sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_sen);
            }
        });
        drag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_drag);
            }
        });
    }


}
