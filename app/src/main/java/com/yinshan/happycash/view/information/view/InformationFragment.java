package com.yinshan.happycash.view.information.view;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yinshan.happycash.R;
import com.yinshan.happycash.framework.BaseFragment;
import com.yinshan.happycash.view.information.view.impl.ContactActivity;
import com.yinshan.happycash.view.information.view.impl.JobInformation;
import com.yinshan.happycash.view.information.view.impl.PersonalInformation;
import com.yinshan.happycash.view.information.view.impl.UploadPhotoActivity;
import com.yinshan.happycash.view.main.MainActivity;
import com.yinshan.happycash.widget.userdefined.ProfilProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * ┃　　　┃   神兽保佑
 * ┃　　　┃   代码无BUG！
 * ┃　　　┗━━━┓
 * ┃　　　　　　　┣┓
 * ┃　　　　　　　┏┛
 * ┗┓┓┏━┳┓┏┛
 * ┃┫┫　┃┫┫
 * ┗┻┛　┗┻┛
 *
 * @author admin
 *         on 2018/1/31
 */

public class InformationFragment extends BaseFragment {


    float progress;

    @BindView(R.id.progressView)
    ProfilProgressView mProgressView;
    @BindView(R.id.progressText)
    TextView mProgressText;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.view_not_finish_person)
    RelativeLayout notFinishPerson;
    @BindView(R.id.view_finish_person)
    RelativeLayout finishPerson;
    @BindView(R.id.view_not_finish_employ)
    RelativeLayout notFinishEmploy;
    @BindView(R.id.view_finish_employ)
    RelativeLayout finishEmploy;
    @BindView(R.id.view_not_finish_contact)
    RelativeLayout notFinishContact;
    @BindView(R.id.view_finish_contact)
    RelativeLayout finishContact;
    @BindView(R.id.view_not_finish_upload_photo)
    RelativeLayout notFinishUploadPhoto;
    @BindView(R.id.view_finish_upload_photo)
    RelativeLayout finishUploadPhoto;
    Unbinder unbinder;

    @Override
    protected void initView() {
        progress = 0.5f;
        mProgressView.setCurrentProgress(progress);
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append(String.valueOf(progress * 100));
        spannableString.append("%");
//        AbsoluteSizeSpan sizeSpan = new AbsoluteSizeSpan(20);
//        AbsoluteSizeSpan sizeSpan2 = new AbsoluteSizeSpan(10);
//        spannableString.setSpan(sizeSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spannableString.setSpan(sizeSpan2, 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mProgressText.setText(spannableString);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_information;
    }

    @OnClick({R.id.view_not_finish_person, R.id.view_finish_person, R.id.view_not_finish_employ, R.id.view_finish_employ,
            R.id.view_not_finish_contact,R.id.view_finish_contact,R.id.view_not_finish_upload_photo,R.id.view_finish_upload_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_not_finish_person:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), PersonalInformation.class);
                break;
            case R.id.view_finish_person:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), PersonalInformation.class);
                break;
            case R.id.view_not_finish_employ:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), JobInformation.class);
                break;
            case R.id.view_finish_employ:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), JobInformation.class);
                break;
            case R.id.view_not_finish_contact:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), ContactActivity.class);
                break;
            case R.id.view_finish_contact:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), ContactActivity.class);
                break;
            case R.id.view_not_finish_upload_photo:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), UploadPhotoActivity.class);
                break;
            case R.id.view_finish_upload_photo:
                ((MainActivity)getActivity()).mStartActivity(getActivity(), UploadPhotoActivity.class);
                break;
        }
    }
}
