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
public class ClipImageView extends View {
    private Rect rect ;
    private int mWidth, mHeight;
    private Bitmap bitmap;
    private Paint mPaint;
    private Paint mTextPaint;

    public ClipImageView(Context context) {
        super(context);
        init();
    }

    public ClipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClipImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    int offset = 0;
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


        rect.set(offset,offset,-mWidth,mHeight - offset);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save(); //保存canvas状态
        canvas.clipRect(100,100,900,900);
        canvas.drawBitmap(bitmap, 0,0,mPaint);
        canvas.restore();//把绘制范围恢复，把裁切的范围去掉，也就是后续的绘制不会被裁切了
        canvas.drawText("Clip",0,mCenterY,mTextPaint);
    }
}
