package com.example.x_etc_54_64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.bean.TJDY1;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 21:08
 */
public class X_TJDY_adapter1 extends BaseAdapter {
    private List<TJDY1> tjdy1s;
    private MyOnclick1 myOnclick1;

    public interface MyOnclick1{

        void setString(String string1, int position);
    }

    public void setMyOnclick1(MyOnclick1 myOnclick1){
        this.myOnclick1 = myOnclick1;
    }



    public X_TJDY_adapter1(List<TJDY1> tjdy1s) {
        this.tjdy1s = tjdy1s;
    }

    @Override
    public int getCount() {
        if (tjdy1s.size() == 0) return 0;
        return tjdy1s.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tjdy1, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final TJDY1 tjdy1 = tjdy1s.get(position);

        holder.listTxt1.setText(tjdy1.getString1());

        holder.listRal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myOnclick1.setString(tjdy1.getString1(),position);
            }
        });


        return convertView;
    }


    class ViewHolder {
        private RelativeLayout listRal;
        private TextView listTxt1;
        
        public ViewHolder(View view) {
            listRal = view.findViewById(R.id.list_ral);
            listTxt1 = view.findViewById(R.id.list_txt1);
        }
    }
}
