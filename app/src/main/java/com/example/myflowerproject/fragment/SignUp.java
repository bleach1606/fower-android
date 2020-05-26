package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.view.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends AppCompatActivity {

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText fullName;
    private EditText password;
    private EditText confirmPassword;

    private Button signUpBtn;

    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        alreadyHaveAnAccount = findViewById(R.id.tv_already_have_an_account);

        parentFrameLayout = findViewById(R.id.register_framelayout);

        email = findViewById(R.id.sign_up_email);
        fullName = findViewById(R.id.sign_up_fullname);
        password = findViewById(R.id.sign_up_password);
        confirmPassword = findViewById(R.id.sign_up_confirm_password);

        signUpBtn = findViewById(R.id.sign_up_btn);

        progressBar = findViewById(R.id.sign_up_progressbar);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });

        email.addTextChangedListener(new TextWatcher() {
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
        fullName.addTextChangedListener(new TextWatcher() {
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
        password.addTextChangedListener(new TextWatcher() {
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
        confirmPassword.addTextChangedListener(new TextWatcher() {
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

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //to do: send data
                checkEmailAndPassword();
            }
        });
    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(fullName.getText())){
                if(!TextUtils.isEmpty(password.getText())){
                    if(!TextUtils.isEmpty(confirmPassword.getText())){
                        signUpBtn.setEnabled(true);
                        signUpBtn.setTextColor(Color.rgb(255,255,255));
                    }
                    else {
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.rgb(238,180,180));
                    }
                }
                else {
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.rgb(238,180,180));
                }
            }
            else {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(238,180,180));
            }
        }
        else {
            signUpBtn.setEnabled(false);
            signUpBtn.setTextColor(Color.rgb(238,180,180));
        }
    }
    private void checkEmailAndPassword() {
        signUpBtn.setEnabled(false);
        signUpBtn.setTextColor(Color.rgb(238,180,180));

        progressBar.setVisibility(View.VISIBLE);

        //Nếu không có lỗi
        Intent homeIntent = new Intent(SignUp.this, HomeActivity.class);
        startActivity(homeIntent);
        finish();

        /*
        //Nếu có lỗi
        //Toast lỗi ra màn hình ("Password doesn't matched!","Invalid Email!", ...)
        // Sau đó gán lại các giá trị hiển thị

        progressBar.setVisibility(View.INVISIBLE);
        signUpBtn.setEnabled(true);
        signUpBtn.setTextColor(Color.rgb(255,255,255));

         */
    }
}
