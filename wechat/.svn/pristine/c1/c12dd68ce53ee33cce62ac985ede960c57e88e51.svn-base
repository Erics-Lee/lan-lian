package com.unionblue.wechat.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.model.Account;
import com.unionblue.wechat.model.CompanyInfo;
import com.unionblue.wechat.service.UserService;
import com.unionblue.wechat.util.Common;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.ReturnTokenUtil;
import com.unionblue.wechat.util.StringUtil;

import javassist.bytecode.Descriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 18501 on 2018/9/19.
 */
@Service
@EnableCaching
public class UserServiceImpl implements UserService{
	
	@Value("${api.url}")
    private String url;
	
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
    @Override
    public String accountExtendProfileQuery(String sessionKey) {
        Map map = new HashMap();

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountSimpleProfileQuery", map, sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");


        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            Map result = new HashMap();

            String ReturnJson = (String) json.get("ReturnJson");
            JSONObject ReturnJsonStr = JSONObject.parseObject(ReturnJson);

            //昵称
            String nickname = (String) ReturnJsonStr.get("nickname");
            result.put("nickname", nickname);

            Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountExtendProfileQuery", map, sessionKey);
            json = JSONObject.parseObject(Access_token);
            ReturnCode = (String) json.get("ReturnCode");

            ReturnJson = (String) json.get("ReturnJson");
            ReturnJsonStr = JSONObject.parseObject(ReturnJson);

            //手机号码
            try {
            	String mobilephonenumber = (String) ReturnJsonStr.get("mobilephonenumber");
                result.put("mobilephonenumber", mobilephonenumber);
			} catch (Exception e) {
				result.put("mobilephonenumber", "");
			}
            //邮箱
            try {
            	String secndemailaddress = (String) ReturnJsonStr.get("secndemailaddress");
                result.put("secndemailaddress", secndemailaddress);
			} catch (Exception e) {
				result.put("secndemailaddress", "");
			}

            /*String Company = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/QueryAgentCompanies", map, sessionKey);
            JSONObject CompanyJson = JSONObject.parseObject(Company);
            //ReturnJson
            String CompanyReturnStr = (String) CompanyJson.get("ReturnJson");
            JSONArray CompanyReturnJson = JSONObject.parseArray(CompanyReturnStr);
            //公司编号
            JSONObject companynoAyyay = CompanyReturnJson.getJSONObject(0);
            String companyno = String.valueOf(companynoAyyay.get("companyno"));
            result.put("companyno", companyno);
            //公司名称
            String rawcompanyname = (String) companynoAyyay.get("rawcompanyname");
            result.put("rawcompanyname", rawcompanyname);*/
            CompanyInfo info = Common.getCompanyInfo(url, sessionKey);
            if(!StringUtil.isEmpty(info.getCompanyno())){
            	result.put("companyno", info.getCompanyno());
            	result.put("rawcompanyname", info.getRawcompanyname());            	
            }else{
            	result.put("companyno", "");
            	result.put("rawcompanyname", "");    
            }

