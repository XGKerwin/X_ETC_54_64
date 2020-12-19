package com.example.x_etc_54_64.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 17:58
 */
public class CXJG {

    private String number,didian,msg;

    public CXJG(String number, String didian, String msg) {
        this.number = number;
        this.didian = didian;
        this.msg = msg;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDidian() {
        return didian;
    }

    public void setDidian(String didian) {
        this.didian = didian;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
