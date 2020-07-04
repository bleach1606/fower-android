package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myflowerproject.R;

public class Activity_ResetPassword_WithCode extends AppCompatActivity {

    private Button btnSave;
    private EditText edtConfirmCode;
    private EditText edtNewPassword;
    private EditText edtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__reset_password__with_code);

        btnSave = findViewById(R.id.btnSave);
        edtConfirmCode = findViewById(R.id.edtConfirmCode);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);

        edtConfirmPassword.addTextChangedListener(new TextWatcher() {
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
        edtConfirmCode.addTextChangedListener(new TextWatcher() {
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
        edtNewPassword.addTextChangedListener(new TextWatcher() {
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNewPassword.getText() == edtConfirmPassword.getText()){
                    //todo:
                } else {
                    Toast.makeText(Activity_ResetPassword_WithCode.this,
                            "Mật khẩu không khớp! Vui lòng nhập lại!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkInputs() {
        if(!TextUtils.isEmpty(edtConfirmCode.getText())){
            if(!TextUtils.isEmpty(edtNewPassword.getText())){
                if(!TextUtils.isEmpty(edtConfirmPassword.getText())) {
                    btnSave.setEnabled(true);
                    btnSave.setTextColor(Color.rgb(255, 255, 255));
                }
                else {
                    btnSave.setEnabled(false);
                    btnSave.setTextColor(Color.rgb(238,180,180));
                }
            }
            else {
                btnSave.setEnabled(false);
                btnSave.setTextColor(Color.rgb(238,180,180));
            }
        }
        else {
            btnSave.setEnabled(false);
            btnSave.setTextColor(Color.rgb(238,180,180));
        }
    }
}
