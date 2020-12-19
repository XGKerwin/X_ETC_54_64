package com.example.x_etc_54_64.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.CLWZ;
import com.example.x_etc_54_64.bean.CXJG;
import com.example.x_etc_54_64.bean.WZZL;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Activity_clwz2 extends AppCompatActivity {

    private List<String> strings;
    private ImageView caidan;
    private TextView title;
    private ListView listview;
    private List<CLWZ> clwzList;
    private List<WZZL> wzzlList;
    private TextView txtDidian;
    private TextView txtMes;
    private ImageView imgZuo;
    private ImageView img1;
    private ImageView img2;
    private ImageView imgYou;
    private TextView txtWeizhang;
    private TextView txtFakuan;
    private TextView txtKoufen;
    private List<CXJG> cxjgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clwz2);
        strings = getIntent().getStringArrayListExtra("1");
        initView();
        getinit();
        getOKHttp();
        btn();


    }

    private int pos = 0;
    private void btn() {
        imgYou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == weizhang-1){

                }else {
                    pos = pos+1;
                    getshow();
                }

            }
        });

        imgZuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0){

                }else {
                    pos = pos-1;
                    getshow();
                }
            }
        });

    }

    private void getshow() {
        CXJG cxjg = cxjgList.get(pos);

        txtDidian.setText(cxjg.getDidian());
        txtMes.setText(cxjg.getMsg());

        switch (cxjg.getNumber()){
            case "1":
                img1.setImageResource(R.drawable.weizhang1);
                img2.setImageResource(R.drawable.weizhang10);
                break;
            case "2":
                img1.setImageResource(R.drawable.weizhang2);
                img2.setImageResource(R.drawable.weizhang9);
                break;
            case "3":
                img1.setImageResource(R.drawable.weizhang3);
                img2.setImageResource(R.drawable.weizhang8);
                break;
            case "4":
                img1.setImageResource(R.drawable.weizhang4);
                img2.setImageResource(R.drawable.weizhang7);
                break;
            case "5":
                img1.setImageResource(R.drawable.weizhang5);
                img2.setImageResource(R.drawable.weizhang6);
                break;
            case "6":
                img1.setImageResource(R.drawable.weizhang6);
                img2.setImageResource(R.drawable.weizhang5);
                break;
            case "7":
                img1.setImageResource(R.drawable.weizhang7);
                img2.setImageResource(R.drawable.weizhang4);
                break;
            case "8":
                img1.setImageResource(R.drawable.weizhang8);
                img2.setImageResource(R.drawable.weizhang3);
                break;
            case "9":
                img1.setImageResource(R.drawable.weizhang9);
                img2.setImageResource(R.drawable.weizhang2);
                break;
            case "10":
                img1.setImageResource(R.drawable.weizhang10);
                img2.setImageResource(R.drawable.weizhang1);
                break;


        }

    }

    private void getOKHttp() {
        //{"UserName":"user1"}
        if (clwzList == null){
            clwzList = new ArrayList<>();
        }else {
            clwzList.clear();
        }
        new OkHttpTo()
                .setUrl("get_all_car_peccancy")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        clwzList.addAll((Collection<? extends CLWZ>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<CLWZ>>(){}.getType()));
                        getokhttp2();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getokhttp2() {
        if (wzzlList == null){
            wzzlList = new ArrayList<>();
        }else {
            wzzlList.clear();
        }
        //{"UserName":"user1"}
        new OkHttpTo()
                .setUrl("get_pessancy_infos")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wzzlList.addAll((Collection<? extends WZZL>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<WZZL>>(){}.getType()));
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }
    private int weizhang = 0;
    private void show() {
        if (cxjgList == null){
            cxjgList = new ArrayList<>();
        }else {
            cxjgList.clear();
        }


        int fakuam = 0;
        int koufen = 0;

        for (int i=0;i<strings.size();i++){
            String s = strings.get(i);
            for (int i1 = 0; i1< clwzList.size(); i1++){
                CLWZ clwz = clwzList.get(i1);
                if (s.equals(clwz.getId())){
                    for (int i2=0;i2<wzzlList.size();i2++){
                        WZZL wzzl = wzzlList.get(i2);
                        if (clwz.getInfoid().equals(wzzl.getInfoid())){
                            weizhang = strings.size();
                            int fk = Integer.parseInt(wzzl.getFine());
                            int kf = Integer.parseInt(wzzl.getDeduct());
                            fakuam += fk;
                            koufen += kf;
                            txtWeizhang.setText("违章："+weizhang);
                            txtFakuan.setText("罚款："+fakuam);
                            txtKoufen.setText("扣分"+koufen);

                            CXJG cxjg = new CXJG(clwz.getId(),wzzl.getRoad(),wzzl.getMessage());
                            cxjgList.add(cxjg);

                        }
                    }
                }
            }
        }



    }


    private void getinit() {
        title.setText("查询结果");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        listview = findViewById(R.id.listview);
        txtDidian = findViewById(R.id.txt_didian);
        txtMes = findViewById(R.id.txt_mes);
        imgZuo = findViewById(R.id.img_zuo);
        img1 = findViewById(R.id.img_1);
        img2 = findViewById(R.id.img_2);
        imgYou = findViewById(R.id.img_you);
        txtWeizhang = findViewById(R.id.txt_weizhang);
        txtFakuan = findViewById(R.id.txt_fakuan);
        txtKoufen = findViewById(R.id.txt_koufen);
    }
}