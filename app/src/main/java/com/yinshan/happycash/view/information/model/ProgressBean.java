package com.yinshan.happycash.view.information.model;

/**
 * Created by huxin on 2018/4/10.
 */

public class ProgressBean {

    private boolean personalInfoPart;
    private boolean employmentPart;
    private boolean contactPart;
    private boolean filePart;
    private boolean bpjsPart;
    private boolean completed;

    public boolean isPersonalInfoPart() {
        return personalInfoPart;
    }

    public void setPersonalInfoPart(boolean personalInfoPart) {
        this.personalInfoPart = personalInfoPart;
    }

    public boolean isEmploymentPart() {
        return employmentPart;
    }

    public void setEmploymentPart(boolean employmentPart) {
        this.employmentPart = employmentPart;
    }

    public boolean isContactPart() {
        return contactPart;
    }

    public void setContactPart(boolean contactPart) {
        this.contactPart = contactPart;
    }

    public boolean isFilePart() {
        return filePart;
    }

    public void setFilePart(boolean filePart) {
        this.filePart = filePart;
    }

    public boolean isBpjsPart() {
        return bpjsPart;
    }

    public void setBpjsPart(boolean bpjsPart) {
        this.bpjsPart = bpjsPart;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void clear(){
        personalInfoPart = false;
        employmentPart = false;
        contactPart = false;
        filePart = false;
        completed = false;
    }
}
