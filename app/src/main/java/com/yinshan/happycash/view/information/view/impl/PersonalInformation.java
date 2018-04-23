package com.yinshan.happycash.view.information.view.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.information.presenter.PersonalPresenter;
import com.yinshan.happycash.view.information.view.IPersonalView;
import com.yinshan.happycash.widget.userdefined.BandaEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
s *
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *        ┃　　　┃   神兽保佑
 *        ┃　　　┃   代码无BUG！
 *        ┃　　　┗━━━┓
 *        ┃　　　　　　　┣┓
 *        ┃　　　　　　　┏┛
 *        ┗┓┓┏━┳┓┏┛
 *           ┃┫┫　┃┫┫
 *           ┗┻┛　┗┻┛
 *
 *  @author  admin
 *  on 2018/4/2
 *
 */
public class PersonalInformation extends BaseSingleActivity implements IPersonalView{
    @BindView(R.id.et_personal_name)
    BandaEditText personalName;
    @BindView(R.id.ll_personal_name)
    LinearLayout llPersonalName;
    @BindView(R.id.et_personal_ktp)
    BandaEditText PersonalKtp;
    @BindView(R.id.ll_personal_ktp)
    LinearLayout llPersonalKtp;
    @BindView(R.id.et_personal_family_name)
    BandaEditText personalFamilyName;
    @BindView(R.id.ll_personal_family_name)
    LinearLayout llPersonalFamilyName;
    @BindView(R.id.tv_personal_gender)
    TextView personalGender;
    @BindView(R.id.ll_personal_gender)
    LinearLayout llPersonalGender;
    @BindView(R.id.tv_personal_education)
    TextView personalEducation;
    @BindView(R.id.ll_personal_education)
    LinearLayout llPersonalEducation;
    @BindView(R.id.tv_personal_marital)
    TextView personalMarital;
    @BindView(R.id.ll_personal_marital)
    LinearLayout llPersonalMarital;
    @BindView(R.id.tv_personal_children_number)
    TextView personalChildrenNumber;
    @BindView(R.id.ll_personal_children_number)
    LinearLayout llPersonalChildrenNumber;
    @BindView(R.id.tv_personal_residence_province)
    TextView personalResidenceProvince;
    @BindView(R.id.ll_personal_residence_province)
    LinearLayout llPersonalResidenceProvince;
    @BindView(R.id.tv_personal_residence_city)
    TextView personalResidenceCity;
    @BindView(R.id.ll_personal_residence_city)
    LinearLayout llPersonalResidenceCity;
    @BindView(R.id.tv_personal_residence_street)
    TextView personalResidenceStreet;
    @BindView(R.id.ll_personal_residence_street)
    LinearLayout llPersonalResidenceStreet;
    @BindView(R.id.tv_personal_residence_area)
    TextView personalResidenceArea;
    @BindView(R.id.ll_personal_residence_area)
    LinearLayout llPersonalResidenceArea;
    @BindView(R.id.et_personal_address)
    BandaEditText personalAddress;
    @BindView(R.id.ll_personal_address)
    LinearLayout llPersonalAddress;
    @BindView(R.id.tv_personal_duration_of_residence)
    TextView personalDurationOfResidence;
    @BindView(R.id.ll_personal_duration_of_residence)
    LinearLayout llPersonalDurationOfResidence;
    @BindView(R.id.rl_personal_confirm)
    RelativeLayout confirm;

    PersonalPresenter mPresenter;

    @Override
    protected String bindTitle() {
        return getString(R.string.title_personal_infor);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_information;
    }

    @Override
    protected void secondInit() {
        mPresenter = new PersonalPresenter(this,this);

    }

    @OnClick({ R.id.ll_personal_gender, R.id.ll_personal_education, R.id.ll_personal_marital, R.id.ll_personal_children_number,
            R.id.ll_personal_residence_province, R.id.ll_personal_residence_city, R.id.ll_personal_residence_street, R.id.ll_personal_residence_area,
            R.id.ll_personal_duration_of_residence, R.id.rl_personal_confirm})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_personal_gender:
                break;
            case R.id.ll_personal_education:
                break;
            case R.id.ll_personal_marital:
                break;
            case R.id.ll_personal_children_number:
                break;
            case R.id.ll_personal_residence_province:
                break;
            case R.id.ll_personal_residence_city:
                break;
            case R.id.ll_personal_residence_street:
                break;
            case R.id.ll_personal_residence_area:
                break;
            case R.id.ll_personal_duration_of_residence:
                break;
            case R.id.rl_personal_confirm:
                finish();
                break;
        }
    }
}
