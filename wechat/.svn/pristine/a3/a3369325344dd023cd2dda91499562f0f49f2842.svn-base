package com.unionblue.wechat.wechatService.message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.weixin4j.message.OutputMessage;
import org.weixin4j.message.TextOutputMessage;


public class DefaultMessageHandlerImpl implements IMessageHandler {

    private OutputMessage allType(InputMessage msg) {
        TextOutputMessage out = new TextOutputMessage();
        System.out.println(msg.getContent()+"消息内容");
        try {
        	System.out.println(getAnswer(msg.getContent()));
        	out.setContent(getAnswer(msg.getContent()));			
        	return out;
		} catch (Exception e) {
            return null;			
		}
    }
    
    public static String getAnswer(String quesiton) throws Exception{
		String APIKEY="f0feee3416c846a6be5fdc523b372c20";
        String INFO=URLEncoder.encode(quesiton, "utf-8");
        String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
        connection.connect();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String line="";
        while ((line = reader.readLine()) != null) 
            sb.append(line);
            
        reader.close();
        connection.disconnect();
        
        String[] ss = new String[10];
        String s = sb.toString();
        String answer;
        ss = s.split(":");
        answer = ss[ss.length-1];
        answer = answer.substring(1,answer.length()-2);
        return answer;
	}

    @Override
    public OutputMessage textTypeMsg(InputMessage inputMessage) {
        return allType(inputMessage);
    }

    @Override
    public OutputMessage imageTypeMsg(InputMessage inputMessage) {
        return allType(inputMessage);
    }

    @Override
    public OutputMessage linkTypeMsg(InputMessage inputMessage) {
        return allType(inputMessage);
    }

    @Override
    public OutputMessage voiceTypeMsg(InputMessage inputMessage) {
        return allType(inputMessage);
    }

    @Override
    public OutputMessage locationTypeMsg(InputMessage inputMessage) {
        return allType(inputMessage);
    }

    @Override
    public OutputMessage eventTypeMsg(InputMessage inputMessage) {



        



        return allType(inputMessage);
    }
}
