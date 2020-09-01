package com.slefbeijingnews.com.pager;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.slefbeijingnews.com.activity.MainActivity;
import com.slefbeijingnews.com.base.BasePager;
import com.slefbeijingnews.com.domain.NewsCenterPagerBean;
import com.slefbeijingnews.com.domain.NewsCenterPagerBean2;
import com.slefbeijingnews.com.fragment.LeftmenuFragment;
import com.slefbeijingnews.com.utils.Constants;
import com.slefbeijingnews.com.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2016/8/15 09:53
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：新闻中心
 */
public class NewsCenterPager extends BasePager {

    /**
     * 起始时间
     */
    private long startTime;

    /**
     * 左侧菜单对应的数据集合
     */
    private List<NewsCenterPagerBean.DataEntity> data;

    public NewsCenterPager(Context context) {
        super(context);
    }
    @Override
    public void initData() {
        super.initData();
        LogUtil.e("新闻中心数据被初始化了..");
        //1.设置标题
        tv_title.setText("新闻中心");
        //2.联网请求，得到数据，创建视图
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        startTime = SystemClock.uptimeMillis();
        //4.绑定数据
        textView.setText("新闻中心内容");

        getDataFromNet();
    }


    /**
     * 解析json数据和显示数据
     *
     * @param json
     */
    private void processData(String json) {

        NewsCenterPagerBean bean = parsedJson(json);
//        NewsCenterPagerBean2 bean = parsedJson2(json);
//        String title = bean.getData().get(0).getChildren().get(1).getTitle();


//        LogUtil.e("使用Gson解析json数据成功-title==" + title);
        String title2 = bean.getData().get(0).getChildren().get(1).getTitle();
        LogUtil.e("使用Gson解析json数据成功NewsCenterPagerBean2-title2-------------------------==" + title2);
        //给左侧菜单传递数据
        data = bean.getData();

        MainActivity mainActivity = (MainActivity) context;
        //得到左侧菜单
        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        //把数据传递给左侧菜单
        leftmenuFragment.setData(data);


    }

    private NewsCenterPagerBean parsedJson(String json) {
        Gson gson = new Gson();
        NewsCenterPagerBean bean = gson.fromJson(json,NewsCenterPagerBean.class);
        return bean;
//        return new Gson().fromJson(json, NewsCenterPagerBean2.class);
    }


    /**
     * 使用xUtils3联网请求数据
     */
    private void getDataFromNet() {

        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        params.addHeader("Connection","close");
        params.setConnectTimeout(6000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                long endTime = SystemClock.uptimeMillis();
                long passTime = endTime - startTime;

                LogUtil.e("xUtils3--passTime==" + passTime);

                LogUtil.e("使用xUtils3联网请求成功==" + result);

                processData(result);
                //缓存数据
//                CacheUtils.putString(context,Constants.NEWSCENTER_PAGER_URL,result);
//
//                processData(result);
                //设置适配器


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("使用xUtils3联网请求失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("使用xUtils3-onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("使用xUtils3-onFinished");
            }
        });

    }
}
