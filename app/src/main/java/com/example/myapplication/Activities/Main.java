package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.myapplication.R;

public class Main extends AppCompatActivity {

    ImageButton Settings, Shop, ScoreBoard, Screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Screen = findViewById(R.id.Screen);
        Settings = findViewById(R.id.Settings);
        Shop = findViewById(R.id.Shop);
        ScoreBoard = findViewById(R.id.ScoreBoard);

        Screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScreen();
            }
        });

        Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 openSettings();
            }
        });

        Shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShop();
            }
        });

        ScoreBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScoreBoard();
            }
        });

    }

    public void openScreen() {
        Intent intent = new Intent(this, com.example.myapplication.Activities.Screen.class);
        startActivity(intent);
    }

    public void openSettings() {
        Intent intent = new Intent(this, com.example.myapplication.Activities.Settings.class);
        startActivity(intent);
    }

    public void openShop() {
        Intent intent = new Intent(this, com.example.myapplication.Activities.Shop.class);
        startActivity(intent);
    }

    public void openScoreBoard() {
        Intent intent = new Intent(this, com.example.myapplication.Activities.ScoreBoard.class);
        startActivity(intent);
    }
}