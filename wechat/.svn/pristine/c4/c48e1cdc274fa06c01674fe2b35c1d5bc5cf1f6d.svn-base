package com.unionblue.wechat.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ReturnTokenUtil {

	public static String getReturnTokenUtilArray(String info, Class<?> class1) {
		JSONObject json = JSONObject.parseObject(info);
        String returnCode = (String) json.get("ReturnCode");
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	String resultInfo = (String) json.get("ReturnJson");
        	List<?> list = JSON.parseArray(resultInfo, class1);
        	return JsonUtil.success(list);
        }else {
        	return JsonUtil.error((String) json.get("ReturnMessage"));
        }
	}
	
	public static String getReturnTokenUtilObject(String info, Class<?> class1) {
		JSONObject json = JSONObject.parseObject(info);
        String returnCode = (String) json.get("ReturnCode");
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	String resultInfo = (String) json.get("ReturnJson");
        	//JSON.parseObject(resultInfo, class1);
        	return JsonUtil.success(JSON.parseObject(resultInfo, class1));
        }else {
        	return JsonUtil.error((String) json.get("ReturnMessage"));
        }
	}
	
	public static String getReturnTokenUtilSuccess(String info) {
		JSONObject json = JSONObject.parseObject(info);
        String returnCode = (String) json.get("ReturnCode");
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	return JsonUtil.success((String) json.get("ReturnMessage"));
        }else {
        	return JsonUtil.error((String) json.get("ReturnMessage"),json.get("ReturnCode").toString());
        }
	}
	
	public static List<?> getReturnTokenUtilList(String info, Class<?> class1) {
		JSONObject json = JSONObject.parseObject(info);
        String returnCode = (String) json.get("ReturnCode");
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	String resultInfo = (String) json.get("ReturnJson");
        	List<?> list = JSON.parseArray(resultInfo, class1);
        	return list;
        }else {
        	return null;
        }
	}
}
