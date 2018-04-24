package com.yinshan.happycash.view.information.model;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/23.
 */

public enum MarriageStatus implements InfoValueType {
    MARRIED("MARRIED", R.string.enum_marriage_married), SINGLE("SINGLE", R.string.enum_marriage_single), DIVORCED("DIVORCED", R.string.enum_marriage_divorced), WIDOWED("WIDOWED", R.string.enum_marriage_widowed);
    private final String mValue;
    private final int showString;

    MarriageStatus(String value, int stringId){
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
