package com.unionblue.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {
	
	
	//首页
		@RequestMapping("/test")
	    public String test(HttpServletRequest request){
	        return "/login";
	    }
	
	//首页
	@RequestMapping("/index")
    public String index(HttpServletRequest request){
        return "/index";
    }
	
	@RequestMapping("/helpCenter")
	public String helpCenter(HttpServletRequest request){
	    return "/helpCenter";
	}
	
	@RequestMapping("/helpCenterInformation")
	public String helpCenterInformation(HttpServletRequest request, Model model, String questionOrderNumber){
		model.addAttribute("questionOrderNumber", questionOrderNumber);
		return "/helpCenterInformation";
	}
	
	@RequestMapping("/invoiceBig")
    public String invoiceBig(HttpServletRequest request){
        return "/invoiceBig";
    }	
	
	@RequestMapping("/invoiceDetail")
    public String invoiceDetail(HttpServletRequest request, Model model, String taskid, String invoiceid){
		model.addAttribute("taskid", taskid);
		model.addAttribute("invoiceid", invoiceid);
        return "/invoiceDetail";
    }	
	
	@RequestMapping("/mine")
	public String mine(HttpServletRequest request){
	    return "/mine";
	}	
	
	@RequestMapping("/mine1")
	public String mine1(HttpServletRequest request){
	    return "/mine1";
	}	
	
	@RequestMapping("/mine2")
	public String mine2(HttpServletRequest request){
		return "/mine2";
	}	
	
	@RequestMapping("/news")
	public String news(HttpServletRequest request){
		return "/news";
	}	
	
	@RequestMapping("/orderBig")
	public String orderBig(HttpServletRequest request){
		return "/orderBig";
	}	
	
	@RequestMapping("/policy")
	public String policy(HttpServletRequest request){
		return "/policy";
	}	
	
	@RequestMapping("/policyInformation")
	public String policyInformation(HttpServletRequest request, Model mode, String countryNumber){
		mode.addAttribute("countryNumber", countryNumber);
		return "/policyInformation";
	}	
	
	@RequestMapping("/tax")
	public String tax(HttpServletRequest request, Model model, String account){
		model.addAttribute("account", account);
		return "/tax";
	}	
	
	

}
