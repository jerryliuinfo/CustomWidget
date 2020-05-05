package com.apache.customwidget.util;

import android.graphics.Paint;

/**
 * Created by Jerry on 2020-05-05.
 */
public class PaintUtil {

    public static Paint config(float textSize){
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(30);
        return mPaint;
    }

    public static Paint configFill(float textSize){
        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        return mPaint;
    }
}
