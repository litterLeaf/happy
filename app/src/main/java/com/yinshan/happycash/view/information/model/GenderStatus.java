package com.yinshan.happycash.view.information.model;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/23.
 */

public enum GenderStatus implements InfoValueType{
    MALE("MALE", R.string.enum_gender_male), FEMALE("FEMALE",R.string.enum_gender_female);
    private final int mStrigId;
    private final String mValue;

    GenderStatus(String value, int stringId){
        mValue = value;
        mStrigId = stringId;
    }

    @Override
    public String getValue() {
        return mValue;
    }

    @Override
    public int getShowString() {
        return mStrigId;
    }
}
