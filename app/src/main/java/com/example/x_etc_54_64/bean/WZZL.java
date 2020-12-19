package com.example.x_etc_54_64.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 17:17
 */
public class WZZL {

    /**
     *             "infoid": "10101",
     *             "road": "学院路",
     *             "message": "在交叉路口不按导向标线行驶在相应车道。",
     *             "deduct": 1,
     *             "fine": 0
     */

    private String infoid,road,message,deduct,fine;

    public String getInfoid() {
        return infoid;
    }

    public void setInfoid(String infoid) {
        this.infoid = infoid;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeduct() {
        return deduct;
    }

    public void setDeduct(String deduct) {
        this.deduct = deduct;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}
