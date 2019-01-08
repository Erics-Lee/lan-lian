package com.unionblue.wechat.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unionblue.wechat.util.StringUtil;

@Controller
/*@RequestMapping(value = "/login")*/
public class BankLoginController {

	private static final Logger LOG = LoggerFactory.getLogger(BankLoginController.class);
	
	/*民生银行*/
	@RequestMapping(value = "/CMBCLogin", method = RequestMethod.GET)
	public String CMBCLogin(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model, String openId, String nickname,
			String headimgurl) throws Exception{
		if (StringUtil.isEmpty(openId)) {
			LOG.info("用户微信账号不能为空！！！");
			response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("用户微信账号不能为空！！！");
            return null;
		}
		if (StringUtil.isEmpty(nickname)) {
			LOG.info("用户昵称不能为空！！！");
			response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("用户昵称不能为空！！！");
            return null;
		}
		if (StringUtil.isEmpty(headimgurl)) {
			LOG.info("用户头像不能为空！！！");
			response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("用户头像不能为空！！！");
            return null;
		}
		
		request.getSession().setAttribute("bankInfo", "CMBC");
		request.getSession().setAttribute("headImgUrl", headimgurl);
		request.getSession().setAttribute("nickName", nickname);
		request.getSession().setAttribute("openId", openId);
		request.getSession().setAttribute("parentAppKey", "parentAppKey");
		model.addAttribute("time", System.currentTimeMillis());
		return "/index";
	}
	
	/*招商银行*/
	@RequestMapping(value = "/CMBLogin", method = RequestMethod.GET)
	public String CMBLogin(HttpSession session, HttpServletResponse response, HttpServletRequest request, Model model, String openId, String nickname,
			String headimgurl) throws Exception{
		if (StringUtil.isEmpty(openId)) {
			LOG.info("用户微信账号不能为空！！！");
			response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("用户微信账号不能为空！！！");
            return null;
		}
		if (StringUtil.isEmpty(nickname)) {
			LOG.info("用户昵称不能为空！！！");
			response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("用户昵称不能为空！！！");
            return null;
		}
		if (StringUtil.isEmpty(headimgurl)) {
			LOG.info("用户头像不能为空！！！");
			response.setContentType("application/json;charset=utf-8");
            response.getWriter().write("用户头像不能为空！！！");
            return null;
		}
		
		request.getSession().setAttribute("bankInfo", "CMB");
		request.getSession().setAttribute("headImgUrl", headimgurl);
		request.getSession().setAttribute("nickName", nickname);
		request.getSession().setAttribute("openId", openId);
		request.getSession().setAttribute("parentAppKey", "parentAppKey");
		model.addAttribute("time", System.currentTimeMillis());
		return "/index";
	}
}
