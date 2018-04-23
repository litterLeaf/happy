package com.yinshan.happycash.view.information.model;

import com.yinshan.happycash.R;

/**
 * Created by huxin on 2018/4/12.
 */

public enum  RelationStatus implements InfoValueType{
    PARENT("PARENT", R.string.relation_parent),
    FRIEND("FRIEND", R.string.relation_friend),
    SPOUSE("SPOUSE", R.string.relation_spouse),
    SIBLING("SIBLING", R.string.relation_sibling),
    COLLEAGUE("COLLEAGUE", R.string.relation_colleague),
    CLASSMATE("CLASSMATE", R.string.relation_classmate);
    private final int mStrigId;
    private final String mValue;

    RelationStatus(String value, int stringId){
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
