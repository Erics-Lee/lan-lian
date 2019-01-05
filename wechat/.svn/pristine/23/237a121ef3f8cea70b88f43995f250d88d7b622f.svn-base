package com.unionblue.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;

@SpringBootApplication
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }
//    @Bean
//     FilterRegistrationBean testFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new WeixinUrlFilter());
//        registration.addUrlPatterns("/weixin/*"); //
//       // registration.addInitParameter("paramName", "paramValue"); //
//        registration.setName("testFilter");
//        registration.setOrder(1);
//        return registration;
//    }

//    @Bean
//    public FilterRegistrationBean authFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean(new AuthFilter());
//        registration.addUrlPatterns("/webapi/*"); //
//        registration.addInitParameter("paramName", "paramValue"); //
//        registration.setName("authFilter");
//        registration.setOrder(2);
//        return registration;
//    }

//    public static void main(String[] args) {
//        //1.创建账号get
////        String url = "https://tapi.jingtum.com/v2/wallet/new";
////        String result = get(url);
////        System.out.println("get请求结果：" + result);
//
//        //支付方钱包
//        String secret1 = "snRptmfzdwc5F2znfgPSmSMLRaMbD";
//        String address1 = "jJWwbTdMBRyE19Pub3AcmSUsEJJQuyKN2v";
//        //收款方钱包
//        String secret2 = "sn5iSHiF35sSFx5hfaKnCmo7tm2xU";
//        String address2 = "jHnPcFMymGshb2NmxAh7iHv7exSGqwn61S";
//        //韦总钱包
//        String address3 = "jKMhoD3bKuysr2oVsrM9WuoN4RmepDVG2H";
//
//        //2.激活钱包
////		String url = "https://tapi.jingtum.com/v2/accounts/" + address1 +"/payments";
////		JSONObject payment_item = new JSONObject();
////		//支付方的钱包私钥
////		payment_item.put("secret", secret1);
////		//交易单号
////		payment_item.put("client_id", UUID.randomUUID().toString());
////		//支付对象
////		JSONObject payment = new JSONObject();
////		//发起账号
////		payment.put("source", address1);
////		//目标账号
////		payment.put("destination", address2);
////		//支付金额对象
////		JSONObject destination_amount = new JSONObject();
////		destination_amount.put("value", "25");
////		destination_amount.put("currency", "SWT");
////		destination_amount.put("issuer", "");
////		payment.put("amount", destination_amount);
////		String[] memos = {"String", "账户激活"};
////		//支付备注
////		payment.put("memos", memos);
////		payment_item.put("payment", payment);
////		System.out.println("激活请求参数：" + payment_item.toJSONString());
////		String result = post(url, payment_item.toJSONString());
////		System.out.println("激活请求结果：" + result);
//
//        //3.业务数据上链
////        String url = "https://api.jingtum.com/v2/accounts/" + address1 +"/payments";
////		JSONObject payment_item = new JSONObject();
////		//支付方的钱包私钥
////		payment_item.put("secret", secret1);
////		//交易单号
////		payment_item.put("client_id", UUID.randomUUID().toString());
////		//支付对象
////		JSONObject payment = new JSONObject();
////		//发起账号
////		payment.put("source", address1);
////		//目标账号
////		payment.put("destination", address3);
////		//支付金额对象
////		JSONObject destination_amount = new JSONObject();
////		destination_amount.put("value", "100");
////		destination_amount.put("currency", "SWT");
////		destination_amount.put("issuer", "");
////		payment.put("amount", destination_amount);
////		String[] memos = {"String", "SWT"};
////		//支付备注
////		payment.put("memos", memos);
////		payment_item.put("payment", payment);
////		System.out.println("上链请求参数：" + payment_item.toJSONString());
////		String result = post(url, payment_item.toJSONString());
////		System.out.println("上链请求结果：" + result);
//
//        //4.查询上链数据
////      String hash = "D19EF585D49ACCA6AEE5E7C5CBBE0BFDC56904DD2129F8392CFDB22E5FC3558A";
////		String url = "https://tapi.jingtum.com/v2/accounts/" + address2 + "/transactions/" + hash;
////		String result = get(url);
////		System.out.println("上链数据请求结果：" + result);
//
//        //5,查询钱包余额
////        System.out.println("钱包地址：" + address3);
//        String url = "https://tapi.jingtum.com/v2/accounts/" + address1 + "/balances";
//        String result = get(url);
//        System.out.println("钱包余额：" + result);
//
//        //6,查询钱包历史
////        String url = "https://tapi.jingtum.com/v2/accounts/" + address1 + "/payments";
////        String result = get(url);
////        System.out.println("钱包历史：" + result);
//
//    }
//
//    public static String get(String location) {
//        HttpURLConnection conn = null;
//        InputStream in = null;
//        BufferedReader br = null;
//        try {
//            URL url = new URL(location);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("contentType", "UTF-8");
//            in = conn.getInputStream();
//            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
//            String lines;
//            StringBuffer sb = new StringBuffer();
//            while ((lines = br.readLine()) != null) {
//                sb.append(lines);
//            }
//            return sb.toString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//            if (in != null) {
//                try {
//                    in.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static String post(String location, String data) {
//        HttpURLConnection conn = null;
//        PrintWriter out = null;
//        BufferedReader br = null;
//        try {
//            URL url = new URL(location);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setRequestMethod("POST");
//            conn.setUseCaches(false);
//            conn.setInstanceFollowRedirects(true);
//            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            conn.connect();
//            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
//            out.write(data);
//            out.flush();
//            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            String lines;
//            StringBuffer sb = new StringBuffer();
//            while ((lines = br.readLine()) != null) {
//                sb.append(lines);
//            }
//            return sb.toString();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            if (conn != null) {
//                conn.disconnect();
//            }
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
