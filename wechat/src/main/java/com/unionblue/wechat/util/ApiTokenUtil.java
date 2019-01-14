package com.unionblue.wechat.util;




import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiTokenUtil {

    /** 指定加密算法为RSA */
    private static final String ALGORITHM = "RSA";
    /** 密钥长度，用来初始化 */
    private static final int KEYSIZE = 1024;
    /** 指定公钥存放文件 */
    private static String PUBLIC_KEY_FILE = "PublicKey";
    /** 指定私钥存放文件 */
    private static String PRIVATE_KEY_FILE = "PrivateKey";
    /** 指定公钥存放文件 */
    private static String PUBLIC_KEY_FILE_TOW = "PublicKey2";
    /** 指定私钥存放文件 */
    private static String PRIVATE_KEY_FILE_TOW = "PrivateKey2";

    /**
     * 生成密钥对
     *
     * @throws Exception
     */
    private static void generateKeyPair() throws Exception {

        // /** RSA算法要求有一个可信任的随机数源 */
        // SecureRandom secureRandom = new SecureRandom();

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        // keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /** 用对象流将生成的密钥写入文件 */
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        } finally {
            /** 清空缓存，关闭文件输出流 */
            oos1.close();
            oos2.close();
        }
    }
    /**
     * 生成第二对密钥对
     *
     * @throws Exception
     */
    private static void generateKeyPair2() throws Exception {

        // /** RSA算法要求有一个可信任的随机数源 */
        // SecureRandom secureRandom = new SecureRandom();

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        // keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /** 用对象流将生成的密钥写入文件 */
            oos1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE_TOW));
            oos2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE_TOW));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        } finally {
            /** 清空缓存，关闭文件输出流 */
            oos1.close();
            oos2.close();
        }
    }

    /**
     * 公钥加密方法
     *
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encrypt(String source , String publicKeyStr) throws Exception {
        Key publicKey=getkey(publicKeyStr);
//        ObjectInputStream ois = null;
//        try {
//            /** 将文件中的公钥对象读出 */
//            ois = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(publicKeyStr).getPath()));
//            publicKey = (Key) ois.readObject();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            ois.close();
//        }

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /** 执行加密操作
         * 分段加密
         * */
        int inputLen = b.length;
        int offLen = 0;//偏移量
        int i = 0;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte [] cache;
            if(inputLen - offLen > 117){
                cache = cipher.doFinal(b, offLen,117);
            }else{
                cache = cipher.doFinal(b, offLen,inputLen - offLen);
            }
            bops.write(cache);
            i++;
            offLen = 117 * i;
        }
        bops.close();
        byte[] b1 = bops.toByteArray();
        return Base64.encodeBase64String(b1);
    }


    /**
     * 私钥加密方法
     *
     * @param source 源数据
     * @return
     * @throws Exception
     */
    public static String encryptPriKey(String source, String privateKeyStr) throws Exception {
        Key privateKey=getkey(privateKeyStr);
//        ObjectInputStream ois = null;
//        try {
//            /** 将文件中的私钥对象读出 */
//            ois = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(privateKeyStr).getPath()));
//            privateKey = (Key) ois.readObject();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            ois.close();
//        }

        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] b = source.getBytes();
        /** 执行加密操作
         * 分段加密
         * */
        int inputLen = b.length;
        int offLen = 0;//偏移量
        int i = 0;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte [] cache;
            if(inputLen - offLen > 117){
                cache = cipher.doFinal(b, offLen,117);
            }else{
                cache = cipher.doFinal(b, offLen,inputLen - offLen);
            }
            bops.write(cache);
            i++;
            offLen = 117 * i;
        }
        bops.close();
        byte[] b1 = bops.toByteArray();
        return Base64.encodeBase64String(b1);
    }

    /**
     * 私钥解密算法
     *
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decrypt(String cryptograph, String privateKeyStr) throws Exception {
        Key privateKey=getkey(privateKeyStr);
//        ObjectInputStream ois = null;
//        try {
//            /** 将文件中的私钥对象读出 */
//            ois = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(privateKeyStr).getPath()));
//            privateKey = (Key) ois.readObject();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            ois.close();
//        }

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] b1 = Base64.decodeBase64(cryptograph);
        /**
         * 执行解密操作 分段解密
         */
        int inputLen = b1.length;
        int offLen = 0;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte[] cache;
            if(inputLen - offLen > 128){
                cache = cipher.doFinal(b1,offLen,128);
            }else{
                cache = cipher.doFinal(b1,offLen,inputLen - offLen);
            }
            byteArrayOutputStream.write(cache);
            i++;
            offLen = 128 * i;

        }
        byteArrayOutputStream.close();
        byte[] b = byteArrayOutputStream.toByteArray();
        return new String(b);
    }

    /**
     * 公钥解密算法
     *
     * @param cryptograph 密文
     * @return
     * @throws Exception
     */
    public static String decryptPubKey(String cryptograph , String publicKeyStr) throws Exception {
        Key publicKey =getkey(publicKeyStr);
//        ObjectInputStream ois = null;
//        try {
//            /** 将文件中的公钥钥对象读出 */
//            ois = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(publicKeyStr).getPath()));
//            publicKey = (Key) ois.readObject();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            ois.close();
//        }

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] b1 = Base64.decodeBase64(cryptograph);

        /** 执行解密操作 */
        /**
         * 执行解密操作 分段解密
         */
        int inputLen = b1.length;
        int offLen = 0;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte[] cache;
            if(inputLen - offLen > 128){
                cache = cipher.doFinal(b1,offLen,128);
            }else{
                cache = cipher.doFinal(b1,offLen,inputLen - offLen);
            }
            byteArrayOutputStream.write(cache);
            i++;
            offLen = 128 * i;

        }
        byteArrayOutputStream.close();
        byte[] b = byteArrayOutputStream.toByteArray();
        return new String(b);
    }


    /**
     * 根据钥匙名称获取对应钥匙
     * @param keyName
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private final  static  Key getkey(String keyName) throws IOException, ClassNotFoundException {
        Key key;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource(keyName).getPath()));
            key = (Key) ois.readObject();
        } catch (Exception e) {
            throw e;
        } finally {
            ois.close();
        }

        return key;
    }


    /**
     * 根据钥匙名称生产对应钥匙对
     *
     * @throws Exception
     */
    private static void generateKeyPair(String publicKeyName,String privateKeyname) throws Exception {

        // /** RSA算法要求有一个可信任的随机数源 */
        // SecureRandom secureRandom = new SecureRandom();

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        // keyPairGenerator.initialize(KEYSIZE, secureRandom);
        keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        /** 得到公钥 */
        Key publicKey = keyPair.getPublic();

        /** 得到私钥 */
        Key privateKey = keyPair.getPrivate();

        ObjectOutputStream oos1 = null;
        ObjectOutputStream oos2 = null;
        try {
            /** 用对象流将生成的密钥写入文件 */
            oos1 = new ObjectOutputStream(new FileOutputStream(publicKeyName));
            oos2 = new ObjectOutputStream(new FileOutputStream(privateKeyname));
            oos1.writeObject(publicKey);
            oos2.writeObject(privateKey);
        } catch (Exception e) {
            throw e;
        } finally {
            /** 清空缓存，关闭文件输出流 */
            oos1.close();
            oos2.close();
        }
    }





    public static String decryptDual(String cryptograph ,String  publicKeyNameA, String privateKeyB ) throws Exception {


     //   System.out.println("准备用私钥1解密的字符串（解密）为：" + cryptograph);
        String target = decrypt(cryptograph,privateKeyB);// 解密密文
     //  System.out.println("用私钥1解密后的字符串为：" + target);
     //   System.out.println();
      //  System.out.println("准备用公钥2解密的字符串（鉴权）为：" + target);
        String target2 = decryptPubKey(target,publicKeyNameA);// 解密密文
       // System.out.println("用公钥2解密后的字符串为：" + target2);
        return target2;
    }
    public static String encryptDual(String source, String  publicKeyNameB, String privateKeyA ) throws Exception {


//        System.out.println("准备用私钥2加密的字符串（签名）为：" + source);
        String cryptograph2 = encryptPriKey(source,privateKeyA);// 生成的密文
//        System.out.print("用私钥2加密后的结果为:" + cryptograph2);
//        System.out.println();
//        System.out.println("准备用公钥1加密的字符串（加密）为：" + cryptograph2);
        String cryptograph = encrypt(cryptograph2,publicKeyNameB);// 生成的密文
//        System.out.print("用公钥1加密后的结果为:" + cryptograph);
//        System.out.println();
        return cryptograph;
    }









