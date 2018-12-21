package com.unionblue.wechat.wechatService.message.resp;

import com.unionblue.wechat.wechatService.common.Constant;

/**
 * 视频消息内容
 * @author 003598
 *
 */
public class VideoMessage extends BaseMessage {
	
	private VideoObj Video;

	public VideoMessage(){
		super.setMsgType(Constant.MsgType.VIDEO);
	}
	
	public VideoObj getVideo() {
		return Video;
	}

	public void setVideo(VideoObj video) {
		Video = video;
	}
	

	

}
