package com.pmr.colorpickerlibs;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pmr.colorpickerlibs.adapters.CPRecyclerAdapter;
import com.pmr.colorpickerlibs.databinding.ColorPickerTabViewBinding;
import com.pmr.colorpickerlibs.managers.CPManager;
import com.pmr.colorpickerlibs.managers.SharedPref;
import com.pmr.colorpickerlibs.models.ColorModel;
import com.pmr.colorpickerlibs.utils.common.SlidingOption;
import com.pmr.colorpickerlibs.utils.interfaces.CPThemeColorClickListener;
import com.pmr.colorpickerlibs.utils.interfaces.LoggingUserColorPalettesSyncAndDeleteListener;
import com.pmr.colorpickerlibs.utils.interfaces.OnColorPickedListener;
import com.pmr.colorpickerlibs.utils.others.NonScrollableGridLayoutManager;
import com.pmr.colorpickerlibs.constants.CPConstants;

import java.util.ArrayList;


public class ColorPickerTabView extends FrameLayout {
    private int windowHeight = 1600, windowWidth = 2560;
    private Context context;
    private ColorPickerTabViewBinding binding;
    private int viewWidth = 1194;
    private int viewHeight = 834;
    private CPManager cpManager;
    private ColorModel colorModel;
    private CPRecyclerAdapter cpRecyclerAdapter;
    private View selectedView;
    private int currentSelectedColor;
    private int previousSelectedColor;
    private OnColorPickedListener onColorPickedListener;
    private LoggingUserColorPalettesSyncAndDeleteListener loggingUserColorPalettesSyncAndDeleteListener;

    public ColorPickerTabView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public ColorPickerTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ColorPickerTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public ColorPickerTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void initView(Context context) {
        this.context = context;
        binding = ColorPickerTabViewBinding.inflate(LayoutInflater.from(context), this, true);
        setOnClickListeners();
        selectedView = binding.ringColorPickerViewContainer;
        // this adapter used for first three views defaults pined color palettes
        cpManager = CPManager.getInstance(context);
        colorModel = cpManager.getColorPalletsList().get(cpManager.getSelectedPalletId());
        cpRecyclerAdapter = new CPRecyclerAdapter(context, colorModel, windowWidth, windowHeight, true, 360 * windowWidth / 1194, windowHeight * 108 / 834, 123221);

        binding.ringColorPickerView.cricleView.getLayoutParams().width = 350 * windowWidth / 1194;
        binding.ringColorPickerView.cricleView.getLayoutParams().height = 350 * windowWidth / 834;

        // callback for separate views
        binding.ringColorPickerView.cricleView.setOnColorSelectedListener(color -> {
            updateViewCommon(color);
        });

        binding.squareColorPickerView.squareColorPickerView.setOnColorPickListener(color -> {
            updateViewCommon(color);
        });

        binding.linearColorPickerView.linearColorPicker.setOnColorPickListener(color -> {
            updateViewCommon(color);
        });

        binding.defaultPalletViewContainer.setOnColorPickListener(color -> {
            updateViewCommon(color);
        });

        binding.defaultPalletViewContainer.setLoggingUserColorPalettesSyncAndDeleteListener(new LoggingUserColorPalettesSyncAndDeleteListener() {
            @Override
            public void onColorPaletteSync(ColorModel selectedModel, int position) {
                loggingUserColorPalettesSyncAndDeleteListener.onColorPaletteSync(selectedModel, position);
            }

            @Override
            public void onColorPaletteDelete(ColorModel selectedModel) {
                loggingUserColorPalettesSyncAndDeleteListener.onColorPaletteDelete(selectedModel);
            }
        });

        cpRecyclerAdapter.setCpThemeColorClickListener(new CPThemeColorClickListener() {
            @Override
            public void onColorClick(int color, ColorModel colorModel) {
                if (color != 0)
                    updateSelectedViews(color);
            }

            @Override
            public void onColorLongClick(int color, ColorModel colorModel) {

            }
        });


        binding.ringColorPickerView.cricleView.setRingRadius(60 * windowWidth / 1194, 210 * windowHeight / 834, 360 * windowHeight / 834, 12 * windowWidth / 1194);

        binding.mainContainer.getLayoutParams().width = 376 * windowWidth / viewWidth;
        binding.mainContainer.getLayoutParams().height = 683 * windowHeight / viewHeight;

        binding.middleContainer.getLayoutParams().height = 563 * windowHeight / viewHeight;
        binding.topTool.getLayoutParams().height = 55 * windowHeight / viewHeight;

        binding.ringColorPickerView.colorPaletteRecyclerView.setLayoutManager(new NonScrollableGridLayoutManager(context, 10));
        binding.ringColorPickerView.colorPaletteRecyclerView.setAdapter(cpRecyclerAdapter);
        binding.ringColorPickerView.colorPaletteRecyclerView.setNestedScrollingEnabled(false);

        String pinnedTitle = colorModel.getTitle().equals("") ? "Untitled palette" : colorModel.getTitle();
        binding.ringColorPickerView.tvColorTitle.setText(pinnedTitle);

        binding.squareColorPickerView.colorPaletteRecyclerView.setLayoutManager(new NonScrollableGridLayoutManager(context, 10));
        binding.squareColorPickerView.colorPaletteRecyclerView.setAdapter(cpRecyclerAdapter);
        binding.squareColorPickerView.colorPaletteRecyclerView.setNestedScrollingEnabled(false);
        binding.squareColorPickerView.tvColorTitle.setText(pinnedTitle);

        binding.linearColorPickerView.colorPaletteRecyclerView.setLayoutManager(new NonScrollableGridLayoutManager(context, 10));
        binding.linearColorPickerView.colorPaletteRecyclerView.setAdapter(cpRecyclerAdapter);
        binding.linearColorPickerView.colorPaletteRecyclerView.setNestedScrollingEnabled(false);
        binding.linearColorPickerView.tvColorTitle.setText(pinnedTitle);

    }


