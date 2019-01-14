package com.unionblue.wechat.wechatService.message.resp;

import com.unionblue.wechat.wechatService.common.Constant;

import java.util.List;

/**
 * 图文消息内容
 * @author 003598
 *
 */
public class NewsMessage extends BaseMessage{
	
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	
	// 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
	private List<Article> Articles;

	public NewsMessage(){
		super.setMsgType(Constant.MsgType.NEWS);
	}
	
	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		this.ArticleCount = articleCount;
	}

	public List<Article> getArticles() {
		return Articles;
	}

	public void setArticles(List<Article> articles) {
		this.Articles = articles;
	}

}
