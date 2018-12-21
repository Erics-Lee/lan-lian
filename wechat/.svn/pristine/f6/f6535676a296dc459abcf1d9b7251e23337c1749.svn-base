package com.unionblue.wechat.service;

/**
 * Created by 18501 on 2018/9/19.
 */
public interface UserService {

    String accountExtendProfileQuery(String sessionKey);

	String accountSimpleProfileUpdate(String sessionKey, String nickname);

    String accountExtendProfileUpdate(String sessionKey, String mobilephonenumber, String secndemailaddress);

    String userDetail(String sessionKey);

	String registerEMail(String account, String societyIDToken, String parentAppKey);

	String accountCertification(String account, String verificationCode);

	String societyQueryAccount(String societyIDToken);

	String firstLogin(String societyIDToken, String parentAppKey, String account, String sessionKey);

	String sendVerificateCode(String account, String parentAppKey);

}
