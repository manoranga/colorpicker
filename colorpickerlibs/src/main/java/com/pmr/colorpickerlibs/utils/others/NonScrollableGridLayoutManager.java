package com.pmr.colorpickerlibs.utils.others;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class NonScrollableGridLayoutManager extends GridLayoutManager {
    private boolean isScrollEnabled = false;

    public NonScrollableGridLayoutManager(Context context, int gridCount) {
        super(context, gridCount);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}