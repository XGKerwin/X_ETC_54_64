package com.example.x_etc_54_64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.activity.Activity_yhsc;
import com.example.x_etc_54_64.bean.YHGL;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/19 9:49
 */
public class X_YHSCadapter extends BaseAdapter {

    private List<YHGL> yhglList;


    public X_YHSCadapter(Activity_yhsc activity_yhsc, List<YHGL> yhglList) {
        this.yhglList = yhglList;
    }

    @Override
    public int getCount() {
        if (yhglList.size() == 0) return 0;
        return yhglList.size();
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
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_yhsc, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        YHGL yhgl = yhglList.get(position);
        holder.ivPhoto.setImageResource(yhgl.getSex().equals("男") ? R.drawable.touxiang_2 : R.drawable.touxiang_1);
        holder.tvUsername.setText("用户名：" + yhgl.getUsername());
        holder.tvName.setText("姓名：" + yhgl.getName());
        holder.tvTel.setText("电话：" + yhgl.getTel());
        holder.tvType.setText(yhgl.getRoot());


        return convertView;
    }


    class ViewHolder {
        private LinearLayout layout;
        private ImageView ivPhoto;
        private TextView tvUsername;
        private TextView tvName;
        private TextView tvTel;
        private TextView tvType;
        private TextView tvDetails;

        public ViewHolder(View view) {
            layout = view.findViewById(R.id.layout);
            ivPhoto = view.findViewById(R.id.iv_photo);
            tvUsername = view.findViewById(R.id.tv_username);
            tvName = view.findViewById(R.id.tv_name);
            tvTel = view.findViewById(R.id.tv_tel);
            tvType = view.findViewById(R.id.tv_type);
            tvDetails = view.findViewById(R.id.tv_details);
        }
    }
}
