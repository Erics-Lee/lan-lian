package com.unionblue.wechat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.ReturnTokenUtil;

@Controller
public class AppUserController {
	
	@RequestMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(HttpServletRequest request, String account){
		Map<String, String> map = new HashMap<String, String>();
		map.put("Account", account);
		map.put("ParentAppKey", "99fd0016d355e8898aaa99fe92df5e68");
		String Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/ResetPassword",map,"");
		return ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
    }
	
	@RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(HttpServletRequest request, String oldPassed, String newPassed){
		Map<String, String> map = new HashMap<String, String>();
		map.put("OldPassword", oldPassed);
		map.put("NewPassword", newPassed);
		String sessionKey = request.getSession().getAttribute("sessionKey").toString();
		String Access_token = HttpClinetUtil.postMap("https://api.taxchain.one/eTaxAPIs100/ChangePassword",map,sessionKey);
		return ReturnTokenUtil.getReturnTokenUtilSuccess(Access_token);
    	
    }

}
