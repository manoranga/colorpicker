package com.example.colorpickerlibs.utils.squarecolorpikcer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.colorpickerlibs.utils.common.ColorPicker;
import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;
import com.example.colorpickerlibs.R;

public class SquareColorPicker extends ColorPicker {

    Paint paint;
    Shader squarPickerShader;
    float[] color = {1.f, 1.f, 1.f};
    private float TouchX, TouchY = this.getMeasuredHeight() / 2;
    OnColorPickedListener onColorSquarePickerListener;
    int selectedColor;

    int colorPickerRadius = 40;
    boolean isFromPallet = false;
    boolean isFromColorPicker = true;
    private boolean isTouchPickerOnce = false;
    Bitmap bitmap2 = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.img_3);

    public SquareColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareColorPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void init() {
        super.init();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        int rgb = Color.rgb(color[0] / 255, color[1] / 255, color[2] / 255);

        Paint paint2 = new Paint();
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.WHITE);
        paint2.setStrokeWidth(6);

        if (paint == null) {
            paint = new Paint();
            squarPickerShader = new LinearGradient(0.f, 0.f, 0.f, this.getMeasuredHeight(), 0xffffffff, 0xff000000, Shader.TileMode.CLAMP);
        }

        Shader dalam = new LinearGradient(0.f, 0.f, this.getMeasuredWidth(), 0.f, 0xffffffff, rgb, Shader.TileMode.CLAMP);
        ComposeShader shader = new ComposeShader(squarPickerShader, dalam, PorterDuff.Mode.MULTIPLY);
        paint.setShader(shader);
        canvas.drawRect(0.f, 0.f, this.getMeasuredWidth(), this.getMeasuredHeight(), paint);


        bitmap2 = Bitmap.createScaledBitmap(bitmap2, this.getMeasuredWidth(), this.getMeasuredHeight(), true);

        Canvas canvas2 = new Canvas(bitmap2);
        canvas2.drawBitmap(bitmap2, this.getMeasuredWidth() / 2, this.getMeasuredHeight() / 2, paint);
        canvas2.drawPaint(paint);

        try {
            int red = Color.red(bitmap2.getPixel((int) TouchX, (int) TouchY));
            int green = Color.green(bitmap2.getPixel((int) TouchX, (int) TouchY));
            int blue = Color.blue(bitmap2.getPixel((int) TouchX, (int) TouchY));
            int color = Color.rgb(red, green, blue);
            selectedColor = color;

            Paint paint3 = new Paint();
            paint3.setColor(color);
            paint3.setStyle(Paint.Style.FILL);

            System.out.println("SELECTED_COLOR_FROM_PICKER 1====>" + TouchX);
            System.out.println("SELECTED_COLOR_FROM_PICKER 2====>" + TouchY);

            if (isFromPallet) {

            }

            canvas.drawCircle(TouchX, TouchY, colorPickerRadius + 1, paint2);
            canvas.drawCircle(TouchX, TouchY, colorPickerRadius, paint);

            invalidate();

        } catch (Exception e) {
            e.printStackTrace();
            canvas.drawCircle(TouchX, TouchY, colorPickerRadius + 1, paint2);
            canvas.drawCircle(TouchX, TouchY, colorPickerRadius, paint);
            invalidate();
        }
    }

    @Override
    protected void handleTouch(int motionAction, float x, float y) {

    }

    @Override
    protected void moveHandleTo(float x, float y) {

    }

    @Override
    protected void animateHandleTo(float x, float y) {

    }

    @Override
    public void setColor(int color) {

    }

    public void setHue(float hue) {
        color[0] = hue;
        invalidate();
    }

    public void setColorToSquare(int color) {
        this.color[0] = Color.red(color);
        this.color[1] = Color.green(color);
        this.color[2] = Color.blue(color);
        if (onColorSquarePickerListener != null && isTouchPickerOnce) {
            onColorSquarePickerListener.colorPicked(selectedColor);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchX = event.getX();
        TouchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getColorAtPosition(TouchX, TouchY);
                isFromPallet = false;
                isTouchPickerOnce = true;
                break;

            case MotionEvent.ACTION_MOVE:
                getColorAtPosition(TouchX, TouchY);
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(event);
    }

    public void setOnColorSquarePickerListener(OnColorPickedListener onColorSquarePickerListener) {
        this.onColorSquarePickerListener = onColorSquarePickerListener;
    }

    public void setPickerRadius(int radius) {
        colorPickerRadius = radius / 2;
    }

    public void selectByHsvToSquarPicker(int color) {
        isFromPallet = true;

        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        //float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);

        TouchX = hsv[1] * getWidth();
        TouchY = (1 - hsv[2]) * getHeight();
        invalidate();
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

        if (onColorSquarePickerListener != null) {
            selectedColor = color;
            onColorSquarePickerListener.colorPicked(selectedColor);
            paint.setColor(selectedColor);
        }

        return color;
    }
}