    private void setOnClickListeners() {
        binding.btnClose.setOnClickListener(view -> {
            onClose();
        });
        binding.btnRingColorPicker.setOnClickListener(view -> {
            if (selectedView == binding.ringColorPickerViewContainer) {
                return;
            }

            SlidingOption.slideRightGone(selectedView);
            SlidingOption.slideRightVisible(binding.ringColorPickerViewContainer);
            selectedView = binding.ringColorPickerViewContainer;
            unselectBottomIcons();
            binding.imgRingColorPicker.setColorFilter(context.getColor(R.color.selected_blue), PorterDuff.Mode.SRC_ATOP);
            binding.selectedColorContainer.setVisibility(VISIBLE);
            updateRingColorPicker(true);
        });

        binding.btnSquareColorPicker.setOnClickListener(view -> {
            if (selectedView == binding.squareColorPickerViewContainer) {
                return;
            }
            if (selectedView == binding.linearColorPickerContainer || selectedView == binding.defaultPalletViewContainer) {
                SlidingOption.slideRightGone(selectedView);
                SlidingOption.slideRightVisible(binding.squareColorPickerViewContainer);
            } else {
                SlidingOption.slideLeftGone(selectedView);
                SlidingOption.slideLeftVisible(binding.squareColorPickerViewContainer);
            }
            selectedView = binding.squareColorPickerViewContainer;
            unselectBottomIcons();
            binding.imgSquareColorPicker.setColorFilter(context.getColor(R.color.selected_blue), PorterDuff.Mode.SRC_ATOP);
            binding.selectedColorContainer.setVisibility(VISIBLE);
            updateSquareColorPicker(true);
        });

        binding.btnLinerColorPicker.setOnClickListener(view -> {
            if (selectedView == binding.linearColorPickerContainer) {
                return;
            }
            if (selectedView == binding.defaultPalletViewContainer) {
                SlidingOption.slideRightGone(selectedView);
                SlidingOption.slideRightVisible(binding.linearColorPickerContainer);
            } else {
                SlidingOption.slideLeftGone(selectedView);
                SlidingOption.slideLeftVisible(binding.linearColorPickerContainer);
            }

            selectedView = binding.linearColorPickerContainer;
            unselectBottomIcons();
            binding.imgLinerColorPicker.setColorFilter(context.getColor(R.color.selected_blue), PorterDuff.Mode.SRC_ATOP);
            binding.selectedColorContainer.setVisibility(VISIBLE);
            updateLinearPickerView(true);
        });

        binding.btnPalettes.setOnClickListener(view -> {
            if (selectedView == binding.defaultPalletViewContainer) {
                return;
            }
            SlidingOption.slideLeftGone(selectedView);
            SlidingOption.slideLeftVisible(binding.defaultPalletViewContainer);
            selectedView = binding.defaultPalletViewContainer;
            unselectBottomIcons();
            binding.imgPalettes.setColorFilter(context.getColor(R.color.selected_blue), PorterDuff.Mode.SRC_ATOP);
            binding.addPallet.setVisibility(VISIBLE);
            binding.defaultPalletViewContainer.setCurrentSelectedColor(currentSelectedColor);
        });

        binding.addPallet.setOnClickListener(view -> {
            binding.defaultPalletViewContainer.addCustomPallet();
        });

        binding.previousSelectedColor.setOnClickListener(view -> {
            currentSelectedColor = previousSelectedColor;
            updateSelectedViews(currentSelectedColor);
        });

    }

    private void onClose() {
        CPManager.getInstance(context).saveColorPalletsList();
        ArrayList<Integer> recentPalette = CPManager.getInstance(context).getColorPalletsList().get(0).getColorCodes();
        ;
        if (!recentPalette.contains(currentSelectedColor)) {
            if (recentPalette.size() >= CPConstants.CPItemCount) {
                recentPalette.remove(CPConstants.CPItemCount - 1);
            }
            recentPalette.add(0, currentSelectedColor);
        }
        CPManager.getInstance(context).saveRecentPalletList(recentPalette);
    }

