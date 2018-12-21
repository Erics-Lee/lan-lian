package com.unionblue.wechat.wechatService.message;

public class InputMessage extends org.weixin4j.spi.InputMessage {
    private int MenuId;

    public int getMenuId() {
        return MenuId;
    }

    public void setMenuId(int menuId) {
        MenuId = menuId;
    }
}
