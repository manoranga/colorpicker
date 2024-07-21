package com.example.colorpickerlibs.utils.ringcolorpicker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;

public class ColorPickRing extends View {

    private Paint paint;
    private Paint ringPaint;

    private int selectedColor;
    private float touchX = -1f;
    private float touchY = -1f;
    private float ringRadius = 20f;
    private int width, height;
    float pX;
    float pY;
    private OnColorPickedListener onColorSelectedListener;
    private boolean isFromSelfView = false;


    public void setOnColorSelectedListener(OnColorPickedListener listener) {
        this.onColorSelectedListener = listener;
    }

    public ColorPickRing(Context context) {
        super(context);
        init();
    }

    public ColorPickRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorPickRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void setRingRadius(float ringRadius) {
        this.ringRadius = ringRadius;
    }

    public void setBaseColorHSV(int color) {
        selectedColor = color;
        getColorAtPosition(touchX, touchY);
        invalidate();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        ringPaint = new Paint();
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(3f);
        ringPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //  if (touchX != -1f && touchY != -1f) {
//        float radius = getWidth() / 2f;
//        float dx = touchX - radius;
//        float dy = touchY - radius;
//        double distance = Math.sqrt(dx * dx + dy * dy);
//
//        if (distance <= radius && touchX >= 0 && touchY >= 0) {
//            ringPaint.setColor(Color.WHITE);
//
//            canvas.drawCircle(touchX, touchY, ringRadius, paint);
//            canvas.drawCircle(touchX, touchY, ringRadius, ringPaint);
//            pX = touchX;
//            pY = touchY;
//        } else {
//            if (pX == 0.f || pY == 0.f) {
//                touchX = getWidth() / 2;
//                touchY = getHeight() / 2;
//            } else {
//                touchX = pX;
//                touchY = pY;
//            }
//        }

        canvas.drawCircle(touchX, touchY, ringRadius, paint);
        canvas.drawCircle(touchX, touchY, ringRadius, ringPaint);
        if(onColorSelectedListener!=null && isFromSelfView){
            onColorSelectedListener.colorPicked(selectedColor);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            isFromSelfView = true;
            float radius = getWidth() / 2f;
            float dx = event.getX() - radius;
            float dy = event.getY() - radius;
            double distance = Math.sqrt(dx * dx + dy * dy);
            touchX = event.getX();
            touchY = event.getY();
            paint.setColor(selectedColor);
            invalidate();

            if (distance <= radius) {
                invalidate();
                if (onColorSelectedListener != null) {
                    getColorAtPosition(touchX, touchY);
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    private int getColorAtPosition(float x, float y) {
        float saturation = x / getWidth();
        float value = 1 - y / getHeight();

        // Clamp values to [0, 1]
        saturation = Math.max(0, Math.min(saturation, 1));
        value = Math.max(0, Math.min(value, 1));

        float[] rgb = new float[3];
        Color.colorToHSV(selectedColor, rgb);

        float[] hsv = {rgb[0], saturation, value};
        int color = Color.HSVToColor(hsv);

        if (onColorSelectedListener != null) {
            selectedColor = color;
            paint.setColor(selectedColor);
            if(isFromSelfView ){
                onColorSelectedListener.colorPicked(color);
            }
            invalidate();
        }

        return color;
    }

    private boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }


    private void getPositionFromColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        touchX = hsv[1] * getWidth();
        touchY = (1 - hsv[2]) * getHeight();
        invalidate();
    }

    public void setSelectedColor(int color) {
        selectedColor = color;
        isFromSelfView = true;
        getPositionFromColor(color);
    }
}
