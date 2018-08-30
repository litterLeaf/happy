package com.yinshan.happycash.view.loan.view.impl;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hwangjr.rxbus.RxBus;
import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.framework.DateManager;
import com.yinshan.happycash.utils.SPKeyUtils;
import com.yinshan.happycash.utils.SPUtils;
import com.yinshan.happycash.view.information.model.ContactBean;
import com.yinshan.happycash.view.information.model.EmploymentBean;
import com.yinshan.happycash.view.information.model.PersonalBean;
import com.yinshan.happycash.view.loan.model.BankInfoBean;
import com.yinshan.happycash.view.loan.model.BuildUpLineModel;
import com.yinshan.happycash.view.loan.model.BuildUpReasonItem;
import com.yinshan.happycash.view.loan.model.BuildUpReasonModel;
import com.yinshan.happycash.view.loan.view.impl.support.BuildUpLineAdapter;
import com.yinshan.happycash.view.loan.view.impl.support.BuildUpReasonAdapter;
import com.yinshan.happycash.view.main.model.LastLoanAppBean;
import com.yinshan.happycash.widget.SpaceItemDecoration;
import com.yinshan.happycash.widget.pullrefresh.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin on 2018/2/1.
 */

public class BuildUpFragment extends BaseFragment {
    @BindView(R.id.reasonList)
    RecyclerView mReasonList;
    @BindView(R.id.togoList)
    RecyclerView togoList;
    @BindView(R.id.build_up_submit_btn)
    Button buildUpSubmitBtn;
    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;
    List<BuildUpReasonModel> reasonList;
    List<BuildUpLineModel> lineList;

    public static List<ContactBean>        mContactInfoBean;
    public static EmploymentBean mEmploymentBean;
    public static PersonalBean mPersonalInfoBean;
    public static BankInfoBean mBankBean;
    public static long loanAppId;

    private static final int REQUEST_WORK = 1100;
    private static final int REQUEST_BANK = 1101;
    private static final int REQUEST_CONTACT      = 1102;
    private static final int REQUEST_PHOTO        = 1103;

    public static final String CONTACT_PRE_STR = "SAME";
    public static final String PHOTO_PRE_STR = "PHOTO";
    public static final String BANK_PRE_STR = "BANK";
    public static final String WORK_PRE_STR = "COMPANY";

    private BuildUpReasonAdapter mReasonAdapter;
    private BuildUpLineAdapter mLineAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_build_up;
    }


    @Override
    protected void initView() {
        mReasonList.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        mReasonList.setHasFixedSize(true);
        mReasonList.addItemDecoration(new SpaceItemDecoration(0));
        reasonList = new ArrayList<>();
//        mProgressBean = new ProgressBean();

        RxBus.get().register(this);

        LastLoanAppBean loanBean = (LastLoanAppBean) DateManager.getMessage(getActivity(),SPKeyUtils.LOANAPPBEAN);

        if(loanBean!=null){
            String comments = loanBean.getComments();
            loanAppId = Long.valueOf(loanBean.getLoanAppId());
            if(comments!=null){
                Log.v("huxin","string is "+comments);
                if(comments.length()>4){
                    String str1 = comments.substring(1,comments.length()-1);
                    String[] array = str1.split("\\},\\{");
                    if(array!=null&&array.length>=1){
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        for(int i=0;i<array.length;i++){
                            String lStr = array[i];
                            if(i==0&&array.length>1)
                                lStr = lStr+"}";
                            else if(i==0&&array.length==1) {
                            }else if(i==array.length-1)
                                lStr = "{"+lStr;
                            else
                                lStr = "{"+lStr+"}";
                            BuildUpReasonItem item = gson.fromJson(lStr,BuildUpReasonItem.class);
                            reasonList.add(new BuildUpReasonModel(item.getCode(),item.getReason(),item.getDetail()));
                        }
                    }
                }
            }
        }

        mReasonAdapter = new BuildUpReasonAdapter(reasonList);
        mReasonList.setAdapter(mReasonAdapter);

        lineList = new ArrayList<>();
        for(int i=0;i<reasonList.size();i++){
            BuildUpReasonModel buildUpReasonModel = reasonList.get(i);
            if(buildUpReasonModel.getCode().startsWith(CONTACT_PRE_STR))
                addLineList(BuildUpLineModel.BuildUpLineType.CONTACT,R.drawable.back1,R.string.build_up_modify_contact_info);
            else if(buildUpReasonModel.getCode().startsWith(PHOTO_PRE_STR))
                addLineList(BuildUpLineModel.BuildUpLineType.KTP,R.drawable.back2,R.string.build_up_upload_ktp);
            else if(buildUpReasonModel.getCode().startsWith(BANK_PRE_STR))
                addLineList(BuildUpLineModel.BuildUpLineType.BANK,R.drawable.back3,R.string.build_up_upload_bank_info);
            else if(buildUpReasonModel.getCode().startsWith(WORK_PRE_STR))
                addLineList(BuildUpLineModel.BuildUpLineType.JOB,R.drawable.back4,R.string.build_up_modify_job_info);
        }

    }

    @Override
    protected void initUIValue(View view) {

    }

    @OnClick(R.id.build_up_submit_btn)
    public void onViewClicked() {

    }

    private void addLineList( BuildUpLineModel.BuildUpLineType type, int drawable, int strIndex) {
        boolean isFind = false;
        for(int j=0;j<lineList.size();j++){
            if(lineList.get(j).getBuildUpType()== type)
                isFind = true;
        }
        if(isFind){

        }else{
            lineList.add(new BuildUpLineModel(type, drawable,getResources().getString(strIndex)));
        }
    }

}
