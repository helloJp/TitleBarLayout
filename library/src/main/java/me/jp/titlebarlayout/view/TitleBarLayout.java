package me.jp.titlebarlayout.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.jp.titlebarlayout.R;

/**
 * TitleBar layout
 * Created by jiangp on 15/9/9.
 */
public class TitleBarLayout extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "title bar";
    private TextView mTvTitle;
    private TextView mTvLeft;
    private TextView mTvRight;

    //title attribute
    private int mTextSizeTitle = 24;//sp
    private int mTextColorTitle = Color.BLACK;
    private String mTextStrTitle = "title";

    //left attribute
    private int mTextSizeLeft = 15;//sp
    private ColorStateList mTextColorLeft = getResources().getColorStateList(R.color.selector_title_bar_text);
    private String mTextStrLeft;
    private Drawable mDrawableLeft = getResources().getDrawable(R.drawable.selector_btn_left);

    //right attribute
    private int mTextSizeRight = 15;//sp
    private ColorStateList mTextColorRight = getResources().getColorStateList(R.color.selector_title_bar_text);
    private String mTextStrRight;
    private Drawable mDrawableRight = getResources().getDrawable(R.drawable.selector_btn_right);

    private Paint mBaseLinePaint;
    private float mBaseLineHeight = 2f;
    private boolean mIsDrawBaseLine = false;


    private ActionType mCurrActionType = ActionType.LEFT_IMG_RIGHT_IMG;


    public TitleBarLayout(Context context) {
        this(context, null);
    }

    public TitleBarLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public TitleBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));

        mTextStrLeft = "left";
        mTextStrRight = "right";

        setActionType(mCurrActionType);
    }

    private void addTitle() {
        //title text
        mTvTitle = new TextView(getContext());
        mTvTitle.setGravity(Gravity.CENTER);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeTitle);
        mTvTitle.setTextColor(mTextColorTitle);
        mTvTitle.setText(mTextStrTitle);
        RelativeLayout.LayoutParams paramTitle = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramTitle.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mTvTitle, paramTitle);
    }

    private void addLeftTextView() {
        //left init
        mTvLeft = new TextView(getContext());
        mTvLeft.setId(R.id.title_bar_left);
        mTvLeft.setClickable(true);
        mTvLeft.setGravity(Gravity.CENTER);
        mTvLeft.setPadding(PADDING, PADDING, PADDING, PADDING);
        mTvLeft.setOnClickListener(this);

        //drawable
        initDrawable(mTvLeft, mDrawableLeft);

        //text
        initLeftText();

        //add to viewGroup
        RelativeLayout.LayoutParams paramLeft = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramLeft.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mTvLeft, paramLeft);
    }

    int PADDING = dip2px(10);

    private void addRightTextView() {
        //Right init

        mTvRight = new TextView(getContext());
        mTvRight.setId(R.id.title_bar_right);
        mTvRight.setClickable(true);
        mTvRight.setGravity(Gravity.CENTER);
        mTvRight.setPadding(PADDING, PADDING, PADDING, PADDING);
        mTvRight.setOnClickListener(this);

        //drawable
        initDrawable(mTvRight, mDrawableRight);

        //text
        initRightText();

        //add to viewGroup
        RelativeLayout.LayoutParams paramRight = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramRight.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mTvRight, paramRight);
    }

    private void initDrawable(TextView textView, Drawable drawable) {
        if (null == textView) {
            return;
        }
        if (null != drawable) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, null, drawable, null);
        } else {
            textView.setCompoundDrawables(null, null, null, null);
        }
    }

    private void initLeftText() {
        if (null == mTvLeft || null == mTextStrLeft) {
            return;
        }
        mTvLeft.setText(mTextStrLeft);
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeLeft > 0 ? mTextSizeLeft : sp2px(15));
        if (null == mTextColorLeft) {
            mTvLeft.setTextColor(Color.BLACK);
        } else {
            mTvLeft.setTextColor(mTextColorLeft);
        }
    }

    private void initRightText() {
        if (null == mTvRight || null == mTextStrRight) {
            return;
        }
        mTvRight.setText(mTextStrRight);
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeRight > 0 ? mTextSizeRight : sp2px(15));
        if (null == mTextColorRight) {
            mTvRight.setTextColor(Color.BLACK);
        } else {
            mTvRight.setTextColor(mTextColorRight);
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //draw baseLine
        if (mIsDrawBaseLine) {
            if (null == mBaseLinePaint) {
                mBaseLinePaint = new Paint();
                mBaseLinePaint.setColor(Color.GRAY);
            }
            canvas.drawRect(0, getMeasuredHeight() - mBaseLineHeight, getMeasuredWidth(), getMeasuredHeight(), mBaseLinePaint);
            return;
        }
    }

    public void setActionType(ActionType actionType) {
        removeAllViews();
        addTitle();
        mCurrActionType = actionType;
        switch (mCurrActionType) {
            case LEFT_IMG:
                addLeftIMG();
                break;
            case LEFT_IMG_RIGHT_IMG:
                addLeftIMG();
                addRightIMG();
                break;
            case LEFT_IMG_RIGHT_TXT:
                addLeftIMG();
                addRightTXT();
                break;

            case LEFT_TXT:
                addLeftTXT();
                break;
            case LEFT_TXT_RIGHT_IMG:
                addLeftTXT();
                addRightIMG();
                break;
            case LEFT_TXT_RIGHT_TXT:
                addLeftTXT();
                addRightTXT();
                break;

            case NO_BOTH_SIDE:
                break;
            case RIGHT_IMG:
                addRightIMG();
                break;
            case RIGHT_TXT:
                addRightTXT();
                break;
        }
    }

    private void addLeftIMG() {
        //if left text not empty,reset text
        mTextStrLeft = TextUtils.isEmpty(mTextStrLeft) ? mTextStrLeft : "";
        addLeftTextView();
    }

    private void addLeftTXT() {
        mDrawableLeft = null;
        addLeftTextView();
    }

    private void addRightIMG() {
        //if right text not empty,reset text
        mTextStrRight = TextUtils.isEmpty(mTextStrRight) ? mTextStrRight : "";
        addRightTextView();
    }

    private void addRightTXT() {
        mDrawableRight = null;
        addRightTextView();
    }

    public enum ActionType {
        LEFT_IMG,
        LEFT_IMG_RIGHT_IMG,
        LEFT_IMG_RIGHT_TXT,

        LEFT_TXT,
        LEFT_TXT_RIGHT_IMG,
        LEFT_TXT_RIGHT_TXT,

        NO_BOTH_SIDE,
        RIGHT_IMG,
        RIGHT_TXT,
    }


    public ActionType getCurrActionType() {
        return mCurrActionType;
    }

    /**
     * return current left type
     *
     * @return -1:empty, 0: text ,1: image
     */
    public int getCurrLeftType() {
        switch (mCurrActionType) {
            case LEFT_IMG:
            case LEFT_IMG_RIGHT_IMG:
            case LEFT_IMG_RIGHT_TXT:
                return 1;

            case LEFT_TXT:
            case LEFT_TXT_RIGHT_IMG:
            case LEFT_TXT_RIGHT_TXT:
                return 0;

            case NO_BOTH_SIDE:
            case RIGHT_IMG:
            case RIGHT_TXT:
                return -1;
        }
        return 0;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.title_bar_left) {
            onTitleLeftClick();
        } else if (id == R.id.title_bar_right) {
            onTitleRightClick();
        }
    }

    public void setBaseLineColor(int baseLineColor) {
        mIsDrawBaseLine = mBaseLineHeight > 0 ? true : false;
        if (null == mBaseLinePaint) {
            mBaseLinePaint = new Paint();
        }
        mBaseLinePaint.setColor(baseLineColor);
    }

    public void setBaseLineHeight(float height) {
        mBaseLineHeight = height;
        mIsDrawBaseLine = mBaseLineHeight > 0 ? true : false;
        
        mBaseLinePaint.setStrokeWidth(height);
        postInvalidate();
    }


    private void onTitleLeftClick() {
        if (null != mOnLeftClickListener) {
            mOnLeftClickListener.onTitleBarLeftClick();
        }
        if (null != mOnBothSideClickListener) {
            mOnBothSideClickListener.onTitleBarLeftClick();
        }
    }

    private void onTitleRightClick() {
        if (null != mOnRightClickListener) {
            mOnRightClickListener.onTitleBarRightClick();
        }
        if (null != mOnBothSideClickListener) {
            mOnBothSideClickListener.onTitleBarRightClick();
        }
    }


    private OnLeftClickListener mOnLeftClickListener;
    private OnRightClickListener mOnRightClickListener;
    private OnBothSideClickListener mOnBothSideClickListener;

    public interface OnLeftClickListener {
        void onTitleBarLeftClick();
    }

    public interface OnRightClickListener {
        void onTitleBarRightClick();
    }

    public interface OnBothSideClickListener {
        void onTitleBarLeftClick();

        void onTitleBarRightClick();

    }

    public void setOnLeftClickListener(OnLeftClickListener listener) {
        mOnLeftClickListener = listener;
    }

    public void setOnRightClickListener(OnRightClickListener listener) {
        mOnRightClickListener = listener;
    }

    public void setOnBothSideClickListener(OnBothSideClickListener listener) {
        mOnBothSideClickListener = listener;
    }

    private int dip2px(int dipValue) {
        int pxValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue,
                getResources().getDisplayMetrics());
        return pxValue;
    }

    private int sp2px(int spValue) {
        int pxValue = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                getResources().getDisplayMetrics());
        return pxValue;
    }

}