            return JsonUtil.success(result);
        }
    }

    @Override
    public String accountSimpleProfileUpdate(String sessionKey, String nickname) {
        Map map = new HashMap();

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountSimpleProfileQuery", map, sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");

        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            map.put("nickname", nickname);
            Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/AccountSimpleProfileUpdate", map, sessionKey);
            json = JSONObject.parseObject(Access_token);
            ReturnCode = (String) json.get("ReturnCode");
            if(ReturnCode.equals("0000")) {
                return JsonUtil.success("个人昵称修改成功");
            } else {
                return JsonUtil.error(json.get("ReturnMessage"));
            }
        }
    }

    @Override
    public String accountExtendProfileUpdate(String sessionKey, String mobilephonenumber, String secndemailaddress) {
        Map map = new HashMap();
        map.put("defaultaddress", "1");
        map.put("addresstype", "1");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountSimpleProfileQuery", map, sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (ReturnCode.equals("0002")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            if (!StringUtil.isEmpty(mobilephonenumber)) {
                map.put("mobilephonenumber", mobilephonenumber);
            } else {
                Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountExtendProfileQuery", map, sessionKey);
                json = JSONObject.parseObject(Access_token);
                String returnJson = (String) json.get("ReturnJson");
                JSONObject returnJsonStr = JSONObject.parseObject(returnJson);

                //手机号码
                try {					
                	mobilephonenumber = (String) returnJsonStr.get("mobilephonenumber");
                	map.put("mobilephonenumber", mobilephonenumber);
				} catch (Exception e) {
					map.put("mobilephonenumber", "");
				}
            }
            if (!StringUtil.isEmpty(secndemailaddress)) {
                map.put("secndemailaddress", secndemailaddress);
            } else {
                Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountExtendProfileQuery", map, sessionKey);
                json = JSONObject.parseObject(Access_token);
                String returnJson = (String) json.get("ReturnJson");
                JSONObject returnJsonStr = JSONObject.parseObject(returnJson);

                //邮箱
                try {
                	secndemailaddress = (String) returnJsonStr.get("secndemailaddress");
                	map.put("secndemailaddress", secndemailaddress);					
				} catch (Exception e) {
					map.put("secndemailaddress", "");	
				}
            }

            Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/AccountExtendProfileUpdate", map, sessionKey);
            json = JSONObject.parseObject(Access_token);
            ReturnCode = (String) json.get("ReturnCode");
            if (!ReturnCode.equals("0000")) {
                return JsonUtil.error(json.get("ReturnMessage"));
            } else {
                return JsonUtil.success("个人信息修改成功");
            }
        }
    }

    @Override
    public String userDetail(String sessionKey) {
        Map map = new HashMap();
        map.put("IsBase64Code", "0");

        String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CompanyBaseProfilesQuery", map, sessionKey);
        JSONObject json = JSONObject.parseObject(Access_token);
        String ReturnCode = (String) json.get("ReturnCode");
        if (!ReturnCode.equals("0000")) {
            return JsonUtil.error(json.get("ReturnMessage"));
        } else {
            String ReturnJson = (String) json.get("ReturnJson");
            JSONObject ReturnJsonStr = JSONObject.parseObject(ReturnJson);
            String companyno = String.valueOf(ReturnJsonStr.get("companyno"));
            map.put("companyno", companyno);

            String InvoiceStatistics = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InvoiceStatistics", map, sessionKey);
            String OrderStatistics = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/OrderStatistics", map, sessionKey);
            LOG.info("xxxx"+InvoiceStatistics);
            LOG.info("yyyy"+OrderStatistics);
            JSONObject InvoiceStatisticsJson = JSONObject.parseObject(InvoiceStatistics);

            String InvoiceStatisticsJsonReturnJson = (String) InvoiceStatisticsJson.get("ReturnJson");
            JSONObject InvoiceStatistic = JSONObject.parseObject(InvoiceStatisticsJsonReturnJson);
            //发票上传数
            String AllInvoices = String.valueOf(InvoiceStatistic.get("AllInvoices"));
            map.put("AllInvoices", AllInvoices);
            //发票有效数
            String AllowInvoices = String.valueOf(InvoiceStatistic.get("AllowInvoices"));
            map.put("AllowInvoices", AllowInvoices);
            //发票审核数
            String NoneInvoices = String.valueOf(InvoiceStatistic.get("NoneInvoices"));
            map.put("NoneInvoices", NoneInvoices);

            JSONObject OrderStatisticsJson = JSONObject.parseObject(OrderStatistics);
            String OrderStatisticsReturnJson = (String) OrderStatisticsJson.get("ReturnJson");
            JSONObject OrderStatistic = JSONObject.parseObject(OrderStatisticsReturnJson);
            //退税订单数
            String EntireOrders = String.valueOf(OrderStatistic.get("EntireOrders"));
            map.put("EntireOrders", EntireOrders);
            //进行中订单数
            String RuningOrders = String.valueOf(OrderStatistic.get("RuningOrders"));
            map.put("RuningOrders", RuningOrders);
            //未开始订单数
            String NotBeginOrders = String.valueOf(OrderStatistic.get("NotBeginOrders"));
            map.put("NotBeginOrders", NotBeginOrders);

            /*String Company = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/QueryAgentCompanies", map, sessionKey);
            JSONObject CompanyJson = JSONObject.parseObject(Company);
            //ReturnJson
            String CompanyReturnStr = (String) CompanyJson.get("ReturnJson");
            JSONArray CompanyReturnJson = JSONObject.parseArray(CompanyReturnStr);
            //公司名称
            JSONObject companynoAyyay = CompanyReturnJson.getJSONObject(0);
            String rawcompanyname = (String) companynoAyyay.get("rawcompanyname");
            map.put("rawcompanyname", rawcompanyname);

            map.put("IsBase64Code", "0");
            String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CompanyBaseProfilesQuery", map, sessionKey);
            JSONObject json = JSONObject.parseObject(Access_token);
            String ReturnJson = (String) json.get("ReturnJson");
            JSONObject ReturnJsonStr = JSONObject.parseObject(ReturnJson);

            //审核状态
            String status = String.valueOf(ReturnJsonStr.get("status"));
            map.put("status", status);*/
            //获取公司信息
            CompanyInfo companyInfo = Common.getCompanyInfo(url, sessionKey);
            LOG.info(companyInfo.toString());
            map.put("rawcompanyname", companyInfo.getRawcompanyname());
            map.put("compayname", companyInfo.getCompayname());
            map.put("rawaddress", companyInfo.getRawaddress());
            map.put("englishaddress", companyInfo.getEnglishaddress());
            map.put("uniformnumber", companyInfo.getUniformnumber());
            map.put("imageBase64", companyInfo.getImageBase64());
            map.put("status", companyInfo.getStatus());
            map.put("contact", companyInfo.getContact());
            map.put("email", companyInfo.getEmail());

            Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/AccountSimpleProfileQuery", null, sessionKey);
            json = JSONObject.parseObject(Access_token);
            ReturnJson = (String) json.get("ReturnJson");
            ReturnJsonStr = JSONObject.parseObject(ReturnJson);

            //昵称
            String nickname = (String) ReturnJsonStr.get("nickname");
            map.put("nickname", nickname);

            return JsonUtil.success(map);
        }
    }

	@Override
	public String registerEMail(String account, String societyIDToken, String parentAppKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Agree", "1");
		map.put("Account", account);
		map.put("Password", societyIDToken.substring(societyIDToken.length()-8));
		map.put("ParentAppKey", parentAppKey);
		map.put("SocietyID", "1");
		map.put("SocietyIDToken", societyIDToken);
		System.out.println(map.get("Account"));
		String Access_token = HttpClinetUtil.postMap(url+"/RegisterEMail", map, "");
		System.out.println(Access_token);
		String result = ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
		return result;
	}
	
	@Override
	public String registerEMail2(String account, String passwd, String parentAppKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Agree", "1");
		map.put("Account", account);
		map.put("Password", passwd);
		map.put("ParentAppKey", parentAppKey);
		System.out.println(map.get("Account"));
		String Access_token = HttpClinetUtil.postMap(url+"/RegisterEMail", map, "");
		System.out.println(Access_token);
		String result = ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
		return result;
	}
	
	@Override
	public String sendVerificateCode(String account, String parentAppKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Account", account);
		map.put("ParentAppKey", parentAppKey);
		System.out.println(1);
		String Access_token = HttpClinetUtil.postMap(url+"/SendVerificateCode", map, "");
        System.out.println(Access_token);
        String result = ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
        return result;
    }

    @Override
    public String accountCertification(String account, String verificationCode) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Account", account);
        map.put("VerificationCode", verificationCode);
        System.out.println(verificationCode);
        String Access_token = HttpClinetUtil.postMap(url+"/AccountCertification", map, "");
        String result = ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
        System.out.println(result);
        return result;
    }

    @Override
    public String societyQueryAccount(HttpServletRequest request, String societyIDToken) {
    	Map<String, String> map = new HashMap<String, String>();
		map.put("SocietyID", "1");
		map.put("SocietyIDToken", societyIDToken);
		String Access_token = HttpClinetUtil.doGet(url+"/SocietyQueryAccount", map, "");
		
		List<Account> list = (List<Account>) ReturnTokenUtil.getReturnTokenUtilList(Access_token, Account.class);
		System.out.println(list);
		if(list != null && list.size() > 0){
			LOG.info("account: "+list.get(0).getAccount());
			request.getSession().setAttribute("account", list.get(0).getAccount());
			JSONObject json = JSONObject.parseObject(Access_token);
			String returnCode = (String) json.get("ReturnCode");
			String ReturnMessage = (String) json.get("ReturnMessage");
			Map<String, String> map2 = new HashMap<String, String>();
			map2.put("ReturnCode", returnCode);
			map2.put("ReturnMessage", ReturnMessage);
			map2.put("account", list.get(0).getAccount());
			return JsonUtil.success(JSONObject.toJSONString(map2));
		}else{
			return JsonUtil.success(Access_token);
		}
		/*JSONObject json = JSONObject.parseObject(Access_token);
        String returnCode = (String) json.get("ReturnCode");
        LOG.info(Access_token);
        if(!StringUtil.isEmpty(returnCode) && (returnCode.equals("000000") || returnCode.equals("0000"))) {
        	String resultInfo = (String) json.get("ReturnJson");
        	JSONObject ReturnJsonStr = JSONObject.parseObject(resultInfo);
            String account = (String) ReturnJsonStr.get("account");
            LOG.info("account: "+account);
            request.getSession().setAttribute("account", account);
        }
		return JsonUtil.success(Access_token);*/
	}

	@Override
	public String firstLogin(String societyIDToken, String parentAppKey, String account, String sessionKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Account", account);
		map.put("Password", societyIDToken.substring(societyIDToken.length()-8));			
		map.put("ParentAppKey", parentAppKey);
		String Access_token = HttpClinetUtil.postMap(url+"/FirstLogin", map, sessionKey);
		return Access_token;
	}

	@Override
	public String societyLogin2(String societyIDToken, String parentAppKey, String sessionKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("SocietyID", "1");
		map.put("SocietyIDToken", societyIDToken);			
		map.put("SocietyPassword", "");
		map.put("ParentAppKey", parentAppKey);
		LOG.info(""+map);
		String Access_token = HttpClinetUtil.postMap(url+"/SocietyLogin", map, sessionKey);
		LOG.info(Access_token);
		return Access_token;
	}

	
}
