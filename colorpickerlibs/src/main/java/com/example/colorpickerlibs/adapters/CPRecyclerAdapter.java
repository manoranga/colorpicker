package com.example.colorpickerlibs.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.colorpickerlibs.R;
import com.example.colorpickerlibs.models.ColorModel;
import com.example.colorpickerlibs.utils.interfaces.CPColorAddListener;
import com.example.colorpickerlibs.utils.interfaces.CPColorClickListener;
import com.example.colorpickerlibs.utils.interfaces.CPThemeColorClickListener;

import java.util.ArrayList;

public class CPRecyclerAdapter extends RecyclerView.Adapter<CPRecyclerAdapter.ViewHolder> {
    private ArrayList<Integer> colorList;
    private LayoutInflater mInflater;
    private int windowWidth, windowHeight;
    private Context mContext;
    private int cellWidth, cellHeight;
    private int rvWidth, rvHeight;
    private boolean isTab;
    private boolean isFromTheme;
    private int currentColor;
    private int defaultPalettePosition;
    private ColorModel colorModel;
    private CPColorClickListener cpColorClickListener;
    private CPThemeColorClickListener cpThemeColorClickListener;
    private CPColorAddListener cpColorAddListener;

    public CPRecyclerAdapter(Context context, ColorModel colorModel, int windowWidth, int windowHeight, boolean isTab, int rvWidth, int rvHeight, int currentColor) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.colorModel = colorModel;
        this.colorList = colorModel.getColorCodes();
        this.isTab = isTab;
        this.rvWidth = rvWidth;
        this.rvHeight = rvHeight;
        this.currentColor = currentColor;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void setColorModel(ColorModel colorModel) {
        this.colorModel = colorModel;
        this.colorList = colorModel.getColorCodes();

        notifyDataSetChanged();
    }

    public void setCpColorClickListener(CPColorClickListener cpColorClickListener) {
        this.cpColorClickListener = cpColorClickListener;
        isFromTheme = false;
    }

    public void setCpThemeColorClickListener(CPThemeColorClickListener cpThemeColorClickListener) {
        this.cpThemeColorClickListener = cpThemeColorClickListener;
        isFromTheme = true;
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_item_color_palette, parent, false);

        if (isTab) {
            view.getLayoutParams().height = 36 * windowHeight / 834;
//            view.getLayoutParams().width = (38 * windowWidth / 1194);
        } else {
            view.getLayoutParams().height = 39 * windowHeight / 375;
//            view.getLayoutParams().width = 41 * windowWidth / 812;
        }

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        int holderPosition = holder.getAdapterPosition();

        if (colorList.get(holderPosition) != 0) {
            holder.rowColor.setImageDrawable(new ColorDrawable(colorList.get(holderPosition)));
        }
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView rowColor;

        ViewHolder(View itemView) {
            super(itemView);
            rowColor = itemView.findViewById(R.id.rowColor);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (colorList.get(getAdapterPosition()) != 0) {
                if (!isFromTheme)
                    cpColorClickListener.onColorClick(colorList.get(getAdapterPosition()));
                else
                    cpThemeColorClickListener.onColorClick(colorList.get(getAdapterPosition()), colorModel);
            } else {
                if (!colorModel.getPaletteID().equals("Recent Colors")) {
                    if (currentColor != 0) {
                        try {
                            rowColor.setImageDrawable(new ColorDrawable(currentColor));
                            colorList.set(getAdapterPosition(), currentColor);
                            colorModel.setSynced(false);

                            if (cpColorAddListener != null){
                                cpColorAddListener.onColorAdded(colorModel, currentColor);
                            }

                            if (!isFromTheme)
                                cpColorClickListener.onColorClick(0);
                            else
                                cpThemeColorClickListener.onColorClick(0, colorModel);

                        } catch (Exception e) {

                        }
                    }
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (colorModel.isEditable()) {
                if (isFromTheme) {
                    cpThemeColorClickListener.onColorLongClick(0, colorModel);
                } else {
                    colorModel.setSynced(false);
                    colorList.set(getAdapterPosition(), 0);
                    cpColorClickListener.onColorLongClick(0);
                }
            }else {
                cpThemeColorClickListener.onColorLongClick(0, null);
            }
            return true;
        }
    }

    public void setCPColorAddListener(CPColorAddListener cpColorAddListener) {
        this.cpColorAddListener = cpColorAddListener;
    }
}
