package com.example.dicerollgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Activity4 extends AppCompatActivity {

    TextView textViewScore ,textViewGameOver,textViewroll;
  //  TextView textViewPresent;
    Button buttonRoll , buttongameDone;

    int score=0,count = 0;

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
                }
            }


    private int getRandomNumber(int min,int max) {
        return (new Random()).nextInt((max - min) + 1) + min;
    }
}