package me.jp.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import me.jp.demo.activity.CommonActivity;
import me.jp.demo.activity.FrgmtActivity;
import me.jp.titlebarlayout.TitleBarActivity;
import me.jp.titlebarlayout.view.TitleBarLayout;

public class MainActivity extends TitleBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTitleBar();
    }

    private void initTitleBar() {
        setTitleBarActionType(TitleBarLayout.ActionType.LEFT_IMG_RIGHT_IMG);
        setTitleBarTxt("MainActivity");
        setOnTitleBarClickListener(this);
        getTitleBarLayout().setTitleTxtColor(Color.WHITE);
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
                gotoActivity(FrgmtActivity.class);
                break;
        }
    }

    private void gotoActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }
}
