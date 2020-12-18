package com.example.x_etc_54_64;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_54_64.activity.Activity_gjcx;
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
                            case R.id.gjcx:
                                Intent intent = new Intent(MainActivity.this, Activity_gjcx.class);
                                startActivity(intent);
                        }


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