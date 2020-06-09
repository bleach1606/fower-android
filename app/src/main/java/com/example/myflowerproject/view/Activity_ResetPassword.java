package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.R;
import com.example.myflowerproject.view.Activity_SignIn;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPassword extends AppCompatActivity {

    private EditText registeredEmail;
    private Button resetPasswordBtn;
    private TextView goBack;

    private FrameLayout parentFrameLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reset_password);

        registeredEmail = findViewById(R.id.forgot_password_email);
        resetPasswordBtn = findViewById(R.id.reset_password_btn);
        goBack = findViewById(R.id.tv_forgot_password_go_back);


        registeredEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPasswordBtn.setEnabled(false);
                resetPasswordBtn.setTextColor(Color.rgb(238,180,180));
                //to do: send & check data
                //Nếu tồn tại Email registed
                //send email
                //Nếu đã gửi email xong
                Toast.makeText(ResetPassword.this,"Email sent successfully! Please check your email.",Toast.LENGTH_LONG).show();

                //Nếu Email không tồn tại
                /*
                Toast.makeText(getActivity(),"Unregistered Email!", Toast.LENGTH_LONG).show();
                resetPasswordBtn.setEnabled(true);
                resetPasswordBtn.setTextColor(Color.rgb(255,255,255));
                 */
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, Activity_SignIn.class);
                startActivity(intent);
            }
        });
    }

    private void checkInputs() {
        if(TextUtils.isEmpty(registeredEmail.getText())){
            resetPasswordBtn.setEnabled(false);
            resetPasswordBtn.setTextColor(Color.rgb(238,180,180));
        }
        else {
            resetPasswordBtn.setEnabled(true);
            resetPasswordBtn.setTextColor(Color.rgb(255,255,255));
        }
    }

}
