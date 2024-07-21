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
import com.example.colorpickerlibs.databinding.SeekbarsPickerMinimizeBinding;
import com.example.colorpickerlibs.managers.SharedPref;
import com.example.colorpickerlibs.utils.common.DoubleTapEditTextColorPicker;
import com.example.colorpickerlibs.utils.interfaces.EditTextDoubleTapListener;
import com.example.colorpickerlibs.utils.interfaces.EditTextTapUpListener;
import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;


public class SeekbarViewMinimized extends FrameLayout {
    private SeekbarsPickerMinimizeBinding binding;
    private Context context;
    private int windowWidth,windowHeight;
    private int currentSelectedColor;
    private OnColorPickedListener onColorSquarePickerListener;
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
    private int viewHeight = 834;
    private int viewWidth = 1194;

    public SeekbarViewMinimized(@NonNull Context context) {
        super(context);
        initViews(context);
    }

    public SeekbarViewMinimized(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public SeekbarViewMinimized(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    public SeekbarViewMinimized(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context);
    }

    private void initViews(Context context){
        windowHeight = SharedPref.getInstance(context).getHeight(0);
        windowWidth = SharedPref.getInstance(context).getWidth(0);
        this.context = context;
        binding = SeekbarsPickerMinimizeBinding.inflate(LayoutInflater.from(context), this, true);
    }

    private void initSeekbars() {
        int textTitleWidth = 25 * windowWidth / viewWidth;
        int textValueCardWidth = 60 * windowWidth / viewWidth;
        int textValueCardHeight = 40 * windowHeight / viewHeight;

        binding.tvNameHMinimized.getLayoutParams().width = textTitleWidth;
        binding.tvNameSMinimized.getLayoutParams().width = textTitleWidth;
        binding.tvNameLMinimized.getLayoutParams().width = textTitleWidth;
        binding.tvNameRMinimized.getLayoutParams().width = textTitleWidth;
        binding.tvNameGMinimized.getLayoutParams().width = textTitleWidth;
        binding.tvNameBMinimized.getLayoutParams().width = textTitleWidth;

        binding.tvValueHContainerMinimized.getLayoutParams().width = textValueCardWidth;
        binding.tvValueHContainerMinimized.getLayoutParams().height = textValueCardHeight;

        binding.tvValueSContainerMinimized.getLayoutParams().width = textValueCardWidth;
        binding.tvValueSContainerMinimized.getLayoutParams().height = textValueCardHeight;

        binding.tvValueLContainerMinimized.getLayoutParams().width = textValueCardWidth;
        binding.tvValueLContainerMinimized.getLayoutParams().height = textValueCardHeight;

        binding.tvValueRContainerMinimized.getLayoutParams().width = textValueCardWidth;
        binding.tvValueRContainerMinimized.getLayoutParams().height = textValueCardHeight;

        binding.tvValueGContainerMinimized.getLayoutParams().width = textValueCardWidth;
        binding.tvValueGContainerMinimized.getLayoutParams().height = textValueCardHeight;

        binding.tvValueBContainerMinimized.getLayoutParams().width = textValueCardWidth;
        binding.tvValueBContainerMinimized.getLayoutParams().height = textValueCardHeight;


        binding.hexadecimalContainerMinimized.getLayoutParams().height = 63 * windowHeight / viewHeight;

        binding.etHexadecimalContainerMinimized.getLayoutParams().width = 87 * windowWidth / viewWidth;

        binding.etHexadecimalContainerMinimized.getLayoutParams().height = textValueCardHeight;


//        editable text  view 3
        binding.etHexadecimalMinimized.setClickableET(true);
        binding.etHexadecimalMinimized.setTextTapListener(new EditTextTapUpListener() {
            @Override
            public void onSingleTap() {
                previousHexValue = binding.etHexadecimalMinimized.getText().toString();
            }
        });
        binding.etHexadecimalMinimized.setTextChangeListener(() -> {
            try {
                currentSelectedColor = Color.parseColor("#" + binding.etHexadecimalMinimized.getText().toString());
                //colorPickerView.selectByHsv(currentSelectedColor, true);
                updateAllViews(currentSelectedColor);
                updateView3();
            } catch (Exception e) {
                binding.etHexadecimalMinimized.setText(previousHexValue);
            }
        });


        binding.tvValueHMinimized.setSettingsKeyboardDismissListener(new DoubleTapEditTextColorPicker.SettingsKeyboardDismissListener() {
            @Override
            public void onKeyboardDismissed() {
                try {
                    float valueH = Float.parseFloat(binding.tvValueHMinimized.getText().toString());
                    if (0.f <= valueH && valueH <= 360.f) {
                        isColorChangeFromSeekbar = true;
                        isColorChangeHSV = true;
                        onSeekbarChange("H", (int) valueH);

                    } else {
                        binding.tvValueHMinimized.setText(previousHValue);
                    }
                } catch (Exception e) {
                    binding.tvValueHMinimized.setText(previousHValue);
                }
            }
        });

        binding.tvValueHMinimized.setEditTextDoubleTapListener(new EditTextDoubleTapListener() {
            @Override
            public void onDoubleTap() {
                previousHValue = binding.tvValueHMinimized.getText().toString();
                binding.tvValueHMinimized.enableEditText(binding.tvValueHMinimized.getText().length());
            }
        });

        binding.tvValueSMinimized.setSettingsKeyboardDismissListener(new DoubleTapEditTextColorPicker.SettingsKeyboardDismissListener() {
            @Override
            public void onKeyboardDismissed() {
                try {
                    float valueS = Float.parseFloat(binding.tvValueSMinimized.getText().toString());
                    if (0.f <= valueS && valueS <= 100.f) {
                        isColorChangeFromSeekbar = true;
                        isColorChangeHSV = true;
                        onSeekbarChange("S", (int) valueS);
                    } else {
                        binding.tvValueSMinimized.setText(previousSValue);
                    }
                } catch (Exception e) {
                    binding.tvValueSMinimized.setText(previousSValue);
                }
            }
        });

        binding.tvValueSMinimized.setOnLongClickListener(view -> {
            previousSValue = binding.tvValueSMinimized.getText().toString();
            binding.tvValueSMinimized.enableEditText(binding.tvValueSMinimized.getText().length());
            return false;
        });


        binding.tvValueLMinimized.setSettingsKeyboardDismissListener(() -> {
            try {
                float valueL = Float.parseFloat(binding.tvValueLMinimized.getText().toString());
                if (0.f <= valueL && valueL <= 100.f) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("L", (int) valueL);
                } else {
                    binding.tvValueLMinimized.setText(previousLValue);
                }
            } catch (Exception e) {
                binding.tvValueLMinimized.setText(previousLValue);
            }
        });

        binding.tvValueLMinimized.setOnLongClickListener(view -> {
            previousLValue = binding.tvValueLMinimized.getText().toString();
            binding.tvValueLMinimized.enableEditText(binding.tvValueLMinimized.getText().length());
            return false;
        });

        binding.tvValueRMinimized.setSettingsKeyboardDismissListener(() -> {
            try {
                int valueR = Integer.parseInt(binding.tvValueRMinimized.getText().toString());
                if (0 <= valueR && valueR <= 255) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("R", valueR);
                } else {
                    binding.tvValueRMinimized.setText(previousRValue);
                }
            } catch (Exception e) {
                binding.tvValueRMinimized.setText(previousRValue);
            }
        });

