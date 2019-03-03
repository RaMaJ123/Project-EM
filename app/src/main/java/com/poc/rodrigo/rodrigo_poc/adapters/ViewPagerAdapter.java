package com.poc.rodrigo.rodrigo_poc.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.poc.rodrigo.rodrigo_poc.fragments.MapsFragment;
import com.poc.rodrigo.rodrigo_poc.fragments.SensorDataFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramjiseetharaman on 3/3/19.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentPageTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentPageTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentPageTitle.get(position);
    }
}
