package com.example.colorpickerlibs.managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


import com.example.colorpickerlibs.constants.CPConstants;
import com.example.colorpickerlibs.models.ColorModel;
import com.example.colorpickerlibs.models.GradientColorPickerModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SharedPref {

    private static SharedPref instance;
    public final String SUBSCRIPTION = "subscription_status";
    public final String LOCAL_SUBSCRIPTION = "local_subscription_status";
    private final String IS_AMAZON_UNLOCKED = "is_amazon_unlocked";
    private final String HEIGHT = "device_height";
    private final String WIDTH = "device_width";
    private final String FIRST_LAUNCH = "first_launch";
    private final String FIRST_LAUNCH_ONBOARD = "first_launch_onboard";
    private final String CP_ARRAY = "cp_array";
    private final String GRADIENT_CP_ARRAY = "gradient_cp_array";
    private final String CP_RECENT_ARRAY = "cp_recent_array";
    private final String MY_PALETTE = "MY_PALETTE";
    private final String BACK_TO_MAIN_COUNT = "back_to_main";
    private final String ARTWORK_COUNTER = "projCounter";
    private final String IS_RATED = "is_rated";
    private final String PREVIOUS_MILLI = "previous_milliseconds";
    private final String EVENT_LOG_NAME = "event_log_name";
    private final String PRODUCT_DURATION_NAME = "product_duration_name";
    private final String PRODUCT_CURRENCY_CODE = "product_currency_code";
    private final String SETTINGS_WATERMARK = "settings_watermark";
    private final String SETTINGS_AUTO_HIDE_WATERMARK = "settings_auto_hide_ui";
    private final String SETTINGS_NOTIFICATION = "settings_notifications";
    private final String SETTINGS_QUICK_SHAPE = "settings_quick_shape";
    private final String SETTINGS_REMEMBER_TOOL_COLOR = "settings_remember_tool_color";
    private final String EXPORT_WATERMARK = "export_watermark";
    private final String SELECTED_COLOR_PALETTE = "default_color_palette";
    private final String SELECTED_DOODLE_COLOR_PALETTE = "default_doodle_color_palette";
    private final String IS_NEW_DAILY_CONTENT_AVAILABLE = "is_new_daily_content_available";
    private final String PREVIOUS_DAILY_CONTENT_DATE = "previous_daily_content_date";
    private final String RATE_VIEW_SHOW_COUNT = "rate_view_show_count";
    private final String DEFAULT_LOCALIZATION_STRING = "default_localization_string";
    private final String CONFIG_DATA = "config_data";
    private final String EXPORT_FORMAT = "export_format";
    private final Context context;
    private SharedPreferences mSharedPref;
    private SharedPreferences artworkPreference;

    public SharedPref(Context context) {
        this.context = context;
        init(context);
    }

    public static SharedPref getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPref(context);
        }
        return instance;
    }

    private void init(Context context) {
        if (mSharedPref == null) {
            if (context.getPackageName() != null) {
                mSharedPref = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            } else {
                mSharedPref = context.getSharedPreferences("com.axis.drawingdesk.v3", Activity.MODE_PRIVATE);
            }
        }

        if (artworkPreference == null)
            artworkPreference = context.getSharedPreferences("artworksManager", Context.MODE_PRIVATE);
    }

    public String read(String key, String defValue) {
        return mSharedPref.getString(key, defValue);
    }

    public void putHeight(int height) {
        if (height != 0) {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putInt(HEIGHT, height);
            editor.apply();
        }
    }

    public void putWidth(int width) {
        if (width != 0) {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.putInt(WIDTH, width);
            editor.apply();
        }
    }

    public int getHeight(int defaultHeight) {
        return mSharedPref.getInt(HEIGHT, 1600);
    }

    public int getWidth(int defaultWidth) {
        return mSharedPref.getInt(WIDTH, 2560);
    }

    public void writePaletteArray(List<ColorModel> colorModel) {
        if (colorModel != null && colorModel.size() > 0) {
            SharedPreferences.Editor prefsEditor = mSharedPref.edit();
            String stringArray = new Gson().toJson(colorModel);
            prefsEditor.putString(CP_ARRAY, stringArray);
            prefsEditor.apply();
        }
    }

    public ArrayList<ColorModel> readPaletteArray() {
        Type type = new TypeToken<ArrayList<ColorModel>>() {
        }.getType();
        ArrayList<ColorModel> array = new Gson().fromJson(mSharedPref.getString(CP_ARRAY, new Gson().toJson(new ArrayList<>())), type);
        if (array == null) {
            return new ArrayList<>();
        } else {
            if (!array.isEmpty()) {
                return array;
            } else {
                return new ArrayList<>();
            }
        }
    }

    private ArrayList<ColorModel> getEmptyArray() {
        return new ArrayList<>();
    }

    public void writeRecentPalette(List<Integer> colorModel) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        String stringArray = new Gson().toJson(colorModel);
        prefsEditor.putString(CP_RECENT_ARRAY, stringArray);
        prefsEditor.apply();
    }

    public ArrayList<Integer> readRecentPalette() {
        Type type = new TypeToken<ArrayList<Integer>>() {
        }.getType();
        ArrayList<Integer> array = new Gson().fromJson(mSharedPref.getString(CP_RECENT_ARRAY, new Gson().toJson(getRecentEmptyArray())), type);
        assert array != null;
        if (array.size() == 0) {
            return getRecentEmptyArray();
        } else {
            return new Gson().fromJson(mSharedPref.getString(CP_RECENT_ARRAY, new Gson().toJson(getRecentEmptyArray())), type);
        }
    }

    private ArrayList<Integer> getRecentEmptyArray() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.addAll(CPConstants.getEmptyColorPalette());

        return arrayList;
    }

    public void putFirstLaunch(boolean isFirstLaunch) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(FIRST_LAUNCH, isFirstLaunch);
        editor.apply();
    }

    public boolean getFirstLaunch() {
        return mSharedPref.getBoolean(FIRST_LAUNCH, true);
    }

    public void putFirstLaunchBoard(boolean isFirstLaunchBoard) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(FIRST_LAUNCH_ONBOARD, isFirstLaunchBoard);
        editor.apply();
    }

    public boolean getFirstLaunchBoard() {
        return mSharedPref.getBoolean(FIRST_LAUNCH_ONBOARD, true);
    }

    public void setEffect(String key, boolean effect) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(key, effect);
        editor.apply();
    }

    public boolean getEffect(String key) {
        return mSharedPref.getBoolean(key, true);
    }


    public void setSubscriptionStatus(boolean subscriptionStatus) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(SUBSCRIPTION, subscriptionStatus);
        editor.apply();
    }

    public boolean getLocalSubscriptionStatus() {
        return mSharedPref.getBoolean(LOCAL_SUBSCRIPTION, false);
    }

    public void setLocalSubscriptionStatus(boolean subscriptionStatus) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(LOCAL_SUBSCRIPTION, subscriptionStatus);
        editor.apply();
    }

    public boolean isAmazonUnlocked() {
        return mSharedPref.getBoolean(IS_AMAZON_UNLOCKED, false);
    }

    public void setAmazonUnlockedOrNot(boolean isAmazonUnlocked) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(IS_AMAZON_UNLOCKED, isAmazonUnlocked);
        editor.apply();
    }

    public int getBackToMainCount() {
        return mSharedPref.getInt(BACK_TO_MAIN_COUNT, 0);
    }

    public void setBackToMainCount(int count) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putInt(BACK_TO_MAIN_COUNT, count);
        editor.apply();
    }

    public boolean getIsRated() {
        return mSharedPref.getBoolean(IS_RATED, false);
    }

    public void setIsRated(boolean isRated) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(IS_RATED, isRated);
        prefsEditor.apply();
    }

    public long readPreviousMilli() {
        return mSharedPref.getLong(PREVIOUS_MILLI, 0);
    }

    public void writePreviousMilli() {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putLong(PREVIOUS_MILLI, System.currentTimeMillis());
        prefsEditor.apply();
    }

    // My Artworks..
    public void putArtworkCounter(int count) {
        SharedPreferences.Editor editor = artworkPreference.edit();
        editor.putInt(ARTWORK_COUNTER, count);
        editor.apply();
    }

    public int getArtworkCounter() {
        return artworkPreference.getInt(ARTWORK_COUNTER, 0);
    }

    public void putArtworkCounter(String sharedKey, int count) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putInt(sharedKey, count);
        editor.apply();
    }

    public int getArtworkCounter(String sharedKey) {
        return mSharedPref.getInt(sharedKey, 0);
    }

    // For firebase log service..
    public void putEventLogName(String eventLogName) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(EVENT_LOG_NAME, eventLogName);
        editor.apply();
    }

    public String getEventLogName() {
        return mSharedPref.getString(EVENT_LOG_NAME, "");
    }

    public void putProductDurationName(String deskName) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(PRODUCT_DURATION_NAME, deskName);
        editor.apply();
    }

    public String getProductDurationName() {
        return mSharedPref.getString(PRODUCT_DURATION_NAME, "");
    }

    public void putPriceCurrencyCode(String currencyCode) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(PRODUCT_CURRENCY_CODE, currencyCode);
        editor.apply();
    }

    public String getPriceCurrencyCode() {
        return mSharedPref.getString(PRODUCT_CURRENCY_CODE, "$");
    }

    public void putProductPriceFloat(String key, float productPrice) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putFloat(key, productPrice);
        editor.apply();
    }

    public float getProductPriceFloat(String key, float defaultValue) {
        return mSharedPref.getFloat(key, defaultValue);
    }

    public void putProductPrice(String key, String deskName) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(key, deskName);
        editor.apply();
    }

    public String getProductPrice(String key, String defaultValue) {
        return mSharedPref.getString(key, defaultValue);
    }

    public void putNumOfTrialDays(String key, int productPrice) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putInt(key, productPrice);
        editor.apply();
    }

    public int getNumOfTrialDays(String key, int defaultValue) {
        return mSharedPref.getInt(key, defaultValue);
    }

