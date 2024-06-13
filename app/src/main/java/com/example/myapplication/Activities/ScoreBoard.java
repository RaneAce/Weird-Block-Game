package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refHighScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ScoreBoard extends AppCompatActivity {


    public ArrayList<String> score_list = new ArrayList<>();
    public ArrayList<String> name_list = new ArrayList<>();

    ValueEventListener score_listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);



        ValueEventListener score_listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                score_list.clear();
                name_list.clear();
                for(DataSnapshot ds_score : snapshot.getChildren()){
                    String scores = (String) ds_score.getKey();
                    for(DataSnapshot ds_dates : ds_score.getChildren()){
                        for(DataSnapshot ds_uids : ds_dates.getChildren()){
                            String usernames = (String) ds_uids.getValue();
                            score_list.add(scores);
                            name_list.add(usernames);
                        }
                    }
                }
                flippingArrays(score_list);
                flippingArrays(name_list);
                //System.out.println(score_list);
                //System.out.println(name_list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        refHighScore.addValueEventListener(score_listener);

    }

    public void flippingArrays(ArrayList<String> list){
        ArrayList<String> list2 = new ArrayList<>();
        for(int i = list.size()-1; i > 1; i--){
            list2.add(list.remove(i));
        }
        list = list2;
    }

    public void onPause(){
        super.onPause();
        refHighScore.removeEventListener(score_listener);
    }

}