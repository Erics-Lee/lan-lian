//package com.unionblue.wechat.wechatService.servlet;
//
//import com.unionblue.wechat.wechatService.service.WechatService;
//import com.unionblue.wechat.wechatService.util.SignUtil;
//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//
//public class WechatServlet extends HttpServlet{
//
//	/*@Resource
//	private SignUtil signUtil;*/
//
//	private static final long serialVersionUID = 2014021800214L;
//
//	/**
//	 * 确认请求来自微信服务器
//	 */
//	@Override
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//
//		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
//		SignUtil signUtil = ctx.getBean(SignUtil.class);
//
//		// 微信加密签名
//		String signature = request.getParameter("signature");
//
//		// 时间戳
//		String timestamp = request.getParameter("timestamp");
//
//		// 随机数
//		String nonce = request.getParameter("nonce");
//
//		// 随机字符串
//		String echostr = request.getParameter("echostr");
//
//		// 获取用户ID
//		int uid = Integer.parseInt(request.getParameter("uid"));
//
//		System.out.println(signature + " " + timestamp +" " + nonce + " " + echostr);
//
//		PrintWriter out = response.getWriter();
//
//		// 校验签名
//		if (signUtil.checkSignature(signature, timestamp, nonce, uid)){
//			out.print(echostr);
//		}
//		out.close();
//		out = null;
//	}
//
//	/**
//	 * 处理微信服务器发来的消息
//	 */
//	@Override
//	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		System.out.println("doPost");
//		// 将请求，响应的编码设置为UTF-8(防止乱码)
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//
//		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
//		WechatService wechatService = ctx.getBean(WechatService.class);
//
//		String respMessage = wechatService.processRequest(request);//
//
//		// 响应消息
//		PrintWriter out = response.getWriter();
//		out.print(respMessage);
//		out.close();
//
//	}
//
//}
