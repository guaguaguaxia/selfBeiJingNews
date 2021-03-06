package com.slefbeijingnews.com.utils;

/**
 * 作者：尚硅谷-杨光福 on 2016/8/15 11:44
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：常量类，配置联网请求地址
 */
public class Constants {

    /**
     * 联网请求的ip和端口
     */
//    public static final String BASE_URL = "http://10.0.2.2:8080/web_home";
//    public static final String BASE_URL = "http://192.168.106.15:8080/web_home";
//
//    public static final String BASE_URL = "http://127.0.0.1:8080/web_home";

    public static final String BASE_URL = "https://hd.zjnan.cn";


    /**
     * 新闻中心的网络地址
     */
    public static final String NEWSCENTER_PAGER_URL = BASE_URL+"/material/categories.json";

    /**
     * 商品热卖
     */
    public static final String WARES_HOT_URL = "http://112.124.22.238:8081/course_api/wares/hot?pageSize=";
}
