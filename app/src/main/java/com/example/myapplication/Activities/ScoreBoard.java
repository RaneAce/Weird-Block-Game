package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refHighScore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.myapplication.Database.ListViewObject;
import com.example.myapplication.Database.ObjectListAdapter;
import com.example.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ScoreBoard extends AppCompatActivity {


    public ArrayList<String> score_list = new ArrayList<>();
    public ArrayList<String> name_list = new ArrayList<>();
    public ArrayList<ListViewObject> listView_list = new ArrayList<>();

    ValueEventListener score_listener;
    public ListView list_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        list_view = (ListView) findViewById(R.id.list_view);

        listView_list.clear();

        refHighScore.addListenerForSingleValueEvent(new ValueEventListener() {
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
                listView_loading(score_list, name_list);

                ObjectListAdapter adapter = new ObjectListAdapter(ScoreBoard.this, R.layout.score_board_adapter, listView_list);
                list_view.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void listView_loading(ArrayList<String> score_list, ArrayList<String> name_list){
        int length = score_list.size();
        for(int i = (length-1); i >= 0; i--){
            ListViewObject object = new ListViewObject(score_list.remove(i),name_list.remove(i));
            listView_list.add(object);
        }
    }

    public void go_back_to_menu(View view){
        Intent intent = new Intent(ScoreBoard.this, com.example.myapplication.Activities.Main.class);
        startActivity(intent);
    }

}