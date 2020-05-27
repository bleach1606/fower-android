package com.example.myflowerproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myflowerproject.R;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Drawable drawable= getResources().getDrawable(R.drawable.ic_keyboard_backspace_black_24dp);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable newdrawable = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap,30 ,20  , true));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
// ca day nua - to inbox bang dt nhe
