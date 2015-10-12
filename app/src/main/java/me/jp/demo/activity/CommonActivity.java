package me.jp.demo.activity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import me.jp.demo.R;
import me.jp.titlebarlayout.TitleBarActivity;
import me.jp.titlebarlayout.view.TitleBarLayout;

/**
 * Created by ${JiangPing} on 2015/9/29.
 */
public class CommonActivity extends TitleBarActivity implements View.OnClickListener {
    private boolean mIsTitleBarExist = true;
    private TitleBarLayout mTitleBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        initTitleBar();

    }

    private void initTitleBar() {
        setTitleBarActionType(TitleBarLayout.ActionType.LEFT_IMG_RIGHT_IMG);
        setTitleBarTxt("CommonActivity");
        setOnTitleBarClickListener(this);
        getTitleBarLayout().setTitleTxtColor(Color.WHITE);

        mTitleBarLayout = getTitleBarLayout();
        mTitleBarLayout.setLeftTxtColorStateList(R.color.selector_txt_color);
        mTitleBarLayout.setRightTxtColorStateList(R.color.selector_txt_color);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_bar_left:
                finish();
                break;
            case R.id.title_bar_right:
                Toast.makeText(this, "right click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_titleBar_toggle:
                if (mIsTitleBarExist) {
                    removeTitleBarLayout();
                } else {
                    addTitleBarLayout();
                }
                mIsTitleBarExist = !mIsTitleBarExist;
                break;
            case R.id.btn_change_color:
                changeColor();
                break;
            case R.id.btn_change_type:
                changeActionType();
                break;
            case R.id.btn_title_txt:
                changeTitleTxt();
                break;
            case R.id.btn_show_baseLine:
                showBaseLine();
                break;
        }
    }

    private void showBaseLine() {
        mTitleBarLayout.setBaseLineHeight(2);
        mTitleBarLayout.setBaseLineColor(Color.GRAY);
    }

    private void changeTitleTxt() {
        final EditText etInput = new EditText(this);
        etInput.setHint("titleBar text");
        etInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        etInput.setPadding(20, 0, 20, 0);
        etInput.setSingleLine(true);
        new AlertDialog.Builder(this)
                .setTitle("input new title text")
                .setView(etInput)
                .setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTitleBarLayout.setTitleTxtString(etInput.getText().toString());
                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }

    private void changeActionType() {
        Random random = new Random();
        TitleBarLayout.ActionType[] types = TitleBarLayout.ActionType.values();

        mTitleBarLayout.setActionType(types[random.nextInt(9)]);
        debug("current ActionType : " + mTitleBarLayout.getCurrActionType().name());
    }


    private void changeColor() {
        long randomValue = (long) Math.floor(Math.random() * 0xFFFFFF);
        String colorStr = Long.toHexString(randomValue).toLowerCase().trim();
        colorStr = "#" + "000000".substring(0, 6 - colorStr.length()) + colorStr;
        mTitleBarLayout.setBackgroundColor(Color.parseColor(colorStr));
    }

    private void debug(String msg) {
        if (null != msg)
            Log.i("debug", msg);
    }
}
