package com.apache.customwidget.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.apache.customwidget.R;
import com.apache.customwidget.base.BaseView;
import com.apache.customwidget.util.LogUtils;

import androidx.annotation.Nullable;

/**
 * Created by Jerry on 2020-05-05.
 */
public class BitmapShaderDemo extends BaseView {
    private Bitmap bitmap;
    private Bitmap mLogo;
    private float mRadis = 500;

    public BitmapShaderDemo(Context context) {
        super(context);
    }

    public BitmapShaderDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        mLogo = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);
        LogUtils.d("init bitmap:%s, mLogo:%s",bitmap, mLogo);

    }

    private Shader configBitmapShader(){
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        LogUtils.d("configBitmapShader bitmap:%s",bitmap);

        return shader;
    }

    private Shader configComposeShader(){
        //// 硬件加速下 ComposeShader 不能使用两个同类型的 Shader
        setLayerType(LAYER_TYPE_SOFTWARE, null);

        // 第一个 Shader：头像的 Bitmap
        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        LogUtils.d("configComposeShader bitmap2: %s, mLogo:%s", mLogo, mLogo);
        Shader shader2 = new BitmapShader(mLogo, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

// ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1,shader2,  PorterDuff.Mode.SRC_OVER);
        return shader;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        getPaint().setShader(configBitmapShader());
//        canvas.drawCircle(getCenterX(), getCenterY()  ,mRadis,getPaint());
//        canvas.drawText("BitmapShader", getCenterX(),getCenterY(),getPaint());


        getPaint().setShader(configComposeShader());
        float centerY = getCenterY() ;
        canvas.drawCircle(getCenterX(), centerY  ,mRadis,getPaint());
        canvas.drawText("ComposeShader", getCenterX(),centerY,getPaint());

    }
}
