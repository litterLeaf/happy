package com.yinshan.happycash.view.information.model;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/23.
 */

public enum DurationStatus implements InfoValueType {
    THREE_MONTH("THREE_MONTH", R.string.enum_period_three_month),SIX_MONTH("SIX_MONTH", R.string.enum_period_six_month),ONE_YEAR("ONE_YEAR",R.string.enum_period_one_year),TWO_YEAR("TWO_YEAR",R.string.enum_period_two_year),OVER_TWO_YEAR("OVER_TWO_YEAR",R.string.enum_period_over_two_year);
    private final String mValue;
    private final int showString;

    DurationStatus(String value, int stringId){
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
