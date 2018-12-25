package com.example.lenovo.cart.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.example.lenovo.cart.R;
import com.example.lenovo.cart.fragment.HomePage;
import com.example.lenovo.cart.fragment.Me;
import com.example.lenovo.cart.fragment.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mMainActivityViewPager;
    private RadioGroup mMainActivityRadioGroup;
    private List<Fragment> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
    }
    private void initView() {
        mMainActivityViewPager = (ViewPager) findViewById(R.id.MainActivity_ViewPager);
        mMainActivityRadioGroup = (RadioGroup) findViewById(R.id.MainActivity_RadioGroup);
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add(new HomePage());
        mList.add(new ShoppingCart());
        mList.add(new Me());
    }

    private void initAdapter() {
        mMainActivityViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mList.get(i);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
        mMainActivityViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mMainActivityRadioGroup.check(mMainActivityRadioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mMainActivityRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                mMainActivityViewPager.setCurrentItem(i-1);
            }
        });
    }
}
