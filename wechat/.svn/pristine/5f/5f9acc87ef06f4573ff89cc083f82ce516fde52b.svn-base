package com.unionblue.wechat.wechatService.message.resp;

import com.unionblue.wechat.wechatService.common.Constant;

/**
 * 文本消息内容
 * @author 003598
 *
 */
public class TextMessage extends BaseMessage {
	
	// 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	private String Content;
	
	public TextMessage(){
		super.setMsgType(Constant.MsgType.TEXT);
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		this.Content = content;
	}

}
