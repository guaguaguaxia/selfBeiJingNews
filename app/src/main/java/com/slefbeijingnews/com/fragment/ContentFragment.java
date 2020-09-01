package com.slefbeijingnews.com.fragment;


import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;
import com.example.slefbeijingnews.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.slefbeijingnews.com.activity.MainActivity;
import com.slefbeijingnews.com.adapter.ContentFragmentAdapter;
import com.slefbeijingnews.com.base.BaseFragment;
import com.slefbeijingnews.com.base.BasePager;
import com.slefbeijingnews.com.pager.GovaffairPager;
import com.slefbeijingnews.com.pager.HomePager;
import com.slefbeijingnews.com.pager.NewsCenterPager;
import com.slefbeijingnews.com.pager.SettingPager;
import com.slefbeijingnews.com.pager.SmartServicePager;
import com.slefbeijingnews.com.utils.LogUtil;
import com.slefbeijingnews.com.view.NoScrollViewPager;


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
    private NoScrollViewPager viewpager;

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

        //设置RadioGroup的选中状态改变的监听
        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //监听某个页面被选中，初始对应的页面的数据
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        return view;
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        /**
         *
         * @param group RadioGroup
         * @param checkedId 被选中的RadioButton的id
         */
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_home://主页radioButton的id
                    viewpager.setCurrentItem(0,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_newscenter://新闻中心radioButton的id
                    viewpager.setCurrentItem(1,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_FULLSCREEN);
                    break;
                case R.id.rb_smartservice://智慧服务radioButton的id
                    viewpager.setCurrentItem(2,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_govaffair://政要指南的RadioButton的id
                    viewpager.setCurrentItem(3,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
                case R.id.rb_setting://设置中心RadioButton的id
                    viewpager.setCurrentItem(4,false);
                    isEnableSlidingMenu(SlidingMenu.TOUCHMODE_NONE);
                    break;
            }

        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中的时候回调这个方法
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
//            BasePager basePager = basePagers.get(position);
            //调用被选中的页面的initData方法
            basePagers.get(position).initData();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("正文Fragment数据被初始化了");
        rg_main.check(R.id.rb_home);
    }

    /**
     根据传人的参数设置是否让SlidingMenu可以滑动
     */
    private void isEnableSlidingMenu(int touchmodeFullscreen) {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.getSlidingMenu().setTouchModeAbove(touchmodeFullscreen);
    }

    /**
     * 得到新闻中心
     * @return
     */
    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) basePagers.get(1);
    }


}