        binding.tvValueRMinimized.setOnLongClickListener(view -> {
            previousRValue = binding.tvValueRMinimized.getText().toString();
            binding.tvValueRMinimized.enableEditText(binding.tvValueRMinimized.getText().length());
            return false;
        });

        binding.tvValueBMinimized.setSettingsKeyboardDismissListener(() -> {
            try {
                int valueB = Integer.parseInt(binding.tvValueBMinimized.getText().toString());
                if (0 <= valueB && valueB <= 255) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("B", valueB);
                } else {
                    binding.tvValueBMinimized.setText(previousBValue);
                }
            } catch (Exception e) {
                binding.tvValueBMinimized.setText(previousBValue);
            }
        });

        binding.tvValueBMinimized.setOnLongClickListener(view -> {
            previousBValue = binding.tvValueBMinimized.getText().toString();
            binding.tvValueBMinimized.enableEditText(binding.tvValueBMinimized.getText().length());
            return false;
        });

        binding.tvValueGMinimized.setSettingsKeyboardDismissListener(() -> {
            try {
                int valueB = Integer.parseInt(binding.tvValueGMinimized.getText().toString());
                if (0 <= valueB && valueB <= 255) {
                    isColorChangeFromSeekbar = true;
                    isColorChangeHSV = true;
                    onSeekbarChange("G", valueB);
                } else {
                    binding.tvValueGMinimized.setText(previousGValue);
                }
            } catch (Exception e) {
                binding.tvValueGMinimized.setText(previousGValue);
            }
        });

        binding.tvValueGMinimized.setOnLongClickListener(view -> {
            previousGValue = binding.tvValueGMinimized.getText().toString();
            binding.tvValueGMinimized.enableEditText(binding.tvValueGMinimized.getText().length());
            return false;
        });
    }


    private void initViews3() {

            int seekBarsWidth = 198 * windowWidth / 1194;
            int view3topButtonWidth = 219 * windowWidth / 1194;
            int view3topButtonHeight = 32 * windowHeight / 834;
            int textTitleWidth = 13 * windowWidth / 1194;

            binding.tvValueRMinimized.getLayoutParams().width = 3 * textTitleWidth;
            binding.tvValueGMinimized.getLayoutParams().width = 3 * textTitleWidth;
            binding.tvValueBMinimized.getLayoutParams().width = 3 * textTitleWidth;
            binding.tvValueHMinimized.getLayoutParams().width = 3 * textTitleWidth;
            binding.tvValueSMinimized.getLayoutParams().width = 3 * textTitleWidth;
            binding.tvValueLMinimized.getLayoutParams().width = 3 * textTitleWidth;

            setSeekBars(seekBarsWidth, 8 * windowWidth / 812);

            binding.view3BtnContainerMinimized.getLayoutParams().width = view3topButtonWidth;
            binding.view3BtnContainerMinimized.getLayoutParams().height = view3topButtonHeight;

            binding.btnHSBMinimized.setBackground(context.getDrawable(R.drawable.all_round_conner_background_color_picker_white_5dp));
            binding.btnRGBMinimized.setBackground(context.getDrawable(R.drawable.all_round_conner_background_color_picker));
            binding.btnTextHSBMinimized.setTextColor(context.getColor(R.color.selected_blue));
            binding.btnTextRGBMinimized.setTextColor(context.getColor(R.color.black));

            binding.HSLValueContainerMinimized.setVisibility(VISIBLE);
            binding.RGBValueContainerMinimized.setVisibility(GONE);


            binding.tvNameHMinimized.getLayoutParams().width = textTitleWidth;
            binding.tvNameSMinimized.getLayoutParams().width = textTitleWidth;
            binding.tvNameLMinimized.getLayoutParams().width = textTitleWidth;
            binding.tvNameRMinimized.getLayoutParams().width = textTitleWidth;
            binding.tvNameGMinimized.getLayoutParams().width = textTitleWidth;
            binding.tvNameBMinimized.getLayoutParams().width = textTitleWidth;

            binding.btnHSBMinimized.setOnClickListener(view -> {
                binding.btnHSBMinimized.setBackground(context.getDrawable(R.drawable.all_round_conner_background_color_picker_white_5dp));
                binding.btnRGBMinimized.setBackground(context.getDrawable(R.drawable.all_round_conner_background_color_picker_5dp));
                binding.HSLValueContainerMinimized.setVisibility(VISIBLE);
                binding.RGBValueContainerMinimized.setVisibility(GONE);
                binding.btnTextHSBMinimized.setTextColor(context.getColor(R.color.selected_blue));
                binding.btnTextRGBMinimized.setTextColor(context.getColor(R.color.black));
            });

            binding.btnRGBMinimized.setOnClickListener(view -> {
                binding.btnRGBMinimized.setBackground(context.getDrawable(R.drawable.all_round_conner_background_color_picker_white_5dp));
                binding.btnHSBMinimized.setBackground(context.getDrawable(R.drawable.all_round_conner_background_color_picker_5dp));
                binding.HSLValueContainerMinimized.setVisibility(GONE);
                binding.RGBValueContainerMinimized.setVisibility(VISIBLE);
                binding.btnTextHSBMinimized.setTextColor(context.getColor(R.color.black));
                binding.btnTextRGBMinimized.setTextColor(context.getColor(R.color.selected_blue));
            });

    }

    private void setSeekBars(float seekBarWidth, int seekBarPadding) {
        LinearGradient sHGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.parseColor("#e32ddc"), Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sHShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sHShape.getPaint().setShader(sHGradient);
        binding.seekBarHMinimized.setProgressDrawable((Drawable) sHShape);
        binding.seekBarHMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarHMinimized.setMax(360);
        binding.seekBarHMinimized.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarHMinimized.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("H", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = false;
                isColorChangeHSV = true;
                binding.seekBarHMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarHMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
            }
        });

        LinearGradient sSGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.GRAY, Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sSShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sSShape.getPaint().setShader(sSGradient);
        binding.seekBarSMinimized.setProgressDrawable((Drawable) sSShape);
        binding.seekBarSMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarSMinimized.setMax(100);
        binding.seekBarSMinimized.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarSMinimized.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("S", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = false;
                isColorChangeHSV = true;
                binding.seekBarSMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.seekBarSMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
                updateAllViews(currentSelectedColor);
            }
        });

        LinearGradient sLGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.WHITE},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sLShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sLShape.getPaint().setShader(sLGradient);
        binding.seekBarLMinimized.setProgressDrawable((Drawable) sLShape);
        binding.seekBarLMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarLMinimized.setMax(100);
        binding.seekBarLMinimized.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarLMinimized.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("L", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = false;
                isColorChangeHSV = true;
                binding.seekBarLMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarLMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

            }
        });

        LinearGradient sRGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.RED},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sRShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sRShape.getPaint().setShader(sRGradient);
        binding.seekBarRMinimized.setProgressDrawable((Drawable) sRShape);
        binding.seekBarRMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarRMinimized.setMax(255);
        binding.seekBarRMinimized.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarRMinimized.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("R", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = true;
                isColorChangeHSV = false;
                binding.seekBarRMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarRMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

            }
        });

        LinearGradient sGGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.GREEN},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sGShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sGShape.getPaint().setShader(sGGradient);
        binding.seekBarGMinimized.setProgressDrawable((Drawable) sGShape);
        binding.seekBarGMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarGMinimized.setMax(255);
        binding.seekBarGMinimized.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarGMinimized.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("G", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = true;
                isColorChangeHSV = false;
                binding.seekBarGMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                updateAllViews(currentSelectedColor);
                binding.seekBarGMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

            }
        });

        LinearGradient sBGradient = new LinearGradient(0.f, 0.f, seekBarWidth, 0.0f,
                new int[]{Color.BLACK, Color.BLUE},
                null, Shader.TileMode.CLAMP);
        ShapeDrawable sBShape = new ShapeDrawable(new RoundRectShape(new float[]{40, 40, 40, 40, 40, 40, 40, 40}, null, null));
        sBShape.getPaint().setShader(sBGradient);
        binding.seekBarBMinimized.setProgressDrawable((Drawable) sBShape);
        binding.seekBarBMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));
        binding.seekBarBMinimized.setMax(255);
        binding.seekBarBMinimized.setPadding(seekBarPadding, 0, seekBarPadding, 0);
        binding.seekBarBMinimized.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                onSeekbarChange("B", i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                isColorChangeFromSeekbar = true;
                isColorChangeRGB = true;
                isColorChangeHSV = false;
                binding.seekBarBMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab_start_tacking)));

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                binding.seekBarBMinimized.setThumb(seekBarThumb((int) getResources().getDimension(R.dimen.cp_seekbar_thumb_size_tab)));

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
                    currentSelectedColor = Color.HSVToColor(new float[]{progress, (float) binding.seekBarSMinimized.getProgress() / 100, (float) binding.seekBarLMinimized.getProgress() / 100});

                    int red = Color.red(currentSelectedColor);
                    int green = Color.green(currentSelectedColor);
                    int blue = Color.blue(currentSelectedColor);

                    binding.seekBarHMinimized.setProgress(progress);
                    previousHValue = progress + "'";
                    binding.tvValueHMinimized.setText(progress + "'");

                    binding.seekBarRMinimized.setProgress(red);
                    previousRValue = String.valueOf(red);
                    binding.tvValueRMinimized.setText(String.valueOf(red));
                    binding.seekBarGMinimized.setProgress(green);
                    previousGValue = String.valueOf(green);
                    binding.tvValueGMinimized.setText(String.valueOf(green));
                    binding.seekBarBMinimized.setProgress(blue);
                    previousBValue = String.valueOf(blue);
                    binding.tvValueBMinimized.setText(String.valueOf(blue));

                    binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "S":
                if (isColorChangeFromSeekbar && isColorChangeHSV) {
                    // colorPickerView.setIsPickFromCP(false);
                    currentSelectedColor = Color.HSVToColor(new float[]{(float) binding.seekBarHMinimized.getProgress(), (float) progress / 100, (float) binding.seekBarLMinimized.getProgress() / 100});

                    int red = Color.red(currentSelectedColor);
                    int green = Color.green(currentSelectedColor);
                    int blue = Color.blue(currentSelectedColor);

                    binding.seekBarSMinimized.setProgress(progress);
                    binding.tvValueSMinimized.setText(progress + "%");
                    previousSValue = progress + "%";

                    binding.seekBarRMinimized.setProgress(red);
                    previousRValue = String.valueOf(red);
                    binding.tvValueRMinimized.setText(String.valueOf(red));
                    binding.seekBarGMinimized.setProgress(green);
                    previousGValue = String.valueOf(green);
                    binding.tvValueGMinimized.setText(String.valueOf(green));
                    binding.seekBarBMinimized.setProgress(blue);
                    previousBValue = String.valueOf(blue);
                    binding.tvValueBMinimized.setText(String.valueOf(blue));

                    binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "L":
                if (isColorChangeFromSeekbar && isColorChangeHSV) {
                    // colorPickerView.setIsPickFromCP(false);
                    currentSelectedColor = Color.HSVToColor(new float[]{(float) binding.seekBarHMinimized.getProgress(), (float) binding.seekBarSMinimized.getProgress() / 100, (float) progress / 100});

                    int red = Color.red(currentSelectedColor);
                    int green = Color.green(currentSelectedColor);
                    int blue = Color.blue(currentSelectedColor);

                    binding.seekBarLMinimized.setProgress(progress);
                    binding.tvValueLMinimized.setText(progress + "%");
                    previousLValue = progress + "%";

                    binding.seekBarRMinimized.setProgress(red);
                    previousRValue = String.valueOf(red);
                    binding.tvValueRMinimized.setText(String.valueOf(red));
                    binding.seekBarGMinimized.setProgress(green);
                    previousGValue = String.valueOf(green);
                    binding.tvValueGMinimized.setText(String.valueOf(green));
                    binding.seekBarBMinimized.setProgress(blue);
                    previousBValue = String.valueOf(blue);
                    binding.tvValueBMinimized.setText(String.valueOf(blue));

                    binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "R":
                if (isColorChangeFromSeekbar && isColorChangeRGB) {
                    //colorPickerView.setIsPickFromCP(false);
                    float[] hsv = new float[3];
                    currentSelectedColor = Color.rgb(progress, binding.seekBarGMinimized.getProgress(), binding.seekBarBMinimized.getProgress());
                    Color.RGBToHSV(progress, binding.seekBarGMinimized.getProgress(), binding.seekBarBMinimized.getProgress(), hsv);

                    float colorH = hsv[0];
                    float colorS = (hsv[1] * 100);
                    float colorL = (hsv[2] * 100);

                    binding.seekBarRMinimized.setProgress(progress);
                    binding.tvValueRMinimized.setText("" + progress);
                    previousRValue = "" + progress;

                    binding.seekBarHMinimized.setProgress((int) colorH);
                    previousHValue = String.format("%.1f", colorH) + "'";
                    binding.tvValueHMinimized.setText(String.format("%.1f", colorH) + "'");
                    binding.seekBarSMinimized.setProgress((int) colorS);
                    previousSValue = String.format("%.1f", colorS) + "%";
                    binding.tvValueSMinimized.setText(String.format("%.1f", colorS) + "%");
                    binding.seekBarLMinimized.setProgress((int) colorL);
                    previousLValue = String.format("%.1f", colorL) + "%";
                    binding.tvValueLMinimized.setText(String.format("%.1f", colorL) + "%");

                    binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "G":
                if (isColorChangeFromSeekbar && isColorChangeRGB) {
                    //colorPickerView.setIsPickFromCP(false);
                    float[] hsv = new float[3];
                    currentSelectedColor = Color.rgb(binding.seekBarRMinimized.getProgress(), progress, binding.seekBarBMinimized.getProgress());
                    Color.RGBToHSV(binding.seekBarRMinimized.getProgress(), progress, binding.seekBarBMinimized.getProgress(), hsv);

                    float colorH = hsv[0];
                    float colorS = (hsv[1] * 100);
                    float colorL = (hsv[2] * 100);

                    binding.seekBarGMinimized.setProgress(progress);
                    binding.tvValueGMinimized.setText("" + progress);
                    previousGValue = "" + progress;

                    binding.seekBarHMinimized.setProgress((int) colorH);
                    previousHValue = String.format("%.1f", colorH) + "'";
                    binding.tvValueHMinimized.setText(String.format("%.1f", colorH) + "'");
                    binding.seekBarSMinimized.setProgress((int) colorS);
                    previousSValue = String.format("%.1f", colorS) + "%";
                    binding.tvValueSMinimized.setText(String.format("%.1f", colorS) + "%");
                    binding.seekBarLMinimized.setProgress((int) colorL);
                    previousLValue = String.format("%.1f", colorL) + "%";
                    binding.tvValueLMinimized.setText(String.format("%.1f", colorL) + "%");

                    binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
                    updateAllViews(currentSelectedColor);
                }
                break;
            case "B":
                if (isColorChangeFromSeekbar && isColorChangeRGB) {
                    //colorPickerView.setIsPickFromCP(false);
                    float[] hsv = new float[3];
                    currentSelectedColor = Color.rgb(binding.seekBarRMinimized.getProgress(), binding.seekBarGMinimized.getProgress(), progress);
                    Color.RGBToHSV(binding.seekBarRMinimized.getProgress(), binding.seekBarGMinimized.getProgress(), progress, hsv);

                    float colorH = hsv[0];
                    float colorS = (hsv[1] * 100);
                    float colorL = (hsv[2] * 100);

                    binding.seekBarBMinimized.setProgress(progress);
                    binding.tvValueBMinimized.setText("" + progress);
                    previousBValue = "" + progress;

                    binding.seekBarHMinimized.setProgress((int) colorH);
                    previousHValue = String.format("%.1f", colorH) + "'";
                    binding.tvValueHMinimized.setText(String.format("%.1f", colorH) + "'");
                    binding.seekBarSMinimized.setProgress((int) colorS);
                    previousSValue = String.format("%.1f", colorS) + "%";
                    binding.tvValueSMinimized.setText(String.format("%.1f", colorS) + "%");
                    binding.seekBarLMinimized.setProgress((int) colorL);
                    previousLValue = String.format("%.1f", colorL) + "%";
                    binding.tvValueLMinimized.setText(String.format("%.1f", colorL) + "%");

                    binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
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

        binding.seekBarRMinimized.setProgress(red);
        previousRValue = String.valueOf(red);
        binding.tvValueRMinimized.setText(String.valueOf(red));
        binding.seekBarGMinimized.setProgress(green);
        previousGValue = String.valueOf(green);
        binding.tvValueGMinimized.setText(String.valueOf(green));
        binding.seekBarBMinimized.setProgress(blue);
        previousBValue = String.valueOf(blue);
        binding.tvValueBMinimized.setText(String.valueOf(blue));

        binding.seekBarHMinimized.setProgress((int) hsvH);
        previousHValue = String.format("%.1f", hsvH) + "'";
        binding.tvValueHMinimized.setText(String.format("%.1f", hsvH) + "'");
        binding.seekBarSMinimized.setProgress((int) hsvS);
        previousSValue = String.format("%.1f", hsvS) + "%";
        binding.tvValueSMinimized.setText(String.format("%.1f", hsvS) + "%");
        binding.seekBarLMinimized.setProgress((int) hsvL);
        previousLValue = String.format("%.1f", hsvL) + "%";
        binding.tvValueLMinimized.setText(String.format("%.1f", hsvL) + "%");

        binding.etHexadecimalMinimized.setText(String.format("%06X", (0xFFFFFF & currentSelectedColor)));
    }

    private void updateAllViews(int color) {
        onColorSquarePickerListener.colorPicked(color);

    }

    public void setCurrentSelectedColor(int color) {
        this.currentSelectedColor = color;
        initViews3();
        updateView3();
    }

    public void setOnColorPickListener(OnColorPickedListener onColorSquarePickerListener) {
        this.onColorSquarePickerListener = onColorSquarePickerListener;
    }
}
