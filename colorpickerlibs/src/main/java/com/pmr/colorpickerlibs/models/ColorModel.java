package com.pmr.colorpickerlibs.models;

import java.util.ArrayList;

public class ColorModel {
    private String title;
    private String paletteID;
    private int orderIndex;
    private int positionInThemeViewList;
    private boolean isDefault;
    private boolean isEditable;
    private boolean isSynced;
    private ArrayList<Integer> colorCodes;

    public ColorModel() {
    }

    public ColorModel(String title, String paletteID, int orderIndex, boolean isDefault, boolean isEditable, boolean isSynced, ArrayList<Integer> colorCodes) {
        this.title = title;
        this.paletteID = paletteID;
        this.orderIndex = orderIndex;
        this.isDefault = isDefault;
        this.isEditable = isEditable;
        this.isSynced = isSynced;
        this.colorCodes = colorCodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPaletteID() {
        return paletteID;
    }

    public void setPaletteID(String paletteID) {
        this.paletteID = paletteID;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public ArrayList<Integer> getColorCodes() {
        return colorCodes;
    }

    public void setColorCodes(ArrayList<Integer> colorCodes) {
        this.colorCodes = colorCodes;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public int getPositionInThemeViewList() {
        return positionInThemeViewList;
    }

    public void setPositionInThemeViewList(int positionInThemeViewList) {
        this.positionInThemeViewList = positionInThemeViewList;
    }
}
