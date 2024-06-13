package com.example.myapplication.Database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference refUser = FBDB.getReference("User");
    public static DatabaseReference refHighScore = FBDB.getReference("HighScore");
    public static String uid;
    public static FirebaseAuth refAuth = FirebaseAuth.getInstance();

    public static void getUser(FirebaseUser fbuser){
        uid = fbuser.getUid();


    }
}
