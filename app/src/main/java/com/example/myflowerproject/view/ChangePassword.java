package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myflowerproject.R;

public class ChangePassword extends AppCompatActivity {
    TextView txtForgotPass;
    ConstraintLayout tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        txtForgotPass = findViewById(R.id.txtForgotPass);
        tmp = findViewById(R.id.____);

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.txtOldPass).setVisibility(View.INVISIBLE);
                findViewById(R.id.txtNewPass).setVisibility(View.INVISIBLE);
                findViewById(R.id.txtEnterNewPass).setVisibility(View.INVISIBLE);
                findViewById(R.id.btnSaveChange).setVisibility(View.INVISIBLE);
                findViewById(R.id.txtForgotPass).setVisibility(View.INVISIBLE);
                findViewById(R.id.tbChangPass).setVisibility(View.INVISIBLE);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                fragmentTransaction.replace(tmp.getId(), new ResetPasswordFragment());
//                tmp.setVisibility(View.INVISIBLE);
//                fragmentTransaction.hide();
                fragmentTransaction.commit();
            }
        });
    }
}