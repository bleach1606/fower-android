package com.example.myflowerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
// to vua commit sau khi chinh sua - cau nhin kip ko
//c lam lai 1 lan nua ddk k ok
// vi du to commment trong file changepasswod nay
// hinh nhu co bug gì rồi