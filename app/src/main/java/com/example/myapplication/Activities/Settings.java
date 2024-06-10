package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refAuth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseUser;

public class Settings extends AppCompatActivity {

    private Button logout;
    private TextView uid_text;
    private String uid;
    private FirebaseUser User = refAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        logout = (Button) findViewById(R.id.logout_button);
        uid_text = (TextView) findViewById(R.id.uid_text);

        uid = User.getUid();
        logout.setText(uid);
    }

    private void logout (View v){
        refAuth.signOut();
        Intent intent = new Intent(Settings.this,com.example.myapplication.Activities.LoginActivity.class);
        startActivity(intent);
    }

}