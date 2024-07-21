package com.example.colorpickerlibs.utils.common;

import android.content.Context;

public class DDUtils {
    public static int dpToPixel(Context context, int dpValue){
        return (int) (dpValue * context.getResources().getDisplayMetrics().density);
    }

    public static float convertPixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }
}
