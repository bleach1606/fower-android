package com.example.myflowerproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.api.GetImage;
import com.example.myflowerproject.ui.notification.Fragment_Notification;
import com.google.android.material.navigation.NavigationView;

//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Activity_Home extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_my_cart, R.id.nav_my_order, R.id.nav_notification)
                .setDrawerLayout(drawer)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity2, menu);
        ((TextView)findViewById(R.id.tvUsername)).setText(Container.users.getUsername());
        new GetImage(findViewById(R.id.ivAvatar))
                .execute(Container.users.getPeople().getAvatar());
        ((Button)findViewById(R.id.btnSignout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Container.users = null;
                Container.listCategory = null;
                Intent intent = new Intent(Activity_Home.this, Activity_SignIn.class);
                startActivity(intent);
                finish();
            }
        });
        return true;
    }


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.app_bar_search:
                ///to do : Search
                return true;
            case R.id.home_cart_icon:
                return true;
            case R.id.home_notification_icon:
                try {
                    Intent intent = new Intent(Activity_Home.this,Fragment_Notification.class);
                    startActivity(intent);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
