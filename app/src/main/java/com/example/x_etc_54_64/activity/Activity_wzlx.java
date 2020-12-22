package com.example.x_etc_54_64.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.WZLX;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_wzlx extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private RadarChart RadarChart;
    private List<WZLX> wzlxList;
    private Map<String,Integer> map;
    private List<Map.Entry<String,Integer>> entryList;
    private List<RadarEntry> radarEntries;
    private List<IRadarDataSet> iRadarDataSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wzlx);
        initView();
        title.setText("违章类型分析");

        getOkhttp();




    }

    private void getOkhttp() {
        if (wzlxList == null){
            wzlxList = new ArrayList<>();
        }else {
            wzlxList.clear();
        }

        new OkHttpTo()
                .setUrl("get_peccancy")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        wzlxList.addAll((Collection<? extends WZLX>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<WZLX>>(){}.getType()));
                        getdata();
                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void getdata() {
        map = new HashMap<>();
        for (int i=0;i<wzlxList.size();i++){
            String id = wzlxList.get(i).getPaddr();
            if (map.get(id) == null) {
                Log.d("lllllll", "getData: "+id);
                map.put(id,1);
            } else {
                map.put(id,map.get(id)+1);
            }
        }
        entryList = new ArrayList<>(map.entrySet());

        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        setdata();
    }

    private String[] t1,t2,t3,t4,t5;
    private void setdata() {
        t1 = entryList.get(1).toString().split("=");
        t2 = entryList.get(2).toString().split("=");
        t3 = entryList.get(3).toString().split("=");
        t4 = entryList.get(4).toString().split("=");
        t5 = entryList.get(5).toString().split("=");
        showChart();
    }

    private void showChart() {
        radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(Integer.parseInt(t1[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(t2[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(t3[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(t4[1])));
        radarEntries.add(new RadarEntry(Integer.parseInt(t5[1])));
        RadarDataSet dataSet = new RadarDataSet(radarEntries,"");
        dataSet.setColor(Color.parseColor("#A8CAEC"));
        dataSet.setFillColor(Color.parseColor("#A8CAEC"));
        dataSet.setFillAlpha(180);
        dataSet.setDrawValues(false);
        dataSet.setDrawFilled(true);
        dataSet.setLineWidth(2);

        XAxis xAxis  =RadarChart.getXAxis();
        xAxis.setAxisMinimum(0);
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = RadarChart.getYAxis();
        yAxis.setStartAtZero(true);
        yAxis.setAxisMaximum(130);
        yAxis.setTextSize(15);

        iRadarDataSets = new ArrayList<>();
        iRadarDataSets.add(dataSet);
        iRadarDataSets.add(tu());
        RadarData data = new RadarData(iRadarDataSets);
        RadarChart.setData(data);

        RadarChart.setDescription(null);
        RadarChart.getLegend().setEnabled(false);
        RadarChart.setTouchEnabled(false);
        RadarChart.invalidate();


    }

    private RadarDataSet tu() {
        List<RadarEntry> yValue = new ArrayList<>();
        int[] dra=new int[]{
                R.drawable.shape_1,
                R.drawable.shape_2,
                R.drawable.shape_3,
                R.drawable.shape_4,
                R.drawable.shape_5,
        };
        for (int i=0;i<5;i++)
        {
            RadarEntry radarEntry = new RadarEntry(140);
            radarEntry.setIcon(getResources().getDrawable(dra[i]));
            yValue.add(radarEntry);
        }
        RadarDataSet radarDataSet = new RadarDataSet(yValue,"");
        radarDataSet.setDrawValues(false);
        radarDataSet.setColors(Color.TRANSPARENT);
        return radarDataSet;
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        RadarChart = findViewById(R.id.RadarChart);
    }
}