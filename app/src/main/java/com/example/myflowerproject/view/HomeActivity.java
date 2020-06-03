package com.example.myflowerproject.view;

import android.content.ClipData;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager.widget.ViewPager;
<<<<<<<<< Temporary merge branch 1

import com.example.myflowerproject.Container;
import com.example.myflowerproject.R;
import com.example.myflowerproject.fragment.HomeFragment;
import com.example.myflowerproject.model.adapter.CategoryAdapter;
import com.example.myflowerproject.model.api.ApiUtils;
import com.example.myflowerproject.model.api.CategoryAPI;
import com.example.myflowerproject.model.api.GetImage;
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

public class HomeActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

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

    private ImageButton hamburger_icon_imagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Fragment homeFragment = new HomeFragment();
        Fragment navigationFragment = new NavigationFragment();

        hamburger_icon_imagebutton = findViewById(R.id.hamburger_imagebutton);

        FragmentManager manager = this.getSupportFragmentManager();

        hamburger_icon_imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(navigationFragment);
                manager.beginTransaction()
                        .show(navigationFragment)
                        .hide(homeFragment)
                        .commit();
            }
        });

        categoryViewPager = findViewById(R.id.category_viewpager);
        categoryTabLayout = findViewById(R.id.category_tab_layout);

        final CategoryAdapter categoryAdapter = new CategoryAdapter(getSupportFragmentManager(), categoryTabLayout.getTabCount());
        categoryViewPager.setAdapter(categoryAdapter);

        categoryViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(categoryTabLayout));
        categoryTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                categoryViewPager.setCurrentItem(tab.getPosition());
                setFragment(new HomeFragment(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        frameLayout = findViewById(R.id.home_framelayout);
        setFragment(homeFragment);

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
        txtNameUser.setText(Container.users.getPeople().getFirstName() + Container.users.getPeople().getLastName());
        txtEmailUser.setText(Container.users.getUsername());
        new GetImage(findViewById(R.id.imageView))
                .execute(Container.users.getPeople().getAvatar());
        return true;
    }


        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText);
                return false;
            }
        });
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

    private void setFragment(Fragment fragment){
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void getListCategory() {
        categoryAPI = ApiUtils.getCategoryAPI();
        try{
            categoryAPI.findCategory(Container.users.getToken()).enqueue(new Callback<CategoryResult>() {
                @Override
                public void onResponse(Call<CategoryResult> call, Response<CategoryResult> response) {
                    if (response.isSuccessful()) {
                        CategoryResult rs = response.body();
                        Container.listCategory = rs.getCategoryModelList();
                        frameLayout = findViewById(R.id.home_framelayout);
                        setFragment(new HomeFragment(-1));
                    } else {
                        Toast.makeText(HomeActivity.this, "Loi ???", Toast.LENGTH_SHORT).show();
                        System.out.println("loi ");
                    }

                }
                @Override
                public void onFailure(Call<CategoryResult> call, Throwable t) {
                    System.out.println("failure "+ t.toString());
                }
            });
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
