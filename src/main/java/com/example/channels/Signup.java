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

public class Signup extends AppCompatActivity {

    TextView uemail;
    TextView upassword;
    Button signup;
    Button signin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signup = findViewById(R.id.signup);
        uemail = findViewById(R.id.email);
        upassword = findViewById(R.id.password);
        signin = findViewById(R.id.usignin);

        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(view -> {
            createUser();
        });
        signin.setOnClickListener(view -> {
            startActivity(new Intent(Signup.this, Signin.class));

        });


    }

    private void createUser(){
        String email = uemail.getText().toString();
        String password = upassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            uemail.setError("Please enter a valid email");
            uemail.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            upassword.setError("Please enter a valid Password");
            upassword.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "User generated successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Signup.this, Signin.class));
                    }else{
                        Toast.makeText(Signup.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}