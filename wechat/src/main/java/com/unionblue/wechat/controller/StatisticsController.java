package com.unionblue.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18501 on 2019/4/25.
 */
@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

    /**
     * 企业统计信号数量
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "StatisticsOrderReport")
    @ResponseBody
    public String StatisticsOrderReport(HttpServletRequest request, HttpServletResponse response, String url) throws ParseException {
        try {
            String Count;
            Map map  = new HashMap();
            map.put("StartIndex","0");
            map.put("FinalIndex","0");
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
            return Count;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("信号获取失败");
        }
    }

}
