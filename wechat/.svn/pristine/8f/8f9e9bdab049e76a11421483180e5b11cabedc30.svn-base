package com.unionblue.wechat.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.model.WechatUser;
import com.unionblue.wechat.service.WechatSservice;
import com.unionblue.wechat.util.HttpClinetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@EnableCaching
public class WechatServiceImpl implements WechatSservice {
    @Value("${wechat.appID}")
    private String appID;
    @Value("${wechat.appsecret}")
    private String appsecret;
    private static Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);
    @Override
    //@Cacheable(cacheNames = "getAccess_token#7200" )
    public String getAccess_token() {

        Map map = new HashMap();
        map.put("grant_type","client_credential");

        map.put("appid",appID);
        map.put("secret",appsecret);
        String access_tokenJSON =HttpClinetUtil.doGet("https://api.weixin.qq.com/cgi-bin/token",map);
        JSONObject jsonObj = JSONObject.parseObject(access_tokenJSON);
        logger.info("获取到Access_token",jsonObj);
        String Access_token="";
        try {
            Access_token = jsonObj.getString("access_token");
        } catch (JSONException e) {
            logger.info("----------换取access_token发生了异常:{}----------", e.getMessage());
        }

        return Access_token;
    }

    @Override
    //@Cacheable(value = "codeGetOpenid#7200")
    public String codeGetOpenid(String code) {
        Map map = new HashMap();
        map.put("grant_type","authorization_code");
        map.put("code",code);
        map.put("appid",appID);
        map.put("secret",appsecret);
        String jsonStr=HttpClinetUtil.doGet("https://api.weixin.qq.com/sns/oauth2/access_token",map);
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        logger.info("获取到openid",jsonObj);
        String openid="";
        try {
            openid = jsonObj.getString("openid");
        } catch (JSONException e) {
            logger.info("----------换取openid发生了异常:{}----------", e.getMessage());
        }
        return openid;
    }


    @Override
    //@Cacheable(value = "getWechatUser#7200")
    public WechatUser getWechatUser(String openid, String access_token) {
        logger.info("获取到access_token"+access_token);
        Map map = new HashMap();
        map.put("access_token",access_token);
        map.put("openid",openid);
        map.put("lang","zh_CN");
        String jsonStr=HttpClinetUtil.doGet("https://api.weixin.qq.com/cgi-bin/user/info",map);
        JSONObject jsonObj = JSONObject.parseObject(jsonStr);
        logger.info("获取到user"+jsonObj);
        WechatUser wechatUse=null;
 
        try {
            wechatUse=JSON.parseObject(jsonStr, WechatUser.class);
        } catch (JSONException e) {
            logger.info("----------获取WechatUser发生了异常:{}----------", e.getMessage());
        }

        return wechatUse;
    }
}
