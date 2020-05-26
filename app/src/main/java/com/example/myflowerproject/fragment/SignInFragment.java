package com.example.myflowerproject.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {

    public SignInFragment() {
        // Required empty public constructor
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        dontHaveAnAccount = view.findViewById(R.id.tv_already_have_an_account);
        parentFrameLayout = getActivity().findViewById(R.id.register_framelayout);
        txtemail = view.findViewById(R.id.sign_in_email);
        txtpassword = view.findViewById(R.id.sign_in_password);
        signInBtn = view.findViewById(R.id.sign_in_btn);
        signInFacebookBtn = view.findViewById(R.id.sign_in_facebook_btn);
        signInGoogleBtn = view.findViewById(R.id.sign_in_google_btn);
        progressBar = view.findViewById(R.id.sign_in_progressbar);
        forgotPassword = view.findViewById(R.id.sign_in_forgot_passwod);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new SignUpFragment());
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ResetPasswordFragment());
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

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
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
        //sendPostLocal(users);
    }

    private void sendPostLocal(Users user) {
        People people = new People();
        people.setName("Nguyen Khac Thanh");
        people.setEmail("bl.bleach1606@gmail.com");
        user.setPeople(people);
        signInBtn.setEnabled(false);
        signInBtn.setTextColor(Color.rgb(238,180,180));

        progressBar.setVisibility(View.VISIBLE);
        Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
        homeIntent.putExtra("user", user);
        startActivity(homeIntent);
        getActivity().finish();
    }

    private void sendPost(final Users users) {
        userAPI.postLogin(users).enqueue(new Callback<UserLoginResult>() {

            @Override
            public void onResponse(Call<UserLoginResult> call, Response<UserLoginResult> response) {
                if (response.isSuccessful()) {
                    Users user = response.body().getDataLoginResult().getUser();
                    signInBtn.setEnabled(false);
                    signInBtn.setTextColor(Color.rgb(238,180,180));

                    progressBar.setVisibility(View.VISIBLE);
                    Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                    homeIntent.putExtra("user", user);
                    startActivity(homeIntent);
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "error login", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    signInBtn.setEnabled(true);
                    signInBtn.setTextColor(Color.rgb(255,255,255));
                }
            }

            @Override
            public void onFailure(Call<UserLoginResult> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                signInBtn.setEnabled(true);
                signInBtn.setTextColor(Color.rgb(255,255,255));
            }
        });

    }
}
