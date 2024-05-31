package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

import java.util.Random;

public class triangle extends Figure {
    Random Random = new Random();
    private String direction;
    public triangle(float x, float y, Bitmap bitmap, String state, String direction){
        super(x, y, bitmap, state);
        this.direction = direction;
    }

    public void Movement_triangle(int speed,Block block){
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









        /*//random direction
        int direction = Random.nextInt(2);
        //setting random height
            int Y = triangle.getBitmap().getHeight() + Random.nextInt(ScreenHeight - triangle.getBitmap().getHeight() + 1);
            triangle.setY(Y);
            switch(direction){
            //right
                case(0):
            triangle.setX(-triangle.getBitmap().getWidth());
            while(triangle.getX() != ScreenWidth){
                triangle.setX(triangle.getX() + speed);
            }
                break;
            //left
                case(1):
            triangle.setX(ScreenWidth);
            while(triangle.getX() != -triangle.getBitmap().getWidth()){
                triangle.setX(triangle.getX() - speed);
            }
                break;
        }*/