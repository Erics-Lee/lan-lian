package com.unionblue.wechat.wechatService;

/*
 * 微信公众平台(JAVA) SDK
 *
 * Copyright (c) 2014, Ansitech Network Technology Co.,Ltd All rights reserved.
 *
 * http://www.weixin4j.org/sdk/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.weixin4j.Configuration;
import org.weixin4j.message.Articles;
import org.weixin4j.message.OutputMessage;
import org.weixin4j.message.TextOutputMessage;
import org.weixin4j.spi.IMessageHandler;
import org.weixin4j.spi.InputMessage;
import org.weixin4j.util.TokenUtil;
import org.weixin4j.util.XStreamFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
/**
 * <p>Title: 微信公众平台接受消息拦截器</p>
 *
 * <p>Description: 拦截Url http://www.weixin4j.org/api/vzhanqun</p>
 *
 * @author weixin4j<weixin4j@ansitech.com>
 */
public class WeixinUrlFilter implements Filter {
    protected static final Logger logger = LoggerFactory.getLogger(WeixinUrlFilter.class);

    private String _token;
    private static IMessageHandler messageHandler = null;
    private static String defaultHandler = "org.weixin4j.spi.DefaultMessageHandler";

    @Override
    public void init(FilterConfig config) throws ServletException {
        if (Configuration.isDebug()) {
            logger.info("WeixinUrlFilter拦截器已启动");
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        //微信服务器将发送GET请求到填写的URL上,这里需要判定是否为GET请求
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        if (Configuration.isDebug()) {
            System.out.println("获得微信请求:" + request.getMethod() + " 方式");
            System.out.println("微信请求URL:" + request.getServletPath());
        }
        if (isGet) {
            String path = request.getServletPath();
            //1.验证消息真实性
            //http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性
            //URL为http://www.weixin4j.org/api/公众号
            //Token为weixin4j.properties中配置的Token
            //获取微信公众号
            String gongzonghao = path.substring(path.lastIndexOf("/"));
            //如果获取不到公众号，则向服务器发生错误信息
            if (gongzonghao == null) {
                response.getWriter().write("error");
            } else {
                //根据公众号，算出对应的Token,然后进行验证
                gongzonghao = gongzonghao.substring(1);
                //算出公众号的Token
                _token = TokenUtil.get(gongzonghao);
                if (Configuration.isDebug()) {
                    System.out.println("取出公众号加密后的Token:" + _token);
                }
                //token为空，则请求地址错误
//                if (!_token.equals("")) {
                    //成为开发者验证
                    doGet(request, response);
              //  }
            }
        } else {
            //用户每次向公众号发送消息、或者产生自定义菜单点击事件时，响应URL将得到推送
            doPost(request, response);
        }
    }

    //开发者提交信息后，微信服务器将发送GET请求到填写的URL上
    private void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
            System.out.println(request.getParameterMap());
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String echostr = request.getParameter("echostr");//
        //确认此次GET请求来自微信服务器，原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败
        if (TokenUtil.checkSignature(_token, signature, timestamp, nonce)) {
            response.getWriter().write(echostr);
        }
    }

    //当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上
    //用户在关注与取消关注公众号时，微信会把这个事件推送到开发者填写的URL
    //用户每次发送语音给公众号时，微信会在推送的语音消息XML数据包中，增加一个Recongnition字段
    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        InputMessage inputMsg = null;
        OutputMessage outputMsg = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");
            if (messageHandler == null) {
                try {
                    //获取
                    defaultHandler = Configuration.getProperty("weixin4j.handler", defaultHandler);
                    if (Configuration.isDebug()) {
                        System.out.println("微信接受消息处理Hanle:" + defaultHandler);
                    }
                    // 加载处理器
                    Class<?> clazz = Class.forName(defaultHandler);
                    try {
                        messageHandler = (IMessageHandler) clazz.newInstance();
                    } catch (Exception ex) {
                        System.out.println("初始化 MessageHandler 异常：");
                        ex.printStackTrace();
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("找不到: " + defaultHandler + " 类!");
                    ex.printStackTrace();
                    throw ex;
                }
            }
            if (Configuration.isDebug()) {
                System.out.println("IMessageHandler创建成功!");
            }
            //获取POST流
            ServletInputStream in = request.getInputStream();
            if (Configuration.isDebug()) {
                System.out.println("获取POST流成功!");
            }
            //将POST流转换为XStream对象
            XStream xs = XStreamFactory.init(false);
            if (Configuration.isDebug()) {
                System.out.println("将POST流转换为XStream对象成功!");
            }
            //将指定节点下的xml节点数据转换为对象
            xs.alias("xml", InputMessage.class);
            if (Configuration.isDebug()) {
                System.out.println("将指定节点下的xml节点数据转换为对象成功!");
            }
            //将流转换为字符串
            String xmlMsg = XStreamFactory.inputStream2String(in);
            if (Configuration.isDebug()) {
                System.out.println("获取POST的消息:[" + xmlMsg + "]");
            }
            //将xml内容转换为InputMessage对象
            inputMsg = (InputMessage) xs.fromXML(xmlMsg);

            // 取得消息类型
            String MsgType = inputMsg.getMsgType();
            if (Configuration.isDebug()) {
                System.out.println("POST的消息类型:[" + MsgType + "]");
            }
            //获取消息处理方法
            Method mt = messageHandler.getClass().getMethod(MsgType + "TypeMsg", InputMessage.class);
            //调用处理方法，返回输出流对象
            outputMsg = (OutputMessage) mt.invoke(messageHandler, inputMsg);
            //如果输出流为null，则说明内部异常
            if (outputMsg == null) {
                //告知用户错误信息
                outputMsg = new TextOutputMessage();
                ((TextOutputMessage) outputMsg).setContent("系统错误，请联系管理员!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outputMsg = new TextOutputMessage();
            ((TextOutputMessage) outputMsg).setContent("系统错误!");
        }
        try {
            setOutputMsgInfo(outputMsg, inputMsg);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        // 把发送发送对象转换为xml输出
        XStream xs = XStreamFactory.init(true);
        xs.alias("xml", outputMsg.getClass());
        xs.alias("item", Articles.class);
        String xml = xs.toXML(outputMsg);
        if (Configuration.isDebug()) {
            System.out.println("POST输出消息:[" + xml + "]");
        }
        response.getWriter().write(xml);
    }

    //设置详细信息
    private static void setOutputMsgInfo(OutputMessage oms, InputMessage msg)
            throws Exception {
        // 设置发送信息
        Class<?> outMsg = oms.getClass().getSuperclass();
        Field CreateTime = outMsg.getDeclaredField("CreateTime");
        Field ToUserName = outMsg.getDeclaredField("ToUserName");
        Field FromUserName = outMsg.getDeclaredField("FromUserName");

        ToUserName.setAccessible(true);
        CreateTime.setAccessible(true);
        FromUserName.setAccessible(true);

        CreateTime.set(oms, new Date().getTime());
        ToUserName.set(oms, msg.getFromUserName());
        FromUserName.set(oms, msg.getToUserName());
    }

    @Override
    public void destroy() {
    }
}

