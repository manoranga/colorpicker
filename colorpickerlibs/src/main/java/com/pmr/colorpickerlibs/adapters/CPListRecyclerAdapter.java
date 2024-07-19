package com.pmr.colorpickerlibs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pmr.colorpickerlibs.R;
import com.pmr.colorpickerlibs.managers.CPCustomColorPalette;
import com.pmr.colorpickerlibs.managers.CPManager;
import com.pmr.colorpickerlibs.managers.SharedPref;
import com.pmr.colorpickerlibs.models.ColorModel;
import com.pmr.colorpickerlibs.utils.common.DoubleClickEditText;
import com.pmr.colorpickerlibs.utils.common.MyDevice;
import com.pmr.colorpickerlibs.utils.interfaces.CPThemeColorClickListener;
import com.pmr.colorpickerlibs.utils.interfaces.ColorPalletsListViewOperationListener;
import com.pmr.colorpickerlibs.utils.interfaces.EditTextChangeListener;

import java.util.ArrayList;


public class CPListRecyclerAdapter extends RecyclerView.Adapter<CPListRecyclerAdapter.ViewHolder> {
    private final CPManager cpManager;
    private ArrayList<ColorModel> colorList;
    private int windowWidth, windowHeight;
    private Context mContext;
    private int cellWidth, cellHeight;
    private int rvWidth, rvHeight;
    private boolean isTab;
    private int currentColor;
    private ColorPalletsListViewOperationListener colorPalletsListViewOperationListener;
    private CPCustomColorPalette cpCustomColorPalette;
    private int deleteCPPosition = -1;
    private int deskColor;
    private boolean isMinimizeVersion;
    private boolean isHidePinButton;
    private boolean isEnableSync = true;
    private int previousSyncPosition = 0;
    private KeyboardDismissListener keyboardDismissListener;

    public CPListRecyclerAdapter(Context context, boolean isTab, int rvWidth, int rvHeight, int currentColor, boolean isMinimizeVersion, boolean isHidePinButton, KeyboardDismissListener keyboardDismissListener) {
        this.cpManager = CPManager.getInstance(context);
        this.keyboardDismissListener = keyboardDismissListener;
        this.mContext = context;
        this.colorList = cpManager.getColorPalletsList();
        this.isTab = isTab;
        this.rvWidth = rvWidth;
        this.rvHeight = rvHeight;
        this.currentColor = currentColor;
        this.windowWidth = SharedPref.getInstance(context).getWidth(0);
        this.windowHeight = SharedPref.getInstance(context).getHeight(0);
        this.isMinimizeVersion = isMinimizeVersion;
        this.isHidePinButton = isHidePinButton;
        this.cpCustomColorPalette = CPCustomColorPalette.getInstance();
    }

    public void setDeleteCPPosition(int deleteCPPosition) {
        this.deleteCPPosition = deleteCPPosition;
    }

    public void addCustomColorPalette() {
        cpManager.addEmptyPalette();
        // Save color palette when theme adding.. otherwise it will add another default palette and sometimes default palette also can rename.. To prevent that bug save cp here also..
        cpManager.saveColorPalletsList();
        notifyDataSetChanged();
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;

        notifyDataSetChanged();
    }

    public void setPalletViewOperationListener(ColorPalletsListViewOperationListener colorPalletsListViewOperationListener) {
        this.colorPalletsListViewOperationListener = colorPalletsListViewOperationListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_color_palette_lits, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);

        ColorModel colorModel = colorList.get(holder.getAdapterPosition());
        int holderPosition = holder.getAdapterPosition();

        if (colorModel.getTitle().equals("")) {
            holder.paletteTitle.setText(mContext.getString(R.string.UNTITLED_PALETTE));
        } else {
            holder.paletteTitle.setText(colorModel.getTitle());
        }

        colorModel.setPositionInThemeViewList(holderPosition);
        CPRecyclerAdapter cpRecyclerAdapter = new CPRecyclerAdapter(mContext, colorModel, windowWidth, windowHeight, isTab, rvWidth, rvHeight, currentColor);
        cpRecyclerAdapter.setCurrentColor(currentColor);
        cpRecyclerAdapter.setCpThemeColorClickListener(new CPThemeColorClickListener() {
            @Override
            public void onColorClick(int color, ColorModel receivedCM) {
                deleteCPPosition = -1;

                if (color == 0) {
                    colorList.get(receivedCM.getPositionInThemeViewList()).setSynced(false);
                    notifyDataSetChanged();
                }
                colorPalletsListViewOperationListener.onPalletColorClick(color);
            }

            @Override
            public void onColorLongClick(int color, ColorModel colorModel) {
                if (cpManager.getSelectedPalletId() == holderPosition) {
                    colorPalletsListViewOperationListener.onPaletteDefaultClick(null);
                } else {
                    if (colorModel == null) {
                        colorPalletsListViewOperationListener.onPaletteDefaultClick(null);
                    } else {
                        deleteCPPosition = colorModel.getPositionInThemeViewList();
                        notifyDataSetChanged();
                    }
                }
            }
        });
        holder.rvItemContainer.setOnLongClickListener(view -> {
            if (cpManager.getSelectedPalletId() == holderPosition) {
                if (!colorModel.isDefault() && colorModel.isEditable()) {
                    colorPalletsListViewOperationListener.onPaletteDefaultClick(null);
                }
            } else {
                if (!colorModel.isDefault() && colorModel.isEditable()) {
                    deleteCPPosition = colorModel.getPositionInThemeViewList();
                    notifyDataSetChanged();
                }
                if (!colorModel.isEditable()) {
                    colorPalletsListViewOperationListener.onPaletteDefaultClick(null);
                }
            }
            return true;
        });
        if (isMinimizeVersion) {
            holder.rvCPInsideList.setLayoutManager(new GridLayoutManager(mContext, 6));
        } else {
            holder.rvCPInsideList.setLayoutManager(new GridLayoutManager(mContext, 10));
        }
        holder.rvCPInsideList.setAdapter(cpRecyclerAdapter);

