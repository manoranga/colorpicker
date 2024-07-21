package com.example.colorpickerlibs.utils.interfaces;

import com.example.colorpickerlibs.models.ColorModel;

public interface ColorPalletsListViewOperationListener {
    void onPaletteDeleted(ColorModel colorModel);
    void onPalletColorClick(int color);
    void onSyncClick(ColorModel colorModel, int colorPosition);

    void onPaletteDefaultClick(ColorModel colorModel);
}
