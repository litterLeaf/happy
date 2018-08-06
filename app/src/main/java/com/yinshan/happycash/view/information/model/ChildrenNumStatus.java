package com.yinshan.happycash.view.information.model;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/23.
 */

public enum ChildrenNumStatus implements InfoValueType {
    ZERO("ZERO", R.string.enum_children_zero),ONE("ONE",R.string.enum_children_one),TWO("TWO",R.string.enum_children_two),
    THREE("THREE",R.string.enum_children_three),FOUR("FOUR",R.string.enum_children_four), OVER_FOUR("OVER_FOUR", R.string.enum_children_overfour);
    private final String mValue;
    private final int showString;

    ChildrenNumStatus(String value, int stringId){
        this.mValue = value;
        this.showString = stringId;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    @Override
    public int getShowString() {
        return showString;
    }
}

