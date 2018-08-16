package com.yinshan.happycash.framework;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by admin on 2017/8/11.
 */

public class BaseStatusLogsBean implements Parcelable ,Serializable{
    private String createTime;
    private String status;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createTime);
        dest.writeString(this.status);
    }


    protected BaseStatusLogsBean(Parcel in) {
        this.createTime = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<BaseStatusLogsBean> CREATOR = new Parcelable.Creator<BaseStatusLogsBean>() {
        @Override
        public BaseStatusLogsBean createFromParcel(Parcel source) {
            return new BaseStatusLogsBean(source);
        }

        @Override
        public BaseStatusLogsBean[] newArray(int size) {
            return new BaseStatusLogsBean[size];
        }
    };
}
