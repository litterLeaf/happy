package com.yinshan.happycash.view.information.view.impl;

import android.app.Dialog;
import android.os.Bundle;
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
import com.yinshan.happycash.analytic.event.MobAgent;
import com.yinshan.happycash.analytic.event.MobEvent;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.utils.StringUtil;
import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.JobStatus;
import com.yinshan.happycash.view.information.model.RegionsBean;
import com.yinshan.happycash.view.information.model.SalaryStatus;
import com.yinshan.happycash.view.information.presenter.JobPresenter;
import com.yinshan.happycash.view.information.view.IJobView;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapter;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterEnum;
import com.yinshan.happycash.view.information.view.impl.support.InfoAdapterString;
import com.yinshan.happycash.view.information.view.impl.support.InfoType;
import com.yinshan.happycash.widget.dialog.DialogManager;
import com.yinshan.happycash.widget.droplistview.DrapListViewAdapter;
import com.yinshan.happycash.widget.droplistview.DropListView;
import com.yinshan.happycash.widget.userdefined.BandaEditText;
import com.yinshan.happycash.widget.userdefined.GoEditTextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/4/2.
 */

public class JobInformation extends BaseSingleActivity implements IJobView{
    @BindView(R.id.tv_job_work_type)
    TextView jobWorkType;
    @BindView(R.id.ll_job_work_type)
    LinearLayout llJobWorkType;
    @BindView(R.id.tv_job_monthly_income)
    TextView jobMonthlyIncome;
    @BindView(R.id.ll_job_monthly_income)
    LinearLayout llJobMonthlyIncome;
    @BindView(R.id.tv_job_company_name)
    BandaEditText jobCompanyName;
    @BindView(R.id.ll_job_company_name)
    LinearLayout llJobCompanyName;
    @BindView(R.id.tv_job_company_province)
    TextView jobCompanyProvince;
    @BindView(R.id.ll_job_company_provice)
    LinearLayout llCompanyProvice;
    @BindView(R.id.tv_job_company_city)
    TextView jobCompanyCity;
    @BindView(R.id.ll_job_company_city)
    LinearLayout llJobCompanyCity;
    @BindView(R.id.tv_job_company_street)
    TextView jobCompanyStreet;
    @BindView(R.id.ll_job_company_street)
    LinearLayout llJobCompanyStreet;
    @BindView(R.id.tv_job_company_in_the_village)
    TextView jobCompanyInTheVillage;
    @BindView(R.id.ll_job_company_in_the_village)
    LinearLayout llJobCompanyInTheVillage;
    @BindView(R.id.et_job_company_address)
    BandaEditText jobCompanyAddress;
    @BindView(R.id.ll_job_company_address)
    LinearLayout llJobCompanyAddress;
    @BindView(R.id.bdlv_job_info_telpre)
    DropListView mbdlv;
    @BindView(R.id.id_edittext_job_info_tel)
    BandaEditText edittextJobInfoTel;
    @BindView(R.id.rl_job_confirm)
    RelativeLayout jobConfirm;

    JobPresenter mPresenter;
    EmploymentBean mBean;
    Dialog dialogPlus;

    private DrapListViewAdapter mAdapter;
    private List<String> mTelPreStrings= new ArrayList<>();

    int chooseProvinceId = -1;
    int chooseCityId = -1;
    int chooseStreetId = -1;

    @Override
    protected String bindTitle() {
        return getString(R.string.textview_job_info_title);
    }

    @Override
    protected int bindDownLayout() {
        return R.layout.activity_job;
    }

    @Override
    protected void secondInit() {
        mPresenter = new JobPresenter(this,this);
        mPresenter.getJobInfo();

        RxBus.get().register(this);
        initWorkTelPre();
        jobWorkType.requestFocus();
        mBean = new EmploymentBean();
        initListener();
        initDataUpload();
        updateButtonState();
    }

