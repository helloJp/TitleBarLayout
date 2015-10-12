package me.jp.titlebarlayout.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
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
    //all views
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

    //base line
    private Paint mBaseLinePaint;
    private float mBaseLineHeight = 2f;
    private boolean mIsDrawBaseLine = false;


    private ActionType mCurrActionType = ActionType.LEFT_IMG_RIGHT_IMG;

    private final int DEFAULT_PADDING = dip2px(10);

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
        setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        setGravity(Gravity.CENTER_VERTICAL);

        addLeftTextView();
        addRightTextView();
        addTitle();
        mTextStrLeft = "LEFT";
        mTextStrRight = "RIGHT";
    }

    private void addTitle() {
        //title text
        mTvTitle = new TextView(getContext());
        mTvTitle.setSingleLine(true);
        mTvTitle.setGravity(Gravity.CENTER);
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeTitle);
        mTvTitle.setTextColor(mTextColorTitle);
        mTvTitle.setText(mTextStrTitle);
        RelativeLayout.LayoutParams paramTitle = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramTitle.addRule(RelativeLayout.CENTER_IN_PARENT);
        paramTitle.addRule(RelativeLayout.RIGHT_OF, mTvLeft.getId());
        paramTitle.addRule(RelativeLayout.LEFT_OF, mTvRight.getId());
        addView(mTvTitle, paramTitle);
    }

    private void addLeftTextView() {
        //left init
        mTvLeft = new TextView(getContext());
        mTvLeft.setId(R.id.title_bar_left);
        mTvLeft.setClickable(true);
        mTvLeft.setGravity(Gravity.CENTER);
        mTvLeft.setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING);
        mTvLeft.setOnClickListener(this);

        //drawable
        setTextViewDrawable(mTvLeft, mDrawableLeft);

        //text
        initLeftText();

        //add to viewGroup
        RelativeLayout.LayoutParams paramLeft = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramLeft.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mTvLeft, paramLeft);
    }


    private void addRightTextView() {
        //Right init
        mTvRight = new TextView(getContext());
        mTvRight.setId(R.id.title_bar_right);
        mTvRight.setClickable(true);
        mTvRight.setGravity(Gravity.CENTER);
        mTvRight.setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING);
        mTvRight.setOnClickListener(this);

        //drawable
        setTextViewDrawable(mTvRight, mDrawableRight);

        //text
        initRightText();

        //add to viewGroup
        RelativeLayout.LayoutParams paramRight = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        paramRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        paramRight.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mTvRight, paramRight);
    }

    private void setTextViewDrawable(TextView textView, Drawable drawable) {
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
        if (null == mTextStrLeft) {
            return;
        }
        mTvLeft.setText(mTextStrLeft);
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeLeft > 0 ? mTextSizeLeft : sp2px(15));
        if (null == mTextColorLeft) {
            mTvLeft.setTextColor(Color.WHITE);
        } else {
            mTvLeft.setTextColor(mTextColorLeft);
        }
    }

    private void initRightText() {
        if (null == mTextStrRight) {
            return;
        }
        mTvRight.setText(mTextStrRight);
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, mTextSizeRight > 0 ? mTextSizeRight : sp2px(15));
        if (null == mTextColorRight) {
            mTvRight.setTextColor(Color.WHITE);
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
            canvas.drawRect(0, getMeasuredHeight() - mBaseLineHeight, getMeasuredWidth(),
                    getMeasuredHeight(), mBaseLinePaint);
            return;
        }
    }

    public void setActionType(ActionType actionType) {
        mCurrActionType = actionType;
        switch (mCurrActionType) {
            case LEFT_IMG:
                setTextViewImgType(mTvLeft, mDrawableLeft);
                mTvRight.setVisibility(View.INVISIBLE);
                break;
            case LEFT_IMG_RIGHT_IMG:
                setTextViewImgType(mTvLeft, mDrawableLeft);
                setTextViewImgType(mTvRight, mDrawableRight);
                break;
            case LEFT_IMG_RIGHT_TXT:
                setTextViewImgType(mTvLeft, mDrawableLeft);
                setTextViewTxtType(mTvRight, mTextStrRight);
                break;

            case LEFT_TXT:
                setTextViewTxtType(mTvLeft, mTextStrLeft);
                mTvRight.setVisibility(View.INVISIBLE);
                break;
            case LEFT_TXT_RIGHT_IMG:
                setTextViewTxtType(mTvLeft, mTextStrLeft);
                setTextViewImgType(mTvRight, mDrawableRight);
                break;
            case LEFT_TXT_RIGHT_TXT:
                setTextViewTxtType(mTvLeft, mTextStrLeft);
                setTextViewTxtType(mTvRight, mTextStrRight);
                break;

            case RIGHT_IMG:
                mTvLeft.setVisibility(View.INVISIBLE);
                setTextViewImgType(mTvRight, mDrawableRight);
                break;
            case RIGHT_TXT:
                mTvLeft.setVisibility(View.INVISIBLE);
                mTvRight.setVisibility(View.VISIBLE);
                break;
            case ONLY_TITLE:
                mTvLeft.setVisibility(View.INVISIBLE);
                mTvRight.setVisibility(View.INVISIBLE);
                break;
        }
    }

    private void setTextViewTxtType(TextView textView, String txtString) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(txtString);
        textView.setCompoundDrawables(null, null, null, null);
    }

    private void setTextViewImgType(TextView textView, Drawable drawable) {
        textView.setVisibility(View.VISIBLE);
        textView.setText("");
        textView.setCompoundDrawables(null, null, drawable, null);
    }


    public enum ActionType {
        LEFT_IMG,
        LEFT_IMG_RIGHT_IMG,
        LEFT_IMG_RIGHT_TXT,

        LEFT_TXT,
        LEFT_TXT_RIGHT_IMG,
        LEFT_TXT_RIGHT_TXT,

        RIGHT_IMG,
        RIGHT_TXT,
        ONLY_TITLE
    }


    public ActionType getCurrActionType() {
        return mCurrActionType;
    }

    //-------------------attribute setting start-----------------------------
    //title
    public void setTitleTxtString(String txtString) {
        mTvTitle.setText(txtString);
    }

    public void setTitleTxtSize(int textSize) {
        mTvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setTitleTxtColor(int color) {
        mTvTitle.setTextColor(color);
    }

    //left
    public void setLeftTextString(String txtString) {
        mTvLeft.setText(txtString);
    }

    public void setLeftTextSize(int textSize) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }

    public void setLeftDrawable(int resId) {
        mDrawableLeft = getResources().getDrawable(resId);
        mTvLeft.setCompoundDrawables(mDrawableLeft, null, null, null);
    }

    public void setLeftPadding(int left, int top, int right, int bottom) {
        mTvLeft.setPadding(left, top, right, bottom);
    }

    public void setLeftSideTxtColor(int resId) {
        mTextColorLeft = ColorStateList.valueOf(getResources().getColor(resId));
        mTvLeft.setTextColor(mTextColorLeft);
    }

    public void setLeftTxtColorStateList(int resId) {
        mTextColorLeft = getResources().getColorStateList(resId);
        mTvLeft.setTextColor(mTextColorLeft);
    }

    //right
    public void setRightTextString(String txtString) {
        mTvRight.setText(txtString);
    }

    public void setRightTextSize(int textSize) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }


    public void setRightDrawable(int resId) {
        mDrawableRight = getResources().getDrawable(resId);
        mTvRight.setCompoundDrawables(mDrawableRight, null, null, null);
    }

    public void setRightPadding(int Right, int top, int right, int bottom) {
        mTvRight.setPadding(Right, top, right, bottom);
    }


    public void setRightSideTxtColor(int resId) {
        mTextColorRight = ColorStateList.valueOf(getResources().getColor(resId));
        mTvRight.setTextColor(mTextColorRight);
    }

    public void setRightTxtColorStateList(int resId) {
        mTextColorRight = getResources().getColorStateList(resId);
        mTvRight.setTextColor(mTextColorRight);
    }

    //base line
    public void setBaseLineColor(int baseLineColor) {
        mIsDrawBaseLine = mBaseLineHeight > 0 ? true : false;
        if (null == mBaseLinePaint) {
            mBaseLinePaint = new Paint();
        }
        mBaseLinePaint.setColor(baseLineColor);
        postInvalidate();
    }

    public void setBaseLineHeight(float height) {
        mIsDrawBaseLine = mBaseLineHeight > 0 ? true : false;
        mBaseLineHeight = height > 0 ? height : 0;

        if (null == mBaseLinePaint) {
            mBaseLinePaint = new Paint();
        }
        mBaseLinePaint.setStrokeWidth(height);
        postInvalidate();
    }
    //-------------------attribute setting end-----------------------------


    //-------------------click event start-----------------------------
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.title_bar_left) {
            onTitleLeftClick(v);
        } else if (id == R.id.title_bar_right) {
            onTitleRightClick(v);
        }
    }

    private void onTitleLeftClick(View view) {
        if (null != mOnLeftClickListener) {
            mOnLeftClickListener.onClick(view);
        }
        if (null != mOnBothSideClickListener) {
            mOnBothSideClickListener.onClick(view);
        }
    }

    private void onTitleRightClick(View view) {
        if (null != mOnRightClickListener) {
            mOnRightClickListener.onClick(view);
        }
        if (null != mOnBothSideClickListener) {
            mOnBothSideClickListener.onClick(view);
        }
    }

    private OnClickListener mOnLeftClickListener;
    private OnClickListener mOnRightClickListener;
    private OnClickListener mOnBothSideClickListener;


    public void setOnLeftClickListener(OnClickListener listener) {
        mOnLeftClickListener = listener;
    }

    public void setOnRightClickListener(OnClickListener listener) {
        mOnRightClickListener = listener;
    }

    public void setOnBothSideClickListener(OnClickListener listener) {
        mOnBothSideClickListener = listener;
    }
    //-------------------click event end-----------------------------

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
