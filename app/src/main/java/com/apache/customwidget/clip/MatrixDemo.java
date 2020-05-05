package com.apache.customwidget.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.apache.customwidget.R;
import com.apache.customwidget.util.LogUtils;

/**
 * Created by Jerry on 2020-05-05.
 */
public class MatrixDemo extends View {
    private int mWidth, mHeight;
    private Bitmap bitmap;
    private Paint mPaint;
    private Paint mTextPaint;
    private Matrix matrix;

    public MatrixDemo(Context context) {
        super(context);
        init();
    }

    public MatrixDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MatrixDemo(Context context, AttributeSet attrs, int defStyleAttr) {
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
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yifei);
        matrix = new Matrix();
        LogUtils.d("ClipImageView init ");

    }
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
        //往后插入平移
        matrix.postTranslate(200,0);
        //往后插入旋转
        matrix.postRotate(45,mWidth/2,mHeight/2);
        canvas.drawBitmap(bitmap, matrix,mPaint);
        canvas.restore();
        canvas.drawText("Matrix PostXX",mCenterX,mCenterY,mTextPaint);

    }
}
