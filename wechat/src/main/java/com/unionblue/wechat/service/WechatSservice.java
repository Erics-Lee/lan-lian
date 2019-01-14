package com.unionblue.wechat.service;

import com.unionblue.wechat.model.WechatUser;

public interface WechatSservice {

    public  String getAccess_token();

    String codeGetOpenid(String code);

    WechatUser getWechatUser(String openid, String access_token);
}
