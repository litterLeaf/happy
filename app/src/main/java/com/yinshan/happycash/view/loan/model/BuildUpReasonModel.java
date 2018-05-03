package com.yinshan.happycash.view.loan.model;

import android.os.Parcel;
import android.os.Parcelable;

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
 *  on 2018/4/28
 *
 */

public class BuildUpReasonModel implements Parcelable {

    String code;
    String reason;
    String detail;

    public BuildUpReasonModel(String code, String reason, String detail) {
        this.code = code;
        this.reason = reason;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected BuildUpReasonModel(Parcel in){
        this.code = in.readString();
        this.reason = in.readString();
        this.detail = in.readString();
    }

    public static final Creator<BuildUpReasonModel> CREATOR = new Creator<BuildUpReasonModel>() {
        @Override
        public BuildUpReasonModel createFromParcel(Parcel in) {
            return new BuildUpReasonModel(in);
        }

        @Override
        public BuildUpReasonModel[] newArray(int size) {
            return new BuildUpReasonModel[size];
        }
    };
}
