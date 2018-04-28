package com.yinshan.happycash.view.information.model;

import java.util.List;

/**
 * Created by huxin on 2018/4/26.
 */

public class RegionsBean {

    private List<RegionBean> regions;

    public List<RegionBean> getRegions() {
        return regions;
    }

    public void setRegions(List<RegionBean> regions) {
        this.regions = regions;
    }

    public static class RegionBean {
        /**
         * id : 88021
         * level : city
         * name : Kab. Badung
         */

        private int id;
        private String level;
        private String name;


        public RegionBean() {
        }

        public RegionBean(int id, String level, String name) {
            this.id = id;
            this.level = level;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
