package com.apache.customwidget.base;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.apache.customwidget.util.PaintUtil;

import androidx.annotation.Nullable;

/**
 * Created by Jerry on 2020-05-05.
 */
public abstract class BaseView extends View {
    private Paint mPaint;
    private Paint mTextPaint;

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    protected void init(){
        mPaint = PaintUtil.configFill(30);
        mTextPaint = PaintUtil.config(30);
    }

    int mWidth,mHeight, mCenterX, mCenterY;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
    }




    public Paint getPaint() {
        return mPaint;
    }

    public int getViewWidth() {
        return mWidth;
    }

    public int getViewHeight() {
        return mHeight;
    }

    public int getCenterX() {
        return mCenterX;
    }

    public int getCenterY() {
        return mCenterY;
    }


    public Paint getTextPaint() {
        return mTextPaint;
    }
}
