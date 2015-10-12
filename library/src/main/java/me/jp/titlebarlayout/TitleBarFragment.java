package me.jp.titlebarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import me.jp.titlebarlayout.view.TitleBarLayout;

/**
 * Created by ${JiangPing} on 2015/9/29.
 */
public class TitleBarFragment extends Fragment {
    private LinearLayout mContainerView;
    private TitleBarLayout mTitleBarLayout;

    @Override
    public void onViewCreated(View contentView, @Nullable Bundle savedInstanceState) {
        ViewGroup parentView = (ViewGroup) contentView.getParent();
        if (null != parentView) {
            parentView.removeView(contentView);
            parentView.addView(getContainer(contentView));
        }
        super.onViewCreated(contentView, savedInstanceState);
    }

    private View getContainer(View contentView) {
        mContainerView = new LinearLayout(getActivity());
        mContainerView.setOrientation(LinearLayout.VERTICAL);
        mTitleBarLayout = new TitleBarLayout(getActivity());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mContainerView.addView(mTitleBarLayout, params);
        mContainerView.addView(contentView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        return mContainerView;
    }

}