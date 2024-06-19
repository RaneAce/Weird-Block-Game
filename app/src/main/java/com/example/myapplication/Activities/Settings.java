package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refAuth;
import static com.example.myapplication.Database.FBref.refUser;
import com.example.myapplication.Database.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Settings extends AppCompatActivity {

    private TextView username_text;
    private String uid;
    private FirebaseUser User = refAuth.getCurrentUser();
    public SharedPreferences user_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username_text = (TextView) findViewById(R.id.username_text);
        user_preferences = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        uid = User.getUid();

        refUser.addListenerForSingleValueEvent(new ValueEventListener() {
            String username;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user_information = ds.getValue(User.class);
                    if(user_information != null && Objects.equals(user_information.getUid(), uid)){
                        username = user_information.getUsername();
                    }
                    else{
                        Log.d("Firebase", "snapshot is null for Key: " + ds.getKey());
                        Log.d("Firebase", "Or UID wasn't right");
                    }
                }
                username_text.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void logout(View view){
        refAuth.signOut();
        user_preferences = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        SharedPreferences.Editor editor = user_preferences.edit();
        editor.putBoolean("stayConnected",false);
        editor.commit();
        Intent intent = new Intent(Settings.this,com.example.myapplication.Activities.LoginActivity.class);
        startActivity(intent);
    }

    public void return_to_menu(View view){
        Intent intent = new Intent(Settings.this, com.example.myapplication.Activities.Main.class);
        startActivity(intent);
    }

    public void open_credits(View view){
        Intent intent = new Intent(Settings.this, com.example.myapplication.Activities.Credits.class);
        startActivity(intent);
    }

}