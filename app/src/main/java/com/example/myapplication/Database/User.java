package com.example.myapplication.Database;

public class User {
    private String uid;
    private String username;

    public User(String uid, String username) {
            this.uid = uid;
            this.username = username;
    }
    public User(){}

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
