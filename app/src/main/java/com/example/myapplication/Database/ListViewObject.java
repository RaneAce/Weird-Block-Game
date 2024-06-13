package com.example.myapplication.Database;

public class ListViewObject {
    public String score;
    public String name;

    public ListViewObject(String score, String name){
        this.score = score;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }
}
