package com.example.colorpickerlibs.models;

public class GradientColorPickerModel {
    private int[] gradientColorArray;
    private float[] gradientRatiosArray;
    private boolean isLinearGradient = true;
    private int selectedButtonID ;//= GradientSliderView.GradientButtonID.NONE;
    private int selectedColor;

    public GradientColorPickerModel() {
    }

    public GradientColorPickerModel(int[] gradientColorArray, float[] gradientRatiosArray, boolean isLinearGradient) {
        this.gradientColorArray = gradientColorArray;
        this.gradientRatiosArray = gradientRatiosArray;
        this.isLinearGradient = isLinearGradient;
    }

    public int[] getGradientColorArray() {
        return gradientColorArray;
    }

    public void setGradientColorArray(int[] gradientColorArray) {
        this.gradientColorArray = gradientColorArray;
    }

    public float[] getGradientRatiosArray() {
        return gradientRatiosArray;
    }

    public void setGradientRatiosArray(float[] gradientRatiosArray) {
        this.gradientRatiosArray = gradientRatiosArray;
    }

    public boolean isLinearGradient() {
        return isLinearGradient;
    }

    public void setLinearGradient(boolean linearGradient) {
        isLinearGradient = linearGradient;
    }

    public int getSelectedButtonID() {
        return selectedButtonID;
    }

    public void setSelectedButtonID(int selectedButtonID) {
        this.selectedButtonID = selectedButtonID;
    }

    public int getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }
}