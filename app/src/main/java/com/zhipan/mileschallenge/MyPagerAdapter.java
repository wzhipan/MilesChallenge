package com.zhipan.mileschallenge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = MyPagerAdapter.class.getSimpleName();
    private List<Integer> pageIdList;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setPageIdList(List<Integer> list) {
        pageIdList = list;
    }

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putInt(CityFragment.PAGE_ID, pageIdList.get(i));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {

        if (pageIdList == null)
            return 0;

        return pageIdList.size();
    }

//    @Override
//    public float getPageWidth (int position)
//    {
//        return 0.93f;
//    }
}
