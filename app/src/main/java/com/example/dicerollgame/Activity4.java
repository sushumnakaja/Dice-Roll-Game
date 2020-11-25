package com.example.dicerollgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Activity4 extends AppCompatActivity {
    public static final String KEY_GAME_STATUS = "GAME STATUS";

    TextView textViewScore ,textViewGameOver,textViewroll;
  //  TextView textViewPresent;
    Button buttonRoll , buttongameDone;

    int score=0,count = 0;
    String winOrLoss="";

    public FirebaseFirestore Fdatabase = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        textViewScore = findViewById(R.id.textView_score);
        textViewGameOver = findViewById(R.id.textViewGameOver);
       // textViewPresent = findViewById(R.id.textViewpresent);
        buttonRoll = findViewById(R.id.button_Roll_dice);
        buttongameDone = findViewById(R.id.button_gamedone);
        textViewroll = findViewById(R.id.textViewtextRoll);


        buttongameDone.setVisibility(View.INVISIBLE);


         saveMoneyData();

    }

    public void saveMoneyData() {


    }


    public void openNewActivity(View view){
        Intent intent5 = new Intent(this,Activity5.class);
        startActivity(intent5);
    }
    public void buttonRoll(View v){

                if(count != 4) {
                    int a = getRandomNumber(1, 6);
                    score += a;
                    buttonRoll.setText(Integer.toString(a));
                    textViewScore.setText(Integer.toString(score));
                   // textViewPresent.setText(Integer.toString(a));
                    count++;
                }else{

                    textViewGameOver.setText("Chances Over !");
                    buttonRoll.setVisibility(View.INVISIBLE);
                    textViewroll.setVisibility(View.INVISIBLE);
                    buttongameDone.setVisibility(View.VISIBLE);



                    if(score >= 16){
                        winOrLoss = "won";
                    }else
                        winOrLoss = "lost";


                    Map<String, Object> note = new HashMap<>();
                    note.put(KEY_GAME_STATUS , winOrLoss);


                    Fdatabase.collection("Dice").document("Game won status").set(note)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Toast.makeText(Activity4.this, "Betting money confirmed", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    //Toast.makeText(Activity4.this, "Error!", Toast.LENGTH_SHORT).show();

                                }
                            });


                }
            }


    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
}