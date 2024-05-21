package com.example.myapplication.Obstacles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public abstract class Figure {
    public float x;
    public float y;
    public Bitmap bitmap;
    public String type;

    public Figure(float x, float y, Bitmap bitmap, String type){
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.type = type;
    }

    public boolean is_inside_screen(int ScreenWidth, int ScreenHeight){
        return(this.x > -this.bitmap.getWidth() &&
                this.x < ScreenWidth &&
                this.y > -this.bitmap.getHeight() &&
                this.y < ScreenHeight);
    }

    public void Movement(int speed, Block block){}
    public boolean circle_check(){
        return(this)
    }
    public Boolean collision_1st_check (Block Block, Figure other){
        return (Block.getX() + Block.getBitmap().getWidth() >= other.getX() &&
                Block.getX() <= other.getX() + other.getBitmap().getWidth() &&
                Block.getY() + Block.getBitmap().getHeight() >= other.getY() &&
                Block.getY() <= other.getY() + other.getBitmap().getHeight());
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

    public String getType() {
        return type;
    }
}