        // visible default or set default button..
        if (cpManager.getSelectedPalletId() == holderPosition) {
            holder.btnDefault.setVisibility(View.VISIBLE);
            holder.btnSetDefault.setVisibility(View.GONE);
        } else {
            holder.btnDefault.setVisibility(View.GONE);
            holder.btnSetDefault.setVisibility(View.VISIBLE);
        }

        // visible delete button..
        if (deleteCPPosition == holderPosition) {
            holder.btnDefault.setVisibility(View.GONE);
            holder.btnSetDefault.setVisibility(View.GONE);
            holder.btnDelete.setVisibility(View.VISIBLE);
            holder.btnDeleteClose.setVisibility(View.VISIBLE);
            holder.btnSync.setVisibility(View.GONE);
        } else {
            holder.btnDelete.setVisibility(View.GONE);
            holder.btnDeleteClose.setVisibility(View.GONE);
            holder.btnSync.setVisibility(View.VISIBLE);
        }

        // set drawable of sync button..
        if (colorModel.isSynced()) {
            holder.imSync.clearAnimation();
            holder.imSync.setImageResource(R.drawable.skp_color_picker_synced);
        } else {
            holder.imSync.clearAnimation();
            holder.imSync.setImageResource(R.drawable.skp_color_picker_sync);
        }

        // set visibility of sync button according to custom palette or not..
        if (cpManager.getDefaultPalletCount() - 1 < holderPosition) {
            // holder.btnSync.setVisibility(View.VISIBLE);
        } else {
            holder.btnSync.setVisibility(View.GONE);
        }

        // set palette name editable or not..
        if (colorModel.isEditable()) {
            holder.paletteTitle.setClickableET(true);
            holder.paletteTitle.setTextTapListener(() -> {
            });

            holder.paletteTitle.setTextChangeListener((EditTextChangeListener) () -> {
                if (!colorModel.getTitle().equals(holder.paletteTitle.getText().toString())) {
                    colorList.get(holderPosition).setSynced(false);
                    notifyDataSetChanged();
                }
                colorModel.setTitle(holder.paletteTitle.getText().toString());
                cpManager.saveColorPalletsList();
                keyboardDismissListener.keyBoardDismissed();
            });
        } else {
            holder.paletteTitle.setClickableET(false);
            keyboardDismissListener.keyBoardDismissed();
        }

        if (isMinimizeVersion) {
            holder.btnDefault.setVisibility(View.GONE);
            holder.btnSetDefault.setVisibility(View.GONE);
            // holder.btnDelete.setVisibility(View.GONE);
            // holder.btnDeleteClose.setVisibility(View.GONE);
            holder.btnSync.setVisibility(View.GONE);
        }

