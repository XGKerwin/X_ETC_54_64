package com.example.x_etc_54_64.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Activity_ssjt extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ListView listview;
    private TextView txtQingkong;
    private List<SSJT> ssjtList;
    private String bianhao = "";
    private EditText edXianlu;
    private TextView btnSousuo;
    private String TAG = "ssjt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssjt);
        initView();
        getinit();


        btnSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bianhao = edXianlu.getText().toString();
                if (bianhao.equals("")){
                    Toast.makeText(Activity_ssjt.this,"请输入编号",Toast.LENGTH_SHORT).show();
                }else {
                    getOkHttp();
                }
            }
        });


    }

    private void getOkHttp() {
        if (ssjtList == null){
            ssjtList = new ArrayList<>();
        }else {
            ssjtList.clear();
        }
        new OkHttpTo().setUrl("get_bus_data")
                .setJsonObject("UserName", "user1")
                .setJsonObject("Busid", bianhao)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        ssjtList.addAll((Collection<? extends SSJT>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<SSJT>>(){}.getType()));

                        Log.i(TAG, "onResponse: "+ssjtList.size());

                        Log.i(TAG, "onResponse:sssss "+ssjtList.get(0).getMileage());

                        if (ssjtList.size()==0){
                            Toast.makeText(Activity_ssjt.this,"未找到此车号",Toast.LENGTH_SHORT).show();
                        } else {
                            Intent intent = new Intent(Activity_ssjt.this,Activity_ssjt_2.class);
                                    intent.putExtra("id", ssjtList.get(0));
                            startActivity(intent);
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getinit() {
        title.setText("实时交通");
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
        txtQingkong = findViewById(R.id.txt_qingkong);
        edXianlu = findViewById(R.id.ed_xianlu);
        btnSousuo = findViewById(R.id.btn_sousuo);
    }
}