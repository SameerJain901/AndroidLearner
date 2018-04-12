package clover.andriod_learner;


import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Random;

public class CounrtyHashMaps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counrty_hash_maps);
        final HashMap<String,String> cMap= new HashMap<String,String>();
        final HashMap<String,String> dMap= new HashMap<String,String>();
        String[] arrayCName=getResources().getStringArray(R.array.Country_Name);
        String[] arrayCSNM=getResources().getStringArray(R.array.Country_SNM);
        int index=0;
        while(index!=arrayCName.length) {
            cMap.put(arrayCName[index].toLowerCase(), arrayCSNM[index].toLowerCase());// Name : Key | SNM : Value
            dMap.put(arrayCSNM[index].toLowerCase(), arrayCName[index].toLowerCase());// Name : Value | SNM : Key
            index++;
        }

        View view=findViewById(R.id.button);

        final EditText editText=findViewById(R.id.editText);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
            }
        });
        final int[] rainbow = getResources().getIntArray(R.array.rainbow);
        final ConstraintLayout constraintLayout=(ConstraintLayout) findViewById(R.id.myConstriant);
        constraintLayout.setSoundEffectsEnabled(true);
        final Intent intent = new Intent(this , MainActivity.class);
        final Button button= findViewById(R.id.button);
        button.setText("Enter");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rr=new Random();
                String input = editText.getText().toString();
                TextView textView;
                textView=findViewById(R.id.textView2);
                if(input.length() > 3)
                {
                    //Find by Country Name and Display SNM
                   textView.setText(cMap.get(input.toLowerCase()));
                }
                else
                {
                    //Find by SNM and Display Country Name
                    textView.setText(dMap.get(input.toLowerCase()));

                }
                view.setBackgroundColor(rainbow[rr.nextInt(12)]);
                constraintLayout.setBackgroundColor(rainbow[rr.nextInt(12)]);
                constraintLayout.animate().rotationBy(360);
                constraintLayout.playSoundEffect(0);

            }
        });

        Button button1 = findViewById(R.id.button3);
        button1.setText("Goto Main");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
//        textView.setText("Here is the length of the array : "+ arrayCName.length+ "Second Length : "+arrayCSNM.length);
//        textView.setText("\n\n\nHere is the Name and ID : "+ arrayCName[247]+ "Second Length : "+arrayCSNM[247]);
    }


}
