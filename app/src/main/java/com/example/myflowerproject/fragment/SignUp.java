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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.UserAPI;
import com.example.myflowerproject.model.entity.People;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.UserLoginResult;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);

        alreadyHaveAnAccount = findViewById(R.id.tv_already_have_an_account);

        email = view.findViewById(R.id.sign_up_email);

        firstName = view.findViewById(R.id.sign_up_first_name);
        lastName = view.findViewById(R.id.sign_up_last_name);
        phoneNumber = view.findViewById(R.id.sign_up_phone_number);
        password = view.findViewById(R.id.sign_up_password);
        confirmPassword = view.findViewById(R.id.sign_up_confirm_password);
        parentFrameLayout = findViewById(R.id.register_framelayout);

        signUpBtn = view.findViewById(R.id.sign_up_btn);
        btnMale = view.findViewById(R.id.sign_up_sex_male);
        btnFemale = view.findViewById(R.id.sign_up_sex_female);
        email = findViewById(R.id.sign_up_email);
        fullName = findViewById(R.id.sign_up_fullname);
        password = findViewById(R.id.sign_up_password);
        confirmPassword = findViewById(R.id.sign_up_confirm_password);

        signUpBtn = findViewById(R.id.sign_up_btn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String _username = firstName.getText().toString();
                    String _password = password.getText().toString();
                    Users user = new Users();
                    user.setPassword(_password);
                    user.setUsername(_username);
                    user.setPeople(new People());
                    UserAPI userAPI = ApiUtils.getAPIService();
                    userAPI.signup(user).enqueue(new Callback<UserLoginResult>() {
                        @Override
                        public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserLoginResult> call, Throwable t) {
                            Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }catch(Exception ex){

                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
                //to do: send data
                checkEmailAndPassword();
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
    private void checkInputs() {
        if (!TextUtils.isEmpty(firstName.getText())) {
            if (!TextUtils.isEmpty(lastName.getText())) {
                if (!TextUtils.isEmpty(email.getText())) {
                    if (!TextUtils.isEmpty(phoneNumber.getText())) {
                        if (!TextUtils.isEmpty(password.getText())) {
                            if (!TextUtils.isEmpty(confirmPassword.getText())) {
                                signUpBtn.setEnabled(true);
                                signUpBtn.setTextColor(Color.rgb(255, 255, 255));
                            } else {
                                signUpBtn.setEnabled(false);
                                signUpBtn.setTextColor(Color.rgb(238, 180, 180));
                            }
                        } else {
                            signUpBtn.setEnabled(false);
                            signUpBtn.setTextColor(Color.rgb(238, 180, 180));
                        }
                    } else {
                        signUpBtn.setEnabled(false);
                        signUpBtn.setTextColor(Color.rgb(238, 180, 180));
                    }
                } else {
                    signUpBtn.setEnabled(false);
                    signUpBtn.setTextColor(Color.rgb(238, 180, 180));
                }
            } else {
                signUpBtn.setEnabled(false);
                signUpBtn.setTextColor(Color.rgb(238, 180, 180));
            }
        } else {
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
