package com.pmr.colorpickerlibs.utils.interfaces;


import com.pmr.colorpickerlibs.models.ColorModel;

public interface CPThemeColorClickListener {
    void onColorClick(int color, ColorModel colorModel);
    void onColorLongClick(int color, ColorModel colorModel);
}
