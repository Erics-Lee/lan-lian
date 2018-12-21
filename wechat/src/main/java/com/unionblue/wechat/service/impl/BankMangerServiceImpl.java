package com.unionblue.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unionblue.wechat.model.BankInfo;
import com.unionblue.wechat.service.IBankMangerService;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.ReturnTokenUtil;

@Service
public class BankMangerServiceImpl implements IBankMangerService{

	private static final Logger LOG = LoggerFactory.getLogger(BankMangerServiceImpl.class);
	
	@Value("${api.url}")
    private String url;
	
	@Override
	public String bankAccountUpdate(BankInfo info, String sessionKey) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("IsDelete", info.getIsDelete());
		map.put("BankCountry", info.getBankCountry());
		map.put("BankCode", info.getBankCode());
		map.put("BankName", info.getBankName());
		map.put("BankAccount", info.getBankAccount());
		map.put("IsDefault", info.getIsDefault());
		LOG.info("银行账户信息："+info);
		String Access_token = HttpClinetUtil.postMap(url+"/BankAccountUpdate", map , sessionKey);
		LOG.info("result："+Access_token);
		return ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
		//return Access_token;
	}

	@Override
	public String bankAllAccountQuery(String sessionKey) {
		String Access_token = HttpClinetUtil.doGet(url+"/BankAllAccountQuery", null, sessionKey);
		return ReturnTokenUtil.getReturnTokenUtilArray(Access_token, BankInfo.class);
	}

	@Override
	public String bankAccountDefault(String sessionKey) {
		String Access_token = HttpClinetUtil.doGet(url+"/BankAccountDefault", null, sessionKey);
		return ReturnTokenUtil.getReturnTokenUtilObject(Access_token, BankInfo.class);
	}

}
