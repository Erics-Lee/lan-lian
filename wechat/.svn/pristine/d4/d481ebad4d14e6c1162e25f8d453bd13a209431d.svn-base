//package com.unionblue.wechat.wechatService.util;
//
//import java.io.UnsupportedEncodingException;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Arrays;
//import java.util.Formatter;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.infosky.wep.entity.weixin.WeiXinPublicAccount;
//import com.infosky.wep.service.weixin.WeiXinUtilService;
//
///**
// * 请求校验工具类
// * @author 003598
// *
// */
//@Service
//public class SignUtil {
//
//	@Resource
//	WeiXinUtilService service;
//
//	// 测试用的token,目前只用于验证微信是否能够联通
//	// 后期token由后台系统自动生成，保存在数据库中，调用数据库获取。
//	//private static final String token = "testWangl003598";
//
//	/**
//	 * 校验签名
//	 *
//	 * @param signature 微信加密签名
//	 * @param timestamp 时间戳
//	 * @param nonce     随机数
//	 *
//	 * @return boolean
//	 */
//	public boolean checkSignature(String signature, String timestamp, String nonce, int uid){
//		// 根据uid获取token
//		WeiXinPublicAccount account = service.getWeiXinPublicAccount(uid);
//		String token = "";
//		if (null != account){
//			token = account.getToken();
//		}
//
//		String[] arr = new String[] {token, timestamp, nonce};
//		// 排序将token、timestamp、nonce三个参数进行字典序排序
//		Arrays.sort(arr);
//
//		StringBuilder contents = new StringBuilder();
//		for (int i = 0; i < arr.length; i++){
//			contents.append(arr[i]);
//		}
//
//		MessageDigest md = null;
//		String tempStr = null;
//
//		try {
//			md = MessageDigest.getInstance("SHA-1");
//
//			byte[] digest = md.digest(contents.toString().getBytes());
//			tempStr = byteToStr(digest);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//
//
//		return tempStr != null ? tempStr.equals(signature.toUpperCase()) : false;
//	}
//
//
//	/**
//	 * 将字节数组转换为十六进制字符
//	 *
//	 * @param byteArray
//	 * @return
//	 */
//	public static String byteToStr(byte[] byteArray) {
//
//		String strDigest = "";
//
//		for (int i = 0; i<byteArray.length; i++){
//			strDigest += byteToHexStr(byteArray[i]);
//		}
//
//		return strDigest;
//	}
//
//
//	/**
//	 * 将字符转换为十六进制字符串
//	 *
//	 * @param mByte
//	 * @return
//	 */
//	public static String byteToHexStr(byte mByte) {
//		char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//
//		char[] tempStr = new char[2];
//
//		tempStr[0] = Digit[(mByte >>> 4) & 0X0F];
//		tempStr[1] = Digit[mByte & 0X0F];
//
//		String s = new String(tempStr);
//
//		return s;
//	}
//	public static Map<String, String> jsapiSign(String jsapi_ticket, String url) {
//		Map<String, String> ret = new HashMap<String, String>();
//		String nonce_str = create_nonce_str();
//		String timestamp = create_timestamp();
//		String string1;
//		String signature = "";
//
//		//注意这里参数名必须全部小写，且必须有序
//		string1 = "jsapi_ticket=" + jsapi_ticket +
//				"&noncestr=" + nonce_str +
//				"&timestamp=" + timestamp +
//				"&url=" + url;
//
//		try
//		{
//			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//			crypt.reset();
//			crypt.update(string1.getBytes("UTF-8"));
//			signature = byteToHex(crypt.digest());//digest() 生成散列码
//		}
//		catch (NoSuchAlgorithmException e)
//		{
//			e.printStackTrace();
//		}
//		catch (UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//		}
//
//		ret.put("url", url);
//		ret.put("jsapi_ticket", jsapi_ticket);
//		ret.put("nonce_str", nonce_str);
//		ret.put("timestamp", timestamp);
//		ret.put("signature", signature);
//
//		return ret;
//	}
//
//	/**
//	 * 字节数组转换为 十六进制
//	 * @param hash 散列码
//	 * @return
//	 */
//	private static String byteToHex(final byte[] hash) {
//		Formatter formatter = new Formatter();
//		for (byte b : hash)
//		{
//			formatter.format("%02x", b);//%02X：以十六进制输出,2为指定的输出字段的宽度.如果位数小于2,则左端补0
//		}
//		String result = formatter.toString();
//		formatter.close();
//		return result;
//	}
//
//
//
//	private static String create_nonce_str() {
//		return UUID.randomUUID().toString();
//	}
//
//	private static String create_timestamp() {
//		return Long.toString(System.currentTimeMillis() / 1000);
//	}
//}
