package com.unionblue.wechat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.model.StatisticsCompany;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

/**
 * Created by 18501 on 2019/4/25.
 */
@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

    /**
     * 企业统计列表长度
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "StatisticsOrderReport")
    @ResponseBody
    public String StatisticsOrderReport(HttpServletRequest request, HttpServletResponse response, String Account, String Password, String ParentAppKey) throws ParseException {
        try {
            Map map = new HashMap();
            Map Return = new HashMap();
            login(request,Account,Password,ParentAppKey);
            String Count;
            map.put("StartIndex","0");
            map.put("FinalIndex","0");
            map.put("LinkerAppKey",ParentAppKey);
            String sessionKey = HttpClinetUtil.getSessionKey(request);
            String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/StatisticsOrderReport",map,sessionKey);
            JSONObject json = JSONObject.parseObject(Access_token);
            String ReturnCode = (String) json.get("ReturnCode");
            if (!ReturnCode.equals("0000")) {
                    return JsonUtil.error(json.get("ReturnMessage"));
                } else {
                    String ReturnStr = (String) json.get("ReturnJson");
                    JSONObject ReturnJson = JSONObject.parseObject(ReturnStr);
                    List Reports = (List) ReturnJson.get("Reports");
                    Count = String.valueOf(Reports.size());
                }
//                Return.put("Count",Count);
//            return JsonUtil.success(Return);
            return Count;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("列表长度获取失败");
        }
    }

    /**
     * 企业统计数据
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "StatisticsCompany")
    @ResponseBody
    public String StatisticsCompany(HttpServletRequest request, HttpServletResponse response, String Account, String Password, String ParentAppKey, String StartIndex, String FinalIndex) throws ParseException {
        try {
            login(request,Account,Password,ParentAppKey);
            Map map  = new HashMap();
            Map map1  = new HashMap();
            Map map2  = new HashMap();
            Map map3  = new HashMap();
            Map map4  = new HashMap();
            map1.put("status","-1");
            if(StringUtil.isEmpty(StartIndex)){
                map1.put("StartIndex","0");
            }else {
                map1.put("StartIndex",StartIndex);
            }
            if(StringUtil.isEmpty(FinalIndex)){
                map1.put("FinalIndex","0");
            }else {
                map1.put("FinalIndex",FinalIndex);
            }
            String sessionKey = request.getSession().getAttribute("sessionKey").toString();
            String Access_token1 = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/EntireCompanyQuery",map1,sessionKey);
            JSONObject json1 = JSONObject.parseObject(Access_token1);
            String ReturnJson1 = (String) json1.get("ReturnJson");
            map2.put("StartIndex","0");
            map2.put("FinalIndex","0");
            String Access_token2 = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/StatisticsOrderReport",map2,sessionKey);
            JSONObject json2 = JSONObject.parseObject(Access_token2);
            String ReturnJson2 = (String) json2.get("ReturnJson");
            JSONObject Reports = JSONObject.parseObject(ReturnJson2);
            JSONArray Report = (JSONArray) Reports.get("Reports");
            String count3 = String.valueOf(Reports.get("AllInvoices"));
            String count4 = String.valueOf(Reports.get("AllowInvoices"));
            List<StatisticsCompany> list1 = JSON.parseArray(ReturnJson1, StatisticsCompany.class);
            List<StatisticsCompany> list2 = JSON.parseArray(Report.toJSONString(), StatisticsCompany.class);
            String count1 = String.valueOf(list1.size());
            String count2;
            int count = 0;
            for (StatisticsCompany companyAll : list1) {
//                if(companyAll.getStatus().equals("2")){
//                    count = count + 1;
//                }
                for (StatisticsCompany companyPart : list2) {
                    if(companyAll.getCompanyNo().equals(companyPart.getCompanyNo())){
                        companyAll.setAllInvoices(companyPart.getAllInvoices());
                        companyAll.setAllowInvoices(companyPart.getAllowInvoices());
                        companyAll.setStatus(companyPart.getStatus());
                    }
                }
            }
            count2 = String.valueOf(count);
            for (StatisticsCompany companyAll : list1) {
                if(StringUtil.isEmpty(companyAll.getAllInvoices())){
                    companyAll.setAllInvoices("0");
                }
                if(StringUtil.isEmpty(companyAll.getAllowInvoices())){
                    companyAll.setAllowInvoices("0");
                }
                if(StringUtil.isEmpty(companyAll.getStatus())){
                    companyAll.setStatus("0");
                }
            }
            map4.put("status","2");
//            String Access_token3 = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CompanyCheckMessage",map4,sessionKey);
//            JSONObject json3 = JSONObject.parseObject(Access_token3);
//            String ReturnJson3 = (String) json3.get("ReturnJson");
//            JSONObject ReturnJsonObj3 = JSONObject.parseObject(ReturnJson3);
//            String count2 = String.valueOf(ReturnJsonObj3.get("Count"));
            map.put("count1",count1);
            map.put("count2",count2);
            map.put("count3",count3);
            map.put("count4",count4);
            map.put("Reports",list1);
            return JsonUtil.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("企业统计数据获取失败");
        }
    }

    //登陆
    public String login(HttpServletRequest request, String Account, String Password, String ParentAppKey){
        String sessionKey = "";
        try {
            sessionKey = HttpClinetUtil.getSessionKey(request);
            String Access_token = HttpClinetUtil.postMap("http://hyisoft.f3322.net:8088/eTaxAPIs/eTaxAPIs100/LoginStatus",null,sessionKey);
            JSONObject json = JSONObject.parseObject(Access_token);
            String returnCode = (String) json.get("ReturnCode");
            if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
                return JsonUtil.success("已登陆");
            }else{
                return loginInfo(request, sessionKey, Account,Password,ParentAppKey);
            }
        } catch (Exception e) {
            sessionKey = UUID.randomUUID().toString();
            return loginInfo(request, sessionKey, Account,Password,ParentAppKey);
        }
        //return "Hello World!";
    }
    private String loginInfo(HttpServletRequest request, String sessionKey, String Account, String Password, String ParentAppKey){
        Map<String, String> map=new HashMap<String, String>();
        try{
            if(StringUtil.isEmpty(Account) || StringUtil.isEmpty(Password) || StringUtil.isEmpty(ParentAppKey)){
                map.put("Account","unionblue2019@lan-lian.com");
                map.put("Password","8d0e8857723f6089342df7d070045f97");
                map.put("ParentAppKey","99fd0016d355e8898aaa99fe92df5e68");
            } else {
                map.put("Account",Account);
                map.put("Password",Password);
                map.put("ParentAppKey",ParentAppKey);
            }
            String Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/FirstLogin",map,sessionKey);
            JSONObject json = JSONObject.parseObject(Access_token);
            String returnCode = (String) json.get("ReturnCode");
            if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
                request.getSession().setAttribute("sessionKey", sessionKey);
                return JsonUtil.success("系统登入成功");
            } else {
                String ReturnMessage = (String) json.get("ReturnMessage");
                return JsonUtil.error("ReturnMessage");
            }
        }catch (Exception e) {
            return JsonUtil.error("系统登入失败");
        }
    }

}