//    public static void main(String[] args) throws Exception {
////        generateKeyPair();//生成密钥对文件
////        generateKeyPair2();//生成密钥对文件
//        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
//        String date2= df.format(new Date());
//        String source = date2;
//        System.out.println("准备加密的字符串为：" + source);
//
//        String cryptograph2 = encryptDual(source);
//        System.out.print("用双重加密后的结果为:" + cryptograph2);
//
//        Map map=new HashMap();
//        map.put("token",cryptograph2);
//
//        HttpClinetUtil.postMap("http://localhost:8080/lltax_manager/api/v1.1/updateOrderDetail1",map);
//
//
//
//
//
//        String target = decryptDual(cryptograph2);// 解密密文
//        System.out.println("解密后的字符串为：" + target);
////        System.out.println("准备用私钥2加密的字符串（签名）为：" + source);
////        String cryptograph2 = encryptPriKey(source,PRIVATE_KEY_FILE_TOW);// 生成的密文
////        System.out.print("用私钥2加密后的结果为:" + cryptograph2);
////        System.out.println();
////        System.out.println("准备用公钥1加密的字符串（加密）为：" + cryptograph2);
////        String cryptograph = encrypt(cryptograph2,PUBLIC_KEY_FILE);// 生成的密文
////        System.out.print("用公钥1加密后的结果为:" + cryptograph);
////        System.out.println();
////        System.out.println("准备用私钥1解密的字符串（解密）为：" + cryptograph);
////        String target = decrypt(cryptograph,PRIVATE_KEY_FILE);// 解密密文
////        System.out.println("用私钥1解密后的字符串为：" + target);
////        System.out.println();
////        System.out.println("准备用公钥2解密的字符串（鉴权）为：" + target);
////        String target2 = decryptPubKey(target,PUBLIC_KEY_FILE_TOW);// 解密密文
////        System.out.println("用公钥2解密后的字符串为：" + target2);
////        System.out.println();
//    }
public static void main(String[] args) throws Exception {
//    for (int i = 1; i <7 ; i++) {
//        System.out.println(i);
//        generateKeyPair(KeyEnum.getPublickeyName(i),KeyEnum.getprivatekeyName(i));
//    }

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        String date2= df.format(new Date());
        String source = date2;
        System.out.println("准备加密的字符串为：" + source);

        String cryptograph2 = encryptDual(source,KeyEnum.getPublickeyName(2),KeyEnum.getprivatekeyName(3));
        System.out.println("用双重加密后的结果为:" + cryptograph2);

        Map map=new HashMap();
        map.put("token",cryptograph2);

//        HttpClinetUtil.postMap("http://localhost:8080/lltax_manager/api/v1.1/updateOrderDetail1",map);





        String target = decryptDual(cryptograph2,KeyEnum.getPublickeyName(3),KeyEnum.getprivatekeyName(2));// 解密密文

    System.out.println("解密后的字符串为：" + target);
}
}



