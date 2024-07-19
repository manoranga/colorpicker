package com.pmr.colorpickerlibs.utils.interfaces;


import com.pmr.colorpickerlibs.models.ColorModel;

public interface CPTextChangeListener {
    void onColorTitleChange(ColorModel colorModel);

    void onColorTitleClicked(int palettePosition);
}
