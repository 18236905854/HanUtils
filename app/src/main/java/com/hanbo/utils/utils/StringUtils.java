package com.hanbo.utils.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Administrator 字符串转换相关类
 */
public class StringUtils {
    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     *
     * @param input
     * @return boolean
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input)) {
            return true;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取assets中json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 手机号中间四位由****代替
     *
     * @param phone
     * @return
     */
    public static String formatPhoneNo(String phone) {
        if (phone.length() == 11) {
            StringBuilder sb = new StringBuilder(phone);
            sb.replace(3, 7, "****");
            return sb.toString();
        } else {
            return phone;
        }
    }

    /**
     * 手机号中间四位由****代替
     *
     * @param idCard
     * @return
     */
    public static String formatIdCard(String idCard) {
        if (idCard.length() == 18) {
            StringBuilder sb = new StringBuilder(idCard);
            sb.replace(1, 17, "****************");
            return sb.toString();
        } else {
            return idCard;
        }
    }


    /**
     * 距离只保留两位小数
     *
     * @param num 以万为单位
     * @return
     */
    public static String getTenThousandFormat(double num) {
        String str = "";
        double value = num;
        if (num >= 10000) {
            value = num / 10000;
            str = "万";
        } else {
            str = "";
        }
        return formatMoney(1, value) + str;
    }


    /**
     * 距离只保留两位小数
     *
     * @param distance 以米为单位
     * @return
     */
    public static String distanceFormat(double distance) {
        String str = "";
        double value = distance;
        if (distance >= 1000) {
            value = distance / 1000;
            str = " km";
        } else {
            str = " m";
        }
        return formatMoney(value) + str;
    }


    /**
     * 距离只保留两位小数
     *
     * @param distance 以米为单位
     * @return
     */
    public static String distanceFormat2(double distance) {
        double value = distance;
        return formatMoney(value) + "km";
    }

    /**
     * 距离只保留两位小数
     *
     * @param distance 以米为单位
     * @return
     */
    public static String distanceFormat(String distance) {
        return distanceFormat(StringUtils.getDouble(distance, 0));
    }

    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String formatMoney(String money) {
        try {

            if (money != null && !"".equals(money)) {
                BigDecimal b = new BigDecimal(money);
                b = b.setScale(2, BigDecimal.ROUND_DOWN); //小数位 直接舍去
                return b.toString();
            }
        } catch (Exception e) {
            return money;
        }

        return "0.00";
    }

    /**
     * 保留x位小数
     *
     * @param value
     * @return
     */
    public static double formatMoney(double value, int size) {
        try {
            BigDecimal b = new BigDecimal(value);
            b = b.setScale(size, BigDecimal.ROUND_DOWN); //小数位 直接舍去
            return b.doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String formatMoney(double money) {
        BigDecimal b = new BigDecimal(money);
        b = b.setScale(2, BigDecimal.ROUND_HALF_UP); //小数位 四舍五入
        return b.toString();
    }

    /**
     * 保留x位小数
     *
     * @param money
     * @return
     */
    public static String formatMoney(int newScale, double money) {
        BigDecimal b = new BigDecimal(money);
        b = b.setScale(newScale, BigDecimal.ROUND_HALF_UP); //小数位 四舍五入
        return b.toString();
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整数
     *
     * @param obj
     * @return 转换异常返回
     */
    public static int getInt(Object obj, int defValue) {
        if (obj == null)
            return defValue;
        return toInt(obj.toString(), defValue);
    }

    /**
     * 字符串转double
     *
     * @param str
     * @param defValue
     * @return
     */
    public static double toDouble(String str, double defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 对象转double
     *
     * @param obj
     * @return 转换异常返回
     */
    public static double getDouble(Object obj, double defValue) {
        if (obj == null)
            return defValue;
        return toDouble(obj.toString(), defValue);
    }

    /**
     * 对象转long
     *
     * @param obj
     * @return 转换异常返回
     */
    public static long getLong(Object obj, long defValue) {
        if (obj == null)
            return defValue;
        return toLong(obj.toString(), defValue);
    }

    /**
     * 对象转long
     *
     * @param obj
     * @return 转换异常返回
     */
    public static long toLong(String obj, long defValue) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转String
     *
     * @param obj
     * @return 转换异常返回
     */
    public static String getString(Object obj) {
        if (null == obj) {
            return "";
        }

        try {
            return String.valueOf(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 判断参数字符串是否为电话格式
     *
     * @param phoneStr
     * @return
     */
    public static boolean isPhoneNumber(String phoneStr) {
        if (TextUtils.isEmpty(phoneStr)) {
            return false;
        }
        if (phoneStr.length() == 11) {
            for (int i = 0; i < 11; i++) {
                boolean b = PhoneNumberUtils.isISODigit(phoneStr.charAt(i));
                if (!b) {
                    return false;
                }
            }
            //定义电话格式的正则表达式
            String regex = "[1][3456789]\\d{9}";
            return phoneStr.matches(regex);
        } else {
            return false;
        }
    }

    public static boolean isPasswordCorrect(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        if (password.length() > 5 && password.length() < 21) {
            //定义密码格式的正则表达式
            String regex = "^(?![\\d]+$)(?![a-zA-Z]+$)(?![^\\da-zA-Z]+$).{6,20}$";
            return password.matches(regex);
        } else {
            return false;
        }
    }

    // 判断email格式是否正确
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    // 判断是否全是数字(包含：整数和小数)
    public static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    // 判断是否全是数字
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    // 判断时间格式是否正确 格式 yyyy-mm 或 yyyy-m
    public static boolean isTime(String mobiles) {
        Pattern p = Pattern.compile("^\\d{4}-\\d{1,2}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    /**
     * 随机字符串用做临时图片名字
     *
     * @return
     */
    public static String Number() {
        String str = "";
        for (int i = 1; i <= 9; i++) {
            int ma = (int) (Math.random() * 25 + 65);
            char ca = (char) ma;
            str += ca + "";
        }
        str = str + ".jpg";
        return str;
    }

    /**
     * 18位或者15位身份证验证 18位的最后一位可以是字母x
     *
     * @param text
     * @return
     */
    public static boolean personIdValidation(String text) {
        String regx = "[0-9]{17}x";
        String reg1 = "[0-9]{15}";
        String regex = "[0-9]{18}";
        boolean flag = text.matches(regx) || text.matches(reg1) || text.matches(regex);
        return flag;
    }

    /**
     * ArrayList<String> 转换为 1，2，3
     *
     * @param list
     * @return
     */
    public static String ListToStringOne(List<String> list) {
        String str = "";
        if (list == null || list.size() == 0) {
            return str;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            str = str + list.get(i) + ",";
        }
        return str.substring(0, str.length() - 1);
    }

    /**
     * @return
     */
    public static String getString(String str) {
        return str == null ? "" : str;
    }

    /**
     * @return
     */
    public static String getString(String str, String showText) {
        return str == null ? showText : str;
    }

    /**
     * 获取String 中的数组
     *
     * @param context
     * @param resid
     * @return
     */
    public static String[] getStringArray(Context context, int resid) {
        return context.getResources().getStringArray(resid);
    }

    /**
     * 获取换行字符串
     *
     * @return
     */
    public static String getNString(String str) {
        str = getString(str);
        if (str.isEmpty()) {
            return str;
        }
        return "\n" + str;
    }

    /**
     * 获取换行字符串
     *
     * @return
     */
    public static String getTString(String str) {
        str = getString(str);
        if (str.isEmpty()) {
            return str;
        }
        return "\t\t" + str;
    }

    /**
     * list转化为String
     *
     * @param stringList
     * @return
     */
    public static String listToString(List<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    /***
     * String 转化为List<String>
     *
     * @param string
     * @return
     */
    public static List<String> StringToList(String string) {
        List<String> list = new ArrayList<String>();

        if (StringUtils.isEmpty(string)) return list;

        String[] d = string.split(",");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        return list;
    }

    public static List<String> StringToListSpace(String string) {
        List<String> list = new ArrayList<String>();

        if (StringUtils.isEmpty(string)) return list;

        String[] d = string.split(" ");
        for (int i = 0; i < d.length; i++) {
            list.add(d[i]);
        }
        return list;
    }

    /***
     * 时间弹出框，选择时间为整点的时候，补0 的方法
     *
     * @param x
     * @return
     */
    public static String format(int x) {
        String s = "" + x;
        if (s.length() == 1)
            s = "0" + s;
        return s;
    }

    /**
     * 复制内容到剪切板
     *
     * @param copyStr
     * @return
     */
    public static boolean copy(Context context, String copyStr) {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", copyStr);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 验证身份证
     *
     * @param id 身份证号码
     * @return
     */
    public static boolean isIDCard(String id) {
        if (id.length() > 15) {
            return isIDCard18(id);
        } else {
            return isIDCard15(id);
        }
    }

    /**
     * @return 验证身份证号码 15 位
     */
    private static boolean isIDCard15(String id) {
        String str = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(id);
        return m.matches();
    }

    /**
     * @return 简单验证身份证号码 18 位
     */
    private static boolean isIDCard18(String id) {
        String str = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
//        Pattern p = Pattern.compile(str);
//        Matcher m = p.matcher(id);
//        return m.matches();
        return Pattern.matches(str, id);
    }


    /**
     * 获取字符串前X位
     *
     * @param str 原始字符串
     * @param i   截取前几位
     * @return
     */
    public static String remove(String str, int i) {
        if (i > str.length()) {
            return str;
        } else {
            return str.substring(0, i); // or  str=str.Remove(i,str.Length-i);
        }
    }


    public static String getStringAfter(String original, String designated) {
//        截取designated之后字符串
        if (isEmpty(original)) {
            return "";
        }

        if (-1 == original.indexOf(designated)) {
            return original;
        } else {
            String str1 = original.substring(0, original.indexOf(designated));
            String str2 = original.substring(str1.length() + 1);
            return str2;
        }

    }


    private static long lastClickTime;

    public static boolean isFastClick() {
        return isFastClick(500);
    }

    /**
     * 禁止连续点击
     *
     * @param intervalTime 间隔时间
     * @return
     */
    public static boolean isFastClick(long intervalTime) {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= intervalTime) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }


    /**
     * 转换字体颜色
     *
     * @return
     */
    @SuppressWarnings("static-access")
    public static SpannableStringBuilder textColorPrase(String text, int color1, int color2, int color3, int fromPosition, int endPosition) {
        if (TextUtils.isEmpty(text) || text.length() == 1 || fromPosition > text.length() || endPosition > text.length())
            return new SpannableStringBuilder("");

        SpannableStringBuilder stylescore = new SpannableStringBuilder(text);
        //颜色
        stylescore.setSpan(new ForegroundColorSpan(color1), 0, fromPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stylescore.setSpan(new ForegroundColorSpan(color2), fromPosition, endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        stylescore.setSpan(new ForegroundColorSpan(color3), endPosition, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return stylescore;
    }

    /**
     * 判断是否是null或长度为0
     *
     * @param str 要判断字符串
     * @return 空：true
     */
    public static boolean isempty(String str) {
        try {
            if (str == null)
                return true;
            if (str.length() == 0)
                return true;
            if (str.isEmpty())
                return true;
            if (str.replace(" ", "").equalsIgnoreCase("null"))
                return true;
            if ("".equals(str.replace(" ", "")))
                return true;
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    //去除指定字符
    public static String deleteCharString(String sourceString, String chElemData) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < sourceString.length(); i++) {
            if (!String.valueOf(sourceString.charAt(i)).equals(chElemData)) {
                sb.append(sourceString.charAt(i));
            }
        }
        return sb.toString();
    }


    //第一个是字母
    public static boolean isFirstLetter(String msg) {
        String reg = "[a-z|A-Z]";
        if (isEmpty(msg)) return false;

        char charAt = msg.charAt(0);

        return Pattern.matches(reg, charAt + "");
    }

    //Url解析
    public static String getUriParam(String url, String paramKey) {
        if (TextUtils.isEmpty(url) || TextUtils.isEmpty(paramKey)) {
            return "";
        }
        String paramValue = "";
        try {
            Uri uri = Uri.parse(url);
            paramValue = uri.getQueryParameter(paramKey);
            if (paramValue == null) {
                paramValue = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paramValue;
    }

}
