package com.unionblue.wechat.util;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/16 0016.
 */
public abstract class JsonUtil {

    private final static String SUCCESS_CODE = "200";
    private final static String ERROR_CODE = "400";

    private JsonUtil() {
    }

    /**
     * @author
     * @return
     */
    public static String success() {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("code", SUCCESS_CODE);
        return         JSONObject.toJSONString(map);
    }

    /**
     * @author
     * @param o
     * @return
     */
    public static String success(Object o) {
        Map<String, Object> map = new HashMap<String, Object>(4);
        map.put("code", SUCCESS_CODE);
        map.put("data", o);
        return         JSONObject.toJSONString(map);
    }

    /**
     * @author
     * @param msg
     * @return
     */
    public static String error(String msg) {
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("code", ERROR_CODE);
        map.put("msg", msg);
        return         JSONObject.toJSONString(map);
    }
    public static String error(Object o) {
        Map<String, Object> map = new HashMap<String, Object>(4);
        map.put("code", ERROR_CODE);
        map.put("data", o);
        return         JSONObject.toJSONString(map);
    }
    public static String error() {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("code", ERROR_CODE);
        return         JSONObject.toJSONString(map);
    }
    
    public static String error(String msg, String code) {
        Map<String, String> map = new HashMap<String, String>(4);
        map.put("code", code);
        map.put("msg", msg);
        return  JSONObject.toJSONString(map);
    }
}
