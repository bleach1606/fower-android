package com.example.myflowerproject.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myflowerproject.fragment.HomeFragment;
import com.example.myflowerproject.fragment.ListItemFragment2;

public class CategoryAdapter2 extends FragmentPagerAdapter {

    private int totalTabs;

    public CategoryAdapter2(@NonNull FragmentManager fm) {
        super(fm);
    }

    public CategoryAdapter2(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        switch (behavior){
            case 0:
                HomeFragment homeFragment = new HomeFragment();
            default:
                ListItemFragment2 listItemFragment2 = new ListItemFragment2(behavior);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                return homeFragment;
            default:
                ListItemFragment2 listItemFragment2 = new ListItemFragment2(i);
                return listItemFragment2;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
