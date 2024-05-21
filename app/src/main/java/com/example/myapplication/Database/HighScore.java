package com.example.myapplication.Database;

public class HighScore {
    private int score;
    private String date;
    private String uid;
    private String username;

    public HighScore(int score, String date, String uid, String username){
        this.score = score;
        this.date = date;
        this.uid = uid;
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

