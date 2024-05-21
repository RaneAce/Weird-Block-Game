package com.example.myapplication.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.myapplication.Obstacles.Block;
import com.example.myapplication.Obstacles.Figure;
import com.example.myapplication.Obstacles.ObsList;
import com.example.myapplication.Obstacles.circle;
import com.example.myapplication.Obstacles.triangle;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends SurfaceView implements Runnable {
        //Random Random = new Random();
        private int width;
        private int height;
        private int speed = 5;
        private Canvas canvas;
        private Thread thread;
        private boolean isRunning = true;
        private SurfaceHolder holder;
        private Block block;
        private ObsList List = new ObsList();
        private ObsList TempList = new ObsList();
        private Paint bgPaint;
        private Bitmap imgBlock, imgtriangle, imgcircle;
        private float x = 0, y = 0, dx = 0, dy = 0;
        private boolean is_holding = false;

        public Game(Context context, int width, int height) {
            super(context);
            this.width = width;
            this.height = height;
            holder = getHolder(); //getting surface
            //image creating with drawables
            imgBlock = BitmapFactory.decodeResource(getResources(), R.drawable.square);
            imgtriangle = BitmapFactory.decodeResource(getResources(), R.drawable.triangle);
            imgcircle = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
            //imgBlock = Bitmap.createScaledBitmap(imgBlock,imgBlock.getWidth()/2,imgBlock.getHeight()/2,false);
            //creating objects
            block = new Block(width / 2 - imgBlock.getWidth() / 2, height / 2 - imgBlock.getHeight() / 2, imgBlock,"block",width, 0, height, 0);
            List.List_Loading(imgtriangle, imgcircle, width, height);
            //painting background
            bgPaint = new Paint();
            bgPaint.setColor(Color.WHITE);
            //running thread
            thread = new Thread(this);
            thread.start();
        }
        
        public void drawCanvas() {
            if (holder.getSurface().isValid()) {
                canvas = holder.lockCanvas(); // canvas lock + create
                canvas.drawPaint(bgPaint); // paint bg
                // drawing objects
                block.Draw(canvas);
                for(int i = 0; i < List.getList().size(); i++){
                    List.getList().get(i).Draw(canvas);
                    //collision check here <------------
                    //moving objects
                        List.getList().get(i).Movement(speed, block);
                    //inside screen check
                    if(List.getList().get(i).is_inside_screen(width,height)){
                        TempList.getList().add(List.getList().get(i));
                    }
                }
                holder.unlockCanvasAndPost(canvas); // unlock canvas after paint
                List.getList().clear();
                for(int j = 0; j < TempList.getList().size(); j++){
                    List.getList().add(TempList.getList().get(j));
                }
                TempList.getList().clear();
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (block.inRange(event.getRawX(), event.getRawY()) || is_holding) {
            is_holding = true;
            }
            if(is_holding){
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        x = event.getRawX();
                        y = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        dx = event.getRawX() - x;
                        dy = event.getRawY() - y;
                        block.setX(block.getX() + dx);
                        block.setY(block.getY() + dy);
                        x = event.getRawX();
                        y = event.getRawY();
                        break;
                }
                //restrictions
                if(block.getX() + block.getBitmap().getWidth() > block.getXmax()){
                    block.setX(block.getXmax() - block.getBitmap().getWidth());
                }
                if(block.getX() < block.getXmin()){
                    block.setX(block.getXmin());
                }
                if(block.getY() + block.getBitmap().getHeight() > block.getYmax()){
                    block.setY(block.getYmax() - block.getBitmap().getHeight());
                }
                if(block.getY() < block.getYmin()){
                    block.setY(block.getYmin());
                }
            }
            if (event.getAction() == MotionEvent.ACTION_UP) {
                is_holding = false;
            }
            //System.out.println(is_holding);
            return true;
        }



        @Override
        public void run() {
            while(isRunning){
                drawCanvas();

                }
        }
    }