package com.pmr.colorpickerlibs.managers;

import android.content.Context;
import android.graphics.Color;


import com.pmr.colorpickerlibs.R;
import com.pmr.colorpickerlibs.constants.CPConstants;
import com.pmr.colorpickerlibs.models.ColorModel;

import java.util.ArrayList;
import java.util.List;

public class CPManager {
    //Instance
    private static CPManager instance;
    private int defaultPalletId = 0;
    private int defaultPalletCount = 12;
    private int selectedPalletId = 11;
    private int currentColor;
    private int previousColor;
    private int deskID;
    private Context context;
    private ArrayList<ColorModel> colorPalletsList = new ArrayList<>();

    private CPManager(Context context) {
        this.context = context;
        currentColor = Color.parseColor("#FFFFFF");
        previousColor = Color.parseColor("#FFFFFF");
    }

    public static CPManager getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        instance = new CPManager(context);
        return instance;
    }

    public void setSelectedColorPalette() {
        if (SharedPref.getInstance(context).getSelectedColorPalette() < getColorPalletsList().size()) {
            selectedPalletId = SharedPref.getInstance(context).getSelectedColorPalette();
        }
    }

    public void setColor(int color) {
        currentColor = color;
    }

    public void setPreviousColor(int color) {
        previousColor = color;
    }

    public void removeTheme(int removedPosition) {
        getColorPalletsList().remove(removedPosition);

        if (selectedPalletId > removedPosition) {
            selectedPalletId = selectedPalletId - 1;

            SharedPref.getInstance(context).putSelectedColorPalette(selectedPalletId);
        }
    }

    public void updateColorPaletteFirstTime() {
//        AsyncTask.execute(() -> {
        try {
            List<ColorModel> paletteArray = SharedPref.getInstance(context).readPaletteArray();

            if (paletteArray.size() > 100) {
                SharedPref.getInstance(context).writePaletteArray(new ArrayList<>());
                paletteArray = new ArrayList<>();
            }

            if (paletteArray.size() >= 12) {
                colorPalletsList = new ArrayList<>();
                colorPalletsList.addAll(paletteArray);
                colorPalletsList.get(0).setColorCodes(SharedPref.getInstance(context).readRecentPalette());

                boolean hasDoodlePalette = false;
                for (ColorModel colorModel : colorPalletsList) {
                    if (colorModel.getPaletteID().equals("DoodleDesk Colors")) {
                        hasDoodlePalette = true;
                        break;
                    }
                }

                for (int i = 1; i < 12; i++) {
                    colorPalletsList.get(i).setDefault(true);
                }

                if (!hasDoodlePalette) {
                    colorPalletsList.add(12, new ColorModel("DoodleDesk Colors", "DoodleDesk Colors",
                            0, true, false, false, CPConstants.getDoodleDeskColors()));
                }

                saveColorPalletsList();
            }
        } catch (Exception ignore) {

        }
//        });
    }

    public ArrayList<ColorModel> getColorPalletsList() {
        if (SharedPref.getInstance(context).readPaletteArray().size() > colorPalletsList.size()) {
            colorPalletsList = new ArrayList<>();
            colorPalletsList.addAll(SharedPref.getInstance(context).readPaletteArray());
            colorPalletsList.get(0).setColorCodes(SharedPref.getInstance(context).readRecentPalette());

//            boolean hasDoodlePalette = false;
//            for (ColorModel colorModel : colorPalletsList){
//                if (colorModel.getPaletteID().equals("DoodleDesk Colors")){
//                    hasDoodlePalette = true;
//                    break;
//                }
//            }
//            if (!hasDoodlePalette){
//                colorPalletsList.add(12, new ColorModel("DoodleDesk Colors", "DoodleDesk Colors",
//                        0, false, true, false, CPConstants.getDoodleDeskColors()));
//            }

            return SharedPref.getInstance(context).readPaletteArray();
        } else if (!colorPalletsList.isEmpty()) {
            colorPalletsList.get(0).setColorCodes(SharedPref.getInstance(context).readRecentPalette());
            return colorPalletsList;
        }

        colorPalletsList = new ArrayList<>();

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.RECENT_COLORS), "Recent Colors",
                        0, false, false, false, SharedPref.getInstance(context).readRecentPalette()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.DARK_AND_EARTHY), "Dark & Earthy",
                        0, true, false, false, CPConstants.getCpDarkEarthy()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.SKIN), "Skin",
                        0, true, false, false, CPConstants.getCpSkin()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.WARM), "Warm",
                        0, true, false, false, CPConstants.getCpWarm()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.COOL_BLUE), "Cool Blue",
                        0, true, false, false, CPConstants.getCpCoolBlue()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.REFRESHING), "Refreshing",
                        0, true, false, false, CPConstants.getCpRefreshing()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.WATERY_BLUE), "Watery Blue-Greens",
                        0, true, false, false, CPConstants.getCpWaterBlueGreens()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.OUTDOORS_NATURE), "Outdoors & Nature",
                        0, true, false, false, CPConstants.getCpOutdoorNature()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.SHADES_OF_PURPLE), "Shades of Purple",
                        0, true, false, false, CPConstants.getCpShadesOfPurple()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.SHADES_OF_GREY), "Shades of Grey",
                        0, true, false, false, CPConstants.getCpShadesOfGrey()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.BRIGHT_ORANGE_COLOR), "Bright Orange Color",
                        0, true, false, false, CPConstants.getCpBrightOrange()));

        colorPalletsList.add(
                new ColorModel(context.getString(R.string.USA_COLOR), "USA Color",
                        0, true, false, false, CPConstants.getCpUSA()));

        return colorPalletsList;
    }

    public void addEmptyPalette() {
        colorPalletsList.add(
                new ColorModel(context.getString(R.string.UNTITLED_PALETTE), "" + colorPalletsList.size(),
                        0, false, true, false, new ArrayList<>(CPConstants.getEmptyColorPalette())));
    }

    public void saveColorPalletsList() {
        SharedPref.getInstance(context).writePaletteArray(colorPalletsList);
    }

    public void saveRecentPalletList(ArrayList<Integer> recentPalette) {
        SharedPref.getInstance(context).writeRecentPalette(recentPalette);
    }

    public int getDefaultPalletCount() {
        return defaultPalletCount;
    }

    public void setDefaultPalletCount(int defaultPalletCount) {
        this.defaultPalletCount = defaultPalletCount;
    }

    public int getSelectedPalletId() {
        return SharedPref.getInstance(context).getSelectedColorPalette();
    }

    public void setSelectedPalletId(int selectedPalletId) {
        SharedPref.getInstance(context).putSelectedColorPalette(selectedPalletId);
        this.selectedPalletId = selectedPalletId;
    }

    public ColorModel getSelectedColorModelColorCodes() {
        return getColorPalletsList().get(selectedPalletId);
    }

    public int getHue(int red, int green, int blue) {

        float min = Math.min(Math.min(red, green), blue);
        float max = Math.max(Math.max(red, green), blue);
        if (min == max) {
            return 0;
        }

        float hue = 0f;
        if (max == red) {
            hue = (green - blue) / (max - min);

        } else if (max == green) {
            hue = 2f + (blue - red) / (max - min);

        } else {
            hue = 4f + (red - green) / (max - min);
        }

        hue = hue * 60;
        if (hue < 0) hue = hue + 360;

        return Math.round(hue);
    }
}
