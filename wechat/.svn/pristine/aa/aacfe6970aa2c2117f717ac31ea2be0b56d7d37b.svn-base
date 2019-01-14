//package com.unionblue.wechat.wechatService.service;
//
//import java.io.BufferedInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//
///**
// * 微信交互工具
// * @author 910298
// *
// */
//public class WeiXinUtil
//{
//    /**
//     * 获取微信端 access_token
//     * @param appid 第三方用户唯一凭证
//     * @param appsecret 第三方用户唯一凭证密钥
//     * @return
//     */
//    public static Map<String, Object> getAccessToken(String appid, String appsecret)
//    {
//        Map<String, Object> map = new HashMap<String, Object>();
//        String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
//        HttpResponse httpResponse;
//        HttpPost post = new HttpPost(uri);
//        try
//        {
//            httpResponse = new DefaultHttpClient().execute(post);
//            String json = EntityUtils.toString(httpResponse.getEntity());
//            System.out.println(json);
//            if (json.indexOf("access_token") < 0)
//            {
//                map.put("code", Integer.parseInt(JSONObject.fromObject(json).getString("errcode")));
//                map.put("msg", JSONObject.fromObject(json).getString("errmsg"));
//            }
//            else
//            {
//                map.put("code", "1000");
//                map.put("msg", JSONObject.fromObject(json).getString("access_token"));
//                map.put("expires_in", JSONObject.fromObject(json).getString("expires_in"));
//            }
//        }
//        catch (ClientProtocolException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        // post.
//        return map;
//    }
//
//
//    /**
//     * 获取微信端 公众号调用微信JS接口的临时票据
//     * @param access_token
//     * @param type 值为jsapi
//     * @return {"errcode":0,"errmsg":"ok","ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA","expires_in":7200}
//     */
//    public static Map<String, Object> getTicket(String access_token, String type)
//    {
//        Map<String, Object> map = new HashMap<String, Object>();
//        String uri = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=" + type;
//        HttpResponse httpResponse;
//        HttpPost post = new HttpPost(uri);
//        try
//        {
//            httpResponse = new DefaultHttpClient().execute(post);
//            String json = EntityUtils.toString(httpResponse.getEntity());
//            if (json.indexOf("ticket") < 0)
//            {
//                map.put("code", Integer.parseInt(JSONObject.fromObject(json).getString("errcode")));
//                map.put("msg", JSONObject.fromObject(json).getString("errmsg"));
//            }
//            else
//            {
//                map.put("code", "1000");
//                map.put("msg", JSONObject.fromObject(json).getString("ticket"));
//                map.put("expires_in", JSONObject.fromObject(json).getString("expires_in"));
//            }
//        }
//        catch (ClientProtocolException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        // post.
//        return map;
//    }
//
//
//    /**
//     * 创建二维码ticket
//     * @param access_token
//     * @param channelid 渠道商编码
//     * @return {"ticket":"gQH47joAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2taZ2Z3TVRtNzJXV1Brb3ZhYmJJAAIEZ23sUwMEmm3sUw==","expire_seconds":60,"url":"http:\/\/weixin.qq.com\/q\/kZgfwMTm72WWPkovabbI"}
//     * 错误返回  {"errcode":40013,"errmsg":"invalid appid"}
//     */
//    public static String getTwoDimensionalCodeTicket(String access_token, String channelid) throws Exception
//    {
//
//        try
//        {
//
//            String uri = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + access_token;
//
//            System.out.println(uri);
//            JSONObject jsonData = new JSONObject();
//            JSONObject sceneData = new JSONObject();
//            JSONObject scene_str = new JSONObject();
//            scene_str.put("scene_str", channelid);
//            sceneData.put("scene", scene_str);
//            jsonData.put("action_name", "QR_LIMIT_STR_SCENE");
//            jsonData.put("action_info", sceneData);
//
//            HttpResponse httpResponse;
//            HttpPost post = new HttpPost(uri);
//            //String encodeJson = URLEncoder.encode(jsonData.toString(), "utf-8");
//            System.out.println(jsonData.toString());
//            StringEntity se = new StringEntity(jsonData.toString());
//            post.setEntity(se);
//            httpResponse = new DefaultHttpClient().execute(post);
//            String json = EntityUtils.toString(httpResponse.getEntity());
//            System.out.println("getTwoDimensionalCodeTicket:" + json);
//            return json;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 通过ticket换取二维码
//     * @param ticket
//     * @return ticket正确情况下，http 返回码是200，是一张图片，可以直接展示或者下载。
//     */
//    public static BufferedInputStream getTwoDimensionalCodePic(String ticket)
//    {
//        try
//        {
//            if (StringUtils.isNotEmpty(ticket))
//            {
//                ticket = URLEncoder.encode(ticket, "utf-8");
//            }
//
//            String uri = "http://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket;
//            URL url = new URL(uri);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setDoOutput(true);
//            connection.setDoInput(true);
//            connection.setRequestMethod("GET");
//            connection.setUseCaches(false);
//            connection.setInstanceFollowRedirects(true);
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.connect();
//
//            return new BufferedInputStream(connection.getInputStream());
//        }
//        catch (UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//        catch (MalformedURLException e)
//        {
//            e.printStackTrace();
//        }
//        catch (ProtocolException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}
