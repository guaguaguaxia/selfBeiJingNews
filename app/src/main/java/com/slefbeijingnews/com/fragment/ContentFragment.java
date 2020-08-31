package com.slefbeijingnews.com.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.viewpager.widget.ViewPager;

import com.example.slefbeijingnews.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.slefbeijingnews.com.adapter.ContentFragmentAdapter;
import com.slefbeijingnews.com.base.BaseFragment;
import com.slefbeijingnews.com.base.BasePager;
import com.slefbeijingnews.com.pager.GovaffairPager;
import com.slefbeijingnews.com.pager.HomePager;
import com.slefbeijingnews.com.pager.NewsCenterPager;
import com.slefbeijingnews.com.pager.SettingPager;
import com.slefbeijingnews.com.pager.SmartServicePager;
import com.slefbeijingnews.com.utils.LogUtil;


import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * 作者：尚硅谷-杨光福 on 2016/8/13 15:42
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：正文Fragment
 */
public class ContentFragment extends BaseFragment {

//    //2.初始化控件
    @ViewInject(R.id.viewpager)
    private ViewPager viewpager;

    @ViewInject(R.id.rg_main)
    private RadioGroup rg_main;

    /**
     * 装五个页面的集合
     */
    private ArrayList<BasePager> basePagers;



    @Override
    public View initView() {
        LogUtil.e("正文Fragemnt视图被初始化了");
        View view = View.inflate(context, R.layout.content_fragment,null);
        //1.把视图注入到框架中，让ContentFragment.this和View关联起来
        x.view().inject(ContentFragment.this,view);
        //初始化五个页面，并且放入集合中
        basePagers = new ArrayList<>();
        basePagers.add(new HomePager(context));//主页面
        basePagers.add(new NewsCenterPager(context));//新闻中心页面
        basePagers.add(new SmartServicePager(context));//智慧服务页面
        basePagers.add(new GovaffairPager(context));//政要指南页面
        basePagers.add(new SettingPager(context));//设置中心面

        //设置ViewPager的适配器
        viewpager.setAdapter(new ContentFragmentAdapter(basePagers));

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("正文Fragment数据被初始化了");
        rg_main.check(R.id.rb_home);
    }

}
