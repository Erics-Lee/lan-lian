package com.unionblue.wechat.service;

import com.unionblue.wechat.model.CompanyInfo;

public interface ICompanyLetterheadService {

	String queryCompany(String companyName, String sessionKey);

	String selectCompanyLetterheadById(String sessionKey);

	String addCompanyLetterhead(CompanyInfo info, String sessionKey);

	String assignRelatedCompany(String companyno, String isDelete, String sessionKey);
	
	String selectCompanyInfoByName(String name);

	String selectCompanyLetterheadUser(String sessionKey);

	String AssignRelatedCompany(String sessionKey, String CompanyNo);

}
