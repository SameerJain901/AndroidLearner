package clover.andriod_learner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static clover.andriod_learner.R.array.Ramu_Ka_Pariwar;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = findViewById(R.id.textView);
        final ProgressBar progressBar= findViewById(R.id.progressBar);
        new Thread() {
            @Override
            public void run() {
                int x = 0;
                while(progressBar.getProgress()!=progressBar.getMax())
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.setProgress(x);
                    x += 5;
                }
                super.run();
            }
        }.start();

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> arrayAdapter;    // Create From Resource for
        arrayAdapter=ArrayAdapter.createFromResource(this, Ramu_Ka_Pariwar,R.layout.support_simple_spinner_dropdown_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT);
                textView.setText(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
