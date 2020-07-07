package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.dao.ForgotPassword;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_ResetPassword_WithCode extends AppCompatActivity {

    private Button btnSave;
    private EditText edtConfirmCode;
    private EditText edtNewPassword;
    private EditText edtConfirmPassword;
    private UserAPI userAPI;

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
                ForgotPassword forgotPassword = new ForgotPassword(edtConfirmCode.getText().toString(),
                        edtNewPassword.getText().toString(),edtConfirmPassword.getText().toString());
                if(edtNewPassword.getText().toString().equals(edtConfirmPassword.getText().toString())){

                    ApiUtils.getAPIService().resetPasswordWithCode(forgotPassword).enqueue(new Callback<UserResult>() {
                        @Override
                        public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                            if(response.isSuccessful()) {
                                Toast.makeText(Activity_ResetPassword_WithCode.this, "Change password successfully!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Activity_ResetPassword_WithCode.this, Activity_SignIn.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Activity_ResetPassword_WithCode.this, response.message(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UserResult> call, Throwable t) {
                            Toast.makeText(Activity_ResetPassword_WithCode.this,
                                    "Fail. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    });
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
