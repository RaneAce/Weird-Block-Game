package com.example.myapplication.Activities;

import static com.example.myapplication.Database.FBref.refAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText eEmail, ePassword;
    private TextView register, forgot_passowrd;
    private Button login_button;
    public SharedPreferences user_preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        user_preferences = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
    }

    private void initViews() {
        eEmail = (EditText) findViewById(R.id.eEmail);
        ePassword = (EditText) findViewById(R.id.ePassword);
        register = (TextView) findViewById(R.id.register_text);
        forgot_passowrd = (TextView) findViewById(R.id.forgot_password);
    }

    public void login(View view){
        String email = eEmail.getText().toString().trim();
        String password = ePassword.getText().toString().trim();

        if(email.isEmpty()){
            eEmail.setError("Email is required!");
            eEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            eEmail.setError("Enter a valid Email");
            eEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
         ePassword.setError("Password is required!");
         ePassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            ePassword.setError("password too short!");
            ePassword.requestFocus();
            return;
        }

        refAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                FirebaseUser user = refAuth.getCurrentUser();

                if(task.isSuccessful()){
                    if(user.isEmailVerified()) {
                        user_preferences = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                        SharedPreferences.Editor editor = user_preferences.edit();
                        editor.putBoolean("stayConnected", true);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this, com.example.myapplication.Activities.Main.class);
                        startActivity(intent);
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check Email to verify your account", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this,"Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void register_go_to(View view){
        Intent intent = new Intent(LoginActivity.this, com.example.myapplication.Activities.RegisterActivity.class);
        startActivity(intent);
    }

    public void forgotpassword(View view){
        Intent intent = new Intent(LoginActivity.this,com.example.myapplication.Activities.ForgotPassword.class);
        startActivity(intent);
    }


    //on start if already logged in - got to next activity
    @Override
    public void onStart(){
        super.onStart();
        Boolean is_connected = user_preferences.getBoolean("stayConnected", false);
        if(refAuth.getCurrentUser() != null && is_connected){
            Intent intent = new Intent(LoginActivity.this, com.example.myapplication.Activities.Main.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

}
