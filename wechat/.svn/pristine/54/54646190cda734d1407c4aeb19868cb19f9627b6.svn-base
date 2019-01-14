package com.unionblue.wechat.util;

public enum KeyEnum {
    KEY2(2,"PublicKey2","PrivateKey2"),
    KEY1(1,"PublicKey","PrivateKey"),
    KEY3(3,"PublicKey3","PrivateKey3"),
    KEY4(4,"PublicKey4","PrivateKey4"),
    KEY5(5,"PublicKey5","PrivateKey5"),
    KEY6(6,"PublicKey6","PrivateKey6");
    private int keyNum;
    private String publickeyName;
    private String privatekeyName;
    KeyEnum(int keyNum, String publickeyName,String privatekeyName) {
        this.keyNum = keyNum;
        this.publickeyName = publickeyName;
        this.privatekeyName=privatekeyName;
    }

    public int getKeyNum() {
        return keyNum;
    }

    public void setKeyNum(int keyNum) {
        this.keyNum = keyNum;
    }

    public String getPublickeyName() {
        return publickeyName;
    }

    public void setPublickeyName(String publickeyName) {
        this.publickeyName = publickeyName;
    }

    public String getPrivatekeyName() {
        return privatekeyName;
    }

    public void setPrivatekeyName(String privatekeyName) {
        this.privatekeyName = privatekeyName;
    }

    public static String getPublickeyName(int keyNum) {
        for (KeyEnum c : KeyEnum.values()) {
            if (c.getKeyNum() == keyNum) {
                return c.getPublickeyName();
            }
        }
        return null;
    }
    public static String getprivatekeyName(int keyNum) {
        for (KeyEnum c : KeyEnum.values()) {
            if (c.getKeyNum() ==keyNum) {
                return c.getPrivatekeyName();
            }
        }
        return null;
    }
}
