package com.example.myapplication.Activities;

import static com.example.myapplication.Activities.Screen.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.R;

public class GameOver extends AppCompatActivity {
    public TextView Score_Text;
    public ImageButton Return_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Score_Text = findViewById(R.id.score_text);
        Return_Button = findViewById(R.id.return_button);

        update_score_text();
    }

    public void update_score_text(){
        Score_Text.setText(String.format("%02d:%02d",counter/60,counter%60));
    }

    public void return_to_menu(View view){
        Intent intent = new Intent(this, com.example.myapplication.Activities.Main.class);
        startActivity(intent);
    }

}