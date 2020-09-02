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
import com.slefbeijingnews.com.base.MenuDetaiBasePager;
import com.slefbeijingnews.com.domain.NewsCenterPagerBean;
import com.slefbeijingnews.com.domain.NewsCenterPagerBean2;
import com.slefbeijingnews.com.fragment.LeftmenuFragment;
import com.slefbeijingnews.com.menudetailpager.InteracMenuDetailPager;
import com.slefbeijingnews.com.menudetailpager.NewsMenuDetailPager;
import com.slefbeijingnews.com.menudetailpager.PhotosMenuDetailPager;
import com.slefbeijingnews.com.menudetailpager.TopicMenuDetailPager;
import com.slefbeijingnews.com.utils.CacheUtils;
import com.slefbeijingnews.com.utils.Constants;
import com.slefbeijingnews.com.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
    private List<NewsCenterPagerBean2.DetailPagerData> data;

    /**
     * 详情页面的集合
     */
    private ArrayList<MenuDetaiBasePager> detaiBasePagers;

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
        ib_menu.setVisibility(View.VISIBLE);
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        //3.把子视图添加到BasePager的FrameLayout中
        fl_content.addView(textView);
        startTime = SystemClock.uptimeMillis();
        //4.绑定数据
        textView.setText("新闻中心内容");

        //得到缓存数据
        String saveJson = CacheUtils.getString(context, Constants.NEWSCENTER_PAGER_URL);//""

//        if (!TextUtils.isEmpty(saveJson)) {
//            processData(saveJson);
//        }

        getDataFromNet();

    }




    /**
     * 解析json数据和显示数据
     *
     * @param json
     */
    private void processData(String json) {

//        NewsCenterPagerBean bean = parsedJson(json);
        NewsCenterPagerBean2 bean = parsedJson2(json);
//        String title = bean.getData().get(0).getChildren().get(1).getTitle();


//        LogUtil.e("使用Gson解析json数据成功-title==" + title);
        String title2 = bean.getData().get(0).getChildren().get(1).getTitle();
        LogUtil.e("使用Gson解析json数据成功NewsCenterPagerBean2-title2-------------------------==" + title2);
        //给左侧菜单传递数据
        data = bean.getData();

        MainActivity mainActivity = (MainActivity) context;
        //得到左侧菜单
        LeftmenuFragment leftmenuFragment = mainActivity.getLeftmenuFragment();

        //添加详情页面
        detaiBasePagers = new ArrayList<>();
        detaiBasePagers.add(new NewsMenuDetailPager(context, data.get(0)));//新闻详情页面
        detaiBasePagers.add(new TopicMenuDetailPager(context));//专题详情页面
        detaiBasePagers.add(new PhotosMenuDetailPager(context));//图组详情页面
        detaiBasePagers.add(new InteracMenuDetailPager(context));//互动详情页面

        //把数据传递给左侧菜单
        leftmenuFragment.setData(data);


    }

    private NewsCenterPagerBean parsedJson(String json) {
        Gson gson = new Gson();
        NewsCenterPagerBean bean = gson.fromJson(json, NewsCenterPagerBean.class);
        return bean;
//        return new Gson().fromJson(json, NewsCenterPagerBean2.class);
    }


    /**
     * 使用xUtils3联网请求数据
     */
    private void getDataFromNet() {

        RequestParams params = new RequestParams(Constants.NEWSCENTER_PAGER_URL);
        params.addHeader("Connection", "close");
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
//                CacheUtils.putString(context, Constants.NEWSCENTER_PAGER_URL, result);
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

    /**
     * 使用Android系统自带的API解析json数据
     *
     * @param json
     * @return
     */
    private NewsCenterPagerBean2 parsedJson2(String json) {
        NewsCenterPagerBean2 bean2 = new NewsCenterPagerBean2();
        try {
            JSONObject object = new JSONObject(json);


            int retcode = object.optInt("retcode");
            bean2.setRetcode(retcode);//retcode字段解析成功

            JSONArray data = object.optJSONArray("data");
            if (data != null && data.length() > 0) {

                List<NewsCenterPagerBean2.DetailPagerData> detailPagerDatas = new ArrayList<>();
                //设置列表数据
                bean2.setData(detailPagerDatas);
                //for循环，解析每条数据
                for (int i = 0; i < data.length(); i++) {

                    JSONObject jsonObject = (JSONObject) data.get(i);

                    NewsCenterPagerBean2.DetailPagerData detailPagerData = new NewsCenterPagerBean2.DetailPagerData();
                    //添加到集合中
                    detailPagerDatas.add(detailPagerData);

                    int id = jsonObject.optInt("id");
                    detailPagerData.setId(id);
                    int type = jsonObject.optInt("type");
                    detailPagerData.setType(type);
                    String title = jsonObject.optString("title");
                    detailPagerData.setTitle(title);
                    String url = jsonObject.optString("url");
                    detailPagerData.setUrl(url);
                    String url1 = jsonObject.optString("url1");
                    detailPagerData.setUrl1(url1);
                    String dayurl = jsonObject.optString("dayurl");
                    detailPagerData.setDayurl(dayurl);
                    String excurl = jsonObject.optString("excurl");
                    detailPagerData.setExcurl(excurl);
                    String weekurl = jsonObject.optString("weekurl");
                    detailPagerData.setWeekurl(weekurl);


                    JSONArray children = jsonObject.optJSONArray("children");
                    if (children != null && children.length() > 0) {

                        List<NewsCenterPagerBean2.DetailPagerData.ChildrenData> childrenDatas = new ArrayList<>();

                        //设置集合-ChildrenData
                        detailPagerData.setChildren(childrenDatas);

                        for (int j = 0; j < children.length(); j++) {
                            JSONObject childrenitem = (JSONObject) children.get(j);

                            NewsCenterPagerBean2.DetailPagerData.ChildrenData childrenData = new NewsCenterPagerBean2.DetailPagerData.ChildrenData();
                            //添加到集合中
                            childrenDatas.add(childrenData);


                            int childId = childrenitem.optInt("id");
                            childrenData.setId(childId);
                            String childTitle = childrenitem.optString("title");
                            childrenData.setTitle(childTitle);
                            String childUrl = childrenitem.optString("url");
                            childrenData.setUrl(childUrl);
                            int childType = childrenitem.optInt("type");
                            childrenData.setType(childType);

                        }

                    }


                }


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return bean2;
    }

    public void swichPager(int position) {
        tv_title.setText(data.get(position).getTitle());
        fl_content.removeAllViews();
        MenuDetaiBasePager detaiBasePager = detaiBasePagers.get(position);
        View rootView = detaiBasePager.rootView;
        detaiBasePager.initData();
        fl_content.addView(rootView);
    }
}
