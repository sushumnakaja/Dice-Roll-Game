package com.example.dicerollgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2Rules extends AppCompatActivity {

    Button buttongoback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_rules);


        buttongoback = findViewById(R.id.button_goback);

    }

    public void goback(View v){
        Intent gotomainmenu = new Intent(this,MainActivity.class);

        startActivity(gotomainmenu);
    }
}