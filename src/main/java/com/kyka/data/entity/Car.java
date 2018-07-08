package com.kyka.data.entity;

public class Car {

    //车牌号
    private String licenseNum;

    //状态：0 进入车库 ，1 等待
    private int state;

    //在车库中的位置
    private int id;


    public Car() {
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