//    public void putProductAlreadySubscribedSKU(String deskName) {
//        SharedPreferences.Editor editor = mSharedPref.edit();
//        editor.putString(PRODUCT_ALREADY_SUBSCRIBED_SKU, deskName);
//        editor.apply();
//    }
//
//    public String getProductAlreadySubscribedSKU(String defaultValue) {
//        return mSharedPref.getString(PRODUCT_ALREADY_SUBSCRIBED_SKU, defaultValue);
//    }

    // watermark enable in settings..
    public boolean getHasEnableSettingsWatermark() {
        return mSharedPref.getBoolean(SETTINGS_WATERMARK, true);
    }

    public void setHasEnableSettingsWatermark(boolean isEnabled) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(SETTINGS_WATERMARK, isEnabled);
        prefsEditor.apply();
    }

    // auto hide enable in settings..
    public boolean getHasEnableSettingsAutoHideUI() {
        return mSharedPref.getBoolean(SETTINGS_AUTO_HIDE_WATERMARK, false);
    }


    // watermark closed flag inside export dialogs..
    public boolean getHasWatermark() {
        return mSharedPref.getBoolean(EXPORT_WATERMARK, true);
    }

    public void setHasWatermark(boolean isRated) {
        SharedPreferences.Editor prefsEditor = mSharedPref.edit();
        prefsEditor.putBoolean(EXPORT_WATERMARK, isRated);
        prefsEditor.apply();
    }

    // color palette preferences..
    public void putSelectedColorPalette(int palette) {
        SharedPreferences.Editor editor = artworkPreference.edit();
        editor.putInt(SELECTED_COLOR_PALETTE, palette);
        editor.apply();
    }

    public int getSelectedColorPalette() {
        return artworkPreference.getInt(SELECTED_COLOR_PALETTE, 11);
    }

    public void putDoodleSelectedColorPalette(int palette) {
        SharedPreferences.Editor editor = artworkPreference.edit();
        editor.putInt(SELECTED_DOODLE_COLOR_PALETTE, palette);
        editor.apply();
    }

}
