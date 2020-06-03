package com.example.myflowerproject.model.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myflowerproject.fragment.HomeFragment;
import com.example.myflowerproject.fragment.ListItemFragment;

public class CategoryAdapter extends FragmentPagerAdapter {

    private int totalTabs;

    public CategoryAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public CategoryAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        switch (behavior){
            case 0:
                HomeFragment homeFragment = new HomeFragment(-1);
            default:
                ListItemFragment listItemFragment = new ListItemFragment(behavior);
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                HomeFragment homeFragment = new HomeFragment(-1);
                return homeFragment;
            default:
                ListItemFragment listItemFragment = new ListItemFragment(i);
                return listItemFragment;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}