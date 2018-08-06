package com.yinshan.happycash.view.login.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huxin on 2018/4/20.
 */

public class VersionBean implements Parcelable{
    /**
     * latestVersionCode : 12345
     * latestVersion : 2.1.1
     * releaseNotes : ["First desc","Second desc","Third desc"]
     * url : https://www.pgyer.com/rupiahplus
     */

    private int latestVersionCode;
    private String       latestVersion;
    private String       url;
    private List<String> releaseNotes;

    public int getLatestVersionCode() {
        return latestVersionCode;
    }

    public void setLatestVersionCode(int latestVersionCode) {
        this.latestVersionCode = latestVersionCode;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getReleaseNotes() {
        return releaseNotes;
    }

    public void setReleaseNotes(List<String> releaseNotes) {
        this.releaseNotes = releaseNotes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.latestVersionCode);
        dest.writeString(this.latestVersion);
        dest.writeString(this.url);
        dest.writeList(this.releaseNotes);
    }

    public VersionBean() {
    }

    private VersionBean(Parcel in) {
        this.latestVersionCode = in.readInt();
        this.latestVersion = in.readString();
        this.url = in.readString();
        this.releaseNotes = new ArrayList<String>();
        in.readList(this.releaseNotes, ArrayList.class.getClassLoader());
    }

    public static final Parcelable.Creator<VersionBean> CREATOR = new Parcelable.Creator<VersionBean>() {
        public VersionBean createFromParcel(Parcel source) {
            return new VersionBean(source);
        }

        public VersionBean[] newArray(int size) {
            return new VersionBean[size];
        }
    };
}
