package com.example.myapplication.Activities;

import static com.example.myapplication.Activities.Screen.counter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.myapplication.Obstacles.Block;
import com.example.myapplication.Obstacles.ObsList;
import com.example.myapplication.R;

import java.util.ArrayList;

public class Game extends SurfaceView implements Runnable {
        private int width, height, speed;
        private Canvas canvas;
        private Thread thread;
        private boolean isRunning = true;
        private SurfaceHolder holder;
        private Block block;
        private ObsList triangle_List = new ObsList();
        private ObsList circle_List = new ObsList();
        private ObsList t_TempList = new ObsList();
        private ObsList c_TempList = new ObsList();
        private Paint bgPaint;
        private Bitmap imgBlock, imgtriangle_right,imgtriangle_right_secret, imgtriangle_left, imgcircle;
        private ArrayList<Bitmap> triangle_bitmap_list = new ArrayList<Bitmap>(3);
        private float x = 0, y = 0, dx = 0, dy = 0;
        private boolean is_holding = false;

        public Game(Context context, int width, int height) {
            super(context);
            this.width = width;
            this.height = height;
            holder = getHolder(); //getting surface
            //image creating with drawables
            imgBlock = BitmapFactory.decodeResource(getResources(), R.drawable.square);
            imgtriangle_right = BitmapFactory.decodeResource(getResources(), R.drawable.triangle_right);
            imgtriangle_left = BitmapFactory.decodeResource(getResources(), R.drawable.triangle_left);
            imgtriangle_right_secret = BitmapFactory.decodeResource(getResources(), R.drawable.triangle_right_secret);
            triangle_bitmap_list.add(imgtriangle_right);
            triangle_bitmap_list.add(imgtriangle_left);
            triangle_bitmap_list.add(imgtriangle_right_secret);
            imgcircle = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
            //creating objects
            block = new Block(width / 2 - imgBlock.getWidth() / 2, height / 2 - imgBlock.getHeight() / 2, imgBlock,"alive",width, 0, height, 0);
            triangle_List.triangle_loading(triangle_bitmap_list, width, height, 3);
            circle_List.circle_Loading(imgcircle,width,height,2);
            //painting background
            bgPaint = new Paint();
            bgPaint.setColor(Color.WHITE);
            //running thread
            thread = new Thread(this);
            thread.start();
        }
        
        public void drawCanvas() {
            if (holder.getSurface().isValid()) {
                speed = 3 + (counter/19);
                canvas = holder.lockCanvas(); // canvas lock + create
                canvas.drawPaint(bgPaint); // paint bg
                // drawing objects
                block.Draw(canvas);
                //triangles
                for(int i = 0; i < triangle_List.getList().size(); i++){
                    triangle_List.getList().get(i).Draw(canvas);
                    //collision check here <------------
                    //moving objects
                    triangle_List.getList().get(i).Movement_triangle(speed, block);
                    //inside screen check
                    if(triangle_List.getList().get(i).is_inside_screen(width,height)){
                        t_TempList.getList().add(triangle_List.getList().get(i));
                    }
                    else{
                        t_TempList.triangle_loading(triangle_bitmap_list, width, height, 1);
                    }
                }
                //circles
                for(int i = 0; i < circle_List.getList().size(); i++){
                    circle_List.getList().get(i).Draw(canvas);
                    //collision check here <------------
                    //moving objects
                    circle_List.getList().get(i).Movement_circle(speed, block);
                    //inside screen and circles colliding check
                        for (int h = 0; h < circle_List.getList().size(); h++) {
                            if (i != h && circle_List.getList().get(i).collision_1st_check(circle_List.getList().get(h))){
                                circle_List.getList().get(i).setState("dead");
                                circle_List.getList().get(h).setState("dead");
                            }
                        }
                    if(circle_List.getList().get(i).is_inside_screen(width, height) &&
                    !circle_List.getList().get(i).getState().equals("dead")){
                        c_TempList.getList().add(circle_List.getList().get(i));
                    }
                    else{
                        c_TempList.circle_Loading(imgcircle,width,height,1);
                    }
                }
                holder.unlockCanvasAndPost(canvas); // unlock canvas after paint
                triangle_List.getList().clear();
                circle_List.getList().clear();
                //adding objects compared to timer
                if(counter % 20 == 19){
                    t_TempList.triangle_loading(triangle_bitmap_list,width,height,1);
                    c_TempList.circle_Loading(imgcircle,width,height,1);
                }
                if(c_TempList.getList().size() > 2 + counter/19 || c_TempList.getList().size() > 4){
                    c_TempList.getList().remove(c_TempList.getList().size()-1);
                }
                if(t_TempList.getList().size() > 3 + counter/19 || t_TempList.getList().size() > 7){
                    t_TempList.getList().remove(t_TempList.getList().size()-1);
                }
                for(int j = 0; j < t_TempList.getList().size(); j++){
                    triangle_List.getList().add(t_TempList.getList().get(j));
                }
                for(int j = 0; j < c_TempList.getList().size(); j++){
                    circle_List.getList().add(c_TempList.getList().get(j));
                }
                t_TempList.getList().clear();
                c_TempList.getList().clear();
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (block.inRange(event.getX(), event.getY())) {
            is_holding = true;
            }
            if(is_holding && isRunning){
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
            System.out.println(is_holding);
            return true;
        }

    public boolean pauseResume() {
        isRunning = !isRunning;
        if (isRunning) {
            thread = new Thread(this);
            thread.start();
        }
        return isRunning;
    }

        @Override
        public void run() {
            while(isRunning){
                drawCanvas();
                }
        }
    }