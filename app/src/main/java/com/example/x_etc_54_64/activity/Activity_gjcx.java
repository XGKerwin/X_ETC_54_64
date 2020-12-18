package com.example.x_etc_54_64.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.HJZB;
import com.example.x_etc_54_64.fragment.Fragment1hao;
import com.example.x_etc_54_64.fragment.Fragment2hao;

import java.util.List;

public class Activity_gjcx extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private TextView btn1;
    private TextView btn2;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gjcx);
        initView();
        getinit();
        btn();

        show();


    }


    private void show() {
        btn1.setBackgroundResource(R.drawable.lan_xian1);
        btn2.setBackgroundResource(R.drawable.bai_xian);
        btn1.setTextColor(Color.WHITE);
        btn2.setTextColor(Color.BLACK);
        Fragment1(new Fragment1hao());
    }

    private void btn() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("1号站台");
                btn1.setBackgroundResource(R.drawable.lan_xian1);
                btn2.setBackgroundResource(R.drawable.bai_xian);
                btn1.setTextColor(Color.WHITE);
                btn2.setTextColor(Color.BLACK);
                Fragment1(new Fragment1hao());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("2号站台");
                btn1.setBackgroundResource(R.drawable.bai_xian);
                btn2.setBackgroundResource(R.drawable.lan_xian1);
                btn1.setTextColor(Color.BLACK);
                btn2.setTextColor(Color.WHITE);
                Fragment1(new Fragment2hao());
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_gjcx,fragment).commit();
    }

    private void getinit() {
        title.setText("1号站台");
        caidan.setImageResource(R.drawable.left_hei);
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
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
    }
}