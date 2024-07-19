package com.pmr.colorpickerlibs.utils.common;

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

import com.pmr.colorpickerlibs.utils.interfaces.EditTextChangeListener;
import com.pmr.colorpickerlibs.utils.interfaces.EditTextDoubleTapListener;
import com.pmr.colorpickerlibs.utils.interfaces.EditTextTapUpListener;

@SuppressLint("AppCompatCustomView")
public class DoubleClickEditText extends EditText implements View.OnTouchListener, TextView.OnEditorActionListener {
    private Context mContext;
    private EditTextChangeListener editTextChangeListener;
    private EditTextTapUpListener editTextTapUpListener;
    private EditTextDoubleTapListener editTextDoubleTapListener;
    private boolean clickable = false;

    private GestureDetector gestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            if (editTextTapUpListener != null){
                editTextTapUpListener.onSingleTap();
            }
            return super.onSingleTapUp(e);
        }

        public boolean onDoubleTap(MotionEvent e) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                if (clickable) {
                    if (editTextDoubleTapListener != null){
                        editTextDoubleTapListener.onDoubleTap();
                    }
                    enableEditText();
                }
            }
            return false;
        }
    });

    public DoubleClickEditText(Context context) {
        super(context);
        this.mContext = context;
        this.setOnTouchListener(this);
        this.setOnEditorActionListener(this);

        disableEditText();
    }

    public DoubleClickEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.setOnTouchListener(this);
        this.setOnEditorActionListener(this);

        disableEditText();
    }

    public DoubleClickEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.setOnTouchListener(this);
        this.setOnEditorActionListener(this);

        disableEditText();
    }

    public DoubleClickEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        this.setOnTouchListener(this);
        this.setOnEditorActionListener(this);

        disableEditText();
    }

    public void setClickableET(boolean clickable) {
        this.clickable = clickable;
    }

    public void setEditTextDoubleTapListener(EditTextDoubleTapListener editTextDoubleTapListener) {
        this.editTextDoubleTapListener = editTextDoubleTapListener;
    }

    public void setTextChangeListener(EditTextChangeListener editTextChangeListener) {
        this.editTextChangeListener = editTextChangeListener;
    }

    public void setTextTapListener(EditTextTapUpListener editTextTapUpListener) {
        this.editTextTapUpListener = editTextTapUpListener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_UP) {
            if (editTextChangeListener != null) {
                editTextChangeListener.onTextChange();
            }
            disableEditText();
        }
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
//        return false;
    }

    private void disableEditText() {
        this.setFocusable(false);
        this.setBackgroundResource(android.R.color.transparent);
    }

    private void enableEditText() {
        this.setFocusable(true);
        this.setClickable(true);
        this.setFocusableInTouchMode(true);
        this.setBackgroundResource(android.R.color.transparent);
    }

    public void enableEditTextOnButtonClick() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        this.requestFocus();
        this.setFocusable(true);
        this.setClickable(true);
        this.setFocusableInTouchMode(true);
        this.setBackgroundResource(android.R.color.transparent);
        imm.showSoftInput(this, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_DONE) {
            if (editTextChangeListener != null) {
                editTextChangeListener.onTextChange();
            }
            disableEditText();
        }
        return false;
    }
}
