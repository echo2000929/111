package com.example.zyfypt_229.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import com.example.zyfypt_229.R;
import com.example.zyfypt_229.bean.CollectBean;
import com.example.zyfypt_229.fragments.CBaseFragment;
import com.example.zyfypt_229.fragments.CFragment1;
import com.example.zyfypt_229.fragments.CFragment2;
import com.example.zyfypt_229.fragments.CFragment3;
import com.example.zyfypt_229.fragments.CFragment4;
import com.example.zyfypt_229.fragments.CFragment5;
import com.example.zyfypt_229.fragments.PagerSlidingTabStrip;
import com.example.zyfypt_229.fragments.fragment41;
import com.example.zyfypt_229.fragments.fragment42;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private DisplayMetrics dm;
    private PagerSlidingTabStrip pagersliding;

    TabLayout mytab;
    ViewPager viewPager4;
    private MyPagerAdapter myPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mytab = findViewById(R.id.mytab);
        viewPager4= findViewById(R.id.viewPager4);
        mytab.setupWithViewPager(viewPager4);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());

        viewPager4.setAdapter(myPagerAdapter);


    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private String[] titles = {"博文", "课件","视频","项目","案例"};//显示在二级导航上的标题文字
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private CFragment1 cFragment1;
        private CFragment2 cFragment2;
        private CFragment3 cFragment3;
        private CFragment4 cFragment4;
        private CFragment5 cFragment5;

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];//确定当页导航上文字
        }
        @Override
        public int getCount() {
            return 5;//二级导航个数
        }
        @Override
        public Fragment getItem(int position) { //根据位置返回具体某个导航上对应的Fragment
            switch (position) {
                case 0:
                    if (cFragment1 == null) {
                        cFragment1 = new CFragment1();
                    }
                    return cFragment1;
                case 1:
                    if (cFragment2 == null) {
                        cFragment2 = new CFragment2();
                    }
                    return cFragment2;
                case 2:
                    if (cFragment3 == null) {
                        cFragment3 = new CFragment3();
                    }
                    return cFragment3;
                case 3:
                    if (cFragment4 == null) {
                        cFragment4 = new CFragment4();
                    }
                    return cFragment4;
                case 4:
                    if (cFragment5 == null) {
                        cFragment5 = new CFragment5();
                    }
                    return cFragment5;
                default:
                    return null;
            }
        }
    }
}