package com.yinshan.happycash.view.information.view.impl;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.information.model.ChildrenNumStatus;
import com.yinshan.happycash.view.information.model.DurationStatus;
import com.yinshan.happycash.view.information.model.GenderStatus;
import com.yinshan.happycash.view.information.model.MarriageStatus;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.presenter.PersonalPresenter;
import com.yinshan.happycash.view.information.view.IPersonalView;
import com.yinshan.happycash.widget.userdefined.BandaEditText;

import butterknife.BindView;
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
    PersonalBean mBean;

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
        mBean = new PersonalBean();
        mPresenter = new PersonalPresenter(this,this);
        mPresenter.getPersonInfo();
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
                checkAndSubmit();
                break;
        }
    }

    @Override
    public void showInfo(PersonalBean personalBean) {
        personalName.setText(personalBean.getFullName());
        PersonalKtp.setText(personalBean.getCredentialNo());
        personalFamilyName.setText(personalBean.getFamilyNameInLaw());
        personalGender.setText(getString(GenderStatus.valueOf(personalBean.getGender()).getShowString()));
        personalEducation.setText(personalBean.getLastEducation());
        personalMarital.setText(getString(MarriageStatus.valueOf(personalBean.getMaritalStatus()).getShowString()));
        personalChildrenNumber.setText(getString(ChildrenNumStatus.valueOf(personalBean.getChildrenNumber()).getShowString()));
        personalResidenceProvince.setText(personalBean.getProvince());
        personalResidenceCity.setText(personalBean.getCity());
        personalResidenceStreet.setText(personalBean.getDistrict());
        personalResidenceArea.setText(personalBean.getArea());
        personalAddress.setText(personalBean.getAddress());
        personalDurationOfResidence.setText(getString(DurationStatus.valueOf(personalBean.getResidenceDuration()).getShowString()));
    }

    @Override
    public void submitPersonOk() {
        finish();
    }

    private void checkAndSubmit(){
        mBean.setFullName(personalName.getText().toString().trim());
        mBean.setCredentialNo(PersonalKtp.getText().toString().trim());
        mBean.setFamilyNameInLaw(personalFamilyName.getText().toString().trim());
        mBean.setGender(personalGender.getText().toString().trim());
        mBean.setProvince(personalResidenceProvince.getText().toString().trim());
        mBean.setCity(personalResidenceCity.getText().toString().trim());
        mBean.setDistrict(personalResidenceStreet.getText().toString().trim());
        mBean.setArea(personalResidenceArea.getText().toString().trim());
        mBean.setAddress(personalAddress.getText().toString().trim());
        mBean.setLastEducation(personalEducation.getText().toString().trim());
        mBean.setMaritalStatus(personalMarital.getText().toString().trim());
        mBean.setChildrenNumber(personalChildrenNumber.getText().toString().trim());
        mBean.setResidenceDuration(personalResidenceArea.getText().toString().trim());
        mPresenter.submitPersonalInfo(mBean);
    }
}
