package com.yinshan.happycash.view.information.view.impl;

import android.app.Dialog;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.StringUtil;
import com.yinshan.happycash.view.information.model.ChildrenNumStatus;
import com.yinshan.happycash.view.information.model.DurationStatus;
import com.yinshan.happycash.view.information.model.GenderStatus;
import com.yinshan.happycash.view.information.model.MarriageStatus;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.information.model.RegionsBean;
import com.yinshan.happycash.view.information.presenter.PersonalPresenter;
import com.yinshan.happycash.view.information.view.IPersonalView;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapter;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterString;
import com.yinshan.happycash.view.information.view.impl.support.InfoType;
import com.yinshan.happycash.widget.HappySnackBar;
import com.yinshan.happycash.widget.common.ToastManager;
import com.yinshan.happycash.widget.dialog.DialogManager;
import com.yinshan.happycash.widget.happyedittext.OnCheckInputResultAdapter;
import com.yinshan.happycash.widget.userdefined.BandaEditText;
import com.yinshan.happycash.widget.userdefined.OnCheckInputResult;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fraudmetrix.octopus.aspirit.bean.OctopusParam;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.main.OctopusTaskCallBack;
import rx.Observable;

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
    BandaEditText personalName;
    LinearLayout llPersonalName;
    BandaEditText PersonalKtp;
    LinearLayout llPersonalKtp;
    BandaEditText personalFamilyName;
    LinearLayout llPersonalFamilyName;
    TextView personalGender;
    LinearLayout llPersonalGender;
    TextView personalEducation;
    LinearLayout llPersonalEducation;
    TextView personalMarital;
    LinearLayout llPersonalMarital;
    TextView personalChildrenNumber;
    LinearLayout llPersonalChildrenNumber;
    TextView personalResidenceProvince;
    LinearLayout llPersonalResidenceProvince;
    TextView personalResidenceCity;
    LinearLayout llPersonalResidenceCity;
    TextView personalResidenceStreet;
    LinearLayout llPersonalResidenceStreet;
    TextView personalResidenceArea;
    LinearLayout llPersonalResidenceArea;
    BandaEditText personalAddress;
    LinearLayout llPersonalAddress;
    TextView personalDurationOfResidence;
    LinearLayout llPersonalDurationOfResidence;
    RelativeLayout confirm;

    PersonalPresenter mPresenter;
    PersonalBean mBean;

    int chooseProvinceId = -1;
    int chooseCityId = -1;
    int chooseStreetId = -1;

    Dialog dialogPlus;

    public enum regionLevel {
        province,
        city,
        district,
        area
    }

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
        initUI();

        RxBus.get().register(this);

        mBean = new PersonalBean();
        mPresenter = new PersonalPresenter(this,this);
        mPresenter.getPersonInfo();

        addEditListener();
    }

    @OnClick({ R.id.ll_personal_gender, R.id.ll_personal_education, R.id.ll_personal_marital, R.id.ll_personal_children_number,
            R.id.ll_personal_residence_province, R.id.ll_personal_residence_city, R.id.ll_personal_residence_street, R.id.ll_personal_residence_area,
            R.id.ll_personal_duration_of_residence, R.id.rl_personal_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_personal_gender:
                showGenderDialog();
                break;
            case R.id.ll_personal_education:
                showEducationDialog();
                break;
            case R.id.ll_personal_marital:
                showMaritalDialog();
                break;
            case R.id.ll_personal_children_number:
                showChildrenAccountDialog();
                break;
            case R.id.ll_personal_residence_province:
                mPresenter.getRegion(regionLevel.province.toString(),1,0);
                break;
            case R.id.ll_personal_residence_city:
                mPresenter.getRegion(regionLevel.city.toString(),chooseProvinceId,1);
                break;
            case R.id.ll_personal_residence_street:
                mPresenter.getRegion(regionLevel.district.toString(),chooseCityId,2);
                break;
            case R.id.ll_personal_residence_area:
                mPresenter.getRegion(regionLevel.area.toString(),chooseStreetId,3);
                break;
            case R.id.ll_personal_duration_of_residence:
                showDurationDialog();
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
        mBean.setGender(personalBean.getGender());
        personalEducation.setText(personalBean.getLastEducation());
        personalMarital.setText(getString(MarriageStatus.valueOf(personalBean.getMaritalStatus()).getShowString()));
        mBean.setMaritalStatus(personalBean.getMaritalStatus());
        personalChildrenNumber.setText(getString(ChildrenNumStatus.valueOf(personalBean.getChildrenNumber()).getShowString()));
        mBean.setChildrenNumber(personalBean.getChildrenNumber());
        personalResidenceProvince.setText(personalBean.getProvince());
        personalResidenceCity.setText(personalBean.getCity());
        personalResidenceStreet.setText(personalBean.getDistrict());
        personalResidenceArea.setText(personalBean.getArea());
        personalAddress.setText(personalBean.getAddress());
        personalDurationOfResidence.setText(getString(DurationStatus.valueOf(personalBean.getResidenceDuration()).getShowString()));
        mBean.setResidenceDuration(personalBean.getResidenceDuration());
    }

    @Override
    public void submitPersonOk() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showRegion(RegionsBean beans, int index) {
        InfoAdapterString adapterString = new InfoAdapterString(this);
        switch (index){
            case 0:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.PROVINCE,regionBean.getId(),regionBean.getLevel());
                }
                break;
            case 1:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.CITY,regionBean.getId(),regionBean.getLevel());
                }
                break;
            case 2:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.STREET,regionBean.getId(),regionBean.getLevel());
                }
                break;
            case 3:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.AREA,regionBean.getId(),regionBean.getLevel());
                }
                break;
        }

        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(adapterString)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    @Subscribe
    public void onSelected(InfoAdapterEnum.ItemSelectedEvent<InfoAdapterEnum.InfoItem> event){
        if(dialogPlus!=null&&dialogPlus.isShowing()){
            dialogPlus.dismiss();
        }
        if(event.data.getType()==InfoType.GENDER){
            personalGender.setText(event.data.getInfoStr());
            mBean.setGender(event.data.getValueStr());
        }else if(event.data.getType()==InfoType.MATRIAL){
            personalMarital.setText(event.data.getInfoStr());
            mBean.setMaritalStatus(event.data.getValueStr());
        }else if(event.data.getType()==InfoType.CHILDREN){
            personalChildrenNumber.setText(event.data.getInfoStr());
            mBean.setChildrenNumber(event.data.getValueStr());
        }else if(event.data.getType()==InfoType.DURATION){
            personalDurationOfResidence.setText(event.data.getInfoStr());
            mBean.setResidenceDuration(event.data.getValueStr());
        }
        updateSubmitSate();
    }

    @Subscribe
    public void onAreaSelected(InfoAdapterString.ItemSelectedEvent<InfoAdapterString.InfoItem> event){
        if(dialogPlus!=null&&dialogPlus.isShowing()){
            dialogPlus.dismiss();
        }
        if(event.data.getType()==InfoType.PROVINCE){
            personalResidenceProvince.setText(event.data.getInfoStr());
            chooseProvinceId = event.data.getId();
        }else if(event.data.getType()==InfoType.CITY){
            personalResidenceCity.setText(event.data.getInfoStr());
            chooseCityId = event.data.getId();
        }else if(event.data.getType()==InfoType.STREET){
            personalResidenceStreet.setText(event.data.getInfoStr());
            chooseStreetId = event.data.getId();
        }else if(event.data.getType()==InfoType.AREA){
            personalResidenceArea.setText(event.data.getInfoStr());
        }else if(event.data.getType()==InfoType.EDUCATION){
            personalEducation.setText(event.data.getInfoStr());
        }
        updateSubmitSate();
    }

    private void checkAndSubmit(){
        if(PersonalKtp.getText().toString().trim().length()!=16){
            PersonalKtp.setTextColor(Color.RED);
            mScrollView.scrollTo(0,0);
            HappySnackBar.showSnackBar(PersonalKtp, R.string.please_input_full_ktp_info, SPKeyUtils.SNACKBAR_TYPE_TIP);
            //HappySnackBar.showSnackBar();
        }else if(isCheckedField()){
            mBean.setFullName(personalName.getText().toString().trim());
            mBean.setCredentialNo(PersonalKtp.getText().toString().trim());
            mBean.setFamilyNameInLaw(personalFamilyName.getText().toString().trim());
            mBean.setProvince(personalResidenceProvince.getText().toString().trim());
            mBean.setCity(personalResidenceCity.getText().toString().trim());
            mBean.setDistrict(personalResidenceStreet.getText().toString().trim());
            mBean.setArea(personalResidenceArea.getText().toString().trim());
            mBean.setAddress(personalAddress.getText().toString().trim());
            mBean.setLastEducation(personalEducation.getText().toString().trim());

            mPresenter.submitPersonalInfo(mBean);
        }
    }

    private void addEditListener(){
        addTextChangeEvent();
        initInputCheck();
    }

    private void addTextChangeEvent(){
        addTextChangeEvent(personalName);
        addTextChangeEvent(PersonalKtp);
        addTextChangeEvent(personalFamilyName);
    }

    private void initInputCheck(){
        PersonalKtp.setOnCheckInputResult(new OnCheckInputResultAdapter() {
            @Override
            public boolean onCheckResult(EditText v) {
                return checkInput(v);
            }

            @Override
            public void onTextChanged(EditText v, Editable s) {
                if (s.length() > 16) {
                    v.setTextColor(Color.RED);
                }else{
                    v.setTextColor(getResources().getColor(R.color.color_text_gray));
                }
            }
        });
    }

    private boolean checkInput(EditText v) {
        Editable text = v.getText();
        if (text == null) {
            return false;
        }
        String result = text.toString();
        if (TextUtils.isEmpty(result)) {
            return false;
        }
        if (v == PersonalKtp) {
            if (result.length() != 16) {
                return false;
            }
            String substring = result.substring(6, 8);
            if (Integer.valueOf(substring) > 40) {
                personalGender.setText(R.string.enum_gender_female);
            } else {
                personalGender.setText(R.string.enum_gender_male);
            }
        }

        return true;
    }

    private void addTextChangeEvent(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateSubmitSate();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isCheckedField(){
        if(StringUtil.isNullOrEmpty(personalName.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(PersonalKtp.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalFamilyName.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalGender.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalResidenceProvince.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalResidenceCity.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalResidenceStreet.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalResidenceArea.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalAddress.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalEducation.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalMarital.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalChildrenNumber.getText().toString()))
            return false;
        if(StringUtil.isNullOrEmpty(personalResidenceArea.getText().toString()))
            return false;
        return true;
    }

    private void updateSubmitSate() {
        if (isCheckedField()) {
            confirm.setClickable(true);
            confirm.setAlpha(0.8f);
        } else {
            confirm.setClickable(false);
            confirm.setAlpha(0.3f);
        }
    }

    private void setGender(){

    }

    private void showGenderDialog(){
        final InfoAdapterEnum genderAdapter = (InfoAdapterEnum)getGenderAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(genderAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    private void showEducationDialog(){
        final InfoAdapterString educationAdapter = getEducationAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(educationAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    private void showMaritalDialog(){
        final InfoAdapterEnum maritalAdapter = (InfoAdapterEnum) getMaritalStatusAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(maritalAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    private void showChildrenAccountDialog(){
        final InfoAdapterEnum childrenAccountAdapter = (InfoAdapterEnum) getChildrenAccountAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(childrenAccountAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    private void showDurationDialog(){
        final InfoAdapterEnum durationAdapter = (InfoAdapterEnum) getDurationAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(durationAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    /**
     * Set  gender adapter
     */
    private InfoAdapter getGenderAdapter() {
        InfoAdapterEnum genderAdapter = new InfoAdapterEnum(PersonalInformation.this);
        genderAdapter.addItem(GenderStatus.MALE, InfoType.GENDER);
        genderAdapter.addItem(GenderStatus.FEMALE, InfoType.GENDER);
        return genderAdapter;
    }

    /**
     * Set education
     */
    public InfoAdapterString getEducationAdapter() {
        InfoAdapterString educationAdapter = new InfoAdapterString(PersonalInformation.this);
        educationAdapter.addItem("DIPLOMA_I", InfoType.EDUCATION);
        educationAdapter.addItem("DIPLOMA_II", InfoType.EDUCATION);
        educationAdapter.addItem("DIPLOMA_III", InfoType.EDUCATION);
        educationAdapter.addItem("SD", InfoType.EDUCATION);
        educationAdapter.addItem("SLTP", InfoType.EDUCATION);
        educationAdapter.addItem("SLTA", InfoType.EDUCATION);
        educationAdapter.addItem("S1", InfoType.EDUCATION);
        educationAdapter.addItem("S2", InfoType.EDUCATION);
        educationAdapter.addItem("S3", InfoType.EDUCATION);
        return educationAdapter;
    }

    /**
     * Set marital status
     */
    public InfoAdapter getMaritalStatusAdapter() {
        InfoAdapterEnum maritalAdapter = new InfoAdapterEnum(PersonalInformation.this);
        maritalAdapter.addItem(MarriageStatus.MARRIED, InfoType.MATRIAL);
        maritalAdapter.addItem(MarriageStatus.SINGLE, InfoType.MATRIAL);
        maritalAdapter.addItem(MarriageStatus.DIVORCED, InfoType.MATRIAL);
        maritalAdapter.addItem(MarriageStatus.WIDOWED, InfoType.MATRIAL);
        return maritalAdapter;
    }

    /**
     * Set children number
     */
    public InfoAdapter getChildrenAccountAdapter() {
        InfoAdapterEnum childrenAccountAdapter = new InfoAdapterEnum(PersonalInformation.this);
        childrenAccountAdapter.addItem(ChildrenNumStatus.ZERO, InfoType.CHILDREN);
        childrenAccountAdapter.addItem(ChildrenNumStatus.ONE, InfoType.CHILDREN);
        childrenAccountAdapter.addItem(ChildrenNumStatus.TWO, InfoType.CHILDREN);
        childrenAccountAdapter.addItem(ChildrenNumStatus.THREE, InfoType.CHILDREN);
        childrenAccountAdapter.addItem(ChildrenNumStatus.FOUR, InfoType.CHILDREN);
        childrenAccountAdapter.addItem(ChildrenNumStatus.OVER_FOUR, InfoType.CHILDREN);
        return childrenAccountAdapter;
    }

    /**
     * Set duration
     */
    public InfoAdapter getDurationAdapter() {
        InfoAdapterEnum areaAdapter = new InfoAdapterEnum(this);
        areaAdapter.addItem(DurationStatus.THREE_MONTH, InfoType.DURATION);
        areaAdapter.addItem(DurationStatus.SIX_MONTH, InfoType.DURATION);
        areaAdapter.addItem(DurationStatus.ONE_YEAR, InfoType.DURATION);
        areaAdapter.addItem(DurationStatus.TWO_YEAR, InfoType.DURATION);
        areaAdapter.addItem(DurationStatus.OVER_TWO_YEAR, InfoType.DURATION);
        return areaAdapter;
    }

    private void initUI() {
        personalName = (BandaEditText)findViewById(R.id.et_personal_name);
        llPersonalName = (LinearLayout)findViewById(R.id.ll_personal_name);
        PersonalKtp = (BandaEditText)findViewById(R.id.et_personal_ktp);
        llPersonalKtp = (LinearLayout)findViewById(R.id.ll_personal_ktp);
        personalFamilyName = (BandaEditText)findViewById(R.id.et_personal_family_name);

        llPersonalFamilyName = (LinearLayout)findViewById(R.id.ll_personal_family_name);
        personalGender = (TextView)findViewById(R.id.tv_personal_gender);
        llPersonalGender = (LinearLayout)findViewById(R.id.ll_personal_gender);
        personalEducation = (TextView)findViewById(R.id.tv_personal_education);
        llPersonalEducation = (LinearLayout)findViewById(R.id.ll_personal_education);
        personalMarital = (TextView)findViewById(R.id.tv_personal_marital);

        llPersonalMarital = (LinearLayout)findViewById(R.id.ll_personal_marital);
        personalChildrenNumber = (TextView)findViewById(R.id.tv_personal_children_number);
        llPersonalChildrenNumber = (LinearLayout)findViewById(R.id.ll_personal_children_number);
        personalResidenceProvince = (TextView)findViewById(R.id.tv_personal_residence_province);
        llPersonalResidenceProvince = (LinearLayout)findViewById(R.id.ll_personal_residence_province);

        personalResidenceCity = (TextView)findViewById(R.id.tv_personal_residence_city);
        llPersonalResidenceCity = (LinearLayout)findViewById(R.id.ll_personal_residence_city);
        personalResidenceStreet = (TextView)findViewById(R.id.tv_personal_residence_street);
        llPersonalResidenceStreet = (LinearLayout)findViewById(R.id.ll_personal_residence_street);
        personalResidenceArea = (TextView)findViewById(R.id.tv_personal_residence_area);

        llPersonalResidenceArea = (LinearLayout)findViewById(R.id.ll_personal_residence_area);
        personalAddress = (BandaEditText)findViewById(R.id.et_personal_address);
        llPersonalAddress = (LinearLayout)findViewById(R.id.ll_personal_address);
        personalDurationOfResidence = (TextView)findViewById(R.id.tv_personal_duration_of_residence);
        llPersonalDurationOfResidence = (LinearLayout)findViewById(R.id.ll_personal_duration_of_residence);

        confirm = (RelativeLayout)findViewById(R.id.rl_personal_confirm);
    }
}
