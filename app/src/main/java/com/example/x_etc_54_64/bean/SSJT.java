package com.example.x_etc_54_64.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 10:41
 */
public class SSJT implements Serializable {

    /**
     *          "id": 1,
     *          "start": "06:23:00",
     *          "end": "21:19:00",
     *          "mileage": "20",
     *          "price": "8",
     *          "site": [
     */

    private String id,start,end,mileage,price;
    private List<String> site;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getSite() {
        return site;
    }

    public void setSite(List<String> site) {
        this.site = site;
    }
}
