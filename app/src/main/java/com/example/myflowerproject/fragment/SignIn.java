package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.People;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserLoginResult;
import com.example.myflowerproject.view.HomeActivity;
import com.example.myflowerproject.view.HomeActivityVer2;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignIn extends AppCompatActivity {

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText txtemail;
    private EditText txtpassword;
    private ProgressBar progressBar;
    private TextView forgotPassword;
    private Button signInBtn;
    private Button signInFacebookBtn;
    private Button signInGoogleBtn;
    private UserAPI userAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_in);
        dontHaveAnAccount = findViewById(R.id.tv_already_have_an_account);
        parentFrameLayout = findViewById(R.id.register_framelayout);
        txtemail = findViewById(R.id.sign_in_email);
        txtpassword = findViewById(R.id.sign_in_password);
        signInBtn = findViewById(R.id.sign_in_btn);
        signInFacebookBtn = findViewById(R.id.sign_in_facebook_btn);
        signInGoogleBtn = findViewById(R.id.sign_in_google_btn);
        progressBar = findViewById(R.id.sign_in_progressbar);
        forgotPassword = findViewById(R.id.sign_in_forgot_passwod);


        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        txtemail.addTextChangedListener(new TextWatcher() {
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
        txtpassword.addTextChangedListener(new TextWatcher() {
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

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailAndPassword();
            }
        });

        signInGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        signInFacebookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void checkInputs() {
        if(!TextUtils.isEmpty(txtemail.getText())){
            if(!TextUtils.isEmpty(txtpassword.getText())){
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));
            }
            else {
                signInBtn.setEnabled(false);
                signInBtn.setTextColor(Color.rgb(238,180,180));
            }
        }
        else {
            signInBtn.setEnabled(false);
            signInBtn.setTextColor(Color.rgb(238,180,180));
        }
    }
    private void checkEmailAndPassword() {
        userAPI = ApiUtils.getAPIService();
        String username = txtemail.getText().toString();
        String passWord = txtpassword.getText().toString();
        Users users = new Users(username, passWord);
        sendPost(users);
//        sendPostLocal(users);
    }

    private void sendPostLocal(Users user) {
        People people = new People();
        people.setFirstName("Nguyen Khac");
        people.setLastName("Thanh");
        people.setEmail("bl.bleach1606@gmail.com");
        user.setPeople(people);
        signInBtn.setEnabled(false);
        signInBtn.setTextColor(Color.rgb(238,180,180));

        //Lưu user vào share preference
        SharedPreferences mPrefs = getSharedPreferences( "user", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.commit();

        progressBar.setVisibility(View.VISIBLE);
        Intent homeIntent = new Intent(SignIn.this, HomeActivityVer2.class);
        startActivity(homeIntent);
        finish();
    }

    private void sendPost(final Users users) {
        userAPI.postLogin(users).enqueue(new Callback<UserLoginResult>() {

            @Override
            public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                if (response.isSuccessful()) {
                    Users user = response.body().getDataLoginResult().getUser();
                    user.setToken(response.body().getDataLoginResult().getAccessToken());

                    //Lưu user vào share preference
                    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(user);
                    prefsEditor.putString("user", json);
                    prefsEditor.commit();

                    signInBtn.setEnabled(false);
                    signInBtn.setTextColor(Color.rgb(238,180,180));

                    progressBar.setVisibility(View.VISIBLE);
                    Intent homeIntent = new Intent(SignIn.this, HomeActivityVer2.class);
                    startActivity(homeIntent);
                    finish();
                } else {
                    Toast.makeText(SignIn.this, "Sai Mật khẩu", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    signInBtn.setEnabled(true);
                    signInBtn.setTextColor(Color.rgb(255,255,255));
                }
            }

            @Override
            public void onFailure(Call<UserLoginResult> call, Throwable t) {
                Toast.makeText(SignIn.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));
            }
        });

    }
}
