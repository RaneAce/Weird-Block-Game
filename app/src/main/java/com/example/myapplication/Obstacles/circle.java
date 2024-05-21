package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

import java.util.Random;

public class circle extends Figure {
    Random Random = new Random();

    public circle(float x, float y, Bitmap bitmap, String type) {
        super(x, y, bitmap, type);
    }

    public void Movement(int speed, Block block) {
       float x = (block.getX()-this.x)/-(Math.abs(block.getX()-this.x))*(-1)*speed;
       float y = (block.getY()-this.y)/-(Math.abs(block.getY()-this.y))*(-1)*speed;



        this.x += x;
        this.y += y;



    }
}