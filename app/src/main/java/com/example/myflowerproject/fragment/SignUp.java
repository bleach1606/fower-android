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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.People;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.DataSignupResult;
import com.example.myflowerproject.view.HomeActivity;
import com.example.myflowerproject.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUp extends AppCompatActivity {

    private TextView alreadyHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText firstName;
    private EditText password;
    private EditText confirmPassword;
    private EditText lastName;
    private EditText phoneNumber;
    private RadioButton btnMale;
    private RadioButton btnFemale;
    private Button signUpBtn;

    private ProgressBar progressBar;
    private UserAPI userAPI;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        alreadyHaveAnAccount = findViewById(R.id.tv_already_have_an_account);

        email = findViewById(R.id.sign_up_email);
        firstName = findViewById(R.id.sign_up_first_name);
        lastName = findViewById(R.id.sign_up_last_name);
        phoneNumber = findViewById(R.id.sign_up_phone_number);
        password = findViewById(R.id.sign_up_password);
        confirmPassword = findViewById(R.id.sign_up_confirm_password);
        parentFrameLayout = findViewById(R.id.register_framelayout);
        signUpBtn = findViewById(R.id.sign_up_btn);
        btnMale = findViewById(R.id.sign_up_sex_male);
        btnFemale = findViewById(R.id.sign_up_sex_female);
        userAPI = ApiUtils.getAPIService();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        firstName.addTextChangedListener(new TextWatcher() {
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
        lastName.addTextChangedListener(new TextWatcher() {
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
        phoneNumber.addTextChangedListener(new TextWatcher() {
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
//                checkEmailAndPassword();

                try{
                    Users user = new Users(email.getText().toString(), password.getText().toString());
                    People people = new People();
                    people.setFirstName(firstName.getText().toString());
                    people.setLastName(lastName.getText().toString());
                    people.setPhoneNumber(phoneNumber.getText().toString());
                    people.setSex((btnMale.isChecked()?"male":(btnFemale.isChecked())?"female":"else"));
                    user.setPeople(people);
                    System.out.println("here");
                    userAPI.signup(user).enqueue(new Callback<DataSignupResult>() {
                        @Override
                        public void onResponse(Call<DataSignupResult> call, Response<DataSignupResult> response) {
                            System.out.println("on respone");
                            if(response.isSuccessful()){
                                Users u = response.body().getUser();
                                Intent intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                System.out.println(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<DataSignupResult> call, Throwable t) {
                            Toast.makeText(SignUp.this, t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch(Exception ex){
                    Toast.makeText(SignUp.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkInputs(){
        if(!TextUtils.isEmpty(email.getText())){
            if(!TextUtils.isEmpty(firstName.getText())){
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
            signUpBtn.setTextColor(Color.rgb(238, 180, 180));
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
