package com.example.x_etc_54_64.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.adapter.X_HJJC_adapter;
import com.example.x_etc_54_64.bean.HJJC;
import com.example.x_etc_54_64.bean.HJJC_bean;
import com.example.x_etc_54_64.net.OkHttpLo;
import com.example.x_etc_54_64.net.OkHttpTo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_hjjc extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private TextView txtTime;
    private TextView txtGengxin;
    private TextView txtName;
    private ListView listview;
    private String Time;
    private PieChart picChart;
    private String[] dizhi = {"北京", "深圳", "上海", "重庆", "雄安"};
    private boolean isLoop = true;
    private Map<String, HJJC> map;
    private Map<String, List<HJJC>> mapList;
    private List<PieEntry> pieEntries;
    private int index = 0, minute = 0, select = 0;
    private List<HJJC_bean> hjjc_beans;
    private X_HJJC_adapter adapter;
    private int[] colors = {Color.parseColor("#B22125"), Color.parseColor("#233543")
            , Color.parseColor("#509098"), Color.parseColor("#C86D53"), Color.parseColor("#80BD9F")};

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            setData();
            return false;
        }
    });

    private void setData() {
        if (map == null){
            map = new HashMap<>();
        } else {
            map.clear();
        }
        for (int i = 0; i < 5; i++) {
            getOkHttp(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hjjc);
        initView();
        getinit();

        getTime();
        getPic();
        getdata();
    }

    private void getinit() {
        title.setText("环境检测");
        caidan.setImageResource(R.drawable.back);
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isLoop) {
                    handler.sendEmptyMessage(0);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        setdanji();

    }

    private void setdanji() {
        picChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                select = (int) h.getX();
                setSelect();
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void getPic() {
        mapList = new HashMap<>();
        picChart.getDescription().setEnabled(false);
        Legend legend = picChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setFormSize(25);
        legend.setTextSize(25);
    }

    private void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年M月d日HH:mm");
        Date date = new Date(System.currentTimeMillis());
        Time = simpleDateFormat.format(date);
    }

    private void getOkHttp(final int i) {
        new OkHttpTo().setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setTime(3000)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {


                        List<HJJC> hjjcs = mapList.get(dizhi[i]);
                        if (hjjcs == null) {
                            hjjcs = new ArrayList<>();
                        }
                        hjjcs.add(0, new Gson().fromJson(jsonObject.toString(), HJJC.class));
                        mapList.put(dizhi[i], hjjcs);
                        map.put(dizhi[i], hjjcs.get(0));
                        if (map.size() == 5) {
                            txtTime.setText(Time);
                            setSelect();
                            setPiechar();
                            index++;
                            if (index == 12 || index == 1) {
                                minute++;
                                index = 1;
                                txtGengxin.setText("最近更新:" + (minute == 1 ? "最新数据" : minute + "分钟之前"));
                            }
                        }

                    }

                    @Override
                    public void onFailure(IOException obj) {

                    }
                }).start();
    }

    private void setPiechar() {
        if (pieEntries == null) pieEntries = new ArrayList<>();
        else pieEntries.clear();
        for (int i = 0; i < map.size(); i++) {
            HJJC hjjc = map.get(dizhi[i]);
            pieEntries.add(new PieEntry(hjjc.getPm25(), dizhi[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(colors);
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataSet.setValueLinePart1Length(0.3f);
        dataSet.setValueLinePart2Length(0.5f);
        dataSet.setValueLinePart1OffsetPercentage(70);
        dataSet.setValueLineColor(Color.BLACK);
        dataSet.setValueTextSize(10);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if (value <= 35) {
                    return "优:" + value;
                } else if (value > 35 && value <= 75) {
                    return "良:" + value;
                } else if (value > 75 && value <= 115) {
                    return "轻度污染:" + value;
                } else if (value > 115 && value <= 150) {
                    return "中度污染:" + value;
                } else {
                    return "重度污染:" + value;
                }
            }
        });
        PieData data = new PieData(dataSet);
        picChart.setData(data);
        picChart.setDrawHoleEnabled(false);
        picChart.setEntryLabelColor(Color.BLACK);
        picChart.invalidate();

    }


    private void setSelect() {
        txtName.setText(dizhi[select]);
        if (mapList.size() != 5) {
            return;
        }
        List<HJJC> hjjcs = mapList.get(dizhi[select]);
        List<Integer> wds = new ArrayList<>();
        List<Integer> cos = new ArrayList<>();
        List<Integer> pms = new ArrayList<>();
        List<Integer> gzs = new ArrayList<>();
        List<Integer> sds = new ArrayList<>();
        int wd = 0, sd = 0, gz = 0, pm = 0, co = 0;
        for (int i = 0; i < hjjcs.size(); i++) {
            HJJC hjjc = hjjcs.get(i);
            wds.add(hjjc.getTemperature());
            wd += hjjc.getTemperature();
            cos.add(hjjc.getCo2());
            co += hjjc.getCo2();
            pms.add(hjjc.getPm25());
            pm += hjjc.getPm25();
            gzs.add(hjjc.getIllumination());
            gz += hjjc.getIllumination();
            sds.add(hjjc.getHumidity());
            sd += hjjc.getHumidity();
        }
        if (hjjc_beans == null) hjjc_beans = new ArrayList<>();
        else hjjc_beans.clear();
        hjjc_beans.add(new HJJC_bean("pm2.5(µg/m3)", Collections.max(pms), Collections.min(pms), pm / pms.size()));
        hjjc_beans.add(new HJJC_bean("二氧化碳(ppm)", Collections.max(cos), Collections.min(cos), co / cos.size()));
        hjjc_beans.add(new HJJC_bean("光照强度(SI)", Collections.max(gzs), Collections.min(gzs), gz / gzs.size()));
        hjjc_beans.add(new HJJC_bean("湿度(RH)", Collections.max(sds), Collections.min(sds), sd / sds.size()));
        hjjc_beans.add(new HJJC_bean("温度(C)", Collections.max(wds), Collections.min(wds), wd / wds.size()));
        if (adapter == null) {
            adapter = new X_HJJC_adapter(hjjc_beans);
            listview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }


    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        txtTime = findViewById(R.id.txt_time);
        txtGengxin = findViewById(R.id.txt_gengxin);
        txtName = findViewById(R.id.txt_name);
        listview = findViewById(R.id.listview);
        picChart = findViewById(R.id.pic_chart);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }
}