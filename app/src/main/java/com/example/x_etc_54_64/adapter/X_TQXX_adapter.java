package com.example.x_etc_54_64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.TQXX;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/19 11:42
 */
public class X_TQXX_adapter extends BaseAdapter {
    private List<TQXX> tqxxes;
    private String[] arr;
    private String[] arr1;
    private List<String> strings;

    public X_TQXX_adapter(List<TQXX> tqxxList, String[] arr, String[] arr1, List<String> day1) {
        this.tqxxes = tqxxList;
        this.arr = arr;
        this.arr1 = arr1;
        this.strings = day1;
    }


    @Override
    public int getCount() {
        return tqxxes.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tqxx, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TQXX tqxx = tqxxes.get(position);

        if (position<=2){
            holder.txtJintian.setText(arr[position]);
        }else {
            holder.txtJintian.setText(strings.get(position-3));
        }

        holder.txtRiqi.setText(tqxx.getInterval().replace("~", "/")+"C");

        switch (tqxx.getWeather()){
            case "晴":
                holder.imgTianqi.setImageResource(R.drawable.qing);
                break;
            case "阴":
                holder.imgTianqi.setImageResource(R.drawable.yin);
                break;
            case "小雨":
                holder.imgTianqi.setImageResource(R.drawable.xiaoyu);
                break;
        }





        return convertView;
    }


    class ViewHolder {
        private TextView txtJintian;
        private TextView txtRiqi;
        private ImageView imgTianqi;

        public ViewHolder(View view) {
            txtJintian = view.findViewById(R.id.txt_jintian);
            txtRiqi = view.findViewById(R.id.txt_riqi);
            imgTianqi = view.findViewById(R.id.img_tianqi);
        }
    }
}
