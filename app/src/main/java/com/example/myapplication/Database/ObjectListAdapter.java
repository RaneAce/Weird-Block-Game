package com.example.myapplication.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ObjectListAdapter extends ArrayAdapter<ListViewObject> {
    private Context myContext;
    private int myResource;

    public ObjectListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListViewObject> objects) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String score = getItem(position).getScore();
        String name = getItem(position).getName();

        ListViewObject template_viewObject = new ListViewObject(score,name);

        LayoutInflater inflater = LayoutInflater.from(myContext);
        //loads all the list view items at once, could potentially cause problems if have too many items
        convertView = inflater.inflate(myResource, parent, false);

        TextView score_tv = (TextView) convertView.findViewById(R.id.score_tv);
        TextView name_tv = (TextView) convertView.findViewById(R.id.name_tv);

        score_tv.setText(score);
        name_tv.setText(name);

        return convertView;
    }
}
