package com.example.myapplication.Activities;

import static com.example.myapplication.Activities.Screen.counter;
import static com.example.myapplication.Database.FBref.refAuth;
import static com.example.myapplication.Database.FBref.refHighScore;
import static com.example.myapplication.Database.FBref.refUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.User;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class GameOver extends AppCompatActivity {
    public TextView Score_Text;
    public ImageButton Return_Button;
    public FirebaseUser dbuser;
    public Date currentDate = new Date();
    public SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    public String date = format.format(currentDate);
    public String uid;
    public User dbuser_score = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        Score_Text = findViewById(R.id.score_text);
        Return_Button = findViewById(R.id.return_button);

        dbuser = refAuth.getCurrentUser();
        uid = dbuser.getUid();

            refUser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds_score : snapshot.getChildren()) {
                        User user_information = ds_score.getValue(User.class);
                        if (user_information != null && Objects.equals(user_information.getUid(), uid)) {
                                dbuser_score.setScore(user_information.getScore());
                                dbuser_score.setUsername(user_information.getUsername());
                                dbuser_score.setDate(user_information.getDate());
                                dbuser_score.setUid(user_information.getUid());
                                break;
                        } else {
                            Log.d("Firebase", "HighScore is null for Key: " + ds_score.getKey());
                            Log.d("Firebase", "Or UID wasn't right");
                        }
                    }
                    update_player_score();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle possible errors
                    Log.e("Firebase", "Error reading data", error.toException());
                }
            });

        update_score_text();
    }

    public void update_score_text(){
        Score_Text.setText(String.format("%02d:%02d",counter/60,counter%60));
    }

    public void return_to_menu(View view){
        Intent intent = new Intent(this, com.example.myapplication.Activities.Main.class);
        startActivity(intent);
    }

    public void update_player_score(){
        if(counter > dbuser_score.getScore()){
            refHighScore.child(String.valueOf(dbuser_score.getScore())).child(dbuser_score.getDate()).child(dbuser_score.getUid()).removeValue();
            dbuser_score.setScore(counter);
            dbuser_score.setDate(date);
            refUser.child(dbuser_score.getUid()).setValue(dbuser_score);
            refHighScore.child(String.valueOf(dbuser_score.getScore())).child(dbuser_score.getDate()).child(dbuser_score.getUid()).setValue(dbuser_score.getUsername());
            Toast.makeText(GameOver.this,"New Best!", Toast.LENGTH_LONG).show();
        }
        else {
         Toast.makeText(GameOver.this,"Didn't do good enough ;)",Toast.LENGTH_SHORT).show();
        }
    }

}