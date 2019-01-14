package com.unionblue.wechat.model;

import java.io.Serializable;

//*国家信息
public class CountryInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*国家编号ID*/
	private int CountryNumber;
	
	/*国家中文名称*/
	private String ChinaName;

	//国家
	private String country;

	//币种符号
	private String currencysymbol;

	//币种英文标识
	private String currencycode;

	//币种名称
	private String firstname;

	//季度起退线
	private String QuarterLimitAmt;

	//年度起退线
	private String YearLimitAmt;

	public int getCountryNumber() {
		return CountryNumber;
	}

	public void setCountryNumber(int countryNumber) {
		CountryNumber = countryNumber;
	}

	public String getChinaName() {
		return ChinaName;
	}

	public void setChinaName(String chinaName) {
		ChinaName = chinaName;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrencysymbol() {
		return currencysymbol;
	}

	public void setCurrencysymbol(String currencysymbol) {
		this.currencysymbol = currencysymbol;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getQuarterLimitAmt() {
		return QuarterLimitAmt;
	}

	public void setQuarterLimitAmt(String quarterLimitAmt) {
		QuarterLimitAmt = quarterLimitAmt;
	}

	public String getYearLimitAmt() {
		return YearLimitAmt;
	}

	public void setYearLimitAmt(String yearLimitAmt) {
		YearLimitAmt = yearLimitAmt;
	}
}
