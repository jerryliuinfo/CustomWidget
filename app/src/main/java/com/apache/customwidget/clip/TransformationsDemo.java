package com.apache.customwidget.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.apache.customwidget.R;
import com.apache.customwidget.util.LogUtils;

/**
 * Created by Jerry on 2020-05-05.
 */
public class TransformationsDemo extends View {
    private Rect rect ;
    private int mWidth, mHeight;
    private Bitmap bitmap;
    private Paint mPaint;
    private Paint mTextPaint;

    public TransformationsDemo(Context context) {
        super(context);
        init();
    }

    public TransformationsDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TransformationsDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(40);
        rect = new Rect();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yifei);
        LogUtils.d("ClipImageView init ");

    }
    int offset = 50;
    private int mCenterX, mCenterY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        mCenterX = mWidth / 2;
        mCenterY = mHeight / 2;
        LogUtils.d("ClipImageView onMeasure mWidth: %s, mHeight: %s, mCenterX:%s, mCenterY: %s",mWidth,mHeight
            ,mCenterX,mCenterY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        //绘制顺序其实是先平移，再旋转

        canvas.rotate(45, mWidth/2, mHeight/2);
        canvas.translate(200,0);

        canvas.drawBitmap(bitmap, 0,0,mPaint);
        canvas.restore();
        canvas.drawText("SaveRestore",mCenterX,mCenterY,mTextPaint);

    }
}