        if (isHidePinButton) {
            holder.btnDefault.setVisibility(View.GONE);
            holder.btnSetDefault.setVisibility(View.GONE);
        }
    }

    private void rotateSyncButton(ImageView imageView) {
        RotateAnimation rotate = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        rotate.setDuration(800);
        rotate.setRepeatCount(Animation.INFINITE);
        imageView.startAnimation(rotate);
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        DoubleClickEditText paletteTitle;
        RecyclerView rvCPInsideList;
        RelativeLayout rvContainerInsideList;
        ImageView imSync;
        RelativeLayout btnSync;
        CardView btnDefault;
        CardView btnSetDefault;
        CardView btnDelete;
        CardView btnDeleteClose;
        RelativeLayout rvListDetailsContainer;
        LinearLayout rvItemContainer;

        int dialogHeight;
        int rvMargin;

        ViewHolder(View itemView) {
            super(itemView);

            paletteTitle = itemView.findViewById(R.id.paletteTitle);
            rvCPInsideList = itemView.findViewById(R.id.rvCPInsideList);
            rvContainerInsideList = itemView.findViewById(R.id.rvContainerInsideList);
            imSync = itemView.findViewById(R.id.imSync);
            btnSync = itemView.findViewById(R.id.btnSync);
            btnDefault = itemView.findViewById(R.id.btnDefault);
            btnSetDefault = itemView.findViewById(R.id.btnSetDefault);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDeleteClose = itemView.findViewById(R.id.btnDeleteClose);
            rvListDetailsContainer = itemView.findViewById(R.id.rvListDetailsContainer);
            rvItemContainer = itemView.findViewById(R.id.rvItemContainer);

            int actionbarHeight;
            int dialogActionbarHeight;
            int dialogWidth;
            isTab = MyDevice.isTab(mContext);

            if (isTab) {
                actionbarHeight = windowHeight / 12;
                dialogHeight = (windowHeight - (actionbarHeight * 2)) - (actionbarHeight / 4);
                dialogWidth = windowWidth / 3;
                rvMargin = windowWidth * 8 / 1194;
                dialogActionbarHeight = (dialogHeight / 14) + 2;

            } else {
                actionbarHeight = windowHeight / 12;
                dialogHeight = (windowHeight - (actionbarHeight * 2)) - (actionbarHeight / 4);
                dialogWidth = windowWidth / 3;
                rvMargin = windowWidth * 8 / 812;
                dialogActionbarHeight = (48 * windowHeight / 375) + 2;

            }
            paletteTitle.setText(mContext.getString(R.string.UNTITLED_PALETTE));


            LinearLayout layout = (LinearLayout) itemView.findViewById(R.id.rvItemContainer);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(rvMargin, rvMargin / 2, rvMargin, rvMargin);
            layout.setLayoutParams(params);

            LinearLayout.LayoutParams rvListDetailsContainerParams = (LinearLayout.LayoutParams) rvListDetailsContainer.getLayoutParams();
            rvListDetailsContainerParams.setMargins(rvMargin, 0, rvMargin, 0);
            rvListDetailsContainer.getLayoutParams().height = dialogActionbarHeight;

            btnSync.getLayoutParams().width = dialogActionbarHeight;
            imSync.getLayoutParams().width = dialogActionbarHeight / 2;
            btnDefault.getLayoutParams().height = dialogActionbarHeight * 4 / 5;
            btnSetDefault.getLayoutParams().height = dialogActionbarHeight * 4 / 5;
            btnDelete.getLayoutParams().height = dialogActionbarHeight * 4 / 5;
            btnDeleteClose.getLayoutParams().height = dialogActionbarHeight * 4 / 5;

            btnDefault.getLayoutParams().width = dialogWidth / 10;
            btnSetDefault.getLayoutParams().width = dialogWidth / 10;
            btnDelete.getLayoutParams().width = dialogWidth / 10;
            btnDeleteClose.getLayoutParams().width = dialogWidth / 10;

            btnSetDefault.setOnClickListener(this);
            btnDefault.setOnClickListener(this);
            btnSync.setOnClickListener(this);
            btnDelete.setOnClickListener(this);
            btnDeleteClose.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnDelete) {
                deleteCPPosition = -1;
                colorPalletsListViewOperationListener.onPaletteDeleted(colorList.get(getAdapterPosition()));
                // Save color palette when theme adding.. otherwise it will add another default palette and sometimes default palette also can rename.. To prevent that bug save cp here also..
                cpManager.saveColorPalletsList();
            }

            if (view.getId() == R.id.btnSetDefault) {
                cpManager.setSelectedPalletId(getAdapterPosition());
                colorPalletsListViewOperationListener.onPaletteDefaultClick(colorList.get(getAdapterPosition()));
                cpManager.setSelectedColorPalette();
                notifyDataSetChanged();
            }

            if (view.getId() == R.id.btnSync) {
                if (colorPalletsListViewOperationListener != null && !colorList.get(getAdapterPosition()).isSynced() && isEnableSync) {
                    isEnableSync = false;
                    colorPalletsListViewOperationListener.onSyncClick(colorList.get(getAdapterPosition()), getAdapterPosition());
                }
            }

            if (view.getId() == R.id.btnDeleteClose) {
                btnDelete.setVisibility(View.GONE);
                btnDeleteClose.setVisibility(View.GONE);
                if (isMinimizeVersion) {
                    btnSetDefault.setVisibility(View.GONE);
                    btnSync.setVisibility(View.GONE);
                } else {
                    btnSetDefault.setVisibility(View.VISIBLE);
                    btnSync.setVisibility(View.VISIBLE);
                }
                deleteCPPosition = -1;
            }

        }
    }


    public void setColorList(ArrayList<ColorModel> colorList) {
        this.colorList = colorList;
        notifyDataSetChanged();
    }

    public void setSyncSuccessListener(int position) {
        isEnableSync = true;
        previousSyncPosition = position;
        notifyDataSetChanged();
    }

    public void setEnableSync() {
        isEnableSync = true;
    }


    public interface KeyboardDismissListener {
        void keyBoardDismissed();
    }
}
