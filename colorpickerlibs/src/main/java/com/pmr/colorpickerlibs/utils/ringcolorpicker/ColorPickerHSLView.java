package com.pmr.colorpickerlibs.utils.ringcolorpicker;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.pmr.colorpickerlibs.databinding.ColorPalleteHslViewBinding;
import com.pmr.colorpickerlibs.utils.interfaces.OnColorPickedListener;


public class ColorPickerHSLView extends FrameLayout {

    private Paint paint;
    private int width, height;
    private Context context;
    private float ringRadius = 20;
    private float pickerRadios = 20;
    private int baseColor;
    private int selectedColor;
    private ColorPalleteHslViewBinding binding;
    private OnColorPickedListener onColorSelectedListener;

    public void setOnColorSelectedListener(OnColorPickedListener listener) {
        this.onColorSelectedListener = listener;
    }

    public ColorPickerHSLView(Context context) {
        super(context);
        initView(context);
    }

    public ColorPickerHSLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ColorPickerHSLView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void setRingRadius(float pickerRadios,float ringRadius){
        this.ringRadius = ringRadius;
        this.pickerRadios = pickerRadios;
        binding.mainView.getLayoutParams().width = (int) pickerRadios;
        binding.mainView.getLayoutParams().height = (int) pickerRadios;
        binding.mainViewCard.setRadius(pickerRadios/2);
        binding.ringView.setRingRadius(ringRadius);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
        invalidate();
    }

    public void setBaseColor(int baseColor){
        this.baseColor = baseColor;
        binding.brightnessColorPicker.setBaseColor(baseColor);
        binding.ringView.setBaseColorHSV(baseColor);

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }


    private void initView(Context context) {
        this.context = context;
        binding = ColorPalleteHslViewBinding.inflate(LayoutInflater.from(context), this, true);

        binding.ringView.setRingRadius(ringRadius);

        binding.ringView.setOnColorSelectedListener(color -> {
            selectedColor = color;
            if(onColorSelectedListener!=null)
                onColorSelectedListener.colorPicked(selectedColor);
        });
    }

    public void setSelectedColor(int color) {
        binding.ringView.setSelectedColor(color);
    }

    public void setColorFromOuter(){
        onColorSelectedListener.colorPicked(selectedColor);
    }
}
