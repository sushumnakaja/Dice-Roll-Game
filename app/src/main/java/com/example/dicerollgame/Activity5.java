package com.example.dicerollgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.dicerollgame.Activity4.KEY_GAME_STATUS;
import static com.example.dicerollgame.MainActivity.KEY_Name;

public class Activity5 extends AppCompatActivity {


    TextView textViewPlayerName;
    TextView textViewWinorLoss;
    TextView textViewMESSAGE;

    public FirebaseFirestore Fdatabase = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);


        textViewPlayerName = findViewById(R.id.textView_player_name);
        textViewWinorLoss = findViewById(R.id.textView_status_winorLoss);
        textViewMESSAGE = findViewById(R.id.textView_MESSAGE);


        loadNote();

    }



    public void loadNote() {

        Fdatabase.collection("Dice").document("Dice info").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {

                            String name = documentSnapshot.getString(KEY_Name);


                            //Map<String, Object> note = documentSnapshot.getData();
                            textViewPlayerName.setText(name);

                        } else {
                            Toast.makeText(Activity5.this, " Document does not exist ", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Activity5.this, "Error!", Toast.LENGTH_SHORT).show();
                        // Log.d(TAG, e.toString());
                    }
                });



        Fdatabase.collection("Dice").document("Game won status").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {


                            String gameStatus = documentSnapshot.getString(KEY_GAME_STATUS);



                            textViewWinorLoss.setText(gameStatus);

                            if(gameStatus.equals("won")){
                                textViewMESSAGE.setText("You won the money");
                            }else{
                               textViewMESSAGE.setText("You lost your money");
                            }




                        } else {
                            Toast.makeText(Activity5.this, " Document does not exist ", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Activity5.this, "Error!", Toast.LENGTH_SHORT).show();
                        // Log.d(TAG, e.toString());
                    }
                });




    }


}