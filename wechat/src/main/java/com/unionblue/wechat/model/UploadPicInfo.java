package com.unionblue.wechat.model;

import java.io.Serializable;

public class UploadPicInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ParentAppKey;
	
	private String companyno;
	
	private String ImageBinaryCodes;
	
	private String errMessage;

	public String getParentAppKey() {
		return ParentAppKey;
	}

	public void setParentAppKey(String parentAppKey) {
		ParentAppKey = parentAppKey;
	}

	public String getCompanyno() {
		return companyno;
	}

	public void setCompanyno(String companyno) {
		this.companyno = companyno;
	}

	public String getImageBinaryCodes() {
		return ImageBinaryCodes;
	}

	public void setImageBinaryCodes(String imageBinaryCodes) {
		ImageBinaryCodes = imageBinaryCodes;
	}
	
	

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	@Override
	public String toString() {
		return "UploadPicInfo [ParentAppKey=" + ParentAppKey + ", companyno=" + companyno + ", ImageBinaryCodes="
				+ ImageBinaryCodes + ", errMessage=" + errMessage + "]";
	}

	

	

	
	
	

}
