package com.example.colorpickerlibs.utils.interfaces;


import com.example.colorpickerlibs.models.ColorModel;

public interface CPThemeColorClickListener {
    void onColorClick(int color, ColorModel colorModel);
    void onColorLongClick(int color, ColorModel colorModel);
}
