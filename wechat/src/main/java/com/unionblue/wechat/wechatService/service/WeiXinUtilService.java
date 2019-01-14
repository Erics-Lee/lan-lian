//package com.unionblue.wechat.wechatService.service;
//
//import com.unionblue.wechat.wechatService.entity.WeiXinPublicAccount;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service("weiXinUtilService")
//@Transactional
//public class WeiXinUtilService
//{
//    @Autowired
//    private WeiXinPublicAccountService service;
//
//
//    /**
//     * 实时获取accessToken
//     * @param accountid
//     * @return
//     */
//    public String getUserToken(int accountid)
//    {
//        String accessToken = "";
//        try
//        {
//            WeiXinPublicAccount weiXinPublicAccount = getWeiXinPublicAccount(1);
//            //首先获取USER_TOKENS中是否存在
//            if (weiXinPublicAccount != null)
//            {
//                String accesstoken = weiXinPublicAccount.getAccesstoken();
//                System.out.println("============================" + accesstoken);
//                String accesstokencreatetime = weiXinPublicAccount.getAccesstokencreatetime();
//                String accesstokenexpiresin = weiXinPublicAccount.getAccesstokenexpiresin();
//                if (StringUtils.isBlank(accesstoken) || StringUtils.isBlank(accesstokencreatetime) || StringUtils.isBlank(accesstokenexpiresin))
//                {
//                    return createToken(weiXinPublicAccount);
//                }
//                else
//                {
//                    int expires_in = Integer.valueOf(accesstokenexpiresin);
//                    Long time = Long.valueOf(accesstokencreatetime);
//
//                    Long now = new Date().getTime();
//                    //判断是否超时
//                    if ((now - time) >= expires_in * 1000)
//                    {
//                        System.out.println("存在，超时，获取token");
//                        //重新创建
//                        return createToken(weiXinPublicAccount);
//                    }
//                    //未超时
//                    else
//                    {
//                        System.out.println("存在，未超时，获取token");
//                        return accesstoken;
//                    }
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return accessToken;
//    }
//
//
//    /**
//     * 创建token  根据公共账户信息创建一个token 并且放入数据库
//     * @return accessToken
//     */
//    private String createToken(WeiXinPublicAccount weiXinPublicAccount)
//    {
//        String accessToken = "";
//        try
//        {
//            //判断app_id,app_secret是否是空值  如果是空值 则返回需要设定app_id,app_secret
//            if (weiXinPublicAccount.getAppid() == null || weiXinPublicAccount.getAppsecret() == null)
//            {
//                return null;
//            }
//            else
//            {
//                String app_id = weiXinPublicAccount.getAppid();
//                String app_secret = weiXinPublicAccount.getAppsecret();
//                // 如果获取成功，则msg存放accessToken的值
//                Map<String, Object> map = WeiXinUtil.getAccessToken(app_id, app_secret);
//                //判断是否成功
//                if (map.get("code").equals("10000") || map.get("code").equals("1000"))
//                {
//                    accessToken = (String) map.get("msg");
//                    //存放到USER_TOKENS中
//                    Map<String, String> userToken = new HashMap<String, String>();
//                    userToken.put("expires_in", (String) map.get("expires_in"));
//                    userToken.put("time", String.valueOf(new Date().getTime()));
//                    userToken.put("access_token", (String) map.get("msg"));
//
//                    weiXinPublicAccount.setAccesstoken((String) map.get("msg"));
//                    weiXinPublicAccount.setAccesstokencreatetime("" + new Date().getTime());
//                    weiXinPublicAccount.setAccesstokenexpiresin((String) map.get("expires_in"));
//                    //保存
//                    try
//                    {
//                        updateWeiXinPublicAccount(weiXinPublicAccount);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                    return (String) map.get("msg");
//                }
//                else
//                {
//                    return null;
//                }
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return accessToken;
//    }
//
//
//    /**
//     * 实时获取ticket
//     * @param accountid
//     * @return
//     */
//    public String getTicket(int accountid)
//    {
//        String ticket = "";
//        try
//        {
//            WeiXinPublicAccount weiXinPublicAccount = getWeiXinPublicAccount(1);
//            //首先获取USER_TOKENS中是否存在
//            if (weiXinPublicAccount != null)
//            {
//                String ticketcreatetime = weiXinPublicAccount.getTicketcreatetime();
//                String ticketexpiresin = weiXinPublicAccount.getTicketexpiresin();
//                String access_token = getUserToken(accountid);
//                if (access_token == null || access_token.equals(""))
//                {
//                    return "";
//                }
//                if (StringUtils.isBlank(weiXinPublicAccount.getTicket()) || StringUtils.isBlank(ticketcreatetime) || StringUtils.isBlank(ticketexpiresin))
//                {
//                    System.out.println("不存在，获取ticket");
//                    return createTicket(weiXinPublicAccount, access_token);
//                }
//                else
//                {
//                    int expires_in = Integer.valueOf(ticketexpiresin);
//                    Long time = Long.valueOf(ticketcreatetime);
//
//                    Long now = new Date().getTime();
//                    //判断是否超时
//                    if ((now - time) >= expires_in * 1000)
//                    {
//                        System.out.println("存在，超时，获取ticket");
//                        //重新创建
//                        return createTicket(weiXinPublicAccount, access_token);
//                    }
//                    //未超时
//                    else
//                    {
//                        System.out.println("存在，未超时，获取ticket");
//                        return weiXinPublicAccount.getTicket();
//                    }
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return ticket;
//    }
//
//
//    /**
//     * 创建ticket  根据公共账户信息创建一个ticket 并且放入数据库
//     * @return accessToken
//     */
//    private String createTicket(WeiXinPublicAccount weiXinPublicAccount, String access_token)
//    {
//        String ticket = "";
//        try
//        {
//            //判断app_id,app_secret是否是空值  如果是空值 则返回需要设定app_id,app_secret
//            if (access_token == null || access_token.equals(""))
//            {
//                return null;
//            }
//            else
//            {
//                // 如果获取成功，则msg存放accessToken的值
//                Map<String, Object> map = WeiXinUtil.getTicket(access_token, "jsapi");
//                //判断是否成功
//                if (map.get("code").equals("10000") || map.get("code").equals("1000"))
//                {
//                    ticket = (String) map.get("msg");
//                    //存放到USER_TOKENS中
//                    Map<String, String> userToken = new HashMap<String, String>();
//                    userToken.put("expires_in", (String) map.get("expires_in"));
//                    userToken.put("time", String.valueOf(new Date().getTime()));
//                    userToken.put("ticket", (String) map.get("msg"));
//
//                    weiXinPublicAccount.setTicket((String) map.get("msg"));
//                    weiXinPublicAccount.setTicketcreatetime("" + new Date().getTime());
//                    weiXinPublicAccount.setTicketexpiresin((String) map.get("expires_in"));
//
//                    //保存
//                    try
//                    {
//                        updateWeiXinPublicAccount(weiXinPublicAccount);
//                    }
//                    catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//                    return (String) map.get("msg");
//                }
//                else
//                {
//                    return null;
//                }
//            }
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return ticket;
//    }
//
//
//    /**
//     * 更新WeiXinPublicAccount对象
//     * @param account
//     * @return
//     */
//    public boolean updateWeiXinPublicAccount(WeiXinPublicAccount account)
//    {
//        return service.updateWeiXinPublicAccount(account);
//    }
//
//
//    /**
//     * 根据条件获取WeiXinPublicAccount对象
//     * @param accountid
//     * @return
//     */
//    public WeiXinPublicAccount getWeiXinPublicAccount(int accountid)
//    {
//        //微信公共账号对象
//        WeiXinPublicAccount account = new WeiXinPublicAccount();
//        account = service.getWeiXinPublicAccountById(1);
//        /* //url
//         String action = "/weiXinPublicAccount/getWeiXinPublicAccountById.do";
//         //将参数存入map
//         Map<String, String> nvs = new HashMap<String, String>();
//         nvs.put("accountid", accountid+"");
//         Status status = WebServiceUtil.doPostToPiccFrontService(action, null, nvs);
//         if (status.getRtnCode() == 0)
//         {
//             JSONObject jsonObject = JSONObject.fromObject(status.getRtnMsg());
//             if (jsonObject.getInt("code") == 0000)
//             {
//                 JSONObject object = jsonObject.getJSONObject("data");
//                 account = (WeiXinPublicAccount) JSONObject.toBean(object, WeiXinPublicAccount.class);
//             }
//         }*/
//        return account;
//    }
//
//}
