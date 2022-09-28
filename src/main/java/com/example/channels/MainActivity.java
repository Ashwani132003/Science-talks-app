package com.example.channels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    Button Watch;
    Button Channel;
    Button Projects;
    Button Listen;
    Button Team;
    Button Facts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAuth = FirebaseAuth.getInstance();

        Watch = findViewById(R.id.watch);
        Channel = findViewById(R.id.channels);
        Projects = findViewById(R.id.project);
        Listen = findViewById(R.id.listen);
        Team = findViewById(R.id.team);
        Facts = findViewById(R.id.fact);

        Channel.setOnClickListener(view -> {
            channel();
        });
    }

    private void channel(){
        startActivity(new Intent(MainActivity.this, Channel.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user == null){
            startActivity(new Intent(MainActivity.this, Signin.class));
        }

    }
}