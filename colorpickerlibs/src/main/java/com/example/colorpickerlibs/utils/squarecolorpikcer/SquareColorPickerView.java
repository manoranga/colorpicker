package com.example.colorpickerlibs.utils.squarecolorpikcer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.colorpickerlibs.R;
import com.example.colorpickerlibs.databinding.ColorPalleteSqureViewBinding;
import com.example.colorpickerlibs.managers.SharedPref;
import com.example.colorpickerlibs.utils.common.ColorUtils;
import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;

public class SquareColorPickerView extends FrameLayout {
    private ColorPalleteSqureViewBinding binding;
    private Context context;
    private int windowHeight, windowWidth;
    private int viewWidth = 1194;
    private int viewHeight = 834;

    private OnColorPickedListener colorPickedListener;
    private int selectedColor;

    public SquareColorPickerView(@NonNull Context context) {
        super(context);
        initViews(context);
    }

    public SquareColorPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public SquareColorPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public SquareColorPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    private void initViews(Context context) {
        this.context = context;
        binding = ColorPalleteSqureViewBinding.inflate(LayoutInflater.from(context), this, true);

        binding.hueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                float[] hsvColor = {0, 1, 1};

                hsvColor[0] = 360f * progress / 360;
                int currentSelectedColor = Color.HSVToColor(hsvColor);
                binding.colorPickerView.setColorToSquare(currentSelectedColor);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        binding.colorPickerView.setOnColorSquarePickerListener(color -> {
            colorPickedListener.colorPicked(color);
        });

        new Handler().postDelayed(() -> viewInit(), 200);
    }

    private void viewInit() {
        windowHeight = SharedPref.getInstance(context).getHeight(0);
        windowWidth = SharedPref.getInstance(context).getWidth(0);
    }

    public void setOnColorPickListener(OnColorPickedListener onColorPickedListener) {
        this.colorPickedListener = onColorPickedListener;
    }

    public void setCurrentSelectedColor(int color,boolean isMinimizeVersion){
        this.selectedColor = color;
        int progress = (int) ColorUtils.getHueFromColor(color);

        binding.colorPickerView.selectByHsvToSquarPicker(selectedColor);
        setIsMinimizeVersion(isMinimizeVersion);
        binding.hueSeekBar.setProgress(progress);
//        binding.colorPickerView.setColorToSquare(selectedColor);
    }

    public void setIsMinimizeVersion(boolean isMinimizeVersion){
        if(isMinimizeVersion){
            binding.colorPickerViewContainer.getLayoutParams().height = 165 * windowHeight / viewHeight;

            int dialogWidth = 244 * windowWidth / viewWidth;

            LinearGradient sHGradient = new LinearGradient(0.f, 0.f, dialogWidth, 0.0f,
                    new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.parseColor("#e32ddc"), Color.RED},
                    null, Shader.TileMode.CLAMP);
            ShapeDrawable sHShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
            binding.hueSeekBar.setProgress(70);
            sHShape.getPaint().setShader(sHGradient);
            binding.hueSeekBar.setProgressDrawable(sHShape);
            //binding.hueSeekBar.setThumb(context.getDrawable(R.drawable.seekbar_thumb));
            binding.hueSeekBar.setMax(360);

            binding.colorPickerView.setPickerRadius(40);

        }else {
            binding.colorPickerViewContainer.getLayoutParams().height = 312 * windowHeight / viewHeight;

            int dialogWidth = 376 * windowWidth / viewWidth;

            LinearGradient sHGradient = new LinearGradient(0.f, 0.f, dialogWidth, 0.0f,
                    new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.parseColor("#e32ddc"), Color.RED},
                    null, Shader.TileMode.CLAMP);
            ShapeDrawable sHShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
            binding.hueSeekBar.setProgress(70);
            sHShape.getPaint().setShader(sHGradient);
            binding.hueSeekBar.setProgressDrawable(sHShape);
            //binding.hueSeekBar.setThumb(context.getDrawable(R.drawable.seekbar_thumb));
            binding.hueSeekBar.setMax(360);

            binding.colorPickerView.setPickerRadius(80);

        }
    }

}
