package com.example.dicerollgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity3 extends AppCompatActivity {

    public static final String KEY_BET = "Amount entered";
    EditText editTextNumber;
    Button buttonStart;

    public FirebaseFirestore Fdatabase = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        editTextNumber = findViewById(R.id.editText_Number);
        buttonStart =findViewById(R.id.button_start);


    }


    public void startButton(View v){
        saveMoneyData();
        Intent intent4 = new Intent(this,Activity4.class);

        startActivity(intent4);
    }


    public void saveMoneyData() {
        String temp=editTextNumber. getText(). toString();
        int value=0;
        if (!"". equals(temp))
            value = Integer.parseInt(temp);


        Map<String, Object> note = new HashMap<>();
        note.put(KEY_BET , value);


        Fdatabase.collection("Dice").document("Betting amount").set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Activity3.this, "Betting money confirmed", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Activity3.this, "Error!", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}