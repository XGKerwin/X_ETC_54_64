package com.example.x_etc_54_64.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/20 10:56
 */
public class HJJC_bean {

    private String mag;
    private int max,min,aver;

    public HJJC_bean(String mag, int max, int min, int aver) {
        this.mag = mag;
        this.max = max;
        this.min = min;
        this.aver = aver;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getAver() {
        return aver;
    }

    public void setAver(int aver) {
        this.aver = aver;
    }
}
