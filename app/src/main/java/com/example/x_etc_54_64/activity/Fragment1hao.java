package com.example.x_etc_54_64.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 8:45
 */
public class Fragment1hao extends Fragment {

    private List<HJZB> hjzbs;
    private TextView txt1;
    private TextView txt2;
    private TextView txtPm;
    private TextView txtShidu;
    private List<GJJL> gjjlList;
    private String TAG = "frrr1";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_1hao, null);
        initView(view);

        getOkHttp();


        return view;
    }

    private void getOkHttp() {
        if (hjzbs == null){
            hjzbs = new ArrayList<>();
        }
        new OkHttpTo()
                .setUrl("get_all_sense")
                .setJsonObject("UserName","user1")
                .setTime(3000)
                .setIsLoop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        hjzbs.clear();
                        hjzbs.add(new Gson().fromJson(jsonObject.toString(),HJZB.class));

                        txtPm.setText("PM2.5："+hjzbs.get(0).getPm25()+"ug/m³，温度："+hjzbs.get(0).getTemperature()+"℃");
                        txtShidu.setText("湿度："+hjzbs.get(0).getHumidity()+"%, CO2："+hjzbs.get(0).getCo2()+"PPM");

                        getgongjiao();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getgongjiao() {
        if (gjjlList == null){
            gjjlList = new ArrayList<>();
        }else {
            gjjlList.clear();
        }
        new OkHttpTo()
                .setUrl("get_bus_stop_distance")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

//                        gjjlList.addAll((Collection<? extends GJJL>) new Gson().fromJson(jsonObject.optJSONArray("中医院站").toString(),
//                                new TypeToken<List<GJJL>>(){}.getRawType()));

                        gjjlList = new Gson().fromJson(jsonObject.optJSONArray("中医院站").toString()
                                , new TypeToken<List<GJJL>>() {}.getType());

                        Log.i(TAG, "onResponse: "+gjjlList.size());

                        Log.i(TAG, "onResponse: "+gjjlList.get(0).getBus());

                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();


    }

    private void show() {
        for (int i=0;i<gjjlList.size();i++){
            GJJL gjjl = gjjlList.get(i);
            if (i==0){
                txt1.setText("1号公交车："+gjjl.getDistance()+" m");
            }
            else if (i==1){
                txt2.setText("2号公交车："+gjjl.getDistance()+" m");
            }


        }

    }

    private void initView(View view) {
        txt1 = view.findViewById(R.id.txt_1);
        txt2 = view.findViewById(R.id.txt_2);
        txtPm = view.findViewById(R.id.txt_pm);
        txtShidu = view.findViewById(R.id.txt_shidu);
    }
}
