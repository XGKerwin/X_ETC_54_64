package com.example.x_etc_54_64.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.App;
import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.adapter.X_YHGL_adapter;
import com.example.x_etc_54_64.bean.Login;
import com.example.x_etc_54_64.bean.YHGL;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Activity_yhgl extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ListView listview;
    private List<YHGL> yhglList;
    private X_YHGL_adapter adapter;
    private App app;
    private List<Login> loginList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yhgl);
        initView();
        app = (App) getApplication();
        getinit();

        getOkHttp();

    }

    private void getOkHttp() {
        if (loginList == null){
            loginList = new ArrayList<>();
        }else {
            loginList.clear();
        }
        new OkHttpTo()
                .setUrl("get_login")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        setVolley_Root((List<YHGL>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<YHGL>>() {
                                }.getType()));
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
        
    }

    private void setVolley_Root(final List<YHGL> yhgls) {
        new OkHttpTo()
                .setUrl("get_roots")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        yhglList = new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString()
                                , new TypeToken<List<YHGL>>() {
                                }.getType());
                        for (int i = 0; i < yhglList.size(); i++) {
                            YHGL yhgl = yhglList.get(i);
                            for (int j = 0; j < yhgls.size(); j++) {
                                YHGL yhgl1 = yhgls.get(j);
                                if (yhgl.getId() == yhgl1.getId()) {
                                    yhgl.setUsername(yhgl1.getUsername());
                                    yhgl.setRoot(yhgl1.getRoot());
                                    yhgl.setPassword(yhgl1.getPassword());
                                }
                            }
                            yhgls.set(i, yhgl);
                        }
                        setListView();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setListView() {
        if (adapter == null) {
            adapter = new X_YHGL_adapter(this, yhglList, this.getWindowManager().getDefaultDisplay().getWidth());
            listview.setAdapter(adapter);

            adapter.setClickItem(new X_YHGL_adapter.OnClickItem() {
                @Override
                public void click(int position, int lx) {
                    if (lx == 1) {
                        YHGL yhgl = yhglList.get(position);
                        if (yhgl.isSc()) {
                            List<YHGL> sc = app.getYhgls();
                            for (int i = 0; i < sc.size(); i++) {
                                YHGL yhgl1 = sc.get(i);
                                if (yhgl1.getUsername().equals(yhgl.getUsername())) {
                                    sc.remove(i);
                                }
                            }
                        } else {
                            app.getYhgls().add(yhgl);
                        }
                        yhgl.setIs(false);
                        yhgl.setSc(!yhgl.isSc());
                        yhglList.set(position, yhgl);
                    } else if (lx == 2) {
                        yhglList.remove(position);
                    } else if (lx == 3) {
                        for (int i = 0; i < yhglList.size(); i++) {
                            YHGL yhgl = yhglList.get(i);
                            if (i == position) {
                                yhgl.setIs(true);
                            } else {
                                yhgl.setIs(false);
                            }
                            yhglList.set(i, yhgl);
                        }
                    } else {
                        startActivity(new Intent(Activity_yhgl.this, Activity_yhsc.class));
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void getinit() {
        title.setText("用户管理");
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
    }
}