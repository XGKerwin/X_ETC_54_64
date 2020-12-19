package com.example.x_etc_54_64.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.adapter.X_TJDY_adapter1;
import com.example.x_etc_54_64.adapter.X_TJDY_adapter2;
import com.example.x_etc_54_64.bean.TJDY1;

import java.util.ArrayList;
import java.util.List;

public class Activity_tjdy extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private GridView gridview1;
    private GridView gridview2;
    private X_TJDY_adapter1 adapter1;
    private X_TJDY_adapter2 adapter2;
    private List<TJDY1> tjdy1s,tjdy2s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tjdy);
        initView();

        title.setText("添加订阅");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tjdy1s = new ArrayList<>();
        tjdy2s = new ArrayList<>();

        tjdy1s.add(new TJDY1("推荐"));
        tjdy1s.add(new TJDY1("安全驾驶"));

        tjdy2s.add(new TJDY1("交通分类"));
        tjdy2s.add(new TJDY1("科技类"));
        tjdy2s.add(new TJDY1("路况类"));
        tjdy2s.add(new TJDY1("汽车类"));
        tjdy2s.add(new TJDY1("二手车类"));
        tjdy2s.add(new TJDY1("改装车"));
        tjdy2s.add(new TJDY1("违章"));


        showList();

    }

    private void showList() {
        adapter1 = new X_TJDY_adapter1(tjdy1s);
        gridview1.setAdapter(adapter1);
        adapter1.setMyOnclick1(new X_TJDY_adapter1.MyOnclick1() {
            @Override
            public void setString(String string1, int position) {
                tjdy1s.remove(position);
                tjdy2s.add(new TJDY1(string1));
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        adapter2 = new X_TJDY_adapter2(tjdy2s);
        gridview2.setAdapter(adapter2);
        adapter2.setMyOnclick2(new X_TJDY_adapter2.MyOnclick2() {
            @Override
            public void setString(String string1, int position) {
                tjdy2s.remove(position);
                tjdy1s.add(new TJDY1(string1));
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });


    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        gridview1 = findViewById(R.id.gridview1);
        gridview2 = findViewById(R.id.gridview2);
    }
}