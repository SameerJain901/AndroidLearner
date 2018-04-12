package clover.andriod_learner;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class Appearance extends AppCompatActivity {
    static int myScore = 0;
    static int comScore = 0;
    static int myTcore = 0;
    ImageView imageView;
    TextView textView;
    Button roll;
    Button hold ;
    Button resEt;
    TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appearance);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.gameText);
        hold= findViewById(R.id.buttonHold);
        resEt= findViewById(R.id.buttonRes);
        roll= findViewById(R.id.buttonRoll);
        textView.setText("Your Score : " + myScore + "\tComputer Score : " + comScore);
        status=findViewById(R.id.status);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( setDiceImage()== 1)
               {
                   new Handler().postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           computerPlay(new Random().nextBoolean());
                       }
                   }, 500);
               }

            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScore += myTcore;
                textView.setText("Your Score : " + myScore + "\tComputer Score : " + comScore);
                myTcore=0;
                checkScores();
                status.setText(">>> Player turn end\n");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        computerPlay(true);
                    }
                }, 1000);
            }
        });

        resEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScore=0;
                comScore=0;
                textView.setText("Your Score : " + myScore + "\tComputer Score : " + comScore);
                Toast toast=Toast.makeText(getBaseContext(),"Computer : Well played!!! Game Reset... ",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    void computerPlay(boolean play) {
        if(!play)
        {setComScore();return;}
        final Random ran = new Random();
        roll.setEnabled(false);
        hold.setEnabled(false);
        resEt.setEnabled(false);
                    switch (ran.nextInt(6)+1) {
                        case 1:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1, getTheme()));
                            myTcore = 0;
                           status.setText(status.getText()+"Computer : Your Turn!!!!\n");
                            roll.setEnabled(true);
                            hold.setEnabled(true);
                            resEt.setEnabled(true);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    setComScore();
                                    return;
                                }
                            },500);
                        case 2:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2, getTheme()));
                            myTcore += 2;
                            break;
                        case 3:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice3, getTheme()));
                            myTcore += 3;
                            break;
                        case 4:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice4, getTheme()));
                            myTcore += 4;
                            break;
                        case 5:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice5, getTheme()));
                            myTcore += 5;
                            break;
                        case 6:
                            imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice6, getTheme()));
                            myTcore += 6;
                            break;
                    }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                computerPlay(new Random().nextBoolean());
            }
        }, 1000);
    }

    void setComScore()
    {
        comScore = comScore + myTcore;
        textView.setText("Your Score : " + myScore + "\tComputer Score : " + comScore);
        roll.setEnabled(true);
        hold.setEnabled(true);
        resEt.setEnabled(true);
        status.setText(status.getText()+"Computer :  Your Turn Now :)\n");
        checkScores();
        myTcore=0;
    }


    int setDiceImage()
    {
        switch (new Random().nextInt(6)) {
            case 1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice1, getTheme()));
                myTcore = 0;
                status.setText(status.getText()+"Computer : Too Bad!!! My turn now...\n");
                return 1;
            case 2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice2, getTheme()));
                myTcore += 2;
                break;
            case 3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice3, getTheme()));
                myTcore += 3;
                break;
            case 4:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice4, getTheme()));
                myTcore += 4;
                break;
            case 5:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice5, getTheme()));
                myTcore += 5;
                break;
            case 6:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.dice6, getTheme()));
                myTcore += 6;
                break;
        }
        return 0;
    }

    void checkScores() {
        if (myScore >= 25) {
            Toast toast = Toast.makeText(getBaseContext(), "Computer : I lost :( Lucky man...", Toast.LENGTH_LONG);
            toast.show();
            myScore = 0;
            comScore = 0;
            status.setText("");
        }
        if (comScore >= 25) {
            Toast toast = Toast.makeText(getBaseContext(), "Computer : I Won :( Better Luck Next Time...", Toast.LENGTH_LONG);
            toast.show();
            myScore = 0;
            comScore = 0;
            status.setText("");
        }
    }

}

