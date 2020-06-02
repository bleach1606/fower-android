package com.example.myflowerproject.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;

import com.example.myflowerproject.R;
import com.example.myflowerproject.fragment.HomeFragment;
import com.example.myflowerproject.model.adapter.CategoryAdapter2;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.CategoryAPI;
import com.example.myflowerproject.model.entity.CategoryModel;
import com.example.myflowerproject.model.entity.Users;
import com.example.myflowerproject.model.results.CategoryResult;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ViewPager categoryViewPager;
    private TabLayout categoryTabLayout;

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;
    private NavigationView navigationView;

    private Users user;
    private List<CategoryModel> categoryModelList;
    private CategoryAPI categoryAPI;

    private TextView txtNameUser;
    private TextView txtEmailUser;
    private ImageView imgAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        categoryViewPager = findViewById(R.id.category_viewpager);
        categoryTabLayout = findViewById(R.id.category_tab_layout);

        final CategoryAdapter2 categoryAdapter = new CategoryAdapter2(getSupportFragmentManager(), categoryTabLayout.getTabCount());
        categoryViewPager.setAdapter(categoryAdapter);

        categoryViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(categoryTabLayout));

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view_test);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
//        onNavigationItemSelected(navigationView.getMenu().getItem(0));
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
//        imgAvatar = findViewById(R.id.imageView);

        // lây dữ liệu về
        Gson gson = new Gson();
        SharedPreferences mPrefs = getSharedPreferences( "user", MODE_PRIVATE);
        String json = mPrefs.getString("user", "");
        user = gson.fromJson(json, Users.class);

        getListCategory();

        Toast.makeText( getBaseContext(), user.toString(), Toast.LENGTH_SHORT).show();

        txtNameUser.setText(user.getPeople().getFirstName() + user.getPeople().getLastName());
        txtEmailUser.setText(user.getUsername());
        new DownloadImageTask(findViewById(R.id.imageView))
                .execute(user.getPeople().getAvatar());
        return true;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
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
                Intent cart_intent = new Intent(HomeActivity.this, MyCartActivity.class);
                startActivity(cart_intent);
                return true;
            case R.id.home_notification_icon:
                try {
                    Intent notification_intent = new Intent(HomeActivity.this, Notification.class);
                    startActivity(notification_intent);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                return true;
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
            Intent homeIntent = new Intent(HomeActivity.this, HomeFragment.class);
            startActivity(homeIntent);
        }
        else if(id == R.id.nav_my_orders){

        }
        else if(id == R.id.nav_my_rewards){

        }
        else if(id == R.id.nav_my_cart){
            Intent intent = new Intent(HomeActivity.this, MyCartActivity.class);
            startActivity(intent);
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

    private void getListCategory() {
        categoryAPI = ApiUtils.getCategoryAPI();
        categoryAPI.findCategory(user.getToken()).enqueue(new Callback<CategoryResult>() {
            @Override
            public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                if (response.isSuccessful()) {
                    categoryModelList = response.body().getCategoryModelList();
                } else {
                    Toast.makeText(HomeActivity.this, "Loi ???", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CategoryResult> call, Throwable t) {

            }
        });
    }
}
