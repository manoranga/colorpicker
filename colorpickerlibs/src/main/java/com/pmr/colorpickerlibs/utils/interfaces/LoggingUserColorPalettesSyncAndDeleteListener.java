package com.pmr.colorpickerlibs.utils.interfaces;

import com.pmr.colorpickerlibs.models.ColorModel;

public interface LoggingUserColorPalettesSyncAndDeleteListener {
    void onColorPaletteSync(ColorModel selectedModel, int position);
    void onColorPaletteDelete(ColorModel selectedModel);
}
