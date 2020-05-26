package com.example.myflowerproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflowerproject.ChangePassword;
import com.example.myflowerproject.MyAccount;
import com.example.myflowerproject.Notification;
import com.example.myflowerproject.fragment.HomeFragment;
import com.example.myflowerproject.fragment.ListItemFragment;
import com.example.myflowerproject.R;
import com.example.myflowerproject.model.entity.Users;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private Users user;
    private TextView txtNameUser;
    private TextView txtEmailUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        frameLayout = findViewById(R.id.home_framelayout);
        setFragment(new HomeFragment());


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        txtNameUser = findViewById(R.id.nav_header_home_fullname);
        txtEmailUser = findViewById(R.id.nav_header_home_email);
        Intent intent = getIntent();
        user = (Users) intent.getSerializableExtra("user");
        Toast.makeText( getBaseContext(), user.toString(), Toast.LENGTH_SHORT).show();

        txtNameUser.setText(user.getPeople().getName());
        txtEmailUser.setText(user.getUsername());
        return true;
    }
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
                Intent intent = new Intent(HomeActivity.this, Notification.class);
                startActivity(intent);
                break;
            case R.id.home_notification_icon:
                //code xử lý khi bấm menu2
                break;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_add_image){

        }
        else if(id == R.id.nav_home){
            setFragment(new HomeFragment());
        }
        else if(id == R.id.nav_my_orders){

        }
        else if(id == R.id.nav_my_rewards){
            setFragment(new ListItemFragment());
        }
        else if(id == R.id.nav_my_account){
            Intent intent = new Intent(HomeActivity.this, MyAccount.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_change_password){
            Intent intent = new Intent(HomeActivity.this, ChangePassword.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_sign_out){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
