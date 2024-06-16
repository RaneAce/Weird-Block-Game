package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity {

    private TextView uid_text;
    private String uid;
    private FirebaseUser User = refAuth.getCurrentUser();
    public SharedPreferences user_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        uid_text = (TextView) findViewById(R.id.uid_text);
        user_preferences = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        uid = User.getUid();
        uid_text.setText(uid);
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