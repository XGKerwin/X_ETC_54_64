package com.example.x_etc_54_64;

import android.app.Application;

import com.example.x_etc_54_64.bean.YHGL;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 15:33
 */
public class App extends Application {

    private List<YHGL> yhgls = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

    public List<YHGL> getYhgls() {
        return yhgls;
    }
}
