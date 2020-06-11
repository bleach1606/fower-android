package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myflowerproject.R;

public class ChangePassword extends AppCompatActivity {
    TextView txtForgotPass;
    ConstraintLayout tmp;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}