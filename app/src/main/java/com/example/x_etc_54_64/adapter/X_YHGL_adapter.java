package com.example.x_etc_54_64.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.x_etc_54_64.R;
import com.example.x_etc_54_64.activity.Activity_yhgl;
import com.example.x_etc_54_64.bean.YHGL;
import com.example.x_etc_54_64.util.MyScrollView;

import java.util.List;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/18 20:09
 */
public class X_YHGL_adapter extends BaseAdapter {

    private List<YHGL> yhglList;
    private int width;


    public interface OnClickItem {
        void click(int position, int lx);
    }

    private OnClickItem clickItem;

    public void setClickItem(OnClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public X_YHGL_adapter(Activity_yhgl activity_yhgl, List<YHGL> yhglList, int width) {
        this.yhglList = yhglList;
        this.width = width;
    }

    @Override
    public int getCount() {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_yhgl, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final YHGL yhgl = yhglList.get(position);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, LinearLayout.LayoutParams.WRAP_CONTENT);
        holder.lin.setLayoutParams(params);

        holder.scrollView.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (!yhgl.isIs()) {
                    if (scrollY > 10) {
                        clickItem.click(position, 3);
                    }
                }
            }
        });

        holder.scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (yhgl.isIs()) {
                    holder.scrollView.fullScroll(View.FOCUS_RIGHT);
                } else {
                    holder.scrollView.fullScroll(View.FOCUS_LEFT);
                }
            }
        }, 500);

        holder.txtShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.click(position, 1);
            }
        });
        holder.txtDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.click(position, 2);
            }
        });
        holder.txtXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickItem.click(position,4);
            }
        });

        holder.txtShoucang.setText(yhgl.isSc() ? "已收藏" : "收藏");
        holder.imgPoto.setImageResource(yhgl.getSex().equals("男") ? R.drawable.touxiang_2 : R.drawable.touxiang_1);
        holder.txtUser.setText("用户名：" + yhgl.getUsername());
        holder.txtName.setText("姓名：" + yhgl.getName());
        holder.txtTel.setText("电话：" + yhgl.getTel());
        holder.txtGuanliyuan.setText(yhgl.getRoot());

        return convertView;
    }


    class ViewHolder {
        private MyScrollView scrollView;
        private LinearLayout lin;
        private ImageView imgPoto;
        private TextView txtUser;
        private TextView txtName;
        private TextView txtTel;
        private TextView txtGuanliyuan;
        private TextView txtXiangqing;
        private LinearLayout linMenu;
        private TextView txtShoucang;
        private TextView txtDel;

        public ViewHolder(View view) {
            scrollView = view.findViewById(R.id.scroll_view);
            lin = view.findViewById(R.id.lin);
            imgPoto = view.findViewById(R.id.img_poto);
            txtUser = view.findViewById(R.id.txt_user);
            txtName = view.findViewById(R.id.txt_name);
            txtTel = view.findViewById(R.id.txt_tel);
            txtGuanliyuan = view.findViewById(R.id.txt_guanliyuan);
            txtXiangqing = view.findViewById(R.id.txt_xiangqing);
            linMenu = view.findViewById(R.id.lin_menu);
            txtShoucang = view.findViewById(R.id.txt_shoucang);
            txtDel = view.findViewById(R.id.txt_del);
        }
    }
}
