package com.unionblue.wechat.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/9/14 0014.
 */
public class StringUtil {

    private static Random randGen = null;

    private static char[] numbersAndLetters = null;

    public static boolean isEmpty(String arg) {
        return (arg == null) || (arg.trim().equals(""))||(arg.equals("null"));
    }

    public static boolean isEmail(String searchPhrase) {
        if (!isEmpty(searchPhrase)) {
            String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";

            Pattern regex = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
            Matcher matcher = regex.matcher(searchPhrase);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^[1][0-9]{10}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    public static String changeTime(String time)  {

        SimpleDateFormat oldFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date date = oldFormat.parse(time);
            return newFormat.format(date);
        }catch (Exception w){
            w.printStackTrace();


        }
        return time;
    }
    public static boolean hasNullStr(String arg) {
        return (arg == null) || (arg.trim().equals("")) || (arg.trim().equalsIgnoreCase("null"));
    }

    public static boolean isDecimal(String str) {
        if ((str == null) || ("".equals(str)))
            return false;
        Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
        return pattern.matcher(str).matches();
    }

    public static boolean isNumber(String arg) {
        return (!isEmpty(arg)) && (arg.matches("-?[0-9]*"));
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static int GetRandomNumber6() {
        Random r = new Random();
        return r.nextInt(900000) + 100000;
    }

    public static int GetRandomNumber4() {
        Random r = new Random();
        return r.nextInt(9000) + 1000;
    }

    public static int getAge(String birthday) {
        try {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(1);
            int age = Integer.valueOf(birthday.substring(0, 4)).intValue();
            return year - age + 1;
        } catch (Exception e) {
        }
        return 0;
    }



    public static void main(String[] iu) {
        System.out.println(isDecimal("93.2121212"));
        System.out.println(isDecimal("3"));
        System.out.println(isDecimal("0"));
        System.out.println(isDecimal("-13.2121212"));
    }

    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
        }
        if (numbersAndLetters == null) {
            numbersAndLetters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        }

        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(25)];
        }
        return new String(randBuffer);
    }

    public static String fillNumWithLeft(String num, int length) {
        StringBuffer sb = new StringBuffer();
        if (!isEmpty(num)) {
            int i = getStrLength(num);
            if (i > length) {
                sb.append(num.substring(0, length));
            } else {
                for (; i < length; i++) {
                    sb.append("0");
                }
                sb.append(num);
            }
        } else {
            for (int i = 0; i < length; i++) {
                sb.append("0");
            }
        }

        return sb.toString();
    }

    public static int getStrLength(String str) {
        if (!isEmpty(str)) {
            try {
                return str.getBytes("GBK").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static String getSpace(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static List<String> getListByStr(String list) {

       String[] array  =list.split(",");
       return Arrays.asList(array);

    }
}
