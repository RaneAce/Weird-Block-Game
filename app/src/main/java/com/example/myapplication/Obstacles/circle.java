package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

public class circle extends Figure {
    private int frame_count = 0;
    private float k, l;
    public circle(float x, float y, Bitmap bitmap, String state) {
        super(x, y, bitmap, state);
    }

    public void Movement_circle(int speed, Block block) {

        if(speed > 10){
            speed = 10;
        }

       float x = (block.getX()-this.x)/-(Math.abs(block.getX()-this.x))*(-1)*speed;
       float y = (block.getY()-this.y)/-(Math.abs(block.getY()-this.y))*(-1)*speed;

       if(frame_count % (20 - speed) == 0){
           k = x;
           l = y;
       }
        this.x += k;
        this.y += l;
        frame_count++;
    }
}