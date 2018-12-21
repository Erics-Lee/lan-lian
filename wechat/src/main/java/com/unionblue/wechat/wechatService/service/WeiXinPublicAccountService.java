//package com.unionblue.wechat.wechatService.service;
//
//import com.unionblue.wechat.wechatService.dao.WeiXinPublicAccountDao;
//import com.unionblue.wechat.wechatService.entity.WeiXinPublicAccount;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WeiXinPublicAccountService
//{
//    @Autowired
//    private WeiXinPublicAccountDao dao;
//
//
//    /**
//     * 编辑WeiXinPublicAccount
//     * @param account
//     * @return
//     */
//    public boolean updateWeiXinPublicAccount(WeiXinPublicAccount account)
//    {
//        account = dao.save(account);
//
//        if (account != null)
//        {
//            return true;
//        }
//
//        return false;
//    }
//
//
//    /**
//     * 根据id获取WeiXinPublicAccount对象
//     * @param accountid
//     * @return
//     */
//    public WeiXinPublicAccount getWeiXinPublicAccountById(int accountid)
//    {
//        //微信公共账号对象
//        WeiXinPublicAccount account = new WeiXinPublicAccount();
//        account = dao.findOne(accountid);
//        return account;
//    }
//}
