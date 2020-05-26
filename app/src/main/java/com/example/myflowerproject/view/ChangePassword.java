package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myflowerproject.R;

public class ChangePassword extends AppCompatActivity {
    TextView txtForgotPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtForgotPass = findViewById(R.id.txtForgotPass);


        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePassword.this, ChangePassword.class);
                startActivity(intent);
            }
        });
    }
}