package com.example.x_etc_54_64.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.adapter.X_ssjt_adapter;
import com.example.x_etc_54_64.bean.SSJT;
import com.example.x_etc_54_64.bean.SSJT_ku;

import java.util.List;

public class Activity_ssjt_2 extends AppCompatActivity {

    private SSJT ssjt;
    private ImageView caidan;
    private TextView title;
    private TextView txtLuxian;
    private TextView txtTime1;
    private TextView txtTime2;
    private TextView txtQuancheng;
    private TextView txtPiaojia;
    private ListView listview;
    private X_ssjt_adapter adapter;
    private int pos = 100;
    private List<String> strings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssjt_2);
        ssjt = (SSJT) getIntent().getSerializableExtra("id");
        initView();
        getinit();

        strings = ssjt.getSite();
        String s1 = strings.get(0);
        String s2 = strings.get(strings.size()-1);

        txtLuxian.setText(s1+"-"+s2);
        txtTime1.setText(ssjt.getStart());
        txtTime2.setText(ssjt.getEnd());

        int sum = strings.size();

        txtQuancheng.setText(sum+"站/"+ssjt.getMileage()+"公里");
        txtPiaojia.setText("票价：最高票价"+ssjt.getPrice()+"元");

        SSJT_ku ssjt_ku = new SSJT_ku();
        ssjt_ku.setLishi(ssjt.getId()+"路("+s1+"-"+s2+")");
        ssjt_ku.setNumber(ssjt.getId());
        ssjt_ku.save();

        showlist();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                showlist();
            }
        });

    }

    private void showlist() {
        adapter = new X_ssjt_adapter(strings,pos);
        listview.setAdapter(adapter);
    }

    private void getinit() {
        title.setText(ssjt.getId()+"路");
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
        txtLuxian = findViewById(R.id.txt_luxian);
        txtTime1 = findViewById(R.id.txt_time1);
        txtTime2 = findViewById(R.id.txt_time2);
        txtQuancheng = findViewById(R.id.txt_quancheng);
        txtPiaojia = findViewById(R.id.txt_piaojia);
        listview = findViewById(R.id.listview);
    }
}