package com.unionblue.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionblue.wechat.model.BankInfo;
import com.unionblue.wechat.service.IBankMangerService;
import com.unionblue.wechat.util.BankAbbreviation;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.StringUtil;

@Controller
@RequestMapping(value = "/bank")
public class BankMangerController {
	
	@Autowired
	private IBankMangerService service;
	
	/**
     * 提交银行转账账户
     * @return
     */
	@RequestMapping(value = "/bankAccountUpdate", method = RequestMethod.POST)
    @ResponseBody
	public String bankAccountUpdate(HttpServletRequest request, BankInfo info) {
		String sessionKey = HttpClinetUtil.getSessionKey(request);
		if(StringUtil.isEmpty(sessionKey)){
			return JsonUtil.error("未登录系统 ");
		}
		info.setBankCode(BankAbbreviation.getBankNameEn(info.getBankId()));
		String result = service.bankAccountUpdate(info, sessionKey);
		return result;
	}
	
	/**
     * 查询默认银行转账账户
     * @return
     */
	@RequestMapping("/bankAccountDefault")
    @ResponseBody
	public String bankAccountDefault(HttpServletRequest request) {
		String sessionKey = HttpClinetUtil.getSessionKey(request);
		if(StringUtil.isEmpty(sessionKey)){
			return JsonUtil.error("未登录系统 ");
		}
		String result = service.bankAccountDefault(sessionKey);
		return result;
	}
	
	/**
     * 获取所有银行
     * @return
     */
	@RequestMapping("/selectBank")
    @ResponseBody
	public String selectBank(HttpServletRequest request) {
		return JsonUtil.success(BankAbbreviation.getBank());
	}
	
	/**
     * 查询全部银行转账账户
     * @return
     */
	@RequestMapping("/bankAllAccountQuery")
    @ResponseBody
	public String bankAllAccountQuery(HttpServletRequest request) {
		String sessionKey = HttpClinetUtil.getSessionKey(request);
		if(StringUtil.isEmpty(sessionKey)){
			return JsonUtil.error("未登录系统 ");
		}
		String result = service.bankAllAccountQuery(sessionKey);
		return result;
	}

}
