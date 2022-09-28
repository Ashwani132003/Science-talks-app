package com.example.channels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signin extends AppCompatActivity {

    TextView lemail;
    TextView lpassword;
    Button signin;
    Button signup;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.lsignup);
        lemail = findViewById(R.id.lemail);
        lpassword = findViewById(R.id.lpassword);
        mAuth = FirebaseAuth.getInstance();


        signin.setOnClickListener(view -> {
            loginuser();
        });

        signup.setOnClickListener(view -> {
            startActivity(new Intent(Signin.this, Signup.class));
        });


    }

    private void loginuser(){
        String email = lemail.getText().toString();
        String password = lpassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            lemail.setError("Please enter a valid email");
            lemail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            lpassword.setError("Please enter a valid Password");
            lpassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signin.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signin.this, MainActivity.class));
                    }else{
                        Toast.makeText(Signin.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}