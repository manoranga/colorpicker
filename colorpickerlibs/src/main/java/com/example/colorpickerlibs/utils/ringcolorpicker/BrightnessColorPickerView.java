package com.example.colorpickerlibs.utils.ringcolorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;
public class BrightnessColorPickerView extends View {

    private Paint paint;
    private int baseColor = Color.RED; // Default base color
    private Bitmap bitmap;
    private Bitmap overlayBitmap; // Additional bitmap to overlay
    private int width, height;
    private OnColorPickedListener onColorSelectedListener;
    private float touchX = -1, touchY = -1;
    private Shader.TileMode tileMode = Shader.TileMode.CLAMP; // Default tile mode

    public BrightnessColorPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void setBaseColor(int color) {
        this.baseColor = color;
        Log.d("KKKKK", String.valueOf(color));
        createGradientBitmap();
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        createGradientBitmap();
    }

    private void createGradientBitmap() {
        try {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            paint.setColor(baseColor);
//            canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint);
            canvas.drawCircle(width / 2f, height / 2f, width / 2f, paint);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }

}
