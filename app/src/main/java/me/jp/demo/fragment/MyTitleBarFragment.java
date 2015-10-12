package me.jp.demo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.jp.demo.R;
import me.jp.titlebarlayout.TitleBarFragment;
import me.jp.titlebarlayout.view.TitleBarLayout;

/**
 * Created by ${JiangPing} on 2015/9/29.
 */
public class MyTitleBarFragment extends TitleBarFragment {
    private static final String KEY_TITLE_BAR = "titleBar_key";
    private static TitleBarLayout.ActionType mActionType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String titleBar = getArguments().getString(KEY_TITLE_BAR);
        TextView textView = new TextView(getActivity());
//        textView.setText(titleBar != null ? titleBar : this.getClass().getName());
        textView.setText("test");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setTextColor(Color.GRAY);
        return textView;
    }

    @Override
    public void onViewCreated(View contentView, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(contentView, savedInstanceState);
    }

    public static Fragment newInstance(String titleBar) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_BAR, titleBar);
        Fragment fragment = new MyTitleBarFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static Fragment newInstance(String titleBar,TitleBarLayout.ActionType actionType) {
        mActionType = actionType;

        Bundle bundle = new Bundle();
        bundle.putString(KEY_TITLE_BAR, titleBar);
        Fragment fragment = new MyTitleBarFragment();
        fragment.setArguments(bundle);
        return fragment;

    }



}
