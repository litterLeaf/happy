package com.yinshan.happycash.view.information.view.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseSingleActivity;
import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.JobStatus;
import com.yinshan.happycash.view.information.model.SalaryStatus;
import com.yinshan.happycash.view.information.presenter.JobPresenter;
import com.yinshan.happycash.view.information.view.IJobView;
import com.yinshan.happycash.widget.droplistview.DropListView;
import com.yinshan.happycash.widget.userdefined.BandaEditText;

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
    @BindView(R.id.ll_job_children_number)
    LinearLayout llJobChildrenNumber;
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
    DropListView bdlvJobInfoTelpre;
    @BindView(R.id.id_edittext_job_info_tel)
    BandaEditText edittextJobInfoTel;
    @BindView(R.id.rl_job_confirm)
    RelativeLayout jobConfirm;

    JobPresenter mPresenter;
    EmploymentBean mBean;

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
    }



    @OnClick({R.id.ll_job_work_type, R.id.ll_job_monthly_income, R.id.ll_job_company_name, R.id.ll_job_children_number,
            R.id.ll_job_company_city, R.id.ll_job_company_street, R.id.ll_job_company_in_the_village, R.id.rl_job_confirm})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_job_work_type:
                break;
            case R.id.ll_job_monthly_income:
                break;
            case R.id.ll_job_company_name:
                break;
            case R.id.ll_job_children_number:
                break;
            case R.id.ll_job_company_city:
                break;
            case R.id.ll_job_company_street:
                break;
            case R.id.ll_job_company_in_the_village:
                break;
            case R.id.rl_job_confirm:
                submitData();
                break;
        }
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
        mBean.setCompanyName("test");
        mPresenter.submitJobInfo(mBean);
    }

    @Override
    public void submitOk() {
        finish();
    }
}
