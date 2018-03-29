package com.yinshan.happycash.widget.happyedittext;

import android.content.Context;
import android.text.Editable;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.yinshan.happycash.R;
import com.yinshan.happycash.widget.userdefined.OnCheckInputResult;

/**
 * Created by huxin on 2018/3/29.
 */

public abstract class OnCheckInputResultAdapter implements OnCheckInputResult{

    @Override
    public abstract boolean onCheckResult(EditText v);

    @Override
    public void onWrong(EditText v) {
        v.setTextColor(v.getResources().getColor(R.color.colorAccent));
        v.requestFocus();
    }

    @Override
    public void onReEdit(EditText v) {
        v.setTextColor(v.getResources().getColor(R.color.hc_input_text));
    }

    @Override
    public void onRight(EditText v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
        v.clearFocus();
    }

    @Override
    public void onTextChanged(EditText v, Editable s) {

    }
}
