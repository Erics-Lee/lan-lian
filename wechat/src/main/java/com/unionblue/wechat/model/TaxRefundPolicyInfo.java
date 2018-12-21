package com.unionblue.wechat.model;

import java.io.Serializable;

/*各国退税政策信息*/
public class TaxRefundPolicyInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String OrderIndex;
	
	private String RuleContent;

	public String getOrderIndex() {
		return OrderIndex;
	}

	public void setOrderIndex(String orderIndex) {
		OrderIndex = orderIndex;
	}

	public String getRuleContent() {
		return RuleContent;
	}

	public void setRuleContent(String ruleContent) {
		ruleContent = ruleContent.replaceAll("[{]%", "<p style='");
		ruleContent = ruleContent.replaceAll("\\|", ";'>");
		ruleContent = ruleContent.replaceAll("%}", "</p>");
		RuleContent = ruleContent;
	}

	@Override
	public String toString() {
		return "TaxRefundPolicyInfo [OrderIndex=" + OrderIndex + ", RuleContent=" + RuleContent + "]";
	}

}
