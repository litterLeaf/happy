package com.yinshan.happycash.view.loan.model;

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

public class BuildUpLineModel {

    BuildUpLineType buildUpType;
    int icon;
    String desc;

    public BuildUpLineModel(BuildUpLineType buildUpType, int icon, String desc) {
        this.buildUpType = buildUpType;
        this.icon = icon;
        this.desc = desc;
    }

    public BuildUpLineType getBuildUpType() {
        return buildUpType;
    }

    public void setBuildUpType(BuildUpLineType buildUpType) {
        this.buildUpType = buildUpType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public enum BuildUpLineType{
        CONTACT,KTP,BANK,JOB
    }
}
