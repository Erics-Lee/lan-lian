package com.unionblue.wechat.wechatService.message.req;

/**
 * 文本消息内容
 * @author 003598
 *
 */
public class TextMessage extends ReqBaseMessage {
	
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

}
