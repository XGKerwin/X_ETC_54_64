package com.example.x_etc_54_64.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 8:56
 */
public class HJZB {

    /**
     *     "temperature": 26,
     *     "humidity": 21,
     *     "illumination": 3067,
     *     "co2": 520,
     *     "pm25": 225,
     */

    private String temperature,humidity,illumination,co2,pm25;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getIllumination() {
        return illumination;
    }

    public void setIllumination(String illumination) {
        this.illumination = illumination;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }
}
