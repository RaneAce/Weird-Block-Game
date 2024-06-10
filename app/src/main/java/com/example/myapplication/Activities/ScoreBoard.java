package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.Database.HighScore;
import com.example.myapplication.R;



public class ScoreBoard extends AppCompatActivity {

    HighScore score = new HighScore(10,"02045","ABC","Roy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);



    }

    //*צריך להדפיס הפוך את score בגלל שמסודר הפוך
}