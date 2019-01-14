//package com.unionblue.wechat.wechatService.service;
//
//import com.infosky.wep.core.constant.SystemConfig;
//import com.infosky.wep.entity.weixin.WeiXinKeyword;
//import com.infosky.wep.entity.weixin.WeiXinMessage;
//import com.infosky.wep.wechatService.message.resp.Article;
//import com.unionblue.wechat.wechatService.common.Constant;
//import com.unionblue.wechat.wechatService.message.resp.CustomerMessage;
//import com.unionblue.wechat.wechatService.message.resp.NewsMessage;
//import com.unionblue.wechat.wechatService.message.resp.TextMessage;
//import com.unionblue.wechat.wechatService.util.MessageUtil;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * 响应服务类
// * 处理对应的响应消息为微信服务器可识别的格式
// *
// * @author 003598
// *
// */
//public class RespService {
//
//	/**
//	 * 设置客服文本信息
//	 * @param createTime
//	 * @param toUserName
//	 * @param fromUserName
//	 *
//	 * @return String(Xml)
//	 */
//	public static String setCustomerMessage(int createTime, String toUserName, String fromUserName){
//		CustomerMessage customerMessage = new CustomerMessage();
//		customerMessage.setCreateTime(createTime);
//		customerMessage.setFromUserName(toUserName);
//		customerMessage.setToUserName(fromUserName);
//		customerMessage.setMsgType(Constant.MsgType.TRANSFER_CUSTOMER_SERVICE);
//		return MessageUtil.messageToXML(customerMessage);
//	}
//	/**
//	 * 设置响应文本信息
//	 * @param createTime
//	 * @param toUserName
//	 * @param fromUserName
//	 * @param content
//	 *
//	 * @return String(Xml)
//	 */
//	public static String setTextMessage(int createTime, String toUserName, String fromUserName, String content){
//		TextMessage textM = new TextMessage();
//		textM.setCreateTime(createTime);
//		textM.setFromUserName(toUserName);
//		textM.setToUserName(fromUserName);
//		textM.setContent(content);
//		textM.setMsgType(Constant.MsgType.TEXT);
//		return MessageUtil.messageToXML(textM);
//	}
//
//	/**
//	 * 设置响应图文信息
//	 *
//	 * @param createTime
//	 * @param toUserName
//	 * @param fromUserName
//	 *
//	 * @return String(Xml)
//	 */
//	public static String setNewsMessage(int createTime, String appId, String openId, WeiXinKeyword weixinKeyword){
//		NewsMessage news = new NewsMessage();
//		news.setCreateTime(createTime);
//		news.setFromUserName(appId);
//		news.setToUserName(openId);
//		news.setMsgType(Constant.MsgType.NEWS);
//
//		int msgCount = weixinKeyword.getMsgcount();
//		List<WeiXinMessage> messageList = weixinKeyword.getMessageList();
//		if (messageList.size() <= 0){
//			return "";
//		}
//		List<Article> artList = new ArrayList<Article>();
//		String imagePath = SystemConfig.readValue("imagePath");
//		for(int i = 0; i<msgCount; i++){
//			String picurl = messageList.get(i).getPicurl();
//			Article art = new Article();
//			art.setDescription(messageList.get(i).getDescription());
//			art.setPicUrl(imagePath + picurl);
//			art.setTitle(messageList.get(i).getTitle());
//			String linkurl = messageList.get(i).getLinkurl() ;
//			if(linkurl.indexOf("?")<0){
//				linkurl += "?1=1";
//			}
//			linkurl += "&need_oauth2_url=true";
//			art.setUrl(linkurl);
//			artList.add(art);
//		}
//
//		news.setArticleCount(artList.size());
//		news.setArticles(artList);
//
//		return MessageUtil.messageToXML(news);
//	}
//
//	public static boolean hasImage(String filePath){
//
//		if(new File(filePath).exists()){
//			return true;
//		}
//		return false;
//	}
//}
