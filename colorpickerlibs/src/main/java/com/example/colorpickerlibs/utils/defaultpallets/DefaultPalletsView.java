package com.example.colorpickerlibs.utils.defaultpallets;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colorpickerlibs.R;
import com.example.colorpickerlibs.adapters.CPListRecyclerAdapter;
import com.example.colorpickerlibs.adapters.CPRecyclerAdapter;
import com.example.colorpickerlibs.databinding.DefaultPalletsViewBinding;
import com.example.colorpickerlibs.dialogs.CustomAlertDialog;
import com.example.colorpickerlibs.managers.CPManager;
import com.example.colorpickerlibs.managers.SharedPref;
import com.example.colorpickerlibs.models.ColorModel;
import com.example.colorpickerlibs.utils.interfaces.CPTextChangeListener;
import com.example.colorpickerlibs.utils.interfaces.ColorPalletsListViewOperationListener;
import com.example.colorpickerlibs.utils.interfaces.LoggingUserColorPalettesSyncAndDeleteListener;
import com.example.colorpickerlibs.utils.interfaces.OnColorPickedListener;

public class DefaultPalletsView extends FrameLayout {
    private DefaultPalletsViewBinding binding;
    private int windowHeight, windowWidth;
    private int viewWidth = 1194;
    private int viewHeight = 834;
    private Context context;
    private ColorModel colorModel;
    private CPManager cpManager;
    private CPListRecyclerAdapter cpListRecyclerAdapterTab;
    private LinearLayoutManager cpListLayoutManager;
    private CPRecyclerAdapter cpRecyclerAdapter;
    private int currentSelectedColor = -1;
    private ColorModel selectedColorModel;
    private CustomAlertDialog confirmDialog;
    private OnColorPickedListener onColorSquarePickerListener;
    private LoggingUserColorPalettesSyncAndDeleteListener loggingUserColorPalettesSyncAndDeleteListener;

    public DefaultPalletsView(@NonNull Context context) {
        super(context);
        intViews(context);
    }

    public DefaultPalletsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intViews(context);
    }

    public DefaultPalletsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intViews(context);
    }

    public DefaultPalletsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intViews(context);
    }

    private void intViews(Context context) {
        binding = DefaultPalletsViewBinding.inflate(LayoutInflater.from(context), this, true);
        windowHeight = SharedPref.getInstance(context).getHeight(0);
        windowWidth = SharedPref.getInstance(context).getWidth(0);
        cpManager = CPManager.getInstance(context);
        this.context = context;
        confirmDialog = new CustomAlertDialog(context);
        initView4();

    }

    private void initView4() {
        int rvWidth = 360 * windowWidth / 812;
        int rvHeight = 151 * windowHeight / 375;


        cpListRecyclerAdapterTab = new CPListRecyclerAdapter(getContext(), true, rvWidth, rvHeight, currentSelectedColor, false, () -> {
        });

        binding.colorPaletteRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        binding.colorPaletteRecyclerView.setAdapter(cpListRecyclerAdapterTab);


        cpListRecyclerAdapterTab.setColorList(cpManager.getColorPalletsList());

        colorModel = cpManager.getColorPalletsList().get(cpManager.getSelectedPalletId());


        cpListRecyclerAdapterTab.setPalletViewOperationListener(new ColorPalletsListViewOperationListener() {
            @Override
            public void onPaletteDeleted(ColorModel colorModel) {
                showConfirmDeleteDialog(colorModel);
            }

            @Override
            public void onPalletColorClick(int color) {
                if (color == 0) {
                    if (cpRecyclerAdapter != null) {
                        cpRecyclerAdapter.notifyDataSetChanged();
                    }
                } else {
                    currentSelectedColor = color;
                    cpListRecyclerAdapterTab.setCurrentColor(currentSelectedColor);
                    onColorSquarePickerListener.colorPicked(currentSelectedColor);
                }
            }

            @Override
            public void onSyncClick(ColorModel colorModel, int colorPosition) {
                loggingUserColorPalettesSyncAndDeleteListener.onColorPaletteSync(colorModel,colorPosition);
            }

            @Override
            public void onPaletteDefaultClick(ColorModel colorModel) {
                if (colorModel == null) {
                    showCannotDeleteDialog();
                } else {
                    selectedColorModel = colorModel;
                }
            }
        });

    }

    public void addCustomPallet() {
        cpListRecyclerAdapterTab.addCustomColorPalette();
        binding.colorPaletteRecyclerView.post(() -> binding.colorPaletteRecyclerView.scrollToPosition(cpListRecyclerAdapterTab.getItemCount() - 1));
    }

    private void showCannotDeleteDialog() {
        confirmDialog.showDialog(context.getString(R.string.WARNING), context.getString(R.string.CANNOT_DELETE_DEFAULT_PALETTE), null, context.getString(R.string.OK), position -> {
            switch (position) {
                case 1:
                    confirmDialog.dismissDialog();
                    break;
                case 2:
                    confirmDialog.dismissDialog();
                    break;
            }
        });
    }

    private void showConfirmDeleteDialog(ColorModel colorModel) {

        confirmDialog.showDialog(context.getString(R.string.WARNING), context.getString(R.string.DELETE_PALETTE_MESSAGE), context.getString(R.string.NO), context.getString(R.string.YES), position -> {
            switch (position) {
                case 1:
                    confirmDialog.dismissDialog();
                    break;
                case 2:
                    loggingUserColorPalettesSyncAndDeleteListener.onColorPaletteDelete(colorModel);

                    cpManager.removeTheme(colorModel.getPositionInThemeViewList());
                    cpListRecyclerAdapterTab.notifyDataSetChanged();

                    confirmDialog.dismissDialog();
                    break;
            }
        });
    }

    public void setCurrentSelectedColor(int color){
        this.currentSelectedColor = color;
        cpListRecyclerAdapterTab.setCurrentColor(currentSelectedColor);
    }

    public void setOnColorPickListener(OnColorPickedListener onColorSquarePickerListener) {
        this.onColorSquarePickerListener = onColorSquarePickerListener;
    }

    public void setLoggingUserColorPalettesSyncAndDeleteListener(LoggingUserColorPalettesSyncAndDeleteListener loggingUserColorPalettesSyncAndDeleteListener){
        this.loggingUserColorPalettesSyncAndDeleteListener = loggingUserColorPalettesSyncAndDeleteListener;
    }

    public void setIsMinimizeVersion(boolean isMinimizeVersion){
        cpListRecyclerAdapterTab.setMinimizeVersion(isMinimizeVersion);
    }
}
