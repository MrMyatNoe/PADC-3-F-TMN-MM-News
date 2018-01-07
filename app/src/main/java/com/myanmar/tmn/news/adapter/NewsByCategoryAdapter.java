package com.myanmar.tmn.news.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by msi on 1/7/2018.
 */

public class NewsByCategoryAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;
    private List<String> tabTitleList;

    public NewsByCategoryAdapter(FragmentManager fm) {
        super(fm);
        fragmentList = new ArrayList<>();
        tabTitleList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addTab(String tabLabel, Fragment fragment){
        tabTitleList.add(tabLabel);
        fragmentList.add(fragment);
        //check
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }
}
