package com.unionblue.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.model.CompanyInfo;
import com.unionblue.wechat.model.InvoiceNumber;
import com.unionblue.wechat.model.InvoiceQuery;
import com.unionblue.wechat.model.StatusReturnJson;
import com.unionblue.wechat.service.InvoiceService;
import com.unionblue.wechat.util.Common;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 18501 on 2018/9/19.
 */
@Service
@EnableCaching
public class InvoiceServiceImpl implements InvoiceService{
	
	@Value("${api.url}")
    private String url;
	
	private static final Logger LOG = LoggerFactory.getLogger(InvoiceServiceImpl.class);
	
    @Override
    public String InvoicesTasksQueryE(String sessionKey) {
        Map map = new HashMap();
        map.put("QueryMode", "1");
        map.put("IsBase64Code", "0");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoicesTasksQueryE",map,sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");

            List<InvoiceNumber> list = new ArrayList();
            list = JSON.parseArray(ReturnJson, InvoiceNumber.class);

            for (int i = 0; i < list.size(); i++) {
                BigDecimal InvoiceTaxPrice = new BigDecimal(list.get(i).getAllInvoiceTaxPrice());
                double AllInvoiceTaxPrice = InvoiceTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() / 2;
                list.get(i).setAllInvoiceTaxPrice(String.valueOf(AllInvoiceTaxPrice));

                map.put("country", list.get(i).getCountry());
                String Country = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CountryTaxRefundAmounts", map, sessionKey);
                JSONObject CountryJson = JSONObject.parseObject(Country);
                //ReturnJson
                String CountryReturnStr = (String) CountryJson.get("ReturnJson");
                JSONArray CountryReturnJsonArray = JSONObject.parseArray(CountryReturnStr);
                //国家起退线
                JSONObject CountryReturnJson = (JSONObject) CountryReturnJsonArray.get(0);
                String YearLimitAmt = String.valueOf(CountryReturnJson.get("QuarterLimitAmt"));
                list.get(i).setYearLimitAmt(YearLimitAmt);
            }
            return JsonUtil.success(list);
        }
    }

    @Override
    public String InvoicesTasksQueryA(String sessionKey) {
        Map map = new HashMap();

        map.put("status", "-1");
        map.put("QueryMode", "0");
        map.put("IsBase64Code", "0");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoicesTasksQueryA", map, sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");

            ReturnCode = (String) json.get("ReturnCode");
            JSONArray ReturnArray = json.getJSONArray("ReturnJson");
            if (ReturnCode.equals("0000") && ReturnArray.size() == 0) {
                return JsonUtil.success("数据为空！");
            } else {
                List<InvoiceQuery> list = new ArrayList();
                list = JSON.parseArray(ReturnJson, InvoiceQuery.class);
                map.put("InvoicesTasksQuery", list);
            }
        }
        return JsonUtil.success(map);
    }

    @Override
    public String InvoicesTasksQueryB(String sessionKey, String status, String country) {
        Map map = new HashMap();
        map.put("status", status);
        String QuarterLimitAmt = null;

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountExtendProfileQuery", map, sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (ReturnCode.equals("0002")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            if (country != null && !country.equals("")) {
                map.put("country", country);
                String Country = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CountryTaxRefundAmounts", map, sessionKey);
                JSONObject CountryJson = JSONObject.parseObject(Country);
                //ReturnJson
                String CountryReturnStr = (String) CountryJson.get("ReturnJson");
                JSONArray CountryReturnJsonArray = JSONObject.parseArray(CountryReturnStr);
                //国家起退线
                JSONObject CountryReturnJson = (JSONObject) CountryReturnJsonArray.get(0);
                QuarterLimitAmt = String.valueOf(CountryReturnJson.get("QuarterLimitAmt"));
            } else {
                map.put("country", "0");
            }

            map.put("QueryMode", "1");
            map.put("createdate", "");
            map.put("IsBase64Code", "0");
            map.put("OrderOnly", "0");

            /*String Company = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/QueryAgentCompanies", map, sessionKey);
            JSONObject CompanyJson = JSONObject.parseObject(Company);
            //ReturnJson
            String CompanyReturnStr = (String) CompanyJson.get("ReturnJson");
            JSONArray CompanyReturnJson = JSONObject.parseArray(CompanyReturnStr);
            //公司编号
            JSONObject companynoAyyay = CompanyReturnJson.getJSONObject(0);
            Integer companyno = (Integer) companynoAyyay.get("companyno");
            map.put("companyno", companyno + "");*/
            //获取公司信息
            CompanyInfo companyInfo = Common.getCompanyInfo(url, sessionKey);
            LOG.info(companyInfo.toString());
            map.put("companyno", companyInfo.getCompanyno());

            Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoicesTasksQueryB", map, sessionKey);
            LOG.info(Access_token);
            json = JSONObject.parseObject(Access_token);
            String ReturnJson = (String) json.get("ReturnJson");

            ReturnCode = (String) json.get("ReturnCode");
            JSONArray ReturnArray = json.getJSONArray("ReturnJson");
            if (ReturnCode.equals("0000") && ReturnArray.size() == 0) {
                return JsonUtil.success("数据为空！");
            } else {

                List<InvoiceQuery> list = new ArrayList();
                list = JSON.parseArray(ReturnJson, InvoiceQuery.class);
                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).getInvoices().size(); j++) {
                        BigDecimal InvoiceTaxPrice = new BigDecimal(list.get(i).getInvoices().get(j).getInvoiceTaxPrice());
                        double AllInvoiceTaxPrice = InvoiceTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() / 2;
                        list.get(i).getInvoices().get(j).setInvoiceTaxPrice(String.valueOf(AllInvoiceTaxPrice));
                    }
                }

                /*map.put("KeyWord", list.get(0).getInvoices().get(0).getCompanyno());
                String  Company = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/QueryCompany", map, sessionKey);
                JSONObject CompanyJson = JSONObject.parseObject(Company);
                //ReturnJson
                String CompanyReturnStr = (String) CompanyJson.get("ReturnJson");
                JSONArray CompanyReturnJson = JSONObject.parseArray(CompanyReturnStr);
                //公司名称
                JSONObject companynameAyyay = CompanyReturnJson.getJSONObject(0);
                String rawcompanyname = (String) companynameAyyay.get("rawcompanyname");*/
                String rawcompanyname = companyInfo.getRawcompanyname();

                for (int i = 0; i < list.size(); i++) {
                    for (int j = 0; j < list.get(i).getInvoices().size(); j++) {
                        list.get(i).getInvoices().get(j).setCompanyno(rawcompanyname);
                    }
                }

                Map hashMap = new HashMap();
                hashMap.put("YearLimitAmt", QuarterLimitAmt);
                hashMap.put("InvoicesTasksQuery", list);

                return JsonUtil.success(hashMap);
            }
        }
    }

    @Override
    public String invoiceQuery(String sessionKey, String tasksid, String invoiceid) {
        Map map = new HashMap();
        map.put("tasksid", tasksid);
        map.put("invoiceid", invoiceid);
        map.put("IsBase64Code", "0");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoiceQuery",map,sessionKey);
        Map result = new HashMap();
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnStr = (String) json.get("ReturnJson");
            JSONObject ReturnJson = JSONObject.parseObject(ReturnStr);
            //发票图片
            String ImageBinaryCodes = (String) ReturnJson.get("ImageBinaryCodes");
            result.put("ImageBinaryCodes", ImageBinaryCodes);

            //企业名称
            String companyno = String.valueOf(ReturnJson.get("companyno"));
            map.put("KeyWord", companyno + "");

            String Company = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/QueryCompany", map, sessionKey);
            JSONObject CompanyJson = JSONObject.parseObject(Company);
            //ReturnJson
            String CompanyReturnStr = (String) CompanyJson.get("ReturnJson");
            JSONArray CompanyNameReturnJson = JSONObject.parseArray(CompanyReturnStr);
            //公司名称
            JSONObject companynameAyyay = CompanyNameReturnJson.getJSONObject(0);
            String rawcompanyname = (String) companynameAyyay.get("rawcompanyname");
            result.put("companyno", rawcompanyname);

            //退税国家
            String country = String.valueOf(ReturnJson.get("country"));
            result.put("country", country);
            //发票类型
            String consumptionType = String.valueOf(ReturnJson.get("consumptionType"));
            result.put("consumptionType", consumptionType);
            //发票金额
            String paidPrice = String.valueOf(ReturnJson.get("paidPrice"));
            result.put("paidPrice", paidPrice);
            //可退税金
            String invoiceTaxPrice = String.valueOf(ReturnJson.get("invoiceTaxPrice"));
            result.put("invoiceTaxPrice", invoiceTaxPrice);
            BigDecimal InvoiceTaxPrice = new BigDecimal(invoiceTaxPrice);
            double invoiceRetireTaxPrice = InvoiceTaxPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() / 2;
            result.put("invoiceRetireTaxPrice", String.valueOf(invoiceRetireTaxPrice));
            //币种
            String countryCurrency = (String) ReturnJson.get("countryCurrency");
            result.put("countryCurrency", countryCurrency);
            //开票日期
            String invoiceDate = (String) ReturnJson.get("invoiceDate");
            result.put("invoiceDate", invoiceDate.replaceAll("T", " "));

            return JsonUtil.success(result);
        }
    }

    @Override
    public String invoicePhoto(String sessionKey, String tasksid, String invoiceid) {
        Map map = new HashMap();
        map.put("tasksid", tasksid);
        map.put("invoiceid", invoiceid);
        map.put("IsBase64Code", "0");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoiceQuery",map,sessionKey);
        Map result = new HashMap();
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnStr = (String) json.get("ReturnJson");
            JSONObject ReturnJson = JSONObject.parseObject(ReturnStr);
            //发票图片
            String ImageBinaryCodes = (String) ReturnJson.get("ImageBinaryCodes");
            result.put("ImageBinaryCodes", ImageBinaryCodes);

            return JsonUtil.success(result);
        }
    }

    @Override
    public String getInvoiceTaskStatus() {
        Map result = new HashMap();
        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/GetInvoiceStatus",result,"");
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");

            List<StatusReturnJson> list = new ArrayList<>();
            list = JSON.parseArray(ReturnJson, StatusReturnJson.class);

            return JsonUtil.success(list);
        }
    }

	@Override
	public String uploadInvoiceImageFiles(String sessionKey, String base64Codes) {
		String Access_token = HttpClinetUtil.postString(url+"/UploadInvoiceImageFiles",base64Codes,sessionKey);
		JSONObject json = JSONObject.parseObject(Access_token);
		String returnCode = (String) json.get("ReturnCode");
		if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
			String resultInfo = (String) json.get("ReturnJson");
			return "200";
		}else {
			return (String) json.get("ReturnMessage");
		}
    }
}