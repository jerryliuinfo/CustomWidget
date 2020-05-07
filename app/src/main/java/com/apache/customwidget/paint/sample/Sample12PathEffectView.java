package com.apache.customwidget.paint.sample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Sample12PathEffectView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Path path = new Path();
    /**
     * 把所有拐角变成圆角
     */
    PathEffect cornerPathEffect = new CornerPathEffect(30);

    /**
     * 把线条进行随机的偏离，让轮廓变得乱七八糟。乱七八糟的方式和程度由参数决定。
     * 把绘制改为使用定长的线段来拼接，并且在拼接的时候对路径进行随机偏离。它的构造方法 DiscretePathEffect(float segmentLength, float deviation) 的两个参数中， segmentLength 是用来拼接的每个线段的长度， deviation 是偏离量。
     * 这两个值设置得不一样，显示效果也会不一样
     */
    PathEffect discretePathEffect = new DiscretePathEffect(40, 15);
    /**
     * 使用虚线来绘制线条。 它的构造方法 DashPathEffect(float[] intervals, float phase) 中， 第一个参数 intervals 是一个数组，它指定了虚线的格式：数组中元素必须为偶数（最少是 2 个），按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列，例如上面代码中的 20, 5, 10, 5 就表示虚线是按照「画 20 像素、空 5 像素、画 10 像素、空 5 像素」的模式来绘制；
     * 第二个参数 phase 是虚线的偏移量。
     * 这里画20长度，空白10，画5，空10
     */
    PathEffect dashPathEffect = new DashPathEffect(new float[]{20, 10, 5, 10}, 0);
    /**
     * PathDashPathEffect(Path shape, float advance, float phase, PathDashPathEffect.Style style)
     */
    PathEffect pathDashPathEffect;
    PathEffect sumPathEffect = new SumPathEffect(dashPathEffect, discretePathEffect);
    PathEffect composePathEffect = new ComposePathEffect(dashPathEffect, discretePathEffect);

    public Sample12PathEffectView(Context context) {
        super(context);
    }

    public Sample12PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Sample12PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        //paint.setStrokeWidth(5);

        path.moveTo(50, 100);
        path.rLineTo(50, 100);
        path.rLineTo(80, -150);
        path.rLineTo(100, 100);
        path.rLineTo(70, -120);
        path.rLineTo(150, 80);

        Path dashPath = new Path();
        dashPath.lineTo(20, -30);
        dashPath.lineTo(40, 0);
        dashPath.close();
        pathDashPathEffect = new PathDashPathEffect(dashPath, 50, 20, PathDashPathEffect.Style.TRANSLATE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 使用 Paint.setPathEffect() 来设置不同的 PathEffect

        // CornerPathEffect
        paint.setPathEffect(cornerPathEffect);
        canvas.drawPath(path, paint);

        canvas.save();
        canvas.translate(500, 0);
        // DiscretePathEffect
        paint.setPathEffect(discretePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(0, 200);
        // DashPathEffect
        paint.setPathEffect(dashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

       canvas.save();
        canvas.translate(500, 200);
        // PathDashPathEffect
        paint.setPathEffect(pathDashPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

         canvas.save();
        canvas.translate(0, 400);
        // SumPathEffect
        paint.setPathEffect(sumPathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        canvas.save();
        canvas.translate(500, 400);
        // ComposePathEffect
        paint.setPathEffect(composePathEffect);
        canvas.drawPath(path, paint);
        canvas.restore();

        paint.setPathEffect(null);
    }
}
