package com.pmr.colorpickerlibs.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.pmr.colorpickerlibs.managers.SharedPref;
import com.pmr.colorpickerlibs.utils.common.MyDevice;


public class BaseDialog extends Dialog {
    public int windowWidth;
    public int windowHeight;
    public boolean isTab;
    public Context context;
    public SharedPref sharedPref;

    public BaseDialog(@NonNull Context context) {
        super(context);

        initValues(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);

        initValues(context);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        initValues(context);
    }

    private void initValues(Context context) {
        this.context = context;
        sharedPref = SharedPref.getInstance(context);

        this.isTab = MyDevice.isTab(context);

        if (MyDevice.isTab(context)) {
            windowWidth = sharedPref.getWidth(1194);
            windowHeight = sharedPref.getHeight(834);
        } else {
            windowWidth = sharedPref.getWidth(812);
            windowHeight = sharedPref.getHeight(375);
        }
    }

    public int getPHDColor(int color) {
        return ContextCompat.getColor(context, color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void showFullScreenDialog(Activity activity) {
        if (!this.isShowing() && !activity.isFinishing()) {
            //Set the dialog to not focusable (makes navigation ignore us adding the window)
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            show();

            //Set the dialog to immersive
            this.getWindow().getDecorView().setSystemUiVisibility(
                    activity.getWindow().getDecorView().getSystemUiVisibility());

            //Clear the not focusable flag from the window
            this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void dismissDialog() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }
}