    private void unselectBottomIcons() {
        binding.imgSquareColorPicker.setColorFilter(context.getColor(R.color.bottom_icon), PorterDuff.Mode.SRC_ATOP);
        binding.imgRingColorPicker.setColorFilter(context.getColor(R.color.bottom_icon), PorterDuff.Mode.SRC_ATOP);
        binding.imgLinerColorPicker.setColorFilter(context.getColor(R.color.bottom_icon), PorterDuff.Mode.SRC_ATOP);
        binding.imgPalettes.setColorFilter(context.getColor(R.color.bottom_icon), PorterDuff.Mode.SRC_ATOP);

        binding.addPallet.setVisibility(GONE);
        binding.selectedColorContainer.setVisibility(GONE);
    }


    public void setViewWidthAndHeight(int viewWidth, int viewHeight) {
        this.windowWidth = viewWidth;
        this.windowHeight = viewHeight;
        SharedPref.getInstance(context).putHeight(windowHeight);
        SharedPref.getInstance(context).putWidth(windowWidth);
        binding.ringColorPickerView.cricleView.setRingRadius(60 * windowWidth / 1194, 210 * windowHeight / 834, 390 * windowHeight / 834, 12 * windowWidth / 1194);
        updateRingColorPicker(true);
    }

    public void setSelectedColorInit(int color, OnColorPickedListener onColorPickedListener) {
        this.previousSelectedColor = color;
        this.currentSelectedColor = color;
        binding.lastSelectedColor.setBackgroundColor(color);
        binding.previousSelectedColor.setBackgroundColor(color);
        updateSelectedViews(currentSelectedColor);
        this.onColorPickedListener = onColorPickedListener;
    }

    private void updateSquareColorPicker(boolean needToUpdateDefaultView) {
        binding.squareColorPickerView.squareColorPickerView.setCurrentSelectedColor(currentSelectedColor);
        if (needToUpdateDefaultView)
            updateDefaultPaletteViews();
    }

    private void updateRingColorPicker(boolean needToUpdateDefaultView) {
        binding.ringColorPickerView.cricleView.setCurrentSelectedColor(currentSelectedColor);
        if (needToUpdateDefaultView)
            updateDefaultPaletteViews();
    }

    private void updateLinearPickerView(boolean needToUpdateDefaultView) {
        binding.linearColorPickerView.linearColorPicker.setCurrentSelectedColor(currentSelectedColor);
        if (needToUpdateDefaultView)
            updateDefaultPaletteViews();
    }

    private void updateViewCommon(int color) {
        this.currentSelectedColor = color;
        binding.lastSelectedColor.setBackgroundColor(color);
        if (onColorPickedListener != null) {
            onColorPickedListener.colorPicked(color);
        }
    }

    private void updateDefaultPaletteViews() {
        //set default palette fro the first three views
        colorModel = cpManager.getColorPalletsList().get(cpManager.getSelectedPalletId());
        cpRecyclerAdapter.setColorModel(colorModel);

        String pinnedTitle = colorModel.getTitle().equals("") ? "Untitled palette" : colorModel.getTitle();
        binding.ringColorPickerView.tvColorTitle.setText(pinnedTitle);
        binding.squareColorPickerView.tvColorTitle.setText(pinnedTitle);
        binding.linearColorPickerView.tvColorTitle.setText(pinnedTitle);

    }

    private void updateSelectedViews(int color) {
        updateViewCommon(color);
        if (selectedView == binding.ringColorPickerViewContainer) {
            updateRingColorPicker(false);
        }

        if (selectedView == binding.squareColorPickerViewContainer) {
            updateSquareColorPicker(false);
        }

        if (selectedView == binding.linearColorPickerContainer) {
            updateLinearPickerView(false);
        }
    }

    public void setLoggingUserColorPalettesSyncAndDeleteListener(LoggingUserColorPalettesSyncAndDeleteListener loggingUserColorPalettesSyncAndDeleteListener) {
        this.loggingUserColorPalettesSyncAndDeleteListener = loggingUserColorPalettesSyncAndDeleteListener;
    }

    public void setSyncPalettesForLoggingUser(ArrayList<ColorModel> arrayList) {
        ArrayList<ColorModel> localCPList = cpManager.getColorPalletsList();
        boolean isExist;

        for (ColorModel syncedCP : arrayList) {
            isExist = false;

            for (ColorModel localCP : localCPList) {
                if (syncedCP.getPaletteID().equals(localCP.getPaletteID())) {
                    isExist = true;

                    break;
                }
            }

            if (!isExist) {
                cpManager.getColorPalletsList().add(syncedCP);
            }
        }
    }
}
