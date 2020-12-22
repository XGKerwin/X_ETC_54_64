package com.example.x_etc_54_64;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_54_64.activity.Activity_clwz;
import com.example.x_etc_54_64.activity.Activity_gjcx;
import com.example.x_etc_54_64.activity.Activity_hjjc;
import com.example.x_etc_54_64.activity.Activity_lxzs;
import com.example.x_etc_54_64.activity.Activity_ssjt;
import com.example.x_etc_54_64.activity.Activity_tjdy;
import com.example.x_etc_54_64.activity.Activity_tqxx;
import com.example.x_etc_54_64.activity.Activity_wzfx;
import com.example.x_etc_54_64.activity.Activity_wzlx;
import com.example.x_etc_54_64.activity.Activity_yhgl;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dra;
    private ImageView caidan;
    private TextView title;
    private FrameLayout fragment;
    private NavigationView nav;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        title.setText("智能交通");

        gethuadong();

    }

    private void gethuadong() {

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.yhgl:
                                Intent intent = new Intent(MainActivity.this, Activity_yhgl.class);
                                startActivity(intent);
                                break;
                            case R.id.gjcx:
                                Intent intent1 = new Intent(MainActivity.this, Activity_gjcx.class);
                                startActivity(intent1);
                                break;
                            case R.id.ssjt:
                                Intent intent2 = new Intent(MainActivity.this, Activity_ssjt.class);
                                startActivity(intent2);
                                break;
                            case R.id.hjjc:
                                Intent intent3 = new Intent(MainActivity.this, Activity_hjjc.class);
                                startActivity(intent3);
                                break;
                            case R.id.wzfx:
                                Intent intent4 = new Intent(MainActivity.this, Activity_wzfx.class);
                                startActivity(intent4);
                                break;
                            case R.id.clwz:
                                Intent intent5 = new Intent(MainActivity.this, Activity_clwz.class);
                                startActivity(intent5);
                                break;
                            case R.id.tjdy:
                                Intent intent6 = new Intent(MainActivity.this, Activity_tjdy.class);
                                startActivity(intent6);
                                break;
                            case R.id.tqxx:
                                Intent intent7 = new Intent(MainActivity.this, Activity_tqxx.class);
                                startActivity(intent7);
                                break;
                            case R.id.wzlx:
                                Intent intent8 = new Intent(MainActivity.this, Activity_wzlx.class);
                                startActivity(intent8);
                                break;
                            case R.id.lxzs:
                                Intent intent9 = new Intent(MainActivity.this, Activity_lxzs.class);
                                startActivity(intent9);
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });

    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView() {
        dra = findViewById(R.id.dra);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        fragment = findViewById(R.id.fragment);
        nav = findViewById(R.id.nav);
    }
}