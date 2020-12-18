package com.example.x_etc_54_64.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.GJJL;
import com.example.x_etc_54_64.bean.HJZB;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 8:45
 */
public class Fragment2hao extends Fragment {

    private TextView txt1;
    private TextView txt2;
    private TextView txtPm;
    private TextView txtShidu;
    private List<GJJL> gjjlList;
    private List<HJZB> hjzbs;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_2hao, null);
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

                        txtPm.setText("PM2.5："+hjzbs.get(0).getPm25()+"ug/m³, 温度："+hjzbs.get(0).getTemperature()+"℃");
                        txtShidu.setText("湿度："+hjzbs.get(0).getHumidity()+"%，CO2："+hjzbs.get(0).getCo2()+"PPM");


                        showgongjiao();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void showgongjiao() {
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
                        gjjlList = new Gson().fromJson(jsonObject.optJSONArray("联想大厦站").toString(),
                                new TypeToken<List<GJJL>>(){}.getType());

                        txt1.setText("1号公交车："+gjjlList.get(0).getDistance()+" m");
                        txt2.setText("2号公交车："+gjjlList.get(1).getDistance()+" m");

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView(View view) {
        txt1 = view.findViewById(R.id.txt_1);
        txt2 = view.findViewById(R.id.txt_2);
        txtPm = view.findViewById(R.id.txt_pm);
        txtShidu = view.findViewById(R.id.txt_shidu);
    }
}
