package com.example.colorpickerlibs.utils.interfaces;


import com.example.colorpickerlibs.models.ColorModel;

public interface CPTextChangeListener {
    void onColorTitleChange(ColorModel colorModel);

    void onColorTitleClicked(int palettePosition);
}
