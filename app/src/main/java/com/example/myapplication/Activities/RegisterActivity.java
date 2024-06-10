package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refAuth;
import static com.example.myapplication.Database.FBref.refHighScore;
import static com.example.myapplication.Database.FBref.refUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Database.HighScore;
import com.example.myapplication.Database.User;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    public TextView already_signed;
    public Button register_button;
    public EditText editUsername, editpassword, editEmail;
    private String email, password, username, uid;
    public User dbuser;
    public HighScore dbuser_score;
    public Date currentDate = new Date();
    public SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    String date = dateFormat.format(currentDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();

    }

    private void initViews() {
        already_signed = (TextView) findViewById(R.id.already_signed);
        editUsername = (EditText) findViewById(R.id.editUsername);
        editpassword = (EditText) findViewById(R.id.editPassword);
        editEmail = (EditText) findViewById(R.id.editEmail);
        register_button = (Button) findViewById(R.id.register_button);
    }

    public void already_signed(View view){
        Intent intent = new Intent(RegisterActivity.this,com.example.myapplication.Activities.LoginActivity.class);
        startActivity(intent);
    }

    public void register(View view){
        email = editEmail.getText().toString().trim();
        password = editpassword.getText().toString().trim();
        username = editUsername.getText().toString().trim();

        refAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user = refAuth.getCurrentUser();
                    Log.d("MainActivity", "createUserWithEmail:success");
                    uid = user.getUid();
                    dbuser = new User(uid, username);
                    dbuser_score = new HighScore(0,date,uid,username);
                    refUser.child(uid).setValue(dbuser);
                    refHighScore.child(uid).setValue(dbuser_score);
                    Toast.makeText(RegisterActivity.this, "Successful registration", Toast.LENGTH_LONG).show();
                }
                else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException)
                        Toast.makeText(RegisterActivity.this, "User with e-mail already exist!", Toast.LENGTH_LONG).show();
                    else {
                        Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(RegisterActivity.this, "Registration failed.",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
}