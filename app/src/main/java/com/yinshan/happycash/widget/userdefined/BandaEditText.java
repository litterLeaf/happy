package com.yinshan.happycash.widget.userdefined;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.yinshan.happycash.R;

/**
 * @作者:My
 * @创建日期: 2017/3/28 12:18
 * @描述:${TODO}
 * @更新者:${Author}$
 * @更新时间:${Date}$
 * @更新描述:${TODO}
 */

public class BandaEditText extends android.support.v7.widget.AppCompatEditText {
    private OnCheckInputResult mOnCheckInputResult;
    private boolean            mInputStatus;
    private GoEditTextListener listeners;

    public BandaEditText(Context context) {
        this(context, null);
    }

    public BandaEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BandaEditText(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, AttributeSet attrs) {
        int gravity = getGravity();
        if (gravity != Gravity.CENTER_VERTICAL && gravity != Gravity.CENTER_VERTICAL && gravity != Gravity.CENTER) {
            setGravity(Gravity.CENTER_VERTICAL);
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BandaEditText);
        int anInt = typedArray.getInt(R.styleable.BandaEditText_customStyle, 1);
        if (anInt == 0) {
            setBackgroundResource(R.drawable.selector_edittext_input);
        } else if (anInt == 1) {

        }
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
                    if (!mOnCheckInputResult.onCheckResult(BandaEditText.this)) {
                        mOnCheckInputResult.onWrong(BandaEditText.this);
                    } else {
                        mOnCheckInputResult.onRight(BandaEditText.this);
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
                    mOnCheckInputResult.onReEdit(BandaEditText.this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mOnCheckInputResult != null) {
                    mOnCheckInputResult.onTextChanged(BandaEditText.this, s);
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

    public BandaEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public void setOnCheckInputResult(OnCheckInputResult listener) {
        mOnCheckInputResult = listener;
    }

    public void setInputLegalState(boolean isLegal) {
        if (isLegal && mOnCheckInputResult != null) {
            mOnCheckInputResult.onWrong(this);
        } else {
            mOnCheckInputResult.onRight(this);
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
}
