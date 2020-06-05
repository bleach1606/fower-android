package com.example.myflowerproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myflowerproject.fragment.DeliveryFragment;
import com.example.myflowerproject.fragment.ReceivedFragment;
import com.example.myflowerproject.fragment.WaitFragment;
import com.example.myflowerproject.model.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MyOrder extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        Toolbar toolbar = findViewById(R.id.toolbarOrder);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        viewPager = findViewById(R.id.viewPager_order);
        addTabs(viewPager);
        //chuyen sang tabLayOut
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }
    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.add(new WaitFragment(),"Wait for confirmation");
        adapter.add(new DeliveryFragment(),"Delivery");
        adapter.add(new ReceivedFragment(),"Received");
        viewPager.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:break;
        }

        return super.onOptionsItemSelected(item);
    }


}
