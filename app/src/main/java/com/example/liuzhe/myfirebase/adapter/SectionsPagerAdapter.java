package com.example.liuzhe.myfirebase.adapter;

/**
 * Created by liuzhe on 2016/5/11.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.liuzhe.myfirebase.MyApp;
import com.example.liuzhe.myfirebase.fragment.PlaceholderFragment;
import com.example.liuzhe.myfirebase.fragment.ToolsFragment;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        if (position < 3) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        } else {
            return ToolsFragment.newTool();
        }
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "我的";
            case 1:
                return "朋友";
            case 2:
                return "新鲜";
            case 3:
                return "工具";
        }
        return null;
    }
}
