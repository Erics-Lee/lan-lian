package com.unionblue.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.controller.CompanyLetterheadController;
import com.unionblue.wechat.model.InvoiceTasks;
import com.unionblue.wechat.model.OrderReturnJosn;
import com.unionblue.wechat.model.StatusReturnJson;
import com.unionblue.wechat.service.OrderService;
import com.unionblue.wechat.util.BankAbbreviation;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 18501 on 2018/9/19.
 */
@Service
@EnableCaching
public class OrderServiceImpl implements OrderService {

	private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);
	
    @Override
    public String invoiceOrder(String sessionKey, String InvoiceTasks, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CompanyDefaultAddress",null,sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnStr = (String) json.get("ReturnJson");
            JSONObject ReturnJson = JSONObject.parseObject(ReturnStr);
            //联络地址
            String AddressID = String.valueOf(ReturnJson.get("id"));
            map.put("AddressID", AddressID);

            Map hashMap = new HashMap();
            //查询银行账户
            Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/BankAccountDefault", null, sessionKey);
            json = JSONObject.parseObject(Access_token);
            ReturnCode = (String) json.get("ReturnCode");
            System.out.println("eeeeeee"+ReturnCode);
            if(!ReturnCode.equals("0000")){
            	String bankCode = request.getSession().getAttribute("bankInfo").toString();
            	String bankName = BankAbbreviation.getBankNameById(BankAbbreviation.getBankNameByEn(bankCode));
                hashMap.put("BankCountry", "156");
                hashMap.put("SWIFTCode", bankCode);
                hashMap.put("BankCode", bankCode);
                hashMap.put("BankName", bankName);
                hashMap.put("BankAccount", "0");
                hashMap.put("IsDefault", "1");
                hashMap.put("IsDelete", "0");
                LOG.info("aaaaa"+JSONObject.toJSONString(hashMap));
                Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/BankAccountUpdate", hashMap, sessionKey);
                LOG.info("bbbbb"+Access_token);
                Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/BankAccountDefault", null, sessionKey);
                LOG.info("ccccc"+Access_token);
                json = JSONObject.parseObject(Access_token);
                LOG.info("ddddd"+json.toJSONString());
            }
            ReturnStr = (String) json.get("ReturnJson");
            ReturnJson = JSONObject.parseObject(ReturnStr);
            //转账银行
            String BankID = String.valueOf(ReturnJson.get("id"));
            map.put("BankID", BankID);

            map.put("RealizationMode", "1");
            map.put("AgreeOrderContract", "1");
            map.put("InvoiceTasks", JSON.parseArray(InvoiceTasks));
            

            LOG.info("InvoiceTasks:"+InvoiceTasks);

            hashMap.put("StructJSON", JSONObject.toJSONString(map));
            LOG.info("StructJSON:"+JSONObject.toJSONString(map));
            
            Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoiceOrderEx", hashMap, sessionKey);
            LOG.info(Access_token);
            json = JSONObject.parseObject(Access_token);
            ReturnCode = (String) json.get("ReturnCode");
            String ReturnMessage = (String) json.get("ReturnMessage");
            if (ReturnCode.equals("0000")) {
                return JsonUtil.success(ReturnMessage);
            } else {
                return JsonUtil.error(ReturnMessage);
            }
        }
    }

    @Override
    public String orderNumber(String sessionKey) {
        HashMap map = new HashMap();
        map.put("companyno","0");
        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/OrderStatistics",map,sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");
            JSONObject ReturnJsonStr = JSONObject.parseObject(ReturnJson);
            //联络地址
            String EntireOrders = String.valueOf(ReturnJsonStr.get("EntireOrders"));
            map.put("EntireOrders", EntireOrders);
            return JsonUtil.success(map);
        }
    }

    @Override
    public String invoiceOrderQuery(String sessionKey) {
        Map map = new HashMap();
        map.put("status", "-1");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoiceOrderQuery",map,sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");

            List<OrderReturnJosn> list = new ArrayList<>();
            list = JSON.parseArray(ReturnJson, OrderReturnJosn.class);

            for (int i = 0; i < list.size(); i++) {
                BigDecimal InvoiceTaxPrice = new BigDecimal(list.get(i).getTotalInvoiceTaxPrice());
                double AllInvoiceTaxPrice = InvoiceTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() / 2;
                list.get(i).setTotalInvoiceTaxPrice(String.valueOf(AllInvoiceTaxPrice));
            }

            return JsonUtil.success(list);
        }
    }

    @Override
    public String invoiceOrderModify(String sessionKey,String orderID) {
        Map map = new HashMap();
        map.put("OrderID",orderID);
        map.put("IsDelete","0");
        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoiceOrderModify",map,sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");
            JSONObject ReturnJsonStr = JSONObject.parseObject(ReturnJson);
            //联络地址
            String status = String.valueOf(ReturnJsonStr.get("status"));
            map.put("status", status);
            return JsonUtil.success(map);
        }
    }



    public static String listToString(List<InvoiceTasks> list){
        if(list==null){
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for(InvoiceTasks InvoiceTasks :list) {
            if(first) {
                first=false;
            }else{
                result.append(",");
            }
            result.append(InvoiceTasks);
        }
        return result.toString();
    }

}
