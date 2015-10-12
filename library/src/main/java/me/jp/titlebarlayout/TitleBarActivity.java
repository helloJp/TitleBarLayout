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

        View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        mContainerView.addView(contentView, new LinearLayout.LayoutParams(
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

    public void addTitleBarLayout() {
        if (null != mTitleBarLayout) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mContainerView.addView(mTitleBarLayout, 0, params);
        }
    }

    public TitleBarLayout getTitleBarLayout() {
        return mTitleBarLayout;
    }

    public void setTitleBarActionType(TitleBarLayout.ActionType actionType) {
        mTitleBarLayout.setActionType(actionType);
    }

    public void setTitleBarTxt(String titleTxt) {
        mTitleBarLayout.setTitleTxtString(titleTxt);
    }

    public void setTitleBarLeftTxt(String txtString) {
        mTitleBarLayout.setLeftTextString(txtString);
    }

    public void setOnTitleBarClickListener(View.OnClickListener listener) {
        mTitleBarLayout.setOnBothSideClickListener(listener);
    }

    public void setOnTitleBarLeftClickListener(View.OnClickListener listener) {
        mTitleBarLayout.setOnLeftClickListener(listener);
    }

    public void setOnTitleBarRightClickListener(View.OnClickListener listener) {
        mTitleBarLayout.setOnRightClickListener(listener);
    }


}
