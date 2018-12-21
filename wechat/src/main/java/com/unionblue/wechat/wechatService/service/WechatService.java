//package com.unionblue.wechat.wechatService.service;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
//import net.sf.json.JSONObject;
//
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.springframework.stereotype.Service;
//
//import com.infosky.wep.core.constant.SystemConfig;
//import com.infosky.wep.entity.channel.ChannelAndUser;
//import com.infosky.wep.entity.customer.Customer;
//import com.infosky.wep.entity.weixin.WeiXinKeyword;
//import com.infosky.wep.entity.weixin.WeiXinPublicAccount;
//import com.infosky.wep.service.channel.ChannelAndUserService;
//import com.infosky.wep.service.common.RelCodeService;
//import com.infosky.wep.service.customer.CustomerService;
//import com.infosky.wep.service.weixin.WeiXinUtilService;
//import com.infosky.wep.util.TimeUtil;
//import com.infosky.wep.util.weixin.WeiXinYongHuGuanLi;
//import com.unionblue.wechat.wechatService.common.Constant;
//import com.unionblue.wechat.wechatService.util.MessageUtil;
//
///**
// * 核心服务
// *
// * @author n910298
// *
// */
//@Service
//public class WechatService
//{
//
//    protected final static Log logger = LogFactory.getLog(WechatService.class);
//    @Resource
//    WeiXinUtilService service;
//
//    @Resource
//    CustomerService customerService;
//
//    @Resource
//    ChannelAndUserService channelAndUserService;
//
//    @Resource
//    private RelCodeService relCodeService;
//
//    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//
//    /**
//     * 处理微信发来的请求
//     * @param request
//     * @return
//     */
//    public String processRequest(HttpServletRequest request)
//    {
//        String respMessage = null;
//        String respContent = "系统繁忙，请稍后再试！";
//
//        try
//        {
//            //解析微信服务器请求xml
//            Map<String, String> requestMap = MessageUtil.parseXml(request);
//
//            //			System.out.println("微信接收消息输出开始....");
//            //			for(String key : requestMap.keySet()){
//            //				System.out.println(key+":"+requestMap.get(key));
//            //			}
//            //			System.out.println("微信接收消息输出结束....");
//            //消息类型
//            String msgType = requestMap.get("MsgType");
//
//            System.out.println("msgType:" + msgType);
//            //接收事件推送
//            if (msgType.equals(Constant.MsgType.EVENT))
//            {
//                respMessage = this.handleEvent(requestMap, 1);
//            }
//            else
//            //接收普通消息
//            {
//                respMessage = this.ordinaryMessage(requestMap);
//
//            }
//
//            if (null != requestMap.get("FromUserName"))
//            {
//                try
//                {
//                    String openId = requestMap.get("FromUserName");
//
//                    Customer customer = new Customer();
//                    customer = customerService.getCustomerByOpenId(openId);
//
//                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//                    String createtime = null;
//                    if (customer == null)
//                    {
//
//                        try
//                        {
//                            String token = service.getUserToken(1);
//                            if (token != null && !"".equals(token))
//                            {
//                                String weiXinUserInfoStr = WeiXinYongHuGuanLi.weiXinGetUserInfoByOpenid(token, openId);
//                                JSONObject jsonObject = JSONObject.fromObject(weiXinUserInfoStr);
//                                if (null != jsonObject.get("subscribe") && jsonObject.getInt("subscribe") == 1)
//                                {
//                                    Customer weiXinCustomer = new Customer();
//                                    weiXinCustomer.setCity(jsonObject.getString("city"));
//                                    weiXinCustomer.setCountry(jsonObject.getString("country"));
//                                    weiXinCustomer.setHeadimgurl(jsonObject.getString("headimgurl"));
//                                    //获取昵称
//                                    String nickName = new String(jsonObject.getString("nickname").getBytes(), "utf-8");
//                                    weiXinCustomer.setNickname(nickName.replaceAll("[^\\u0000-\\uFFFF]", ""));
//                                    weiXinCustomer.setOpenid(openId);
//                                    weiXinCustomer.setProvince(jsonObject.getString("province"));
//                                    weiXinCustomer.setSex(jsonObject.getString("sex"));
//                                    String remark = jsonObject.getString("remark");
//                                    createtime = format.format(new Date());
//                                    String lastactivetime = format.format(new Date());
//                                    weiXinCustomer.setOpenid(openId);
//                                    weiXinCustomer.setLastactivetime(lastactivetime);
//                                    weiXinCustomer.setCreatetime(createtime);
//                                    weiXinCustomer.setRemark(remark == null || remark.equals("") ? null : remark);
//                                    try
//                                    {
//                                        customerService.updateCustomer(weiXinCustomer);
//                                    }
//                                    catch (Exception e)
//                                    {
//                                        e.printStackTrace();
//                                        weiXinCustomer.setNickname("微信用户");
//                                        customerService.updateCustomer(weiXinCustomer);
//                                    }
//                                }
//                            }
//                        }
//                        catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
//
//                    }
//                    else
//                    {
//                        if (customer.getCustomerid() != null && !"".equals(customer.getCustomerid()))
//                        {
//                        }
//                    }
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//            return respContent;
//        }
//        return respMessage;
//    }
//
//
//    public static void main(String[] args)
//    {
//        String keyWord = "我要买东西";
//        System.out.println(ChatI(keyWord));
//    }
//
//
//    /**
//     * 处理普通消息
//     * @param requestMap 请求消息集合
//     * @return String 响应消息的xml
//     */
//    private String ordinaryMessage(Map<String, String> requestMap)
//    {
//
//        //消息类型
//        String msgType = requestMap.get("MsgType");
//        //文本消息
//        if (msgType.toUpperCase().equals("TEXT"))
//        {
//            String keyWord = requestMap.get("Content");
//
//            return this.getKeyWordMessage(requestMap, keyWord);
//        }
//        //图片消息
//        else if (msgType.toUpperCase().equals("IMAGE"))
//        {
//            //PicUrl	 图片链接
//            //MediaId	 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
//            //MsgId	 消息id，64位整型
//        }
//        //语音消息
//        else if (msgType.toUpperCase().equals("VOICE"))
//        {
//            //MediaId	 语音消息媒体id，可以调用多媒体文件下载接口拉取数据。
//            //Format	 语音格式，如amr，speex等
//            //MsgID	 消息id，64位整型
//
//            //Recognition	 语音识别结果，UTF8编码  只有开启语音识别功能才有这个字段
//
//            if (null != requestMap.get("Recognition"))
//            {
//                String recognition = requestMap.get("Recognition");
//                return this.getKeyWordMessage(requestMap, recognition);
//            }
//            //没有语音识别功能 取出语音文件
//            else
//            {
//
//            }
//        }
//        //视频消息
//        else if (msgType.toUpperCase().equals("VIDEO"))
//        {
//            //MediaId	 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
//            //ThumbMediaId	 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
//            //MsgId	 消息id，64位整型
//        }
//        //地理位置消息
//        else if (msgType.toUpperCase().equals("LOCATION"))
//        {
//
//            // 发送消息用户id
//            String fromUserName = requestMap.get("FromUserName");
//            // 将消息发往的用户id
//            String toUserName = requestMap.get("ToUserName");
//            // 消息创建事件
//            int createTime = Integer.parseInt(requestMap.get("CreateTime"));
//            //Location_X	 地理位置维度
//            //Location_Y	 地理位置经度
//            //Scale	 地图缩放大小
//            //Label	 地理位置信息
//            //MsgId	 消息id，64位整型
//            String Location_X = requestMap.get("Location_X");
//            String Location_Y = requestMap.get("Location_Y");
//            //保存用户信息
//            Customer customer = new Customer();
//            String openId = requestMap.get("FromUserName");
//            customer.setOpenid(openId);
//            customer.setLocation_x(Location_X);
//            customer.setLocation_y(Location_Y);
//            customerService.updateCustomer(customer);
//            String content = "您的地理位置：";
//            content += "维度：" + Location_X + "";
//            content += "经度：" + Location_Y + "";
//
//            return RespService.setTextMessage(createTime, toUserName, fromUserName, content);
//        }
//        //链接消息
//        else if (msgType.toUpperCase().equals("LINK"))
//        {
//            //Title	 消息标题
//            //Description	 消息描述
//            //Url	 消息链接
//            //MsgId	 消息id，64位整型
//        }
//        return "";
//    }
//
//
//    /**
//     * 根据关键字获取回复消息
//     * @param requestMap 请求消息集合
//     * @param keyWord 关键字
//     * @return String 响应消息的xml
//     */
//    private String getKeyWordMessage(Map<String, String> requestMap, String keyWord)
//    {
//        // 发送消息用户id
//        String fromUserName = requestMap.get("FromUserName");
//        // 将消息发往的用户id
//        String toUserName = requestMap.get("ToUserName");
//        // 消息创建事件
//        int createTime = Integer.parseInt(requestMap.get("CreateTime"));
//
//        if (keyWord == null)
//        {
//            return "";
//        }
//        //接入客服信息
//        if (keyWord.equals("接入客服"))
//        {
//            return RespService.setCustomerMessage(createTime, toUserName, fromUserName);
//
//        }
//
//        // 获取回复消息信息
//        WeiXinKeyword weixinKeyword = service.getWeiXinKeyword(0, keyWord);
//
//        System.out.println("weixinKeyword" + weixinKeyword);
//        // 如果用户发送的消息内容不是关键字，则调用默认恢复
//        if (null != weixinKeyword)
//        {
//            return ReqService.handleReqText(requestMap, weixinKeyword);
//        }
//        else
//        {
//
//            // 根据响应类型设置对象响应消息
//            WeiXinPublicAccount wxpa = service.getWeiXinPublicAccount(1);
//
//            keyWord = wxpa.getDkeyword();
//
//            // 获取回复消息信息
//            weixinKeyword = service.getWeiXinKeyword(1, keyWord);
//
//            if (weixinKeyword != null)
//            {
//                return ReqService.handleReqText(requestMap, weixinKeyword);
//            }
//            else
//            {
//
//                return null;
//            }
//
//            //此处是小I的关键字回复
//            //			String content = ChatI(keyWord);
//            //			return RespService.setTextMessage(createTime, toUserName, fromUserName, content);
//        }
//    }
//
//
//    /**
//     * 处理推送事件
//     * (均按照处理文本消息请求方法处理推荐事件)
//     *
//     * @param requestMap 请求消息集合
//     * @return String 响应消息的xml
//     */
//    private String handleEvent(Map<String, String> requestMap, int uid)
//    {
//
//        String respContent = "";
//        String eventType = requestMap.get("Event");
//
//        /**
//         * 判断推送事件类型   1.自定义菜单事件
//         *              2.关注/取消关注事件
//         *              3.扫描带参数二维码事件
//         *              4.上报地理位置事件
//         */
//        if (Constant.Event.SUBSCRIBE.equals(eventType))
//        {
//            // 订阅
//            // 获取二维码的key(qrscene_为前缀，后面为二维码的参数值)
//            String eventKey = requestMap.get("EventKey");
//
//            System.out.println("eventKey" + eventKey);
//
//            // 根据响应类型设置对象响应消息
//            WeiXinPublicAccount wxpa = service.getWeiXinPublicAccount(uid);
//            System.out.println("wxpa getAccountid:" + wxpa.getAccountid());
//            System.out.println("wxpa:" + JSONObject.fromObject(wxpa).toString());
//            // 如果二维码的key为空，则为普通关注事件
//            if (StringUtils.isNotBlank(eventKey))
//            {
//                try
//                {
//                    String codeArgument = eventKey.substring(eventKey.indexOf("qrscene_") + 8);
//                    String openid = requestMap.get("FromUserName");
//                    System.out.println("openid:++++++++++=" + openid);
//                    Customer customer = customerService.getCustomerByOpenId(openid);
//
//                    //用户已关注不需要插入渠道用户表
//                    if (customer == null)
//                    {
//                        ChannelAndUser cuser = channelAndUserService.selectChannelAndUser(openid);
//
//                        if (null == cuser)
//                        {
//                            ChannelAndUser channelUser = new ChannelAndUser();
//                            channelUser.setOpenid(openid);
//                            channelUser.setChannelId(codeArgument);
//                            channelUser.setCreatetime(TimeUtil.getDateStr());
//                            channelAndUserService.saveChannelUser(channelUser);
//                        }
//                    }
//
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//
//                // 扫描带参数二维码事件: 用户未关注时，进行关注后的事件推送
//
//                // 解析二维码的KEY,获取参数(关键字：qrscene_为前缀，后面为二维码的参数值)
//
//                // 解析二位码
//
//                // 事件处理
//
//            }
//
//            String keyWord = wxpa.getFkeyword();
//            return this.getKeyWordMessage(requestMap, keyWord);
//
//        }
//        else if (Constant.Event.UNSUBSCRIBE.equals(eventType))
//        {
//
//            // 取消订阅
//
//        }
//        else if (Constant.Event.SCAN.equals(eventType))
//        {
//
//            // 扫描带参数二维码事件:用户已关注时的事件推送
//
//        }
//        else if (Constant.Event.LOCATION.equals(eventType))
//        {
//            //			ToUserName	开发者微信号
//            //			FromUserName	发送方帐号（一个OpenID）
//            //			CreateTime	消息创建时间 （整型）
//            //			MsgType	消息类型，event
//            //			Event	事件类型，LOCATION
//            //			Latitude	地理位置纬度
//            //			Longitude	地理位置经度
//            //			Precision	地理位置精度
//            // 消息创建事件
//            //Location_X	 地理位置维度
//            //Location_Y	 地理位置经度
//            //Scale	 地图缩放大小
//            //Label	 地理位置信息
//            //MsgId	 消息id，64位整型
//            String Location_X = requestMap.get("Latitude");
//            String Location_Y = requestMap.get("Longitude");
//            //保存用户信息
//            Customer customer = new Customer();
//            String openId = requestMap.get("FromUserName");
//            customer.setOpenid(openId);
//            customer.setLocation_x(Location_X);
//            customer.setLocation_y(Location_Y);
//            customerService.updateCustomer(customer);
//            // 上报地理位置事件
//
//        }
//        else if (Constant.Event.CLICK.equals(eventType))
//        {
//
//            String keyWord = requestMap.get("EventKey");
//
//            return this.getKeyWordMessage(requestMap, keyWord);
//
//            /*	// 自定义菜单事件
//            	// 获取响应消息关键字
//            	String keyWord = requestMap.get("EventKey");
//            	  WeiXinKeyword weixinKeyword = new WeiXinKeyword();
//            	   List<WeiXinMessage> messageList = service.getNewsInfo(keyWord);
//
//                    if (messageList != null && messageList.size() > 0)
//                    {
//                        logger.info("===========自定义菜单事件自动回复的报文：" + JSONArray.fromObject(messageList));
//                        weixinKeyword.setMsgtype("news");
//                        weixinKeyword.setMsgcount(messageList.size());
//                        weixinKeyword.setMessageList(messageList);
//                    }
//                    else
//                    {
//                        weixinKeyword.setMsgtype("text");
//                        weixinKeyword.setDescription("欢迎关注蓝联®微信服务号，我们将竭诚为您提供餐饮、住宿、交通、展会、咨询等一站式境外退税服务。"+
//            "退税项目：餐饮、住宿、酒店、交通、展会、会计、活动、广告、咨询等服务业税金。退税国家：日本、韩国、台湾（中国）、澳大利亚、新西兰、英国、法国、德国、意大利、荷兰、奥地利、比利时、丹麦、芬兰、冰岛、爱尔兰、卢森堡、马耳他、摩洛哥、挪威、西班牙、瑞典。"+
//            "【新手退税指引】请点击右下角主菜单上“个人中心”下面的“使用帮助”键。"+
//            "【常见问题答疑】请点击右下角主菜单上“个人中心”下面的“常见问题”键。"+"在线办理，简单方便，不论何地，要退税找我们，蓝联退税，企业境外消费的伙伴。"
//            );
//                    }
//
//                    return ReqService.handleReqText(requestMap, weixinKeyword);*/
//        }
//
//        return respContent;
//
//        //return this.getKeyWordMessage(requestMap,keyWord);
//
//    }
//
//
//    //	return respContent;
//
//    /**
//     * 小I机器人API接入方法
//     *
//     */
//    @SuppressWarnings("unchecked")
//    public static String ChatI(String Message)
//    {
//        String result = "";
//        BufferedReader in = null;
//        InputStream inputStream = null;
//        try
//        {
//            inputStream = ChatIWork(Message);
//
//            if (inputStream == null)
//            {
//                return "系统繁忙！";
//            }
//
//            String type = SystemConfig.readValue("type");
//            //文本信息直接取出字符串
//            if (type.equals("0"))
//            {
//                in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                String line;
//                while ((line = in.readLine()) != null)
//                    result += line;
//                return result;
//            }
//            //解析高级信息 xml格式
//            else if (type.equals("1"))
//            {
//                Map<String, String> map = new HashMap<String, String>();
//                // 读取输入流
//                SAXReader reader = new SAXReader();
//                Document document = reader.read(new InputStreamReader(inputStream, "UTF-8"));
//                // 获取xml的根元素
//                Element root = document.getRootElement();
//                // 遍历子节点
//                List<Element> elementList = root.elements();
//                for (Element e : elementList)
//                {
//                    map.put(e.getName(), e.getText());
//                    //					System.out.println(e.getName()+":"+e.getText());
//                }
//                result = map.get("Content");
//                return result;
//            }
//            else
//            {
//                in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
//                String line;
//                while ((line = in.readLine()) != null)
//                    result += line;
//                return result;
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                if (inputStream != null)
//                    inputStream.close();
//                if (in != null)
//                    in.close();
//            }
//            catch (IOException ex)
//            {
//                ex.printStackTrace();
//            }
//        }
//
//        return "系统繁忙！";
//    }
//
//
//    /**
//     * 小I机器人高级API接入方法Type=1
//     *
//     */
//    private static InputStream ChatIWork(String Message)
//    {
//
//        String realm = "xiaoi.com";
//        String method = "POST";
//        String uri = "/robot/ask.do";
//
//        String app_key = SystemConfig.readValue("app_key");
//        String app_secret = SystemConfig.readValue("app_secret");
//        String userId = SystemConfig.readValue("userid");
//        String type = SystemConfig.readValue("type");
//        String platform = SystemConfig.readValue("platform");
//
//        byte[] b = new byte[40];
//        new Random().nextBytes(b);
//        String nonce = new String(Hex.encodeHex(b));
//        String HA1 = DigestUtils.shaHex(StringUtils.join(new String[] { app_key, realm, app_secret }, ":"));
//        String HA2 = DigestUtils.shaHex(StringUtils.join(new String[] { method, uri }, ":"));
//        String sign = DigestUtils.shaHex(StringUtils.join(new String[] { HA1, nonce, HA2 }, ":"));
//        String auth = "app_key=\"" + app_key + "\",nonce=\"" + nonce + "\",signature=\"" + sign + "\"";
//        String param = "userId=" + userId + "&type=" + type + "&question=" + Message + "&platform=" + platform;
//        PrintWriter out = null;
//        BufferedReader in = null;
//        try
//        {
//            URL realUrl = new URL("http://nlp.xiaoi.com/robot/ask.do");
//            URLConnection conn = realUrl.openConnection();
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestProperty("connection", "Keep-Alive");
//            conn.setRequestProperty("X-Auth", auth);
//            conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; zh-CN)");
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            out = new PrintWriter(conn.getOutputStream());
//            out.print(param);
//            out.flush();
//            return conn.getInputStream();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            try
//            {
//                if (out != null)
//                    out.close();
//                if (in != null)
//                    in.close();
//            }
//            catch (IOException ex)
//            {
//                ex.printStackTrace();
//            }
//        }
//        return null;
//    }
//
//}
