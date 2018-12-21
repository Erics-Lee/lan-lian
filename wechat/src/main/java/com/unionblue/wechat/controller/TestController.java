package com.unionblue.wechat.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.service.ITestService;
import com.unionblue.wechat.service.WechatSservice;
import com.unionblue.wechat.util.ApiTokenUtil;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.KeyEnum;
import com.unionblue.wechat.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class TestController {

	@Autowired
	private ITestService service;
    @Autowired
    private WechatSservice wechatSservice;
    
    @RequestMapping("/hello")
    public String index(HttpServletRequest request){
        request.setAttribute("mima", "hello world111");
        return "/hello";
        //return "Hello World!";
    }

    @RequestMapping("/getAccess_token")
    @ResponseBody
    public String getAccess_token(HttpServletRequest request){

        String Access_token =wechatSservice.getAccess_token();


        request.setAttribute("mima", Access_token);
        return  Access_token;
        //return "Hello World!";
    }
    @RequestMapping("/hello3")
    public String index3(HttpServletRequest request){
        request.setAttribute("mima", "hello world111");
        return "/index3";
        //return "Hello World!";
    }
    @RequestMapping("/jiami")
    @ResponseBody
    public String jiami(HttpServletRequest request ,int keyA,int keyB ,String jiamiStr) throws Exception {


        String mima = ApiTokenUtil.encryptDual(jiamiStr, KeyEnum.getPublickeyName(keyA),KeyEnum.getprivatekeyName(keyB));
        request.setAttribute("mima", mima);
        return  mima;
        //return "Hello World!";
    }

    @RequestMapping("/jiemi")
    @ResponseBody
    public String jiemi(HttpServletRequest request,int keyA,int keyB ,String jiamiStr){
        String mima="";
        try {
            mima = ApiTokenUtil.decryptDual(jiamiStr,KeyEnum.getPublickeyName(keyB),KeyEnum.getprivatekeyName(keyA));// 解密密文
            request.setAttribute("mima", mima);
        } catch (Exception e) {
            mima="请核对密码本";
            e.printStackTrace();
        }


        return mima ;
        //return "Hello World!";
    }

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @RequestMapping("/tttt")
    @ResponseBody
    public String tttt(HttpServletRequest request,String info){
    	System.out.println(redisTemplate.getExpire("test::"+info));
    	String result = service.testInfo(info);
    	if(!StringUtil.isEmpty(result) && redisTemplate.getExpire("test::"+info) == -1) {
    		redisTemplate.expire("test::"+info, 20, TimeUnit.SECONDS);
    	}
        return result;
        //return "Hello World!";
    }



    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        String sessionKey = "";
        try {
        	sessionKey = request.getSession().getAttribute("sessionKey").toString();
        	String Access_token = HttpClinetUtil.postMap("http://hyisoft.f3322.net:8088/eTaxAPIs/eTaxAPIs100/LoginStatus",null,sessionKey);
        	JSONObject json = JSONObject.parseObject(Access_token);
	        String returnCode = (String) json.get("ReturnCode");
	        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
	        	return JsonUtil.success("已登陆");	        	
	        }else{
	        	return loginInfo(request, sessionKey);
	        }
		} catch (Exception e) {
			sessionKey = UUID.randomUUID().toString();
			return loginInfo(request, sessionKey);
		}
        //return "Hello World!";
    }
    
    private String loginInfo(HttpServletRequest request, String sessionKey){
    	Map<String, String> map=new HashMap<String, String>();
        map.put("Account","quaniya@163.com");
        map.put("Password","598910564");
        map.put("ParentAppKey","d32ddqwr2sqf4t3qef4t34fqwq32r2de2ed");
//        map.put("Account","jackiehcc@hotmail.com");
//        map.put("Password","1qaz2wsx");
//        map.put("ParentAppKey","d32ddqwr2sqf4t3qef4t34fqwq32r2de2ed");
    	String Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/FirstLogin",map,sessionKey);
		JSONObject json = JSONObject.parseObject(Access_token);
        String returnCode = (String) json.get("ReturnCode");
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	request.getSession().setAttribute("sessionKey", sessionKey);
        }
		return Access_token ;
    }
    
    @RequestMapping("/uploadfile")
    @ResponseBody
    public String uploadfile(HttpServletRequest request,String info){
        Map map=new HashMap();
        map.put("Account","quaniya@163.com");
       /* map.put("Password","598910564");
        map.put("LinkerAppKey","e7c10cd40d289f3956798466b1a00bb5");*/
        //HttpClinetUtil.setSessionKey(request.getSession().getId());
        String Access_token =HttpClinetUtil.doGet("http://hyisoft.f3322.net:8088/eTaxAPIs/eTaxAPIs100/CompanyBaseProfilesQuery",map,request.getSession().getId());
        return Access_token ;
        //return "Hello World!"; 
    }

}
