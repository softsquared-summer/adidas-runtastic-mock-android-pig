package com.softsquared.runtastic.src.main.fragment.Act.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.softsquared.runtastic.src.main.fragment.Act.viewpage.SlidePage;
import com.softsquared.runtastic.src.main.fragment.Act.viewpage.StopPage;

public class StopPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;

    public StopPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StopPage.newInstance(0,"Page # 1");
            case 1:
                return SlidePage.newInstance(1, "Page # 2");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }
}
