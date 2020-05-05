package com.apache.customwidget.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;

import com.apache.customwidget.base.BaseView;

import androidx.annotation.Nullable;

/**
 * Created by Jerry on 2020-05-05.
 */
public class PaintDemo extends BaseView {

    int mRadis =  120;

    public PaintDemo(Context context) {
        super(context);
    }

    public PaintDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PaintDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
    }

    private Shader configLinearGradientShader(){
        Shader.TileMode tileMode = Shader.TileMode.MIRROR;
        Shader shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), tileMode);
        return shader;
    }

    private Shader configRadialGradientShader(){
        Shader.TileMode tileMode = Shader.TileMode.MIRROR;
        Shader shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"), tileMode);
        return shader;
    }

    private Shader configSweepGradientShader(){
        Shader shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3"));
        return shader;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        getPaint().setShader(configLinearGradientShader());
        float centerY1 = getCenterY() - (mRadis * 2 + mRadis);
        canvas.drawCircle(getCenterX(), centerY1,mRadis,getPaint());
        canvas.drawText("LinearGradientShader", getCenterX(),centerY1,getTextPaint());


        getPaint().setShader(configRadialGradientShader());
        canvas.drawCircle(getCenterX(), getCenterY(),mRadis,getPaint());
        canvas.drawText("RadialGradientShader", getCenterX(),getCenterY(),getTextPaint());


        getPaint().setShader(configSweepGradientShader());
        float centerY2 = getCenterY() + (mRadis * 2 + mRadis);
        canvas.drawCircle(getCenterX(), centerY2 ,mRadis,getPaint());
        canvas.drawText("SweepGradientShader", getCenterX(),centerY2,getTextPaint());


    }
}
