package com.example.myflowerproject.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.OrderBillAPI;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.OrderBill;
import com.example.myflowerproject.model.entity.People;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.CategoryResult;
import com.example.myflowerproject.model.results.UserLoginResult;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_SignIn extends AppCompatActivity {

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText txtemail;
    private EditText txtpassword;
    private ProgressBar progressBar;
    private TextView forgotPassword;
    private Button signInBtn;
    private LoginButton loginButton;
    private Button signInGoogleBtn;
    private UserAPI userAPI;
    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userAPI = ApiUtils.getAPIService();
        dontHaveAnAccount = findViewById(R.id.tv_already_have_an_account);
        txtemail = findViewById(R.id.sign_in_email);
        txtpassword = findViewById(R.id.sign_in_password);
        signInBtn = findViewById(R.id.sign_in_btn);
//        signInGoogleBtn = findViewById(R.id.sign_in_google_btn);
        progressBar = findViewById(R.id.sign_in_progressbar);
        forgotPassword = findViewById(R.id.sign_in_forgot_passwod);
        callbackManager = CallbackManager.Factory.create();
        if(AccessToken.getCurrentAccessToken() != null &&
                !AccessToken.getCurrentAccessToken().isExpired()){
            findViewById(R.id.login_button).setVisibility(View.INVISIBLE);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setMessage("Bạn có muốn đăng nhập bằng facebook");
            b.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Users u = new Users();
                    u.setAccessToken(AccessToken.getCurrentAccessToken().getToken());
                    u.setType(2);
                    loginFace(u);
                }
            });
            b.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LoginManager.getInstance().logOut();
                    (findViewById(R.id.login_button)).setVisibility(View.VISIBLE);
                }
            });
            AlertDialog al = b.create();
            al.show();
            try{
                al.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.black);
                al.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.black);
                al.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(R.color.bithat);
                al.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(R.color.bithat);
                al.setInverseBackgroundForced(true);
            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }
        }
        loginButton = findViewById(R.id.login_button);
//        loginButton.setReadPermissions("email",  "public_profile");
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {

                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        Users u = new Users();
                        u.setAccessToken(loginResult.getAccessToken().getToken());
                        u.setType(2);
                        loginFace(u);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_SignIn.this, Activity_Signup.class);
                startActivity(intent);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_SignIn.this, Activity_ResetPassword.class);
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

        user.setToken( "1312312");

        progressBar.setVisibility(View.VISIBLE);
        Intent homeIntent = new Intent(Activity_SignIn.this, Activity_Home.class);
        startActivity(homeIntent);
        finish();
    }

    private void sendPost(final Users users) {
        progressBar.setVisibility(View.VISIBLE);
        userAPI.postLogin(users).enqueue(new Callback<UserLoginResult>() {
            @Override
            public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                if (response.isSuccessful()) {
                    Users user = response.body().getDataLoginResult().getUser();
                    user.setToken( "Bearer " + response.body().getDataLoginResult().getAccessToken());
                    Container.users = user;
                    signInBtn.setEnabled(false);
                    signInBtn.setTextColor(Color.rgb(238,180,180));
                    getCurrentOrderBill();
                    getListCategory();
                } else {
                    Toast.makeText(Activity_SignIn.this, "Sai Mật khẩu", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    signInBtn.setEnabled(true);
                    signInBtn.setTextColor(Color.rgb(255,255,255));
                }
            }

            @Override
            public void onFailure(Call<UserLoginResult> call, Throwable t) {
                Toast.makeText(Activity_SignIn.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));
            }
        });

    }

    private void loginFace(Users u){
        progressBar.setVisibility(View.VISIBLE);
        (findViewById(R.id.login_button)).setVisibility(View.INVISIBLE);
        userAPI.postLoginFace(u).enqueue(new Callback<UserLoginResult>() {
            @Override
            public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                if (response.isSuccessful()) {
                    Users user = response.body().getDataLoginResult().getUser();
                    user.setToken( "Bearer " + response.body().getDataLoginResult().getAccessToken());
                    Container.users = user;
                    signInBtn.setEnabled(false);
                    signInBtn.setTextColor(Color.rgb(238,180,180));
                    getCurrentOrderBill();
                    getListCategory();
                } else {
                    Toast.makeText(Activity_SignIn.this, "Sai Mật khẩu", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    signInBtn.setEnabled(true);
                    signInBtn.setTextColor(Color.rgb(255,255,255));
                }
            }

            @Override
            public void onFailure(Call<UserLoginResult> call, Throwable t) {
                Toast.makeText(Activity_SignIn.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));
            }
        });
    }

    public void getListCategory(){
        try{
            (ApiUtils.getCategoryAPI()).findCategory(Container.users.getToken()).enqueue(new Callback<CategoryResult>() {
                @Override
                public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                    if (response.isSuccessful()) {
                        CategoryResult rs = response.body();
                        Container.listCategory = rs.getCategoryList();
                        Intent homeIntent = new Intent(Activity_SignIn.this, Activity_Home.class);
                        startActivity(homeIntent);
//                        (findViewById(R.id.login_button)).setVisibility(View.VISIBLE);
                        finish();
                    } else {
                        Toast.makeText(Activity_SignIn.this, "Loi ???", Toast.LENGTH_SHORT).show();
//                        System.out.println("loi ");
                    }
                }
                @Override
                public void onFailure(Call<CategoryResult> call, Throwable t) {
                    System.out.println("failure "+ t.toString());
                }
            });
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void getCurrentOrderBill() {
        OrderBillAPI orderBillAPI = ApiUtils.getOrderBillAPI();
        orderBillAPI.getCurrentOrder(Container.users.getToken()).enqueue(new Callback<OrderBill>() {
            @Override
            public void onResponse(Call<OrderBill> call, Response<OrderBill> response) {
                Container.orderBill = response.body();
            }

            @Override
            public void onFailure(Call<OrderBill> call, Throwable t) {

            }
        });
    }
}
