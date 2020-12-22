package com.example.x_etc_54_64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.HJJC_bean;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/20 10:56
 */
public class X_HJJC_adapter extends BaseAdapter {
    private List<HJJC_bean> hjjc_beans;

    public X_HJJC_adapter(List<HJJC_bean> hjjc_beans) {
        this.hjjc_beans = hjjc_beans;
    }

    @Override
    public int getCount() {
        return hjjc_beans.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hjjc, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        HJJC_bean hjjc_bean = hjjc_beans.get(position);
        holder.txtShzb.setText(hjjc_bean.getMag()+"");
        holder.txtZuigao.setText(hjjc_bean.getMax()+"");
        holder.txtZuidi.setText(hjjc_bean.getMin()+"");
        holder.txtPingjun.setText(hjjc_bean.getAver()+"");
        return convertView;
    }

    class ViewHolder {
        private TextView txtShzb;
        private TextView txtZuigao;
        private TextView txtZuidi;
        private TextView txtPingjun;


        public ViewHolder(View convertView) {
            txtShzb =   convertView.findViewById(R.id.txt_shzb);
            txtZuigao = convertView.findViewById(R.id.txt_zuigao);
            txtZuidi =  convertView.findViewById(R.id.txt_zuidi);
            txtPingjun =convertView.findViewById(R.id.txt_pingjun);
        }
    }
}