    private void initDataUpload(){
        initPasteUpload();
        initTxtTimeUpload();
    }

    private void initPasteUpload(){
        onPasteListener(jobCompanyName,"COMPANY_NAME");
        onPasteListener(jobCompanyAddress,"COMPANY_ADDRESS");
        onPasteListener(edittextJobInfoTel,"COMPANY_TELEPHONE");
    }

    private void initTxtTimeUpload(){
        onFocusChange(jobCompanyName, MobEvent.INPUT_COMPANY_NAME);
        onFocusChange(jobCompanyAddress, MobEvent.INPUT_COMPANY_ADDRESS);
        onFocusChange(edittextJobInfoTel, MobEvent.INPUT_COMPANY_TELPHONE);
    }

    private void initListener(){
        initTextChangeEvent();
    }

    private void initTextChangeEvent(){
        addTextChangeEvent(jobCompanyName);
        addTextChangeEvent(jobCompanyAddress);
        addTextChangeEvent(edittextJobInfoTel);
    }

    private void addTextChangeEvent(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 获取区号
     */
    private void initWorkTelPre() {
//        if (mTelPreStrings != null) {
        mAdapter = new DrapListViewAdapter(mbdlv, R.layout.my_simple_spinner_item, mTelPreStrings);
        mbdlv.setAdapter(mAdapter);
//        }
    }

    @OnClick({R.id.ll_job_work_type, R.id.ll_job_monthly_income,  R.id.ll_job_company_provice,
            R.id.ll_job_company_city, R.id.ll_job_company_street, R.id.ll_job_company_in_the_village, R.id.rl_job_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_job_work_type:
                showWorkType();
                break;
            case R.id.ll_job_monthly_income:
                showSalary();
                break;
            case R.id.ll_job_company_provice:
                mPresenter.getRegion(PersonalInformation.regionLevel.province.toString(),1,0);
                break;
            case R.id.ll_job_company_city:
                mPresenter.getRegion(PersonalInformation.regionLevel.city.toString(),chooseProvinceId,1);
                break;
            case R.id.ll_job_company_street:
                mPresenter.getRegion(PersonalInformation.regionLevel.district.toString(),chooseCityId,2);
                break;
            case R.id.ll_job_company_in_the_village:
                mPresenter.getRegion(PersonalInformation.regionLevel.area.toString(),chooseStreetId,3);
                break;
            case R.id.rl_job_confirm:
                submitData();
                break;
        }
    }

    private void showWorkType(){
        final InfoAdapterEnum jobTypeAdapter = (InfoAdapterEnum) getJobTypeAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(jobTypeAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    private void showSalary(){
        final InfoAdapterEnum salaryAdapter = (InfoAdapterEnum) getSalaryAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(salaryAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    @Override
    public void showInfo(EmploymentBean bean) {
        mBean = bean;
        jobWorkType.setText(getString(JobStatus.valueOf(bean.getProfession()).getShowString()));
        jobMonthlyIncome.setText(getString(SalaryStatus.valueOf(bean.getSalary()).getShowString()));
        jobCompanyName.setText(bean.getCompanyName());
        jobCompanyProvince.setText(bean.getCompanyProvince());
        jobCompanyCity.setText(bean.getCompanyCity());
        jobCompanyStreet.setText(bean.getCompanyDistrict());
        jobCompanyInTheVillage.setText(bean.getCompanyArea());
        jobCompanyAddress.setText(bean.getCompanyAddress());
        String companyPhone = bean.getCompanyPhone();
        List<String> areaCodes = bean.getAreaCodes();
        if (areaCodes != null) {
            for (int i = 0; i < areaCodes.size(); i++) {
                if (companyPhone.startsWith(areaCodes.get(i))) {
                    companyPhone = companyPhone.substring(areaCodes.get(i).length());
                    break;
                }
            }
        }
        if(!TextUtils.isEmpty(companyPhone)){
            String[] c=bean.getCompanyPhone().split(companyPhone);
            if(c.length>0){
                String areaCode = c[0];
                if(areaCode!=null){
                    if(!TextUtils.isEmpty(areaCode)){
//                        mbdlv.setText(areaCode);
                    }else {
//                        mbdlv.setText(areaCodes.get(0));
                    }
                }
            }
        }
        edittextJobInfoTel.setText(companyPhone);
    }

    private void submitData(){
        MobAgent.onEvent(MobEvent.CLICK+MobEvent.WORK_CONFIRM);
        if(isCheckedField()){
            mBean.setCompanyName(jobCompanyName.getText().toString().trim());
            mBean.setCompanyAddress(jobCompanyAddress.getText().toString().trim());
            mBean.setCompanyPhone(edittextJobInfoTel.getText().toString().trim());
            mPresenter.submitJobInfo(mBean);
        }
    }

    @Override
    public void submitOk() {
        setResult(RESULT_OK);
        finish();
    }

    @Subscribe
    public void onSelected(InfoAdapterEnum.ItemSelectedEvent<InfoAdapterEnum.InfoItem> event){
        if(dialogPlus!=null&&dialogPlus.isShowing()){
            dialogPlus.dismiss();
        }
        if(event.data.getType()==InfoType.JOBTYPE){
            jobWorkType.setText(event.data.getInfoStr());
            mBean.setProfession(event.data.getValueStr());
        }else if(event.data.getType()==InfoType.SALARY){
            jobMonthlyIncome.setText(event.data.getInfoStr());
            mBean.setSalary(event.data.getValueStr());
        }
        updateButtonState();
    }

    @Subscribe
    public void onAreaSelected(InfoAdapterString.ItemSelectedEvent<InfoAdapterString.InfoItem> event){
        if(dialogPlus!=null&&dialogPlus.isShowing()){
            dialogPlus.dismiss();
        }
        if(event.data.getType()==InfoType.C_PROVINCE){
            jobCompanyProvince.setText(event.data.getInfoStr());
            mBean.setCompanyProvince(event.data.getValueStr());
            chooseProvinceId = event.data.getId();
        }else if(event.data.getType()==InfoType.C_CITY){
            jobCompanyCity.setText(event.data.getInfoStr());
            mBean.setCompanyCity(event.data.getValueStr());
            chooseCityId = event.data.getId();
            mPresenter.getCityTel(chooseCityId);
        }else if(event.data.getType()==InfoType.C_STREET){
            jobCompanyStreet.setText(event.data.getInfoStr());
            mBean.setCompanyDistrict(event.data.getValueStr());
            chooseStreetId = event.data.getId();
        }else if(event.data.getType()==InfoType.C_AREA){
            jobCompanyInTheVillage.setText(event.data.getInfoStr());
            mBean.setCompanyArea(event.data.getValueStr());
        }
        updateButtonState();
    }

    @Override
    public void showRegion(RegionsBean beans, int index) {
        InfoAdapterString adapterString = new InfoAdapterString(this);
        switch (index){
            case 0:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.C_PROVINCE,regionBean.getId(),regionBean.getLevel());
                }
                break;
            case 1:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.C_CITY,regionBean.getId(),regionBean.getLevel());
                }
                break;
            case 2:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.C_STREET,regionBean.getId(),regionBean.getLevel());
                }
                break;
            case 3:
                for(RegionsBean.RegionBean regionBean:beans.getRegions()){
                    adapterString.addItem(regionBean.getName(),InfoType.C_AREA,regionBean.getId(),regionBean.getLevel());
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

    @Override
    public void showTelList(List<String> strs) {
        if(strs!=null){
            mTelPreStrings.clear();
            for(String s :strs){
                mTelPreStrings.add(s);
            }
            initWorkTelPre();
            if(mTelPreStrings!=null&&mTelPreStrings.size()>0){
                mbdlv.setText(mTelPreStrings.get(0));
            }
        }
    }

    /**
     * Set JobType adapter;
     *
     * @return
     */
    public InfoAdapter getJobTypeAdapter() {
        InfoAdapterEnum jobTypeAdapter = new InfoAdapterEnum(this);
        jobTypeAdapter.addItem(JobStatus.ACCOUNTING, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.WAITER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.ENGINEER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.EXECUTIVE, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.GENERAL_ADMINISTRATION, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.INFORMATION_TECHNOLOGY, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.CONSULTANT, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.MARKETING, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.TEACHER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.MILITARY, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.RETIRED, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.STUDENT, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.ENTREPRENEUR, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.POLICE, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.FARMER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.FISHERMAN, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.BREEDER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.DOCTOR, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.MEDICAL_PERSONNEL, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.LAWYER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.CHEF, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.RESEARCHER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.DESIGNER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.ARCHITECT, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.WORKERS_ART, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.SECURITY, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.BROKER, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.DISTRIBUTOR, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.AIR_TRANSPORTATION, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.SEA_TRANSPORTATION, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.LAND_TRANSPORTATION, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.LABOR, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.CRAFTSMAN, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.HOUSEWIFE, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.STATE_OFFICIALS, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.GOVERNMENT_EMPLOYEE, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.INFORMAL_WORKERS, InfoType.JOBTYPE);
        jobTypeAdapter.addItem(JobStatus.OTHER, InfoType.JOBTYPE);
        return jobTypeAdapter;
    }

    /**
     * BELOW_2B("below 2,000,000", "小于2,000,000", ""),
     * BETWEEN_2B_4B("between 2,000,000 and 4,000,000", "2,000,000-4,000,000之间", ""),
     * BETWEEN_4B_8B("between 4,000,000 and 8,000,000", "4,000,000-8,000,000之间", ""),
     * BETWEEN_8B_12B("between 8,000,000 and 12,000,000", "8,000,000-12,000,000之间", ""),
     * BETWEEN_12B_20B("between 12,000,000 and 20,000,000", "12,000,000-20,000,000之间", ""),
     * OVER_20B("over 20,000,000", "20,000,000以上", "");
     *
     * @return
     */

    public InfoAdapter getSalaryAdapter() {
        InfoAdapterEnum salaryAdapter = new InfoAdapterEnum(this);
        salaryAdapter.addItem(SalaryStatus.BELOW_2B, InfoType.SALARY);
        salaryAdapter.addItem(SalaryStatus.BETWEEN_2B_4B, InfoType.SALARY);
        salaryAdapter.addItem(SalaryStatus.BETWEEN_4B_8B, InfoType.SALARY);
//        salaryAdapter.addItem(SalaryStatus.BETWEEN_12B_20B, InfoType.SALARY);
        salaryAdapter.addItem(SalaryStatus.OVER_8B, InfoType.SALARY);
        return salaryAdapter;
    }

    private void showJobType(){
        final InfoAdapterEnum jobTypeAdapter = (InfoAdapterEnum) getJobTypeAdapter();
        dialogPlus = DialogManager.newListViewDialog(this)
                .setAdapter(jobTypeAdapter)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .create();
        dialogPlus.show();
    }

    private boolean isCheckedField(){
        if (StringUtil.isNullOrEmpty(jobWorkType.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobMonthlyIncome.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyName.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyProvince.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyCity.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyStreet.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyInTheVillage.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyAddress.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(jobCompanyAddress.getText().toString())) {
            return false;
        }
        if (StringUtil.isNullOrEmpty(edittextJobInfoTel.getText().toString())) {
            return false;
        }
        return true;
    }

    //update submit button
    private void updateButtonState() {
        if (isCheckedField()) {
            jobConfirm.setClickable(true);
            jobConfirm.setAlpha(0.8f);
        } else {
            jobConfirm.setClickable(false);
            jobConfirm.setAlpha(0.3f);
        }
    }
}
