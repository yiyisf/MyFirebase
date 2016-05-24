package com.example.liuzhe.myfirebase.adapter;

/**
 * Created by liuzhe on 2016/5/11.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.liuzhe.myfirebase.MyApp;
import com.example.liuzhe.myfirebase.fragment.MusicFragment;
import com.example.liuzhe.myfirebase.fragment.PlaceholderFragment;
import com.example.liuzhe.myfirebase.fragment.ToolsFragment;
import com.example.liuzhe.myfirebase.fragment.UsersFragment;

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

//        switch (position){
//            case 3:
//                return ToolsFragment.newTool();
//            case 1:
//                return UsersFragment.newUser();
//            case 0:
//                return PlaceholderFragment.newInstance(position + 1);
//            case 2:
//                return PlaceholderFragment.newInstance(position + 1);
//        }
//        return null;

        if (position == 0) {
            return UsersFragment.newUser(MyApp.getFirebase());
        } else if (position == 3) {
            if (MyApp.getGoogleSignInAccount() != null) {
                return ToolsFragment.newTool(MyApp.getGoogleSignInAccount());
            } else {
                return PlaceholderFragment.newInstance(position + 1);
            }
        } else if (position == 1) {
            return MusicFragment.newInstance(1);

        } else {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
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
                return "朋友";
            case 1:
                return "我的";
            case 2:
                return "新鲜";
            case 3:
                return "工具";
        }
        return null;
    }
}
