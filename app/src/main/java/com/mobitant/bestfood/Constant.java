package com.mobitant.bestfood;

public interface Constant {
    int MAX_LENGTH_DESCRIPTION = 50;

    int MAP_MAX_ZOOM_LEVEL = 10;
    int MAP_ZOOM_LEVEL_DETAIL = 13;

    String ORDER_TYPE_HITS = "hits_cnt";
    String ORDER_TYPE_FAVORITE = "keep_cnt";
    String ORDER_TYPE_RECENT = "reg_date";
    public static final String NETWORK_URL = "http://192.168.10.104:8005/";
    public static final String CHAT_SERVER_URL = "http://192.168.10.104:8005/";

        /*
    게시글 :
    MainActivity -> RegisterActivity : 1003
    ListFragment -> RegisterActivity : 1002
    ContestActivity -> RegisterActivity : 1001
NotificationActivity -> RegisterActivity : 1004
     */
}