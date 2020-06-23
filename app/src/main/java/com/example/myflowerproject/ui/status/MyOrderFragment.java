package com.example.myflowerproject.ui.status;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myflowerproject.R;
import com.google.android.material.tabs.TabLayout;

public class MyOrderFragment extends Fragment {

    public MyOrderFragment() {

    }

    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_my_order, container, false);

        viewPager = view.findViewById(R.id.viewPager_order);
        addTabs(viewPager);
        //chuyen sang tabLayOut
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        return view;

    }
    public void addTabs(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getParentFragmentManager());
        adapter.add(new WaitFragment(),"Wait for confirmation");
        adapter.add(new DeliveryFragment(),"Delivery");
        adapter.add(new ReceivedFragment(),"Received");
        viewPager.setAdapter(adapter);
    }


}
