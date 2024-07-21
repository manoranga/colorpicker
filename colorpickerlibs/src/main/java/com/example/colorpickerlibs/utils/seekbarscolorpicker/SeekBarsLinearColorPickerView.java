package com.example.colorpickerlibs.utils.seekbarscolorpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.colorpickerlibs.R;
import com.example.colorpickerlibs.databinding.SeekbarsPickerBinding;
import com.example.colorpickerlibs.managers.SharedPref;
import com.example.colorpickerlibs.utils.common.DoubleTapEditTextColorPicker;
import com.example.colorpickerlibs.utils.interfaces.EditTextDoubleTapListener;
import com.example.colorpickerlibs.utils.interfaces.EditTextTapUpListener;
import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;

public class SeekBarsLinearColorPickerView extends FrameLayout {

    private SeekbarsPickerBinding binding;
    private Context context;
    private int windowWidth, windowHeight;
    private String previousHexValue;
    private String previousHValue;
    private String previousSValue;
    private String previousLValue;
    private String previousRValue;
    private String previousGValue;
    private String previousBValue;
    private boolean isColorChangeFromSeekbar;
    private boolean isColorChangeRGB;
    private boolean isColorChangeHSV;
    private int currentSelectedColor;
    private int viewHeight = 834;
    private int viewWidth = 1194;
    private OnColorPickedListener onColorSquarePickerListener;
    private boolean isMinimizeVersion = false;

    public SeekBarsLinearColorPickerView(@NonNull Context context) {
        super(context);
        intViews(context);
    }

    public SeekBarsLinearColorPickerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intViews(context);
    }

    public SeekBarsLinearColorPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intViews(context);
    }

    public SeekBarsLinearColorPickerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intViews(context);
    }

    private void intViews(Context context) {
        windowHeight = SharedPref.getInstance(context).getHeight(0);
        windowWidth = SharedPref.getInstance(context).getWidth(0);
        this.context = context;
        binding = SeekbarsPickerBinding.inflate(LayoutInflater.from(context), this, true);
        initViews3();
        initSeekbars();

    }

    private void initSeekbars() {
        int textTitleWidth = 25 * windowWidth / viewWidth;
        int textValueCardWidth = 60 * windowWidth / viewWidth;
        int textValueCardHeight = 40 * windowHeight / viewHeight;

        binding.tvNameH.getLayoutParams().width = textTitleWidth;
        binding.tvNameS.getLayoutParams().width = textTitleWidth;
        binding.tvNameL.getLayoutParams().width = textTitleWidth;
        binding.tvNameR.getLayoutParams().width = textTitleWidth;
        binding.tvNameG.getLayoutParams().width = textTitleWidth;
        binding.tvNameB.getLayoutParams().width = textTitleWidth;

        binding.tvValueHContainer.getLayoutParams().width = textValueCardWidth;
        binding.tvValueHContainer.getLayoutParams().height = textValueCardHeight;

        binding.tvValueSContainer.getLayoutParams().width = textValueCardWidth;
        binding.tvValueSContainer.getLayoutParams().height = textValueCardHeight;

        binding.tvValueLContainer.getLayoutParams().width = textValueCardWidth;
        binding.tvValueLContainer.getLayoutParams().height = textValueCardHeight;

        binding.tvValueRContainer.getLayoutParams().width = textValueCardWidth;
        binding.tvValueRContainer.getLayoutParams().height = textValueCardHeight;

        binding.tvValueGContainer.getLayoutParams().width = textValueCardWidth;
        binding.tvValueGContainer.getLayoutParams().height = textValueCardHeight;

        binding.tvValueBContainer.getLayoutParams().width = textValueCardWidth;
        binding.tvValueBContainer.getLayoutParams().height = textValueCardHeight;


        binding.hexadecimalContainer.getLayoutParams().height = 63 * windowHeight / viewHeight;

        binding.etHexadecimalContainer.getLayoutParams().width = 87 * windowWidth / viewWidth;

        binding.etHexadecimalContainer.getLayoutParams().height = textValueCardHeight;


//        FrameLayout.LayoutParams paletteViewTopContainerParams = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 367 * windowHeight / viewHeight);
//        paletteViewTopContainerParams.setMargins(2 * topBarMargin, 14 * windowHeight / viewHeight, topBarMargin, 0);
//        binding.paletteViewTopContainer.setLayoutParams(paletteViewTopContainerParams);
//

        binding.paletteViewTopContainer.getLayoutParams().height = 367 * windowHeight / viewHeight;


//        editable text  view 3
        binding.etHexadecimal.setClickableET(true);
        binding.etHexadecimal.setTextTapListener(new EditTextTapUpListener() {
            @Override
            public void onSingleTap() {
                previousHexValue = binding.etHexadecimal.getText().toString();
            }
        });
        binding.etHexadecimal.setTextChangeListener(() -> {
            try {
                currentSelectedColor = Color.parseColor("#" + binding.etHexadecimal.getText().toString());
                //colorPickerView.selectByHsv(currentSelectedColor, true);
                updateAllViews(currentSelectedColor);
                updateView3();
            } catch (Exception e) {
                binding.etHexadecimal.setText(previousHexValue);
            }
        });


        binding.tvValueH.setSettingsKeyboardDismissListener(new DoubleTapEditTextColorPicker.SettingsKeyboardDismissListener() {
            @Override
            public void onKeyboardDismissed() {
                try {
                    float valueH = Float.parseFloat(binding.tvValueH.getText().toString());
                    if (0.f <= valueH && valueH <= 360.f) {
                        isColorChangeFromSeekbar = true;
                        isColorChangeHSV = true;
                        onSeekbarChange("H", (int) valueH);

                    } else {
                        binding.tvValueH.setText(previousHValue);
                    }
                } catch (Exception e) {
                    binding.tvValueH.setText(previousHValue);
                }
            }
        });

        binding.tvValueH.setEditTextDoubleTapListener(new EditTextDoubleTapListener() {
            @Override
            public void onDoubleTap() {
                previousHValue = binding.tvValueH.getText().toString();
                binding.tvValueH.enableEditText(binding.tvValueH.getText().length());
            }
        });

        binding.tvValueS.setSettingsKeyboardDismissListener(new DoubleTapEditTextColorPicker.SettingsKeyboardDismissListener() {
            @Override
            public void onKeyboardDismissed() {
                try {
                    float valueS = Float.parseFloat(binding.tvValueS.getText().toString());
                    if (0.f <= valueS && valueS <= 100.f) {
                        isColorChangeFromSeekbar = true;
                        isColorChangeHSV = true;
                        onSeekbarChange("S", (int) valueS);
                    } else {
                        binding.tvValueS.setText(previousSValue);
                    }
                } catch (Exception e) {
                    binding.tvValueS.setText(previousSValue);
                }
            }
        });

        binding.tvValueS.setOnLongClickListener(view -> {
            previousSValue = binding.tvValueS.getText().toString();
            binding.tvValueS.enableEditText(binding.tvValueS.getText().length());
            return false;
        });


        binding.tvValueL.setSettingsKeyboardDismissListener(() -> {
            try {
                float valueL = Float.parseFloat(binding.tvValueL.getText().toString());
                if (0.f <= valueL && valueL <= 100.f) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("L", (int) valueL);
                } else {
                    binding.tvValueL.setText(previousLValue);
                }
            } catch (Exception e) {
                binding.tvValueL.setText(previousLValue);
            }
        });

        binding.tvValueL.setOnLongClickListener(view -> {
            previousLValue = binding.tvValueL.getText().toString();
            binding.tvValueL.enableEditText(binding.tvValueL.getText().length());
            return false;
        });

        binding.tvValueR.setSettingsKeyboardDismissListener(() -> {
            try {
                int valueR = Integer.parseInt(binding.tvValueR.getText().toString());
                if (0 <= valueR && valueR <= 255) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("R", valueR);
                } else {
                    binding.tvValueR.setText(previousRValue);
                }
            } catch (Exception e) {
                binding.tvValueR.setText(previousRValue);
            }
        });

        binding.tvValueR.setOnLongClickListener(view -> {
            previousRValue = binding.tvValueR.getText().toString();
            binding.tvValueR.enableEditText(binding.tvValueR.getText().length());
            return false;
        });

        binding.tvValueB.setSettingsKeyboardDismissListener(() -> {
            try {
                int valueB = Integer.parseInt(binding.tvValueB.getText().toString());
                if (0 <= valueB && valueB <= 255) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("B", valueB);
                } else {
                    binding.tvValueB.setText(previousBValue);
                }
            } catch (Exception e) {
                binding.tvValueB.setText(previousBValue);
            }
        });

        binding.tvValueB.setOnLongClickListener(view -> {
            previousBValue = binding.tvValueB.getText().toString();
            binding.tvValueB.enableEditText(binding.tvValueB.getText().length());
            return false;
        });

        binding.tvValueG.setSettingsKeyboardDismissListener(() -> {
            try {
                int valueB = Integer.parseInt(binding.tvValueG.getText().toString());
                if (0 <= valueB && valueB <= 255) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("G", valueB);
                } else {
                    binding.tvValueG.setText(previousGValue);
                }
            } catch (Exception e) {
                binding.tvValueG.setText(previousGValue);
            }
        });

        binding.tvValueG.setOnLongClickListener(view -> {
            previousGValue = binding.tvValueG.getText().toString();
            binding.tvValueG.enableEditText(binding.tvValueG.getText().length());
            return false;
        });
    }


    private void initViews3() {
        int dialogWidth = 392 * windowWidth / viewWidth;
        setSeekBars(dialogWidth, 16 * windowWidth / 812);

        if (isMinimizeVersion) {
            binding.paletteViewTopContainer.setVisibility(GONE);
            binding.seekBarsPickerMinimizeContainer.setVisibility(VISIBLE);
        } else {
            binding.paletteViewTopContainer.setVisibility(VISIBLE);
            binding.seekBarsPickerMinimizeContainer.setVisibility(GONE);


            //setSeekBars(dialogWidth, 16 * windowWidth / 812);
        }

    }

    private void setSeekBars(float seekBarWidth, int seekBarPadding) {
        LinearGradient sHGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.parseColor("#e32ddc"), Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sHShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sHShape.getPaint().setShader(sHGradient);
        binding.seekBarH.setProgressDrawable((Drawable) sHShape);
        binding.seekBarH.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarH.setMax(360);
        binding.seekBarH.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarH.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("H", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = false;
                isColorChangeHSV = true;
                binding.seekBarH.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarH.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
            }
        });

        LinearGradient sSGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.GRAY, Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sSShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sSShape.getPaint().setShader(sSGradient);
        binding.seekBarS.setProgressDrawable((Drawable) sSShape);
        binding.seekBarS.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarS.setMax(100);
        binding.seekBarS.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarS.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("S", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = false;
                isColorChangeHSV = true;
                binding.seekBarS.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.seekBarS.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
                updateAllViews(currentSelectedColor);
            }
        });

        LinearGradient sLGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.WHITE},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sLShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sLShape.getPaint().setShader(sLGradient);
        binding.seekBarL.setProgressDrawable((Drawable) sLShape);
        binding.seekBarL.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarL.setMax(100);
        binding.seekBarL.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarL.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("L", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = false;
                isColorChangeHSV = true;
                binding.seekBarL.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarL.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

            }
        });

        LinearGradient sRGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sRShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sRShape.getPaint().setShader(sRGradient);
        binding.seekBarR.setProgressDrawable((Drawable) sRShape);
        binding.seekBarR.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarR.setMax(255);
        binding.seekBarR.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("R", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = true;
                isColorChangeHSV = false;
                binding.seekBarR.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarR.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

            }
        });

        LinearGradient sGGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.GREEN},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sGShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sGShape.getPaint().setShader(sGGradient);
        binding.seekBarG.setProgressDrawable((Drawable) sGShape);
        binding.seekBarG.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarG.setMax(255);
        binding.seekBarG.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("G", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = true;
                isColorChangeHSV = false;
                binding.seekBarG.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarG.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

            }
        });

        LinearGradient sBGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.BLUE},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sBShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sBShape.getPaint().setShader(sBGradient);
        binding.seekBarB.setProgressDrawable((Drawable) sBShape);
        binding.seekBarB.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarB.setMax(255);
        binding.seekBarB.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("B", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = true;
                isColorChangeHSV = false;
                binding.seekBarB.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.seekBarB.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

                updateAllViews(currentSelectedColor);
            }
        });

        // set seekbar thumbs..

    }

    private void onSeekbarChange(String h, int progress) {
        switch (h) {
            case "H":
                if (isColorChangeFromSeekbar && isColorChangeHSV) {
                    //colorPickerView.setIsPickFromCP(false);
                    currentSelectedColor = Color.HSVToColor(new float[]{progress, (float) binding.seekBarS.getProgress() / 100, (float) binding.seekBarL.getProgress() / 100});

                    int red = Color.red(currentSelectedColor);
                    int green = Color.green(currentSelectedColor);
                    int blue = Color.blue(currentSelectedColor);

                    binding.seekBarH.setProgress(progress);
                    previousHValue = progress + "'";
                    binding.tvValueH.setText(progress + "'");

                    binding.seekBarR.setProgress(red);
                    previousRValue = String.valueOf(red);
                    binding.tvValueR.setText(String.valueOf(red));
                    binding.seekBarG.setProgress(green);
                    previousGValue = String.valueOf(green);
                    binding.tvValueG.setText(String.valueOf(green));
                    binding.seekBarB.setProgress(blue);
                    previousBValue = String.valueOf(blue);
                    binding.tvValueB.setText(String.valueOf(blue));

                    binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "S":
                if (isColorChangeFromSeekbar && isColorChangeHSV) {
                    // colorPickerView.setIsPickFromCP(false);
                    currentSelectedColor = Color.HSVToColor(new float[]{(float) binding.seekBarH.getProgress(), (float) progress / 100, (float) binding.seekBarL.getProgress() / 100});

                    int red = Color.red(currentSelectedColor);
                    int green = Color.green(currentSelectedColor);
                    int blue = Color.blue(currentSelectedColor);

                    binding.seekBarS.setProgress(progress);
                    binding.tvValueS.setText(progress + "%");
                    previousSValue = progress + "%";

                    binding.seekBarR.setProgress(red);
                    previousRValue = String.valueOf(red);
                    binding.tvValueR.setText(String.valueOf(red));
                    binding.seekBarG.setProgress(green);
                    previousGValue = String.valueOf(green);
                    binding.tvValueG.setText(String.valueOf(green));
                    binding.seekBarB.setProgress(blue);
                    previousBValue = String.valueOf(blue);
                    binding.tvValueB.setText(String.valueOf(blue));

                    binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "L":
                if (isColorChangeFromSeekbar && isColorChangeHSV) {
                    // colorPickerView.setIsPickFromCP(false);
                    currentSelectedColor = Color.HSVToColor(new float[]{(float) binding.seekBarH.getProgress(), (float) binding.seekBarS.getProgress() / 100, (float) progress / 100});

                    int red = Color.red(currentSelectedColor);
                    int green = Color.green(currentSelectedColor);
                    int blue = Color.blue(currentSelectedColor);

                    binding.seekBarL.setProgress(progress);
                    binding.tvValueL.setText(progress + "%");
                    previousLValue = progress + "%";

                    binding.seekBarR.setProgress(red);
                    previousRValue = String.valueOf(red);
                    binding.tvValueR.setText(String.valueOf(red));
                    binding.seekBarG.setProgress(green);
                    previousGValue = String.valueOf(green);
                    binding.tvValueG.setText(String.valueOf(green));
                    binding.seekBarB.setProgress(blue);
                    previousBValue = String.valueOf(blue);
                    binding.tvValueB.setText(String.valueOf(blue));

                    binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "R":
                if (isColorChangeFromSeekbar && isColorChangeRGB) {
                    //colorPickerView.setIsPickFromCP(false);
                    float[] hsv = new float[3];
                    currentSelectedColor = Color.rgb(progress, binding.seekBarG.getProgress(), binding.seekBarB.getProgress());
                    Color.RGBToHSV(progress, binding.seekBarG.getProgress(), binding.seekBarB.getProgress(), hsv);

                    float colorH = hsv[0];
                    float colorS = (hsv[1] * 100);
                    float colorL = (hsv[2] * 100);

                    binding.seekBarR.setProgress(progress);
                    binding.tvValueR.setText("" + progress);
                    previousRValue = "" + progress;

                    binding.seekBarH.setProgress((int) colorH);
                    previousHValue = String.format("%.1f", colorH) + "'";
                    binding.tvValueH.setText(String.format("%.1f", colorH) + "'");
                    binding.seekBarS.setProgress((int) colorS);
                    previousSValue = String.format("%.1f", colorS) + "%";
                    binding.tvValueS.setText(String.format("%.1f", colorS) + "%");
                    binding.seekBarL.setProgress((int) colorL);
                    previousLValue = String.format("%.1f", colorL) + "%";
                    binding.tvValueL.setText(String.format("%.1f", colorL) + "%");

                    binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "G":
                if (isColorChangeFromSeekbar && isColorChangeRGB) {
                    //colorPickerView.setIsPickFromCP(false);
                    float[] hsv = new float[3];
                    currentSelectedColor = Color.rgb(binding.seekBarR.getProgress(), progress, binding.seekBarB.getProgress());
                    Color.RGBToHSV(binding.seekBarR.getProgress(), progress, binding.seekBarB.getProgress(), hsv);

                    float colorH = hsv[0];
                    float colorS = (hsv[1] * 100);
                    float colorL = (hsv[2] * 100);

                    binding.seekBarG.setProgress(progress);
                    binding.tvValueG.setText("" + progress);
                    previousGValue = "" + progress;

                    binding.seekBarH.setProgress((int) colorH);
                    previousHValue = String.format("%.1f", colorH) + "'";
                    binding.tvValueH.setText(String.format("%.1f", colorH) + "'");
                    binding.seekBarS.setProgress((int) colorS);
                    previousSValue = String.format("%.1f", colorS) + "%";
                    binding.tvValueS.setText(String.format("%.1f", colorS) + "%");
                    binding.seekBarL.setProgress((int) colorL);
                    previousLValue = String.format("%.1f", colorL) + "%";
                    binding.tvValueL.setText(String.format("%.1f", colorL) + "%");

                    binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "B":
                if (isColorChangeFromSeekbar && isColorChangeRGB) {
                    //colorPickerView.setIsPickFromCP(false);
                    float[] hsv = new float[3];
                    currentSelectedColor = Color.rgb(binding.seekBarR.getProgress(), binding.seekBarG.getProgress(), progress);
                    Color.RGBToHSV(binding.seekBarR.getProgress(), binding.seekBarG.getProgress(), progress, hsv);

                    float colorH = hsv[0];
                    float colorS = (hsv[1] * 100);
                    float colorL = (hsv[2] * 100);

                    binding.seekBarB.setProgress(progress);
                    binding.tvValueB.setText("" + progress);
                    previousBValue = "" + progress;

                    binding.seekBarH.setProgress((int) colorH);
                    previousHValue = String.format("%.1f", colorH) + "'";
                    binding.tvValueH.setText(String.format("%.1f", colorH) + "'");
                    binding.seekBarS.setProgress((int) colorS);
                    previousSValue = String.format("%.1f", colorS) + "%";
                    binding.tvValueS.setText(String.format("%.1f", colorS) + "%");
                    binding.seekBarL.setProgress((int) colorL);
                    previousLValue = String.format("%.1f", colorL) + "%";
                    binding.tvValueL.setText(String.format("%.1f", colorL) + "%");

                    binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
        }
    }

    private Drawable seekBarThumb(int thumbSize) {
        int thumbColor = context.getColor(R.color.themColor);
        GradientDrawable seekBarSelectorDrawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]
                {thumbColor, thumbColor});
        seekBarSelectorDrawable.setShape(GradientDrawable.OVAL);
        seekBarSelectorDrawable.setCornerRadius(thumbSize / 2f);
        seekBarSelectorDrawable.setSize(thumbSize, thumbSize);
        seekBarSelectorDrawable.setStroke(3, ContextCompat.getColor(context, R.color.colorWhite));

        return seekBarSelectorDrawable;
    }

    private void updateView3() {
        isColorChangeFromSeekbar = false;

        float[] hsv = new float[3];
        int red = Color.red(currentSelectedColor);
        int green = Color.green(currentSelectedColor);
        int blue = Color.blue(currentSelectedColor);
        Color.RGBToHSV(red, green, blue, hsv);
        float hsvH = hsv[0];
        float hsvS = (hsv[1] * 100);
        float hsvL = (hsv[2] * 100);

        binding.seekBarR.setProgress(red);
        previousRValue = String.valueOf(red);
        binding.tvValueR.setText(String.valueOf(red));
        binding.seekBarG.setProgress(green);
        previousGValue = String.valueOf(green);
        binding.tvValueG.setText(String.valueOf(green));
        binding.seekBarB.setProgress(blue);
        previousBValue = String.valueOf(blue);
        binding.tvValueB.setText(String.valueOf(blue));

        binding.seekBarH.setProgress((int) hsvH);
        previousHValue = String.format("%.1f", hsvH) + "'";
        binding.tvValueH.setText(String.format("%.1f", hsvH) + "'");
        binding.seekBarS.setProgress((int) hsvS);
        previousSValue = String.format("%.1f", hsvS) + "%";
        binding.tvValueS.setText(String.format("%.1f", hsvS) + "%");
        binding.seekBarL.setProgress((int) hsvL);
        previousLValue = String.format("%.1f", hsvL) + "%";
        binding.tvValueL.setText(String.format("%.1f", hsvL) + "%");

        binding.etHexadecimal.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
    }

    private void updateAllViews(int color) {
        onColorSquarePickerListener.colorPicked(color);

    }

    public void setCurrentSelectedColor(int color, boolean isMinimizeVersion) {
        this.currentSelectedColor = color;
        this.isMinimizeVersion = isMinimizeVersion;
        initViews3();
        updateView3();
        binding.seekBarsPickerMinimized.setCurrentSelectedColor(color);
    }

    public void setOnColorPickListener(OnColorPickedListener onColorSquarePickerListener) {
        this.onColorSquarePickerListener = onColorSquarePickerListener;
        binding.seekBarsPickerMinimized.setOnColorPickListener(color -> {
            this.onColorSquarePickerListener.colorPicked(color);
        });
    }
}
