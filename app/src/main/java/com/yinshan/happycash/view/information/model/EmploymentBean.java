package com.yinshan.happycash.view.information.model;

import java.util.List;

/**
 * Created by huxin on 2018/4/19.
 */

public class EmploymentBean {

    /**
     * companyAddress : string
     * companyArea : string
     * companyCity : string
     * companyDistrict : string
     * companyName : string
     * companyPhone : string
     * companyProvince : string
     * profession : PROGRAMMER
     * salary : BELOW_2B
     * workEmail : string
     */

    private String companyAddress;
    private String companyArea;
    private String companyCity;
    private String companyDistrict;
    private String companyName;
    private String companyPhone;
    private String companyProvince;
    private String profession;
    private String salary;
    private String salaryDay;
    private List<String> areaCodes;

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyDistrict() {
        return companyDistrict;
    }

    public void setCompanyDistrict(String companyDistrict) {
        this.companyDistrict = companyDistrict;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalaryDay() {
        return salaryDay;
    }

    public void setSalaryDay(String salaryDay) {
        this.salaryDay = salaryDay;
    }

    public List<String> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(List<String> areaCodes) {
        this.areaCodes = areaCodes;
    }
}
