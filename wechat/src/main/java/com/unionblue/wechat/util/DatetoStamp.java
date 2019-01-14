package com.unionblue.wechat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 18501 on 2017/8/26.
 */
public class DatetoStamp {
    //创建唯一实例
    private static DatetoStamp ONLY=new DatetoStamp();
    //屏蔽外部的new  私有构造方法
    private DatetoStamp(){
    }
    //提供一个全家访问点
    public static DatetoStamp getInstance(String createTime, String timeStamp, String str) throws ParseException {
        //其他代码
        dateToStamp(createTime);//时间转换为时间戳
        stampToDate(timeStamp);//将时间戳转换为时间
        dateaToStamp(createTime);//
        isValidDate(str);
        return ONLY;
    }
    /*
     * 将时间转换为时间戳  yyyy-MM-dd HH:MM:SS
     */
    public static String dateToStamp(String s) throws ParseException, ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /*
     * 将时间转换为时间戳  yyyy-MM-dd
     */
    public static String dateaToStamp(String s) throws ParseException, ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /*
    * 将时间转换为时间戳  yyyy-MM-dd
    */
    public static String dateaToStampS(String s) throws ParseException, ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /*
 * 将时间戳转换为时间
 */
    public static String stampToDateS(String s){
        if (!StringUtil.isEmpty(s)){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            long lt = new Long(s+000);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        }
        return s;
    }
    /*
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        if (!StringUtil.isEmpty(s)){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
        }
        return s;
    }

    /*
  * 将时间戳转换为时间
  */
    public static String stampToDateICBC(String s){
        if (!StringUtil.isEmpty(s)){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            long lt = new Long(s);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        }
        return s;
    }
    public static String stampToDate2(String s){
        if (!StringUtil.isEmpty(s)){
            String res;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            long lt = new Long(s);
            Date date = new Date(lt);
            res = simpleDateFormat.format(date);
            return res;
        }
        return s;
    }
    public static boolean isValidDate(String date) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }
    public static boolean isValidDate1(String date) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }
    //  03/12/2017 转换为时间戳
    public static String daToStamp(String s) throws ParseException, ParseException {
        boolean b = isValidDate1(s);
        String res = null;
        String date;
        if(b){
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-mm-dd");
            DateFormat _dateFormat1 = new SimpleDateFormat("dd/mm/yyyy");
            date = dateFormat1.format(_dateFormat1.parse(s));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dates = simpleDateFormat.parse(date);
            long ts = dates.getTime();
            res = String.valueOf(ts);
            return res;
            //System.out.println(dateFormat1.format(_dateFormat1.parse(s)));
        }else {
            return res;
        }

        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);*/

    }





    //测试
    public static void main(String[] args) throws ParseException {
//        boolean b = isValidDate1("03/12/2017");
//        String bb = daToStamp("09/09/2001");//2001/09/09
//        System.out.println(bb);
//        double d = 114.145;
//        DecimalFormat df = new DecimalFormat("#");
//        String str = df.format(d);
//        System.out.println(str);
//        ServiceOrderInfoService serviceOrderInfoService= ApplicationContextHelper.getBean(ServiceOrderInfoServiceImpl.class);
//        ServiceOrderInfo serviceOrderInfo=serviceOrderInfoService.getOderByNubid("UB592347920007");

//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//        String time = date.format("2018-03-09");
//        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("2005-06-09"));

        //"yyyy-MM-dd HH:mm"
        /*String A = dateaToStamp("2019-1-16");

        System.out.println(A);*/
       /* System.out.println(Integer.parseInt(dateaToStamp("2017-10-1").substring(0,9)));
       *//* System.out.println((int)System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        System.out.println( (int) (System.currentTimeMillis()/1000));*//*
        String a = stampToDate(1510562177+"000");
        *//*System.err.println(System.currentTimeMillis());
        String c = stampToDate(String.valueOf(System.currentTimeMillis()));
        System.out.println("c:"+c);*//*
        System.out.println(a);
//        int [] array ={0,1,3,3,4};
//        for (int i =0 ;i<array.length;i++) {
//            for (int j=i;j<array.length+i;j++){
//                out.print(array[j==0?0:j%array.length]);
//            }
//            out.println("");
//        }*/
        //System.out.println(WordUtils.class.getClassLoader().getResource("../../").getPath());

     }

}
