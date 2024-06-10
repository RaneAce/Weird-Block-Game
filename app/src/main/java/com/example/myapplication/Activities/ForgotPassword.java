package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ForgotPassword extends AppCompatActivity {

   public EditText editEmail;
   public Button reset_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        editEmail = (EditText) findViewById(R.id.editEmail);
        reset_password = (Button) findViewById(R.id.reset_password);

    }


    public void reset_password(View view){
        String email = editEmail.getText().toString().trim();

        if(email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide a valid Email");
            editEmail.requestFocus();
        }

        refAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this,"check your email to reset password",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ForgotPassword.this,"something went wrong, try again", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}