// Generated by view binder compiler. Do not edit!
package com.example.colorpickerlibs.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.colorpickerlibs.R;
import com.example.colorpickerlibs.utils.ringcolorpicker.BrightnessColorPickerView;
import com.example.colorpickerlibs.utils.ringcolorpicker.ColorPickRing;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ColorPalleteHslViewBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final BrightnessColorPickerView brightnessColorPicker;

  @NonNull
  public final FrameLayout mainView;

  @NonNull
  public final CardView mainViewCard;

  @NonNull
  public final ColorPickRing ringView;

  private ColorPalleteHslViewBinding(@NonNull FrameLayout rootView,
      @NonNull BrightnessColorPickerView brightnessColorPicker, @NonNull FrameLayout mainView,
      @NonNull CardView mainViewCard, @NonNull ColorPickRing ringView) {
    this.rootView = rootView;
    this.brightnessColorPicker = brightnessColorPicker;
    this.mainView = mainView;
    this.mainViewCard = mainViewCard;
    this.ringView = ringView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ColorPalleteHslViewBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ColorPalleteHslViewBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.color_pallete_hsl_view, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ColorPalleteHslViewBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.brightnessColorPicker;
      BrightnessColorPickerView brightnessColorPicker = ViewBindings.findChildViewById(rootView, id);
      if (brightnessColorPicker == null) {
        break missingId;
      }

      FrameLayout mainView = (FrameLayout) rootView;

      id = R.id.mainViewCard;
      CardView mainViewCard = ViewBindings.findChildViewById(rootView, id);
      if (mainViewCard == null) {
        break missingId;
      }

      id = R.id.ringView;
      ColorPickRing ringView = ViewBindings.findChildViewById(rootView, id);
      if (ringView == null) {
        break missingId;
      }

      return new ColorPalleteHslViewBinding((FrameLayout) rootView, brightnessColorPicker, mainView,
          mainViewCard, ringView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
