package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.myapplication.R;

public class Screen extends AppCompatActivity implements Runnable {

    public FrameLayout framelayout;
    private Thread thread;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        framelayout = findViewById(R.id.framelayout);

        //thread starting
        thread = new Thread(this);
        thread.start();

    }

         @Override
    public void onWindowFocusChanged(boolean hasFocus) {
             super.onWindowFocusChanged(hasFocus);
             if (hasFocus) {
                 game = new Game(this, framelayout.getWidth(), framelayout.getHeight());
                 framelayout.addView(game);
             }
         }

    @Override
    public void run(){

    }

}