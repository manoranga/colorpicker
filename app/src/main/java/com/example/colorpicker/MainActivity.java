package com.example.colorpicker;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.colorpickerlibs.ColorPickerTabView;
import com.example.colorpickerlibs.models.ColorModel;
import com.example.colorpickerlibs.utils.interfaces.LoggingUserColorPalettesSyncAndDeleteListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ColorPickerTabView colorPickerTab;
    private RelativeLayout pickedClor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableFullScreen();

        colorPickerTab = findViewById(R.id.colorPickerTab);
        pickedClor = findViewById(R.id.pickedClor);

        pickedClor.setOnClickListener(view -> {
            colorPickerTab.setViewWidthAndHeight(2560, 1600,true);
        });

        colorPickerTab.setViewWidthAndHeight(2560, 1600,false);
        int currentColor = Color.parseColor("#dbc486");
        colorPickerTab.setSelectedColorInit(currentColor, color -> {
            pickedClor.setBackgroundColor(color);
        });

        colorPickerTab.setLoggingUserColorPalettesSyncAndDeleteListener(new LoggingUserColorPalettesSyncAndDeleteListener() {
            @Override
            public void onColorPaletteSync(ColorModel selectedModel, int position) {
                Toast.makeText(MainActivity.this, "Color sync for logged user", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onColorPaletteDelete(ColorModel selectedModel) {
                Toast.makeText(MainActivity.this, "Color Delete for logged user", Toast.LENGTH_SHORT).show();

            }
        });
        colorPickerTab.setSyncPalettesForLoggingUser(new ArrayList<>());
    }

    public void enableFullScreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
        );
    }
}