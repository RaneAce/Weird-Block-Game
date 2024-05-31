package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public abstract class Figure {
    public float x;
    public float y;
    public Bitmap bitmap;
    public String state;

    public Figure(float x, float y, Bitmap bitmap, String state){
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.state = state;
    }

    public boolean is_inside_screen(int ScreenWidth, int ScreenHeight){
        return(this.x > -this.bitmap.getWidth() &&
                this.x < ScreenWidth &&
                this.y > -this.bitmap.getHeight() &&
                this.y < ScreenHeight);
    }

    public void Movement_triangle(int speed, Block block){}
    public void Movement_circle(int speed, Block block){}
    public Boolean collision_1st_check (Figure other){
        return (this.x + this.bitmap.getWidth() >= other.getX() &&
                this.x <= other.getX() + other.getBitmap().getWidth() &&
                this.y + this.bitmap.getHeight() >= other.getY() &&
                this.y <= other.getY() + other.getBitmap().getHeight());
    }
    public void Draw (Canvas canvas){
        canvas.drawBitmap(bitmap, x, y,null);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
