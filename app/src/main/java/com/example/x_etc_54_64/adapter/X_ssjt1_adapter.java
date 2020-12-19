package com.example.x_etc_54_64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.SSJT_ku;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 15:40
 */
public class X_ssjt1_adapter extends BaseAdapter {
    private List<SSJT_ku> ssjt_kus;

    public X_ssjt1_adapter(List<SSJT_ku> ssjt_kus) {
        this.ssjt_kus = ssjt_kus;
    }

    @Override
    public int getCount() {
        if (ssjt_kus.size() == 0) return 0;
        return ssjt_kus.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ssjt1, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SSJT_ku ssjt_ku = ssjt_kus.get(position);
        holder.txt.setText(ssjt_ku.getLishi());

        return convertView;
    }


    class ViewHolder {
        private TextView txt;

        public ViewHolder(View view) {
            txt = view.findViewById(R.id.txt);
        }
    }
}
