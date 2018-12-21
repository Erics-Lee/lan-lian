package com.unionblue.wechat.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.unionblue.wechat.model.BankInfo;

public class BankAbbreviation {

	
	public static String getBankNameEn(String name){
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "ICBC");
		map.put("2", "ABC");
		map.put("3", "BOC");
		map.put("4", "CCB");
		map.put("5", "BCM");
		map.put("6", "PSBC");
		map.put("7", "CMBC");
		return map.get(name);
	}
	
	public static String getBankNameByEn(String name){
		Map<String, String> map = new HashMap<String, String>();
		map.put("ICBC", "1");
		map.put("ABC", "2");
		map.put("BOC", "3");
		map.put("CCB", "4");
		map.put("BCM", "5");
		map.put("PSBC", "6");
		map.put("CMBC", "7");
		return map.get(name);
	}
	
	public static String getBankNameById(String id){
		Map<String, String> map = getBank();
		return map.get(id);
	}
	
	public static Map<String, String> getBank(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "中国工商银行");
		map.put("2", "中国农业银行");
		map.put("3", "中国银行");
		map.put("4", "中国建设银行");
		map.put("5", "交通银行");
		map.put("6", "中国邮政储蓄银行");
		map.put("7", "中国民生银行");
		return map;
	}
	
}
