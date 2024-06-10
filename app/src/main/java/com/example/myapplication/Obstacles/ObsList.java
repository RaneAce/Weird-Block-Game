package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

public class ObsList {
    private ArrayList<Figure> List;
    Random random = new Random();

    public ObsList(){
        this.List = new ArrayList<>();
    }

    public ArrayList<Figure> getList() {
        return List;
    }

    public void circle_Loading(Bitmap imgcircle, int Screenwidth, int Screenheight, int loadCount) {
        for (int h = 0; h < loadCount; h++) {
            int randheight;
            int location;
                location = -imgcircle.getWidth() + random.nextInt(Screenwidth + imgcircle.getWidth()) + 1;
                if( 0 < location && location < Screenwidth){
                    int[] randos = {-imgcircle.getHeight()+1, Screenheight};
                    randheight = randos[random.nextInt(2)];
                }
                else {
                    randheight = -imgcircle.getHeight() + random.nextInt(Screenheight + imgcircle.getHeight()) + 1;
                }
                circle Obsta = new circle(location, randheight , imgcircle, "alive");
                this.List.add(Obsta);
        }
    }

    public void triangle_loading(ArrayList<Bitmap> triangle_bitmap_list, int Screenwidth, int Screenheight, int loadCount){
        for(int i = 0; i < loadCount; i++) {
            int location;
            int randheight = random.nextInt(Screenheight - triangle_bitmap_list.get(0).getHeight() + 1);
            int randdirection = random.nextInt(2);
            String direction;
            // right
            if (randdirection == 0) {
                direction = "right";
                location = -triangle_bitmap_list.get(1).getWidth() + 1;
                triangle Obsta = new triangle(location, randheight, triangle_bitmap_list.get(1), "alive", direction);
                this.List.add(Obsta);
            }
            // left
            else {
                int secret_roll = 1 + random.nextInt(100);
                direction = "left";
                location = Screenwidth - 1;
                if(secret_roll == 69){
                    triangle Obsta = new triangle(location, randheight, triangle_bitmap_list.get(2), "alive", direction);
                    this.List.add(Obsta);
                }
                else {
                    triangle Obsta = new triangle(location, randheight, triangle_bitmap_list.get(0), "alive", direction);
                    this.List.add(Obsta);
                }
            }
        }
    }
}
