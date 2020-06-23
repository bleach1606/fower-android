package com.example.myflowerproject.ui.account;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myflowerproject.R;
import com.example.myflowerproject.view.Activity_ResetPassword;
import com.example.myflowerproject.view.ChangePassword;

public class Fragment_ChangePassword extends Fragment {
    public Fragment_ChangePassword() {

    }
    TextView txtForgotPass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment__change_password, container, false);
        txtForgotPass = view.findViewById(R.id.txtForgotPass);

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart_intent = new Intent(getContext(), Activity_ResetPassword.class);
                getContext().startActivity(cart_intent);
            }
        });
        return view;
    }
}
