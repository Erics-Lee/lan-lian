package com.unionblue.wechat.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.model.CompanyInfo;
import com.unionblue.wechat.service.impl.CompanyLetterheadServiceImpl;

public class Common {
	
	private static final Logger LOG = LoggerFactory.getLogger(Common.class);
	
	public static CompanyInfo getCompanyInfo(String url, String sessionKey){
		Map<String, String> map=new HashMap<String, String>();
        map.put("IsBase64Code","0");
        String Access_token1 = HttpClinetUtil.doGet(url+"/CompanyBaseProfilesQuery",map,sessionKey);
		LOG.info("result:"+Access_token1);
		JSONObject json = JSONObject.parseObject(Access_token1);
        String returnCode = (String) json.get("ReturnCode");
        boolean flag = true;
        CompanyInfo info = new CompanyInfo();
        info.setStatus(-1);
        //在公司基本数据信息中查询
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	String resultInfo = (String) json.get("ReturnJson");
        	info = JSON.parseObject(resultInfo, CompanyInfo.class);
        	if(info != null){
        		flag = false;
        		return selectCompanyLetterheadUser(url, sessionKey, info);
        	}
        }
        if(flag){ 
        	//在代理公司中查询
        	String Access_token2 = HttpClinetUtil.doGet(url+"/QueryAgentCompanies", null, sessionKey);
        	JSONObject json2 = JSONObject.parseObject(Access_token2);
        	LOG.info("result:"+Access_token2);
        	String returnCode2 = (String) json2.get("ReturnCode");
        	if(!StringUtil.isEmpty(returnCode2) && (returnCode2.equals("000000") || returnCode2.equals("0000"))) {
        		String resultInfo = (String) json2.get("ReturnJson");
            	List<CompanyInfo> list = JSON.parseArray(resultInfo, CompanyInfo.class);
            	if(list != null && list.size() > 0){
            		info = list.get(0);
            		info.setStatus(3);
            	}
        	}
        }
        return info;
	}
	
	//获取公司详细信息
	public static CompanyInfo selectCompanyLetterheadUser(String url, String sessionKey, CompanyInfo info) {
		try {
			String Access_token = HttpClinetUtil.doGet(url+"/CompanyDetailedProfilesQuery",null,sessionKey);
			
			JSONObject json = JSONObject.parseObject(Access_token);
	        String returnCode = (String) json.get("ReturnCode");
	        CompanyInfo info2 = new CompanyInfo();
	        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
	        	String resultInfo = (String) json.get("ReturnJson");
	        	info2 = JSON.parseObject(resultInfo, CompanyInfo.class);
	        	if(info2 != null){
	        		info.setContact(info2.getContact());
					info.setEmail(info2.getEmail());
	        	}
	        }		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

}
