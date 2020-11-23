package com.example.dicerollgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String KEY1 = "Player Name";


    EditText editTextPlayer1;

    Button buttonRules;
    Button buttonContinue;

    public FirebaseFirestore Fdatabase = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPlayer1 = findViewById(R.id.editText_player1);

        buttonRules = findViewById(R.id.button_rules);
        buttonContinue = findViewById(R.id.button_continue);



    }


    public void GotoRules(View v){

        Intent intent1 = new Intent(this,Activity2Rules.class);

        startActivity(intent1);

    }

    public void Continue(View v){

       // Toast.makeText(this, "You cliked continue", Toast.LENGTH_SHORT).show();
         saveNames();

        Intent intent3 = new Intent(this,Activity3.class);

        startActivity(intent3);

    }


    public void saveNames() {
        String name1 = editTextPlayer1.getText().toString();


        Map<String, Object> note = new HashMap<>();
        note.put(KEY1 , name1);


        Fdatabase.collection("Dice").document("Dice info").set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Name saved to Firebase", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}





