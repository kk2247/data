package com.kyka.data.entity;

public class ScenicSpot {

    private int id;

    //景点名称
    private String name;

    //景点简介
    private String introduce;

    //景点的欢迎度
    private int welcome;

    //是否有休息区
    private boolean relax;

    //是否有公厕
    private boolean toilet;

    public ScenicSpot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScenicSpot(String name, String introduce, int welcome, boolean relax, boolean toilet) {
        this.name = name;
        this.introduce = introduce;
        this.welcome = welcome;
        this.relax = relax;
        this.toilet = toilet;
    }

    public ScenicSpot(String name) {
        this.name = name;
        this.introduce = "";
        this.welcome = 0;
        this.relax = true;
        this.toilet = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getWelcome() {
        return welcome;
    }

    public void setWelcome(int welcome) {
        this.welcome = welcome;
    }

    public boolean isRelax() {
        return relax;
    }

    public void setRelax(boolean relax) {
        this.relax = relax;
    }

    public boolean isToilet() {
        return toilet;
    }

    public void setToilet(boolean toilet) {
        this.toilet = toilet;
    }
}
