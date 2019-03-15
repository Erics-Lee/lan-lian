package com.unionblue.wechat.controller;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.unionblue.wechat.model.WechatUser;
import com.unionblue.wechat.service.WechatSservice;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.SignUtil;
import com.unionblue.wechat.util.StringUtil;
import com.unionblue.wechat.wechatService.message.DefaultMessageHandlerImpl;
import com.unionblue.wechat.wechatService.message.IMessageHandler;
import com.unionblue.wechat.wechatService.message.InputMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.weixin4j.Configuration;
import org.weixin4j.message.Articles;
import org.weixin4j.message.OutputMessage;
import org.weixin4j.message.TextOutputMessage;
import org.weixin4j.util.XStreamFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Wechat2Controller {
    protected static final Logger logger = LoggerFactory.getLogger(Wechat2Controller.class);
    @Autowired
    private WechatSservice wechatSservice;
    
    @RequestMapping(value = "/user2", method = RequestMethod.GET)
    public String user2(String code, String state, String serviceIp, Model model, HttpSession session,HttpServletResponse response,HttpServletRequest request) {
        Map map = new HashMap();
        map.put("grant_type","authorization_code");
        map.put("code",code);
        map.put("appid","wxf071d46282bd559f");
        map.put("secret","81cd36b42a2cb5b8fa5b036b82fe33ed");
        String uri1 = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + map.get("appid") + 
    		      "&secret=" + map.get("secret") + "&code=" + map.get("code") + "&grant_type=authorization_code";
        String openid = getOpenId(uri1, map);
        String uri2 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + map.get("appid") + "&secret=" + map.get("secret");
        String Access_token = getOpenAccessToken(uri2, map);
        String uri = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + Access_token + "&openid=" + openid + "&lang=zh_CN";
        System.out.println("uri:"+uri);
        WechatUser wechatUser = wechatSservice.getWechatUser(openid,Access_token);
        request.getSession().setAttribute("bankInfo", "ALL");
        request.getSession().setAttribute("headImgUrl", wechatUser.getHeadimgurl());
        request.getSession().setAttribute("nickName", wechatUser.getNickname());
        request.getSession().setAttribute("openId", wechatUser.getOpenid());
        model.addAttribute("time", System.currentTimeMillis());
        
        if(!StringUtil.isEmpty(state) && state.equals("tax")){
        	System.out.println(3);
        	model.addAttribute("response", "tax");
        	return "/login";
        }else if(!StringUtil.isEmpty(state) && state.equals("index")){
        	System.out.println(1);
        	return "/index";
        }else if(!StringUtil.isEmpty(state) && state.equals("mine")){
        	System.out.println(2);
        	model.addAttribute("response", "mine");
        	return "/login";
		}
        return null;
    }
    
    private String getOpenId(String uri, Map<String, String> map){
        HttpPost post = new HttpPost(uri);
    	String openid = "";
        try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(post);
			//System.out.println(EntityUtils.toString(httpResponse.getEntity()));
			JSONObject returnJsonStr = JSONObject.parseObject(EntityUtils.toString(httpResponse.getEntity()));
			openid = returnJsonStr.getString("openid");
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return openid;
    }
    
    private String getOpenAccessToken(String uri, Map<String, String> map){
        HttpPost post = new HttpPost(uri);
    	String access_token = "";
        try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(post);
			String result = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(result);
			JSONObject returnJsonStr = JSONObject.parseObject(result);
			access_token = returnJsonStr.getString("access_token");
		} catch (ClientProtocolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        return access_token;
    }


}
