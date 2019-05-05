package com.unionblue.wechat.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.unionblue.wechat.util.HttpClinetUtil;
import com.unionblue.wechat.util.JsonUtil;
import com.unionblue.wechat.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.WeixinUrlFilter;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 18501 on 2019/4/12.
 */

@Controller
@RequestMapping(value = "/img")
public class ImgController {

    @Autowired
    protected static final Logger logger = LoggerFactory.getLogger(WeixinUrlFilter.class);

    /**
     * 图片url转base64
     * @param request
     * @param response
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "UrltoBase64")
    @ResponseBody
    public String UrltoBase64(HttpServletRequest request, HttpServletResponse response, String url) throws ParseException {
        try {
            String base64;
//            if(StringUtil.isEmpty(url)){
//                Map map  = new HashMap();
//                map.put("companyno",companyno);
//                map.put("IsBase64Code","0");
//                String sessionKey = HttpClinetUtil.getSessionKey(request);
//                String Access_token = HttpClinetUtil.doGet("https://api.taxchain.one/eTaxAPIs100/SingleCompanyQuery",map,sessionKey);
//                JSONObject json = JSONObject.parseObject(Access_token);
//                String ReturnCode = (String) json.get("ReturnCode");
//                if (!ReturnCode.equals("0000")) {
//                    return JsonUtil.error(json.get("ReturnMessage"));
//                } else {
//                    String ReturnStr = (String) json.get("ReturnJson");
//                    JSONObject ReturnJson = JSONObject.parseObject(ReturnStr);
//                    String registrationimagename = String.valueOf(ReturnJson.get("registrationimagename"));
//                    base64 = "data:image/jpeg;base64," + image2Base64(registrationimagename);
//                }
//            }else{
                base64 = "data:image/jpeg;base64," + image2Base64(url);
//            }
            return base64;
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtil.error("转换失败");
        }
    }

    public String imageBase64Test() {
        String imgUrl = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";
        String base64 = "data:image/jpeg;base64," + image2Base64(imgUrl);

//        byte[] decode = decode(base64);
//        System.out.println(base64);
//        System.out.println("===================");
//        System.out.println(decode.length);
//        System.out.println(decodeBuffer(base64));
        return base64;
    }

    private static String image2Base64(String imgUrl) {
        URL url;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            url = new URL(imgUrl);
            urlConnection = ( HttpURLConnection ) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();

            baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inputStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                baos.write(buffer, 0, len);
            }
            // 对字节数组Base64编码
            return encode(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return imgUrl;
    }

    private static String encode(byte[] image){
        BASE64Encoder decoder = new BASE64Encoder();
        return replaceEnter(decoder.encode(image));
    }

    private static String replaceEnter(String str){
        String reg ="[\n-\r]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    /**
     * 字符串转图片
     * @param base64Str
     * @return
     */
    private static byte[] decode(String base64Str){
        byte[] b = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            b = decoder.decodeBuffer(replaceEnter(base64Str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    private static ByteBuffer decodeBuffer(String base64Str) {
        ByteBuffer buffer = null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            buffer = decoder.decodeBufferToByteBuffer(base64Str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

}
