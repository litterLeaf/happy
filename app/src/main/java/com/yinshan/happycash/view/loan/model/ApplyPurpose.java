package com.yinshan.happycash.view.loan.model;

import com.yinshan.happycash.R;
import com.yinshan.happycash.view.information.model.InfoValueType;

/**
 * Created by huxin on 2018/4/19.
 */

public enum ApplyPurpose  implements InfoValueType {

    EDUCATION_FEE("EDUCATION_FEE", R.string.apply_purpose_EDUCATION),
    MEDICAL_FEE("MEDICAL_FEE", R.string.apply_purpose_MEDICAL),
    DAILY_PRODUCTS("DAILY_PRODUCTS", R.string.apply_purpose_DAILY_PRODUCTS),
    BUSINESS_CAPITAL("BUSINESS_CAPITAL", R.string.apply_purpose_BUSINESS_CAPITAL),
    ELECTRICITY_BILL("ELECTRICITY_BILL", R.string.apply_purpose_ELECTRICITY_BILL),
    LOAN_PAYMENT("LOAN_PAYMENT", R.string.apply_purpose_LOAN_PAYMENT),
    ELECTRONICS_INSTALLMENT("ELECTRONICS_INSTALLMENT", R.string.apply_purpose_ELECTRONICS_INSTALLMENT),
    HOUSE_INSTALLMENT("HOUSE_INSTALLMENT", R.string.apply_purpose_HOUSE_INSTALLMENT),
    VEHICLE_INSTALLMENT("VEHICLE_INSTALLMENT", R.string.apply_purpose_VEHICLE_INSTALLMENT),
    VEHICLE_REPAIRMENT("VEHICLE_REPAIRMENT", R.string.apply_purpose_VEHICLE_REPAIRMENT),
    VACATION("VACATION", R.string.apply_purpose_VACATION),
    APARTMENT_RENT("APARTMENT_RENT", R.string.apply_purpose_APARTMENT_RENT),
    HOUSE_RENOVATION("HOUSE_RENOVATION", R.string.apply_purpose_HOUSE_RENOVATION),
    WEDDING_EXPENSES("WEDDING_EXPENSES", R.string.apply_purpose_WEDDING_EXPENSES),
    TRANSPORTAION_FEE("TRANSPORTAION_FEE", R.string.apply_purpose_TRANSPORTAION_FEE),
    OTHERS("OTHERS", R.string.apply_purpose_OTHERS);

    private final int mStringId;
    private final String mValue;

    ApplyPurpose(String mValue, int mStringId) {
        this.mStringId = mStringId;
        this.mValue = mValue;
    }


    @Override
    public String getValue() {
        return mValue;
    }

    @Override
    public int getShowString() {
        return mStringId;
    }

}