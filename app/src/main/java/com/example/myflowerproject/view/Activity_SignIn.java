package com.example.myflowerproject.view;

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
import com.example.myflowerproject.model.results.OrderBillResult;
import com.example.myflowerproject.model.results.UserLoginResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Activity_SignIn extends AppCompatActivity {

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
        setContentView(R.layout.activity_sign_in);
        dontHaveAnAccount = findViewById(R.id.tv_already_have_an_account);
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
        orderBillAPI.getCurrentOrder(Container.users.getToken()).enqueue(new Callback<OrderBillResult>() {
            @Override
            public void onResponse(Call<OrderBillResult> call, Response<OrderBillResult> response) {
                Container.orderBill = response.body().getOrderBill();
            }

            @Override
            public void onFailure(Call<OrderBillResult> call, Throwable t) {

            }
        });
    }
}
