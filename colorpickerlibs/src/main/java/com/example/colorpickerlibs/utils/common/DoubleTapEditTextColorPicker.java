package com.example.colorpickerlibs.utils.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.colorpickerlibs.utils.interfaces.EditTextDoubleTapListener;

@SuppressLint("AppCompatCustomView")
public class DoubleTapEditTextColorPicker extends EditText implements TextView.OnEditorActionListener, View.OnTouchListener {
    private Context mContext;
    private boolean clickable = false;
    private SettingsKeyboardDismissListener settingsKeyboardDismissListener;
    private EditTextDoubleTapListener editTextDoubleTapListener;
    private GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            return super.onSingleTapUp(e);
        }

        public boolean onDoubleTap(MotionEvent e) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                if (true) {
                    if (editTextDoubleTapListener != null) {
                        editTextDoubleTapListener.onDoubleTap();
                    }
                    enableEditText(5);
                }
            }
            return false;
        }
    });

    public DoubleTapEditTextColorPicker(Context context) {
        super(context);
        this.mContext = context;
        this.setOnEditorActionListener(this);
        this.setOnTouchListener(this);
        disableEditText();
    }

    public DoubleTapEditTextColorPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.setOnEditorActionListener(this);
        this.setOnTouchListener(this);
        disableEditText();
    }

    public DoubleTapEditTextColorPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.setOnEditorActionListener(this);
        this.setOnTouchListener(this);
        disableEditText();
    }

    public DoubleTapEditTextColorPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        this.setOnEditorActionListener(this);
        this.setOnTouchListener(this);
        disableEditText();
    }

    public void setSettingsKeyboardDismissListener(SettingsKeyboardDismissListener settingsKeyboardDismissListener) {
        this.settingsKeyboardDismissListener = settingsKeyboardDismissListener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP) {
            if (settingsKeyboardDismissListener != null) {
                settingsKeyboardDismissListener.onKeyboardDismissed();
            }
            disableEditText();
        }
        return super.onKeyPreIme(keyCode, event);
    }

    private void disableEditText() {
        this.setFocusable(false);
        this.setBackgroundResource(android.R.color.transparent);
    }

    public void enableEditText(int textLength) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            this.setSelection(textLength);
        } catch (Exception e) {
        }
        this.requestFocus();
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.setBackgroundResource(android.R.color.transparent);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);

        this.requestFocus();
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            if (settingsKeyboardDismissListener != null) {
                settingsKeyboardDismissListener.onKeyboardDismissed();
            }
            disableEditText();
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    public void setEditTextDoubleTapListener(EditTextDoubleTapListener editTextDoubleTapListener) {
        this.editTextDoubleTapListener = editTextDoubleTapListener;
    }

    public interface SettingsKeyboardDismissListener {
        void onKeyboardDismissed();
    }


}
