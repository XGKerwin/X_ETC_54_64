package com.example.x_etc_54_64.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.App;
import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.adapter.X_YHSCadapter;
import com.example.x_etc_54_64.bean.YHGL;

import java.util.List;

public class Activity_yhsc extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ListView listView;
    private RelativeLayout layoutNull;
    private App app;
    private List<YHGL> yhglList;
    private X_YHSCadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yhsc);
        initView();
        title.setText("用户收藏");

        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        app = (App) getApplication();
        yhglList = app.getYhgls();
        adapter = new X_YHSCadapter(this,yhglList);
        listView.setAdapter(adapter);
        listView.setEmptyView(layoutNull);

    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        listView = findViewById(R.id.list_view);
        layoutNull = findViewById(R.id.layout_null);
    }
}