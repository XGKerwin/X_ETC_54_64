package com.example.x_etc_54_64.bean;

import org.litepal.crud.LitePalSupport;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 15:27
 */
public class SSJT_ku extends LitePalSupport {

    private String lishi;
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLishi() {
        return lishi;
    }

    public void setLishi(String lishi) {
        this.lishi = lishi;
    }
}
