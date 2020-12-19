package com.example.x_etc_54_64.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.List;

public class Activity_clwz extends AppCompatActivity {

    private EditText edCph;
    private Button btnChaxun;
    private ImageView caidan;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clwz);
        initView();

        title.setText("车辆违章");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "鲁A"+edCph.getText().toString();
                getOkHttp(s);
            }
        });


    }

    private void getOkHttp(final String s) {
        new OkHttpTo()
                .setUrl("get_peccancy_plate")
                .setJsonObject("UserName", "user1")
                .setJsonObject("plate",s)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        List<String> strings = new Gson().fromJson(jsonObject.optJSONArray("id").toString(),
                                new TypeToken<List<String>>() {}.getType());

                        if (strings.size()==0){
                            Toast.makeText(Activity_clwz.this,"未找到此车牌",Toast.LENGTH_SHORT).show();
                        }else {
                            Intent intent = new Intent(Activity_clwz.this,Activity_clwz2.class)
                                    .putExtra("1", (Serializable) strings);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView() {
        edCph = findViewById(R.id.ed_cph);
        btnChaxun = findViewById(R.id.btn_chaxun);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
    }
}