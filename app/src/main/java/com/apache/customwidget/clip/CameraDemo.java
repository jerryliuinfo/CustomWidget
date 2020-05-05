package com.apache.customwidget.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
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
public class CameraDemo extends View {
    private Rect rect ;
    private int mWidth, mHeight;
    private Bitmap bitmap;
    private Paint mPaint;
    private Paint mTextPaint;
    private Matrix matrix;
    private Camera camera;

    public CameraDemo(Context context) {
        super(context);
        init();
    }

    public CameraDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CameraDemo(Context context, AttributeSet attrs, int defStyleAttr) {
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
        matrix = new Matrix();
        camera = new Camera();
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

        //camera默认是以原点为中心旋转的，但是camera不支持设置轴心

        camera.save();
        camera.rotateX(30);
        //移到原点后再移回去
        canvas.translate(mCenterX, mCenterY);
        //将camera应用到canvas
        camera.applyToCanvas(canvas);
        //camera不支持设置轴心，需要先移动到原点，旋转了之后再移回去
        canvas.translate(-mCenterX,-mCenterY);
        camera.restore();

        canvas.drawBitmap(bitmap, matrix,mPaint);

        canvas.restore();
        canvas.drawText("Camera Matrix",mCenterX,mCenterY,mTextPaint);

    }
}
