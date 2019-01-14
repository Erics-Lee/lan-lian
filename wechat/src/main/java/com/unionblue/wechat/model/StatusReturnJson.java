package com.unionblue.wechat.model;

import java.io.Serializable;

/**
 * Created by 18501 on 2018/10/24.
 */
public class StatusReturnJson implements Serializable {

    //编号
    private String EnumIndex;
    //名称
    private String DefaultName;

    public String getEnumIndex() {
        return EnumIndex;
    }

    public void setEnumIndex(String enumIndex) {
        EnumIndex = enumIndex;
    }

    public String getDefaultName() {
        return DefaultName;
    }

    public void setDefaultName(String defaultName) {
        DefaultName = defaultName;
    }
}
