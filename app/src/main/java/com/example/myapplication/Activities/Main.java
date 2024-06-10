package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;

import com.example.myapplication.R;

public class Main extends AppCompatActivity {

    ImageButton Settings, ScoreBoard, Screen;

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.ConstraintLayout);
        Screen = findViewById(R.id.Screen);
        Settings = findViewById(R.id.Settings);
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

    public void openScoreBoard() {
        Intent intent = new Intent(this, com.example.myapplication.Activities.ScoreBoard.class);
        startActivity(intent);
    }

    public void help_click(View view) {
        CreatepopUpwindow();
    }

    public void CreatepopUpwindow() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        View popUpView = inflater.inflate(R.layout.help_popup,null);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.MATCH_PARENT;
        boolean focusable = true;

        PopupWindow popupWindow = new PopupWindow(popUpView,width,height,focusable);

        //post - makes it run on the UI thread
        layout.post(new Runnable() {
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.CENTER,0,0);
            }
        });

        Button okay = popUpView.findViewById(R.id.okay);

        okay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        popUpView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}