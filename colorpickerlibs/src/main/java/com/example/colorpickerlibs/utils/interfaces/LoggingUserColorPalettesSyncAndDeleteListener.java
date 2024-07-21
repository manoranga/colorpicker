package com.example.colorpickerlibs.utils.interfaces;

import com.example.colorpickerlibs.models.ColorModel;

public interface LoggingUserColorPalettesSyncAndDeleteListener {
    void onColorPaletteSync(ColorModel selectedModel,int position);
    void onColorPaletteDelete(ColorModel selectedModel);
}
