package com.yinshan.happycash.widget.userdefined;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * Created by admin on 2017/7/25.
 */

public class RupiahEditText extends android.support.v7.widget.AppCompatEditText  {

    private OnCheckInputResult mOnCheckInputResult;
    private boolean            mInputStatus;
    private GoEditTextListener listeners;
    public RupiahEditText(Context context) {
        this(context, null);
    }

    public RupiahEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RupiahEditText(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (!isFocusable()) {
            setFocusable(true);
        }
        if (!isFocusableInTouchMode()) {
            setFocusableInTouchMode(true);
        }

        setImeActionLabel(context.getString(R.string.text_next), EditorInfo.IME_ACTION_NEXT);
        setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (mOnCheckInputResult != null && actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!mOnCheckInputResult.onCheckResult(RupiahEditText.this)) {
                        mOnCheckInputResult.onWrong(RupiahEditText.this);
                    } else {
                        mOnCheckInputResult.onRight(RupiahEditText.this);
                    }
                    return true;
                }
                return false;
            }
        });
        post(new Runnable() {
            @Override
            public void run() {
                if (isFocused()) {
                    ViewParent parent = getParent();
                    if (parent != null && parent instanceof ViewGroup) {
                        ((ViewGroup) parent).setFocusableInTouchMode(true);
                        ((ViewGroup) parent).setFocusable(true);
                        ((ViewGroup) parent).requestFocus();
                    }
                    hideInputSoftware();
                    setCursorVisible(false);
                }
            }
        });

        addTextChangedListener(new BandaTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mOnCheckInputResult != null) {
                    mOnCheckInputResult.onReEdit(RupiahEditText.this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mOnCheckInputResult != null) {
                    mOnCheckInputResult.onTextChanged(RupiahEditText.this, s);
                }
            }
        });
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setCursorVisible(true);
                if (!isFocused()) {
                    mInputStatus = true;
                    requestFocus();
                    showInputSoftware();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused) {
            if (!mInputStatus) {
                hideInputSoftware();
            }else{
                showInputSoftware();
            }
        }
    }

    /**
     * 剪贴板监听
     * @param id
     * @return
     */
    @Override
    public boolean onTextContextMenuItem(int id) {
        boolean consumed = super.onTextContextMenuItem(id);
        switch (id){
            case android.R.id.paste:
                onTextPaste();
                break;
        }
        return consumed;
    }

    public void onTextPaste(){
        listeners.onPaste();
    }
    public void addListener(GoEditTextListener listener) {
        try {
            listeners=listener;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void showInputSoftware() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(this, 0);
        mInputStatus = false;
    }

    public void hideInputSoftware() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
//        mInputStatus = false;
    }

    public void setOnCheckInputResult(OnCheckInputResult listener) {
        mOnCheckInputResult = listener;
    }
}
