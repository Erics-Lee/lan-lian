package com.unionblue.wechat.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Chat {
	
	public static void main(String[] args) throws Exception{
		//System.out.println(getAnswer("你好"));
		String societyIDToken = "otQ261cenuncZj95ajhU6NOJHicw";
		System.out.println(societyIDToken.substring(societyIDToken.length()-8));
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
}
