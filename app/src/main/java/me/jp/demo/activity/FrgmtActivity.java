package me.jp.demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import me.jp.demo.R;
import me.jp.demo.fragment.MyTitleBarFragment;

/**
 * Created by ${JiangPing} on 2015/9/29.
 */
public class FrgmtActivity extends AppCompatActivity {
    private RadioGroup mRgTabs;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frgmt);

        initView();
        initEvent();
    }

    private void initEvent() {
        mRgTabs.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < mRgTabs.getChildCount(); i++) {
                    if (((RadioButton) mRgTabs.getChildAt(i)).isChecked()) {
                        mViewPager.setCurrentItem(i, false);
                    }
                }
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) mRgTabs.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        mRgTabs = (RadioGroup) findViewById(R.id.rg_tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));


    }


    private class FragmentsAdapter extends FragmentPagerAdapter {
        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return MyTitleBarFragment.newInstance("Fragment1");
                case 1:
                    return MyTitleBarFragment.newInstance("Fragment2");
                case 2:
                    return MyTitleBarFragment.newInstance("Fragment3");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
