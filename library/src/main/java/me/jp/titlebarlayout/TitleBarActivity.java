package me.jp.titlebarlayout;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import me.jp.titlebarlayout.view.TitleBarLayout;

/**
 * TitleBar Base Activity
 * Created by jiangp on 15/9/9.
 */
public class TitleBarActivity extends AppCompatActivity {

    private LinearLayout mContainerView;
    private TitleBarLayout mTitleBarLayout;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(getContainer());

        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        mContainerView.addView(view, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

    }

    private View getContainer() {

        mContainerView = new LinearLayout(this);
        mContainerView.setOrientation(LinearLayout.VERTICAL);
        mTitleBarLayout = new TitleBarLayout(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContainerView.addView(mTitleBarLayout, params);

        return mContainerView;
    }

    public void removeTitleBarLayout() {
        if (null != mTitleBarLayout) {
            mContainerView.removeView(mTitleBarLayout);
        }
    }

    public TitleBarLayout getTitleBarLayout() {
        return mTitleBarLayout;
    }

    public void onTitleBarLeftClick(){

    }

}
