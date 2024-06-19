package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.TextView;

import com.example.myapplication.R;

public class Screen extends AppCompatActivity implements Runnable {

    public FrameLayout framelayout;
    private Button pause;
    public TextView timer, level_of_game;
    public static int counter;
    private Thread thread;
    Game game;
    private boolean result = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        framelayout = findViewById(R.id.framelayout);
        pause = findViewById(R.id.pause);
        timer = findViewById(R.id.timer);
        level_of_game = (TextView) findViewById(R.id.level_of_game);
        pause.setBackgroundResource(android.R.drawable.ic_media_pause);


        //thread starting
        thread = new Thread(this);
        thread.start();

        counter = 0;

    }

    //sends a message with counter and then updates the timer text
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            timer.setText(String.format("%02d:%02d",counter/60,counter%60));
            if(counter/10 < 14) {
                level_of_game.setText(String.format("%02d",counter/10));
            } else{
                level_of_game.setText("Max");
            }
        }
    };

    public void PauseResume(View view) {
        Button btn = (Button) view;
        result = game.pauseResume();
        if (result) {
            btn.setBackgroundResource(android.R.drawable.ic_media_pause);
            thread = new Thread(this);
            thread.start();
        } else {
            btn.setBackgroundResource(android.R.drawable.ic_media_play);
        }
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
             while(result){
                 try{
                     if(result) {
                         Thread.sleep(1000);
                         counter++;
                         handler.sendEmptyMessage(0);
                         if(game.collision_happened()){
                             Intent intent = new Intent(this, com.example.myapplication.Activities.GameOver.class);
                             startActivity(intent);
                             result = false;
                         }

                     }
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
    }

}