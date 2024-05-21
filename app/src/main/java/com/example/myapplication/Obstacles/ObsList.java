package com.example.myapplication.Obstacles;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Random;

public class ObsList {
    private ArrayList<Figure> List;
    Random random = new Random();

    //replacing old list
    public ObsList(ArrayList<Figure> ListToCopy){
        this.List = ListToCopy;
    }

    //creating new list
    public ObsList(){
        this.List = new ArrayList<>();
    }

    public ArrayList<Figure> getList() {
        return List;
    }

    public void List_Loading(Bitmap imgtriangle, Bitmap imgcircle, int Screenwidth, int Screenheight) {
        for (int h = 0; h < 10; h++) {
            int rand = random.nextInt(10);
            int randheight;
            int location;
            //triangle
            if (rand <= 5) {
                randheight = random.nextInt(Screenheight - imgtriangle.getHeight() + 1);
                int randdirection = random.nextInt(2);
                String direction;
                // right
                if (randdirection == 0) {
                    direction = "right";
                    location = -imgtriangle.getWidth() + 1;
                }
                // left
                else {
                    direction = "left";
                    location = Screenwidth - 1;
                }
                triangle Obsta = new triangle(location, randheight, imgtriangle, "triangle", direction);
                this.List.add(Obsta);
            }
            //circle
            else{
                location = -imgcircle.getWidth() + random.nextInt(Screenwidth + imgcircle.getWidth()) + 1;
                if( 0 < location && location < Screenwidth){
                    int[] randos = {-imgcircle.getHeight()+1, Screenheight};
                    randheight = randos[random.nextInt(2)];
                }
                else {
                    randheight = -imgcircle.getHeight() + random.nextInt(Screenheight + imgcircle.getHeight()) + 1;
                }
                circle Obsta = new circle(location, randheight , imgcircle, "circle");
                this.List.add(Obsta);
            }
        }
    }
}
