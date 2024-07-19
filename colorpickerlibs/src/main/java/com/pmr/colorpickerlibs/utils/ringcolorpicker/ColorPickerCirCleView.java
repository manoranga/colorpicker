package com.pmr.colorpickerlibs.utils.ringcolorpicker;

import android.content.Context;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.pmr.colorpickerlibs.databinding.ColorPickerCircleViewBinding;
import com.pmr.colorpickerlibs.utils.interfaces.OnColorPickedListener;


public class ColorPickerCirCleView extends FrameLayout {

    private Paint paint;
    private int width, height;
    private Context context;
    private float outerPickerRadios = 20;
    private ColorPickerCircleViewBinding binding;
    private OnColorPickedListener onColorSelectedListener;
    int currentSelectedColor;

    private int lastValue = -1; // Initialize with a value that is not expected to be sent
    private long lastTime = 0;
    private Handler handler = new Handler();
    private Runnable callbackRunnable;

    public void setOnColorSelectedListener(OnColorPickedListener listener) {
        this.onColorSelectedListener = listener;
    }

    public ColorPickerCirCleView(Context context) {
        super(context);
        initView(context);
    }

    public ColorPickerCirCleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ColorPickerCirCleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setRingRadius(int ringWidth, float pickerInnerRadios, int width, int smallPicker) {
        binding.outerCircleView.setRingWidth(ringWidth);

        binding.mainView.getLayoutParams().width = width;
        binding.mainView.getLayoutParams().height = width;

        binding.hslView.setRingRadius(pickerInnerRadios, smallPicker);
    }

    public void setCurrentSelectedColor(int selectedColor) {
        new Handler().postDelayed(() -> {
            currentSelectedColor = selectedColor;
            binding.hslView.setBaseColor(selectedColor);
            binding.hslView.setSelectedColor(selectedColor);

            //if(!isFromView1) {
            //binding.outerCircleView.setNeedToCallListener(isFromView1);
            binding.outerCircleView.setColor(selectedColor);
            //}
        }, 100);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }


    private void initView(Context context) {
        this.context = context;
        binding = ColorPickerCircleViewBinding.inflate(LayoutInflater.from(context), this, true);

        binding.hslView.setOnColorSelectedListener(color -> {
            currentSelectedColor = color;
            updateValue(color);
        });

        binding.outerCircleView.setOnColorPickedListener((color) -> {
            currentSelectedColor = color;

            binding.hslView.setBaseColor(color);
            //binding.hslView.setSelectedColor(color);
            updateValue(color);
        });

    }

    public void updateValue(int newValue) {
        long currentTime = System.currentTimeMillis();

        if (newValue != lastValue) {
            lastValue = newValue;
            lastTime = currentTime;
            if (onColorSelectedListener != null) {
                onColorSelectedListener.colorPicked(newValue);
            }


        }
    }

}
