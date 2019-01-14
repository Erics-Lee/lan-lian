package com.unionblue.wechat.wechatService.test;

import com.unionblue.wechat.wechatService.message.resp.*;
import com.unionblue.wechat.wechatService.util.MessageUtil;

import java.util.ArrayList;
import java.util.List;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("=====测试文本消息======");
		System.out.println(testTextMessage());
		System.out.println();
		System.out.println("=====测试图片消息======");
		System.out.println(testImageMessage());
		System.out.println();
		System.out.println("=====音频视频消息======");
		System.out.println(testVoiceMessage());
		System.out.println();
		System.out.println("=====测试视频消息======");
		System.out.println(testVideoMessage());
		System.out.println();
		System.out.println("=====测试音乐消息======");
		System.out.println(testMusicMessage());
		System.out.println();
		System.out.println("=====测试图文消息======");
		System.out.println(testNewsMessage());
		System.out.println();
	}
	
	private static String testTextMessage(){
		TextMessage textM = new TextMessage();
		textM.setContent("测试");
		textM.setCreateTime(20140219);
		textM.setFromUserName("441122");
		textM.setToUserName("爱班级");
		
		return MessageUtil.messageToXML(textM);
		
	}
	
	private static String testNewsMessage(){
		NewsMessage news = new NewsMessage();
		List<Article> artList = new ArrayList<Article>();
		for (int i = 0; i<=2; i++){
			Article art = new Article();
			art.setDescription("这是测试"+i);
			art.setPicUrl("http://www.baid.com");
			art.setTitle("测试"+i);
			art.setUrl("http://www.163.com");
			artList.add(art);
		}
		
		news.setArticles(artList);
		news.setArticleCount(3);
		news.setCreateTime(20140219);
		news.setFromUserName("441122");
		news.setToUserName("爱班级");
		
		
		return MessageUtil.messageToXML(news);
	}
	
	private static String testMusicMessage(){
		
		Music m = new Music();
		m.setDescription("这是一个音乐文件");
		m.setHQMusicUrl("http://www.baid.com");
		m.setMusicURL("http://www.163.com");
		m.setThumbMediaId("20140219000000");
		m.setTitle("buddy");
		
		MusicMessage mm = new MusicMessage();
		mm.setCreateTime(20140219);
		mm.setFromUserName("441122");
		mm.setMusic(m);
		mm.setToUserName("爱班级");
		
		return MessageUtil.messageToXML(mm);
	}
	
	private static String testImageMessage(){
		ImageObj image = new ImageObj();
		image.setMediaId("20140219000000L");
		ImageMessage im = new ImageMessage();
		im.setCreateTime(20140219);
		im.setFromUserName("441122");
		im.setImage(image);
		im.setToUserName("爱班级");
		
		return MessageUtil.messageToXML(im);
	}
	
	private static String testVideoMessage(){
		VideoMessage video = new VideoMessage();
		VideoObj v = new VideoObj();
		v.setMediaId("20140219000000L");
		v.setDescription("视频消息");
		v.setTitle("视频消息");
		
		video.setCreateTime(20140219);
		video.setFromUserName("441122");
		video.setToUserName("爱班级");
		video.setVideo(v);
		
		return MessageUtil.messageToXML(video);
	}
	
	private static String testVoiceMessage(){
		VoiceObj vo = new VoiceObj();
		vo.setMediaId("20140219000000L");
		VoiceMessage voice = new VoiceMessage();
		voice.setCreateTime(20140219);
		voice.setFromUserName("441122");
		voice.setVoice(vo);
		voice.setToUserName("爱班级");
		
		return MessageUtil.messageToXML(voice);
	}

}
