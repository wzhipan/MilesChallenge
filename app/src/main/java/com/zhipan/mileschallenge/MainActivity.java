package com.zhipan.mileschallenge;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private TextView mPageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        viewPager = findViewById(R.id.view_pager);
        mPageIndicator = findViewById(R.id.page_indicator);

        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
                getResources().getDisplayMetrics());
        viewPager.setOffscreenPageLimit(1);

        PagesViewModel pagesViewModel = ViewModelProviders.of(this).get(PagesViewModel.class);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        myPagerAdapter.setPageIdList(pagesViewModel.getPageIdList());
        viewPager.setAdapter(myPagerAdapter);
        updatePageIndicator();

        viewPager.setClipToPadding(false);
        viewPager.setPageMargin(margin);

        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        Log.i(TAG, "onPageSelected: page="+i);

        updatePageIndicator();

        if (i > 0) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:"
                    + R.id.view_pager + ":" + (i-1));
            if (fragment != null)
                fragment.setUserVisibleHint(false);
        }

        if (i < myPagerAdapter.getCount()-1) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag("android:switcher:"
                    + R.id.view_pager + ":" + (i+1));
            if (fragment != null)
                fragment.setUserVisibleHint(false);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void updatePageIndicator() {
        if (myPagerAdapter == null)
            mPageIndicator.setVisibility(View.GONE);
        else
            mPageIndicator.setVisibility(View.VISIBLE);

        mPageIndicator.setText(getString(R.string.current_page,
                viewPager.getCurrentItem()+1, myPagerAdapter.getCount()));
    }
}
