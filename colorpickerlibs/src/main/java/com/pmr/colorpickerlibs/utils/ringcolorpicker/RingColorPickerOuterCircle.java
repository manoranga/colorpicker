package com.pmr.colorpickerlibs.utils.ringcolorpicker;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.pmr.colorpickerlibs.R;
import com.pmr.colorpickerlibs.utils.common.ColorPicker;
import com.pmr.colorpickerlibs.utils.common.ColorUtils;
import com.pmr.colorpickerlibs.utils.common.Utils;
import com.pmr.colorpickerlibs.utils.interfaces.OnColorPickedListener;


public class RingColorPickerOuterCircle extends ColorPicker {

    private Paint mInnerPaint;
    private RectF mHandleRect;
    private boolean isTouchingCenterPicker;

    private int mRingWidth, mGapWidth; // view attributes
    private float mInnerRadius, mOuterRadius; // view measurements

    private static final int HANDLE_PADDING = 10;

    private OnColorPickedListener onColorPickedListener;

    public RingColorPickerOuterCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setDrawingCacheEnabled(true);
    }

    public RingColorPickerOuterCircle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setDrawingCacheEnabled(true);
    }

    protected void init() {
        super.init();

        mHandleRect = new RectF();

        // init paints
        mColorPaint.setStyle(Paint.Style.STROKE);
        mColorPaint.setStrokeWidth(mRingWidth);
        mColorPaint.setShader(new SweepGradient(0, 0, ColorUtils.getHueRingColors(7, mSat, mVal), null));

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setColor(getColor());

        if (isInEditMode()) {
            mColorPaint.setShader(new SweepGradient(0, 0, COLORS, null));
            mInnerPaint.setColor(Color.BLUE);
            mHandlePaint.setColor(Color.BLUE);

            mHandlePaint.setColor(Color.BLUE);
        }
    }

    protected void initAttributes(AttributeSet attrs) {
        super.initAttributes(attrs);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RingColorPicker);

        try {
            mRingWidth = 150;
            mGapWidth = 30;

        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        mOuterRadius = Math.min(mHalfWidth, mHalfHeight) - mColorPaint.getStrokeWidth() / 2 - getMaxPadding() - HANDLE_PADDING;
        mInnerRadius = mOuterRadius - mColorPaint.getStrokeWidth() / 2 - mGapWidth;

        float s = mHandleStrokePaint.getStrokeWidth() / 2;

        float left = mInnerRadius + mGapWidth - HANDLE_PADDING / 4f + s;
        float top = -mHandleSize / 1.1f;

        float right = left + mColorPaint.getStrokeWidth();
        float bottom = top + mColorPaint.getStrokeWidth();

        mHandleRect.set(
                left,
                top,
                right,
                bottom
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mHalfWidth, mHalfHeight);
        // outer ring
        canvas.drawCircle(0, 0, mOuterRadius, mColorPaint);

        Paint paint2 = new Paint();
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.WHITE);
        paint2.setStrokeWidth(4);

        canvas.rotate(mHue);

        float[] corners = new float[]{
                mOuterRadius, mOuterRadius,        // Top, left in px
                mOuterRadius, mOuterRadius,        // Top, right in px
                mOuterRadius, mOuterRadius,          // Bottom, right in px
                mOuterRadius, mOuterRadius           // Bottom,left in px
        };

        final Path path = new Path();
        path.addRoundRect(mHandleRect, corners, Path.Direction.CW);
        mHandleStrokePaint.setColor(Color.WHITE);
        // Draw
        canvas.drawPath(path, mHandleStrokePaint);
        invalidate();
    }

    private void triggerCallBackListener(int color) {
        if (onColorPickedListener != null) {
            onColorPickedListener.colorPicked(color);
        }
    }

    @Override
    protected void handleTouch(int motionAction, float x, float y) {
        // set origin to center
        x -= mHalfWidth;
        y -= mHalfHeight;

        float dist = Utils.getDistance(0, 0, x, y);

        boolean isTouchingRing = dist > mInnerRadius + mGapWidth - HANDLE_PADDING
                && dist < mOuterRadius + mColorPaint.getStrokeWidth() / 2 + HANDLE_PADDING;
        boolean isTouchingCenter = dist < mInnerRadius;
        isTouchingCenterPicker = dist < mInnerRadius;

        switch (motionAction) {
            case MotionEvent.ACTION_DOWN:
                // check if touching handle
                float angle = Utils.normalizeAngle(Utils.getAngleDeg(0, 0, x, y));
                float absDiff = Math.abs(angle - mHue);
                absDiff = absDiff > 180 ? 360 - absDiff : absDiff;
                float touchDist = (float) Math.toRadians(absDiff) * dist;
                mDragging = touchDist < mTouchSize / 2 && isTouchingRing;
                if (isTouchingRing)
                    moveHandleTo(x, y);
                break;

            case MotionEvent.ACTION_MOVE:
                    moveHandleTo(x, y);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
                if (mDragging && !isTouchingCenterPicker) {
                    moveHandleTo(event.getX(), event.getY());
                }
                break;

            case MotionEvent.ACTION_UP:

                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int min = Math.min(View.MeasureSpec.getSize(widthMeasureSpec), View.MeasureSpec.getSize(heightMeasureSpec));
        setMeasuredDimension(min, min);
    }


    @Override
    protected void moveHandleTo(float x, float y) {
        float angle = Utils.normalizeAngle(Utils.getAngleDeg(0, 0, x, y));
        moveHandleTo(angle);
    }

    private void moveHandleTo(float angle) {
        mHue = Utils.normalizeAngle(angle);
        int color = ColorUtils.getColorFromHSV(mHue, mSat, mVal);

        // repaint
        mInnerPaint.setColor(color);
        mHandlePaint.setColor(color);
        invalidate();

        triggerCallBackListener(color);

//        // fire event
//        if (mOnColorChangedListener != null)
//            mOnColorChangedListener.colorChanged(color);
//
//        // set linear pickers if attached
//        if (mSatLCP != null)
//            mSatLCP.updateHSV(mHue, mSat, mVal);
//        if (mValLCP != null)
//            mValLCP.updateHSV(mHue, mSat, mVal);
//        if (onColorPickedListener != null && isNeedToTriggerCallBack) {
//            onColorPickedListener.colorPicked(color,isFromColorPicker);
//        }
    }

    @Override
    protected void animateHandleTo(float x, float y) {
        float angle = Utils.normalizeAngle(Utils.getAngleDeg(0, 0, x, y));
        animateHandleTo(angle);
    }

    private void animateHandleTo(float angle) {
        float diff = mHue - angle;

        // correct angles
        if (diff < -180) diff += 360;
        else if (diff > 180) diff -= 360;

        // start animating
        ValueAnimator anim = ValueAnimator.ofFloat(mHue, mHue - diff);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveHandleTo((float) animation.getAnimatedValue());
            }
        });
        anim.start();
    }

    @Override
    public void setColor(int color) {
        float angle = ColorUtils.getHueFromColor(color);
//        mSat = ColorUtils.getSaturationFromColor(color);
//        mVal = ColorUtils.getValueFromColor(color);
//        mColorPaint.setShader(new SweepGradient(0, 0, ColorUtils.getHueRingColors(7, mSat, mVal), null));
        animateHandleTo(angle);
    }

    /**
     * Set outer color ring width
     *
     * @param ringWidth outer ring width in pixels
     */
    public void setRingWidth(int ringWidth) {
        mRingWidth = ringWidth;
        mColorPaint.setStrokeWidth(mRingWidth);

        onSizeChanged(getWidth(), getHeight(), 0, 0);
        invalidate();
    }

    /**
     * Get outer color ring width
     *
     * @return outer ring width in pixels
     */
    public int getRingWidth() {
        return mRingWidth;
    }

    /**
     * Set gap width between outer ring and inner circle. Values are clamped
     *
     * @param gapWidth gap width in pixels
     */
    public void setGapWidth(int gapWidth) {
        mGapWidth = Math.max(gapWidth, HANDLE_PADDING * 2);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
        invalidate();
    }


    public int getGapWidth() {
        return mGapWidth;
    }

    public void setOnColorPickedListener(OnColorPickedListener listener) {
        onColorPickedListener = listener;
    }


}


