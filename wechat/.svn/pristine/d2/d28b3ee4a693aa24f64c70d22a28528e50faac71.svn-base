package com.unionblue.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unionblue.wechat.model.CountryInfo;
import com.unionblue.wechat.model.TaxRefundPolicy;
import com.unionblue.wechat.service.ICountryService;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.ReturnTokenUtil;
import com.unionblue.wechat.util.StringUtil;

@Service
public class CountryServiceImpl implements ICountryService{

	private static final Logger LOG = LoggerFactory.getLogger(CountryServiceImpl.class);
	
	@Value("${api.url}")
    private String url;
	
	@Override
	public String selectTaxRefundCountrys() {
		System.out.println(url+"/GetTaxRefundCountrys");
        String Access_token = HttpClinetUtil.doGet(url+"/GetTaxRefundCountrys",null,"");
        System.out.println(Access_token);
        return ReturnTokenUtil.getReturnTokenUtilArray(Access_token, CountryInfo.class);

	}

	@Override
	public String selectTaxRefundPolicy(String countryNumber) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("country", countryNumber);
		map.put("PureText", "1");
		System.out.println(countryNumber);
		String Access_token = HttpClinetUtil.doGet(url+"/TaxRefundPolicy",map,"");
		String result = ReturnTokenUtil.getReturnTokenUtilArray(Access_token, TaxRefundPolicy.class);
		System.out.println(result);
		return result;
	}

	@Override
	public String internationalCurrencys() {
		String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/InternationalCurrencys",null,"");
		return ReturnTokenUtil.getReturnTokenUtilArray(Access_token, CountryInfo.class);
	}

	@Override
	public String countryTaxRefundAmounts() {
		String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/CountryTaxRefundAmounts",null,"");
		return ReturnTokenUtil.getReturnTokenUtilArray(Access_token, CountryInfo.class);
	}

}
