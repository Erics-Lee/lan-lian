package com.unionblue.wechat.model;

import java.io.Serializable;

import com.unionblue.wechat.util.StringUtil;

/*公司信息*/
public class CompanyInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String companyno;
	
	/*原始公司名称*/
	private String rawcompanyname;
	
	/*原始公司地址*/
	private String rawaddress;
	
	/* 英文公司名称 */
	private String compayname;
	
	/* 英文公司地址 */
	private String englishaddress;
	
	/* 联絡人 */
	private String contact;
	
	/* 默认电子邮件 */
	private String email;
	
	/* 默认电话一 */
	private String phone1;
	
	/* 0:公司管理者 1:代理长传者 */
	private Integer status;

	public String getCompanyno() {
		return companyno;
	}

	public void setCompanyno(String companyno) {
		this.companyno = companyno;
	}

	public String getRawcompanyname() {
		return rawcompanyname;
	}

	public void setRawcompanyname(String rawcompanyname) {
		this.rawcompanyname = rawcompanyname;
	}

	public String getRawaddress() {
		return rawaddress;
	}

	public void setRawaddress(String rawaddress) {
		this.rawaddress = rawaddress;
	}

	public String getCompayname() {
		return compayname;
	}

	public void setCompayname(String compayname) {
		this.compayname = StringUtil.isEmpty(compayname) ? "正在翻译中...":compayname;
	}

	public String getEnglishaddress() {
		return englishaddress;
	}

	public void setEnglishaddress(String englishaddress) {
		this.englishaddress = StringUtil.isEmpty(englishaddress) ? "正在翻译中...":englishaddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CompanyInfo [companyno=" + companyno + ", rawcompanyname=" + rawcompanyname + ", rawaddress="
				+ rawaddress + ", compayname=" + compayname + ", englishaddress=" + englishaddress + ", contact="
				+ contact + ", email=" + email + ", phone1=" + phone1 + ", status=" + status + "]";
	}
	
	

	

}
