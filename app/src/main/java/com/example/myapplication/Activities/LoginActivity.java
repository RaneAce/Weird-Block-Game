package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.Database.User;
import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText eUsername;
    private Button Register_Button;
    private String username, uid;
    private Boolean Registered;
    private User UserDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();

        Intent intent = new Intent(this, com.example.myapplication.Activities.Main.class);
        startActivity(intent);
    }


    private void initViews() {
        eUsername = findViewById(R.id.eUsername);
        Register_Button = findViewById(R.id.register_button);
    }

    public void registration(){

    }

}