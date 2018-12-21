package com.unionblue.wechat.wechatService.message.req;

/**
 * 链接消息内容
 * @author 003598
 *
 */
public class LinkMessage extends ReqBaseMessage {
	
	// 消息标题
	private String Title;
	
	// 消息描述
	private String Description;
	
	// 消息链接
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		this.Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		this.Url = url;
	}

}
