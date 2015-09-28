package me.jp.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import me.jp.titlebarlayout.TitleBarActivity;
import me.jp.titlebarlayout.view.TitleBarLayout;

public class MainActivity extends TitleBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TitleBarLayout titlebarLayout = getTitleBarLayout();
        titlebarLayout.setBaseLineColor(Color.RED);
    }


}
