package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

public class Block extends Figure {
    private int xmax;
    private int xmin;
    private int ymax;
    private int ymin;
    public Block(int x, int y, Bitmap bitmap, String type, int xmax, int xmin, int ymax, int ymin){
        super(x, y, bitmap,type);
        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;
    }

    public int getXmax() {
        return xmax;
    }

    public int getXmin() {
        return xmin;
    }

    public int getYmax() {
        return ymax;
    }

    public int getYmin() {
        return ymin;
    }

    public boolean inRange(float eventX, float eventY){
        if(eventX >= this.x &&
                eventX <= this.x + this.bitmap.getWidth() &&
                eventY >= this.y &&
                eventY <= this.y + this.bitmap.getHeight()){
            return true;
        }
        return false;
    }

}
