package com.slefbeijingnews.com.menudetailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.slefbeijingnews.com.base.MenuDetaiBasePager;
import com.slefbeijingnews.com.utils.LogUtil;

public class TopicMenuDetailPager extends MenuDetaiBasePager {

    private TextView textView;


    public TopicMenuDetailPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("主题菜单页面初始化");
        textView.setText("主题菜单页面内容");
    }
}
