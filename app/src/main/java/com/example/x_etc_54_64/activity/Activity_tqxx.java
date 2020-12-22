package com.example.x_etc_54_64.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.adapter.X_TQXX_adapter;
import com.example.x_etc_54_64.bean.TQXX;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Activity_tqxx extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private ImageView imgShuaxin;
    private GridView gridview;
    private X_TQXX_adapter adapter;
    private List<TQXX> tqxxList;
    private TextView txtWendu;
    private TextView txtQing;
    private TextView txtTime;
    private String[] arr = {"今天", "明天", "后天"};
    private String[] arr1 = {"周天", "周一", "周二", "周三", "周四", "周五", "周六", "周天", "周一", "周二","周三","周四","周五"};
    private LineChart Linechart;
    private List<String> strings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tqxx);
        initView();
        getinit();

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        for (int i=0;i<3;i++){
            strings.add(arr1[day+i+2]);
        }
        
        imgShuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOkHttp();
            }
        });

        getOkHttp();

    }

    private void getOkHttp() {
        if (tqxxList == null) {
            tqxxList = new ArrayList<>();
        } else {
            tqxxList.clear();
        }
        new OkHttpTo()
                .setUrl("get_weather_info")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        getTime();
                        txtWendu.setText(jsonObject.optString("temperature"));
                        txtQing.setText("°" + jsonObject.optString("weather"));
                        tqxxList.addAll((Collection<? extends TQXX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<TQXX>>() {
                                }.getType()));
                        showLinechart();
                        show();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private List<Entry> entries1,entries2;
    private void showLinechart() {
        if (entries1 == null){
            entries1 = new ArrayList<>();
            entries2 = new ArrayList<>();
        }else {
            entries1.clear();
            entries2.clear();
        }

        for (int i = 0; i < 6; i++) {
            TQXX tqxx = tqxxList.get(i);
            String values [] = tqxx.getInterval().split("~");
            entries1.add(new Entry( i+0.5f,Integer.parseInt(values[0])));
            entries2.add(new Entry(i+0.5f,Integer.parseInt(values[1])));
            tqxxList.set(i,tqxx);
        }
        LineDataSet lineDataSet = new LineDataSet(entries1,"");
        lineDataSet.setColor(Color.parseColor("#54FD54"));
        lineDataSet.setCircleColor(Color.parseColor("#5EA3E8"));
        lineDataSet.setCircleHoleRadius(10);
        lineDataSet.setLineWidth(4);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(20);
        lineDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int)value+"°";
            }
        });
        LineDataSet lineDataSet1 = new LineDataSet(entries2,"");
        lineDataSet1.setColor(Color.parseColor("#5EA3E8"));
        lineDataSet1.setCircleColor(Color.parseColor("#5EA3E8"));
        lineDataSet1.setCircleHoleRadius(10);
        lineDataSet1.setLineWidth(4);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setValueTextSize(20);
        lineDataSet1.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int)value+"°";
            }
        });


        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        dataSets.add(lineDataSet1);
        LineData data = new LineData(dataSets);
        XAxis xAxis = Linechart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setAxisMinimum(0);
        xAxis.setAxisMaximum(6);
        xAxis.setTextColor(Color.TRANSPARENT);
        Linechart.animateXY(1000,1000);
        Linechart.getAxisLeft().setEnabled(false);
        Linechart.getAxisRight().setEnabled(false);
        Linechart.setData(data);
        Linechart.getDescription().setEnabled(false);
        Linechart.getLegend().setEnabled(false);
        Linechart.invalidate();

    }

    private void show() {
        if (adapter == null) {
            adapter = new X_TQXX_adapter(tqxxList, arr, arr1,strings);
            gridview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    private void getTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        txtTime.setText(time + "刷新");
    }

    private void getinit() {
        title.setText("天气信息");
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
        imgShuaxin = findViewById(R.id.img_shuaxin);
        gridview = findViewById(R.id.gridview);
        txtWendu = findViewById(R.id.txt_wendu);
        txtQing = findViewById(R.id.txt_qing);
        txtTime = findViewById(R.id.txt_time);
        Linechart = findViewById(R.id.Linechart);
    }
}