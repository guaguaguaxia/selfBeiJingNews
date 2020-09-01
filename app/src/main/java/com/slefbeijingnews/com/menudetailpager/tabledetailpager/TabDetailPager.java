package com.slefbeijingnews.com.menudetailpager.tabledetailpager;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.slefbeijingnews.com.base.MenuDetaiBasePager;
import com.slefbeijingnews.com.domain.NewsCenterPagerBean2;
import com.slefbeijingnews.com.utils.LogUtil;

/**
 * 作者：尚硅谷-杨光福 on 2016/8/16 10:57
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：页签详情页面
 */
public class TabDetailPager extends MenuDetaiBasePager {
    private TextView textView;

    private NewsCenterPagerBean2.DetailPagerData.ChildrenData childrenData;


    public TabDetailPager(Context context, NewsCenterPagerBean2.DetailPagerData.ChildrenData childrenData) {
        super(context);
        this.childrenData = childrenData;

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
        LogUtil.e("新闻详情页面初始化");
        textView.setText(childrenData.getTitle());
    }
}
