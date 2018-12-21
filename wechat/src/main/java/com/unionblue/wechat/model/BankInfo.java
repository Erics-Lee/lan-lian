package com.unionblue.wechat.model;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.util.BankAbbreviation;
import com.unionblue.wechat.util.StringUtil;

public class BankInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*1为删除；0为更新或新增*/
	private String isDelete;
	
	/*国家id*/
	private String bankCountry;
	
	/*由四位易于识别的银行行名*/
	private String bankCode;
	
	/*银行id*/
	private String bankId;
	
	/*银行名称*/
	private String bankName;
	
	/*银行帐户编码*/
	private String bankAccount;
	
	/*常规为0，设置为1的时候为默认账户*/
	private String isDefault;
	
	private String allBank;

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getBankCountry() {
		return bankCountry;
	}

	public void setBankCountry(String bankCountry) {
		this.bankCountry = bankCountry;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		if(StringUtil.isEmpty(getBankId()) && !StringUtil.isEmpty(bankCode)){
			setBankId(BankAbbreviation.getBankNameByEn(bankCode));
		}
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAllBank() {
		if(StringUtil.isEmpty(allBank)){
			return JSONObject.toJSONString(BankAbbreviation.getBank());
		}
		return allBank;
	}


	

}
