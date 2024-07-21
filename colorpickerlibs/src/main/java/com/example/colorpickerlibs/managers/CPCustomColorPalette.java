package com.example.colorpickerlibs.managers;




import com.example.colorpickerlibs.constants.CPConstants;
import com.example.colorpickerlibs.models.ColorModel;

import java.util.ArrayList;

public class CPCustomColorPalette {
    //Instance
    private static CPCustomColorPalette instance;

    private ArrayList<ColorModel> colorPalletsList = new ArrayList<>();
    private ColorModel customEmptyColorPalette;

    private CPCustomColorPalette() {
    }

    public static CPCustomColorPalette getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new CPCustomColorPalette();
        return instance;
    }

    public void addCustomColorPalette() {
        colorPalletsList.add(
                new ColorModel("", "Untitled",
                        0, false, true, false, CPConstants.getEmptyColorPalette()));
    }

    public ArrayList<ColorModel> getCustomCPList(){
        return colorPalletsList;
    }

}
