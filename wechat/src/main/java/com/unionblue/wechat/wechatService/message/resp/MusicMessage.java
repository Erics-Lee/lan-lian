package com.unionblue.wechat.wechatService.message.resp;

import com.unionblue.wechat.wechatService.common.Constant;

/**
 * 音乐消息内容
 * @author 003598
 *
 */
public class MusicMessage extends BaseMessage{
	
	private Music Music;
	
	public MusicMessage(){
		super.setMsgType(Constant.MsgType.MUSIC);
	}

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		this.Music = music;
	}

}
