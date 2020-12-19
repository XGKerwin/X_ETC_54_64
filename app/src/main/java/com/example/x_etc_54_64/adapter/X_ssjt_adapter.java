package com.example.x_etc_54_64.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_54_64.R;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 15:05
 */
public class X_ssjt_adapter extends BaseAdapter {
    private List<String> strings;
    private int pos;

    public X_ssjt_adapter(List<String> strings, int pos) {
        this.strings = strings;
        this.pos = pos;
    }

    @Override
    public int getCount() {
        return strings.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_ssjt, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String s = strings.get(position);
        holder.txt.setText(s);
        holder.txtNumber.setText(position+1+"");
        Log.i("vvvvvvvv", "getView: "+position);
        Log.i("vvvvvvvv", "getView: "+pos);
        if (pos == position){
            Log.i("vvvvvvvvsss", "getView: "+position);
            holder.txtNumber.setBackgroundResource(R.drawable.yuan_hong_zi);
        }

        return convertView;
    }

    class ViewHolder {
        private TextView txtNumber;
        private TextView txt;

        public ViewHolder(View view) {
            txtNumber = view.findViewById(R.id.txt_number);
            txt = view.findViewById(R.id.txt);
        }
    }

}
