package com.slefbeijingnews.com.pager;

import android.content.Context;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slefbeijingnews.com.base.BasePager;
import com.slefbeijingnews.com.utils.LogUtil;


/**
 * 作者：尚硅谷-杨光福 on 2016/8/15 09:53
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：智慧服务
 */
public class SmartServicePager extends BasePager {


    public SmartServicePager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        super.initData();
        LogUtil.e("智慧服务数据被初始化了..");
        //1.设置标题
        tv_title.setText("智慧服务");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        //4.绑定数据
        textView.setText("智慧服务内容");

    }
}
