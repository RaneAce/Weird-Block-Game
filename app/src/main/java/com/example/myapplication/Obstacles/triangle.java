package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;


public class triangle extends Figure {
    private String direction;
    public triangle(float x, float y, Bitmap bitmap, String state, String direction){
        super(x, y, bitmap, state);
        this.direction = direction;
    }

    public void Movement_triangle(int speed){
        if(speed > 12){
            speed = 12;
        }
        if(this.direction.equals("right")){
            setX(getX()+speed);
        }
        if(this.direction.equals("left")){
            setX(getX()-speed);
        }
    }
}