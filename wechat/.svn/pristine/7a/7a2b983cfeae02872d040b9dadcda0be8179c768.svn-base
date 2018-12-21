package com.unionblue.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {
	
	
	//首页
	@RequestMapping("/index")
    public String index(HttpServletRequest request){
        return "/index";
    }
	
	//个人中心
	@RequestMapping("/personalCenter")
	public String personalCenter(HttpServletRequest request){
	    return "/mine1";
	}
	
	//个人中心
	@RequestMapping("/tax")
	public String tax(HttpServletRequest request, Model mode, String account){
		mode.addAttribute("account", account);
		return "/tax";
	}
	
	//开票信息
	@RequestMapping("/componyInformation")
    public String componyInformation(HttpServletRequest request){
        return "/componyInformation";
    }	
	
	//添加企业
	@RequestMapping("/addCompany")
    public String addCompany(HttpServletRequest request){
        return "/addCompany";
    }	
	
	//帮助中心
	@RequestMapping("/helpCenter")
	public String helpCenter(HttpServletRequest request){
	    return "/helpCenter";
	}	
	
	//帮助中心详情
	@RequestMapping("/helpCenterContent")
	public String helpCenterContent(HttpServletRequest request, Model mode, String questionOrderNumber){
		mode.addAttribute("questionOrderNumber", questionOrderNumber);
	    return "/helpCenterContent";
	}	
	
	//常见问题
	@RequestMapping("/question")
	public String question(HttpServletRequest request, Model mode, String questionOrderNumber){
		mode.addAttribute("questionOrderNumber", questionOrderNumber);
		return "/question";
	}	
	
	

}
