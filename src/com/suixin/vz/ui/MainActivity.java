package com.suixin.vz.ui;

import java.util.ArrayList;
import java.util.List;

import com.suixin.vy.ui.R;
import com.suixin.vz.ui.adapter.ViewPagerAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

// 实现引导界面
public class MainActivity extends Activity
        implements OnPageChangeListener, OnClickListener {
    private static final Intent HomeActivity = null;

    private ViewPager pager;// 控件

    private List<View> data = new ArrayList<View>();// 数据

    private ViewPagerAdapter adapter;

    private LinearLayout layout;// 装小圆点的布局

    private List<View> viewPoints = new ArrayList<View>();// 保存小圆点

    private Button btn_open;// 进入主界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.vp_view_pager);
        layout = (LinearLayout) findViewById(R.id.ll_points);

        initData();
        adapter = new ViewPagerAdapter(this, data);
        pager.setAdapter(adapter);
        initPoint();
        // viewPager的滑动监听
        pager.setOnPageChangeListener(this);
    }

    /**
     * 初始化小圆点
     */
    private void initPoint() {
        if (data != null && data.size() > 0) {// data代表显示的界面的个数
            for (int i = 0; i < data.size(); i++) {
                View v = new View(this);
                // 设置内间距
                v.setPadding(5, 5, 5, 5);
                // 设置图片显示的大小
                v.setLayoutParams(new LayoutParams(40, 40));
                if (i == 0) {// 代表当前选中的
                    v.setBackgroundResource(R.drawable.icon_like);
                } else {
                    v.setBackgroundResource(R.drawable.unicon_like);
                }
                layout.addView(v);// 将代码中生成的控件放入到布局的位置
                viewPoints.add(v);
            }
        }

    }

    /**
     * 初始化数据
     */
    private void initData() {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_view3_vz, null);
        view.findViewById(R.id.btn_open)
                .setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,
                                com.suixin.vy.ui.HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
        data.add(LayoutInflater.from(this).inflate(R.layout.activity_view1_vz,
                null));
        data.add(LayoutInflater.from(this).inflate(R.layout.activity_view2_vz,
                null));
        data.add(view);

    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    // 代表界面被选中
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < viewPoints.size(); i++) {
            if (i == position) {
                viewPoints.get(i).setBackgroundResource(R.drawable.icon_like);
            } else {
                viewPoints.get(i).setBackgroundResource(R.drawable.unicon_like);
            }
        }
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub

    }
}
