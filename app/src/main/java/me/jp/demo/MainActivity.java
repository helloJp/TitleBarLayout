package me.jp.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import me.jp.demo.activity.CommonActivity;
import me.jp.demo.activity.FragmentsActivity;
import me.jp.titlebarlayout.view.TitleBarLayout;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private TitleBarLayout mTitleBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTitleBarLayout = (TitleBarLayout) findViewById(R.id.titleBarLayout);
        mTitleBarLayout.setTitleTxtColor(Color.WHITE);
        mTitleBarLayout.setTitleTxtString("MainActivity");
        mTitleBarLayout.setOnBothSideClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_left:
                Toast.makeText(this, "left click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.title_bar_right:
                Toast.makeText(this, "right click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_common_activity:
                gotoActivity(CommonActivity.class);
                break;
            case R.id.btn_fragment_activity:
                gotoActivity(FragmentsActivity.class);
                break;
        }
    }

    private void gotoActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }
}
