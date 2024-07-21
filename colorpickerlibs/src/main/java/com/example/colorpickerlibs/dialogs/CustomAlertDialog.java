package com.example.colorpickerlibs.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.colorpickerlibs.R;
import com.example.colorpickerlibs.utils.common.DDUtils;
import com.google.android.material.card.MaterialCardView;


public class CustomAlertDialog extends BaseDialog {
    public Activity activity;
    public Context context;
    private ConfirmDialogListener confirmDialogListener;

    private MaterialCardView btn1, btn2;
    private LinearLayout buttonLinearLayout;
    private RelativeLayout btn1Parent, btn2Parent;
    private TextView btn1Text, btn2Text, title, message;

    public CustomAlertDialog(Context context) {
        super(context, R.style.PopupDialogWithBGTheme);
        this.activity = (Activity) context;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_alert);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1Parent = findViewById(R.id.btn1Parent);
        btn2Parent = findViewById(R.id.btn2Parent);
        btn1Text = findViewById(R.id.btn1Text);
        btn2Text = findViewById(R.id.btn2Text);
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);
        buttonLinearLayout = findViewById(R.id.buttonLinearLayout);


        this.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        initViews();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btn1.setOnClickListener(v -> {
            confirmDialogListener.onButtonClick(1);
            dismissDialog();
        });
        btn2.setOnClickListener(v -> {
            confirmDialogListener.onButtonClick(2);
            dismissDialog();
        });
    }

    private void initViews() {

    }

    public void showDialog(String title, String description, String btn1Text, String btn2Text, ConfirmDialogListener confirmDialogListener) {
        this.confirmDialogListener = confirmDialogListener;

        showFullScreenDialog(activity);

        if (btn1Text == null && btn2Text == null) {
            buttonLinearLayout.setVisibility(View.GONE);
        } else {
            buttonLinearLayout.setVisibility(View.VISIBLE);
        }

        if (btn1Text == null) {
            btn1Parent.setVisibility(View.GONE);
        } else {
            this.btn1Text.setText(btn1Text);
            btn1Parent.setVisibility(View.VISIBLE);
        }

        if (btn2Text == null) {
            btn2Parent.setVisibility(View.GONE);
        } else {
            this.btn2Text.setText(btn2Text);
            btn2Parent.setVisibility(View.VISIBLE);
        }

        if (title == null) {
            this.title.setVisibility(View.GONE);
        } else {
            this.title.setText(title);
            this.title.setVisibility(View.VISIBLE);
        }

        if (description == null) {
            this.message.setVisibility(View.GONE);
        } else {
            this.message.setText(description);
            this.message.setVisibility(View.VISIBLE);
        }

        if (btn1Text == null || btn2Text == null) {
            buttonLinearLayout.getLayoutParams().width = DDUtils.dpToPixel(context, 160);
        } else {
            buttonLinearLayout.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        }
    }

    public interface ConfirmDialogListener {
        void onButtonClick(int position);
    }
}
