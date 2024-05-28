package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

import java.util.Random;

public class circle extends Figure {
    Random Random = new Random();
    int frame_count = 0;
    float k, l;
    public circle(float x, float y, Bitmap bitmap, String type) {
        super(x, y, bitmap, type);
    }

    public void Movement_circle(int speed, Block block) {

        if(speed > 8){
            speed = 8;
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