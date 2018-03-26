package com.yinshan.happycash.view.me.model;

/**
 * Created by huxin on 2018/3/22.
 */

public class NameDescData {

    String name;
    String desc;

    public NameDescData(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
