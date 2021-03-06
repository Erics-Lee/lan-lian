package com.unionblue.wechat.model;

import java.io.Serializable;

public class CompanyName implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String address;
	
	private String regno;
	
	private String credit_no;
	
	private String oper_name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public String getCredit_no() {
		return credit_no;
	}

	public void setCredit_no(String credit_no) {
		this.credit_no = credit_no;
	}
}
