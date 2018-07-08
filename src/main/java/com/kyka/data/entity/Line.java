package com.kyka.data.entity;

public class Line {

    private int id;

    private boolean shortest;

    private int length;

    private int time;

    private String sideName1;

    private String sideName2;

    public Line() {
    }

    public Line(int length) {
        this.length = length;
        this.shortest=false;
        this.time=length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Line(int length, String sideName1, String sideName2) {
        this.length = length;
        this.sideName1 = sideName1;
        this.sideName2=sideName2;
        this.time=length;
        this.shortest=false;
    }

    public Line(boolean shortest, int length, int time, String sideName1, String sideName2) {
        this.shortest = shortest;
        this.length = length;
        this.time = time;
        this.sideName1 = sideName1;
        this.sideName2 = sideName2;
    }

    public boolean isShortest() {
        return shortest;
    }

    public void setShortest(boolean shortest) {
        this.shortest = shortest;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSideName1() {
        return sideName1;
    }

    public void setSideName1(String sideName1) {
        this.sideName1 = sideName1;
    }

    public String getSideName2() {
        return sideName2;
    }

    public void setSideName2(String sideName2) {
        this.sideName2 = sideName2;
    }
}
