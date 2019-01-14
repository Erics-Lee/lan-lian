package com.unionblue.wechat.wechatService.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.unionblue.wechat.wechatService.message.resp.Article;
import com.unionblue.wechat.wechatService.message.resp.NewsMessage;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {
	
	/**
	 * 解析微信发来的请求
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		
		// request中取得输入流
		InputStream inputStream = request.getInputStream();
		
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);  
		
		// 获取xml的根元素
		Element root = document.getRootElement();
		
		// 遍历子节点
		List<Element> elementList = root.elements();
		
		for (Element e : elementList) {
			
			map.put(e.getName(), e.getText());
			
		}
		
		inputStream.close();
		inputStream = null;
		
		return map;
	}
	
	/**
	 * 将消息转换成xml
	 * @param obj 消息对象
	 * @return
	 */
	public static String messageToXML(Object obj){
		
		xstream.alias("xml", obj.getClass());
		// 消息类型为图文消息，则设置子节点，放置多个图文消息
		if (obj instanceof NewsMessage){
			xstream.alias("item", new Article().getClass());
		}
		return xstream.toXML(obj);
	}
	
    /**
     * 扩展xstream，使其支持CDATA块 
     */
	private static XStream xstream = new XStream(new XppDriver() {  
	    @Override
		public HierarchicalStreamWriter createWriter(Writer out) {  
	        return new PrettyPrintWriter(out) {  
	            // 对所有xml节点的转换都增加CDATA标记   
	            boolean cdata = true;  
	  
	            
				@Override
				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {  
	                super.startNode(name, clazz);  
	            }  
	  
	            @Override
				protected void writeText(QuickWriter writer, String text) {  
	                if (cdata) {  
	                    writer.write("<![CDATA[");  
	                    writer.write(text);  
	                    writer.write("]]>");  
	                } else {  
	                    writer.write(text);  
	                }  
	            }  
	        };  
	    }  
	}); 
	
}
