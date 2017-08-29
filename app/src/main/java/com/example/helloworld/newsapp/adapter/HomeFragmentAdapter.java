package com.example.helloworld.newsapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.helloworld.newsapp.fragment.SocialNewsFragment;
import com.example.helloworld.newsapp.fragment.SportNewsFragment;
import com.example.helloworld.newsapp.fragment.WeixinNewsFragment;

/**
 * Created by helloworld on 2017/8/23.
 */

public class HomeFragmentAdapter extends FragmentPagerAdapter {

    private Fragment[] fragments = {
            new WeixinNewsFragment(),
            new SportNewsFragment(),
            new SocialNewsFragment()
    };
    private String[] titles = {"互联网", "体育", "科技"};

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragments[0];
            case 1:
                return fragments[1];
            default:
                return fragments[2];
        }
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
