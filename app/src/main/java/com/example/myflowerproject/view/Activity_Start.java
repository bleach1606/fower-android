package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.myflowerproject.R;
import com.example.myflowerproject.view.Activity_SignIn;

public class Activity_Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemClock.sleep(500);
        Intent loginIntent = new Intent(Activity_Start.this, Activity_SignIn.class);
        startActivity(loginIntent);
        finish();
    }
}
