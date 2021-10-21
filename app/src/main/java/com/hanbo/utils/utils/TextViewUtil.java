package com.hanbo.utils.utils;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.hanbo.utils.Interface.OnClickableSpanListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: han
 * 创建时间: 21/04/17 14:22
 * 描    述: TextView设置
 */
public class TextViewUtil {


    //设置可跨接的字串
    public static void setSpannableString(TextView textView, Object value, String regex, OnClickableSpanListener listener) {
        List<String> list = new ArrayList<>();
        list.clear();
        if (regex.contains("%d")) {
            regex = regex.replace("%d", "\\d+(\\.\\d+)?");
            list.addAll(getMatchValue(StringUtils.getString(value), regex));
        } else if (regex.contains("%s")) {
            if (regex.endsWith("%s")) {
                regex = regex.replace("%s", ".*");
            } else {
                regex = regex.replace("%s", ".*?");
            }
            list.addAll(getMatchValue(StringUtils.getString(value), regex));
        } else {
            list.add(regex);
        }
        setSpannableString(textView, value, list, listener);
    }

    //设置可跨接的字串
    public static void setSpannableString(TextView textView, Object value, List<String> regexLists, OnClickableSpanListener listener) {


        if (textView == null) {
            return;
        }
        if (StringUtils.isEmpty(StringUtils.getString(value)) || regexLists == null || regexLists.size() == 0) {
            textView.setText(StringUtils.getString(value));
            return;
        }

        List<String> regexList = getRegexList(value, regexLists);

        for (int i = regexList.size() - 1; i >= 0; i--) {
            if (!StringUtils.getString(value).contains(regexList.get(i))) {
                regexList.remove(i);
            }
        }

        try {
            boolean isContains = false;
            String mTitleMsg = StringUtils.getString(value);

            List<String> mRegexLists = new ArrayList<>();
            mRegexLists.addAll(regexList);
            String temp;//临时变量
            boolean flag;//是否交换的标志
            for (int i = 0; i < mRegexLists.size() - 1; i++) {   //表示趟数，一共 arr.length-1 次
                // 每次遍历标志位都要先置为false，才能判断后面的元素是否发生了交换
                flag = false;
                for (int j = mRegexLists.size() - 1; j > i; j--) { //选出该趟排序的最长字段排在前面
                    if (mRegexLists.get(j).length() > mRegexLists.get(j - 1).length()) {
                        temp = mRegexLists.get(j);
                        mRegexLists.set(j, mRegexLists.get(j - 1));
                        mRegexLists.set(j - 1, temp);
                        flag = true;    //只要有发生了交换，flag就置为true
                    }
                }
                // 判断标志位是否为false，如果为false，说明后面的元素已经有序，就直接return
                if (!flag) break;
            }

            StringBuffer regexs = new StringBuffer();
            if (mRegexLists != null && mRegexLists.size() > 0) {
                for (int i = 0; i < mRegexLists.size(); i++) {
                    if (i != 0) {
                        regexs.append("|");
                    }

                    String regex = mRegexLists.get(i);
                    if (mRegexLists.get(i).contains("+"))
                        regex = mRegexLists.get(i).replace("+", "\\+");
                    regexs.append(regex);

                    if (!isContains) {
                        isContains = mTitleMsg.contains(mRegexLists.get(i));
                    }
                }
            }

            if (isContains) {
                String[] split = mTitleMsg.split(regexs.toString());
                List<String> mRegexList = getRegexReductionList(value, regexList);

//                for (int i = 0; i < split.length; i++) {
//                    System.out.println("split[" + i + "] = " + split[i]);
//                }
//                for (int i = 0; i < regexList.size(); i++) {
//                    System.out.println("regexList[" + i + "] = " + regexList.get(i));
//                }
//                for (int i = 0; i < mRegexList.size(); i++) {
//                    System.out.println("mRegexList[" + i + "] = " + mRegexList.get(i));
//                }

                for (int i = 0; i < (split.length > mRegexList.size() ? split.length : mRegexList.size()); i++) {
                    if (i < split.length) {
                        if (i == 0) {
                            textView.setText(split[i]);
                        } else {
                            textView.append(split[i]);
                        }
                    }

                    if (i < mRegexList.size()) {
                        int finalI = i;
                        ClickableSpan clickableSpan = new ClickableSpan() {
                            @Override
                            public void onClick(@NonNull View widget) {
                                if (listener != null) {
                                    listener.onClick(StringUtils.getString(mRegexList.get(finalI)));
                                }
                            }

                            @Override
                            public void updateDrawState(@NonNull TextPaint ds) {
//                                super.updateDrawState(ds);
                                if (listener != null) {
                                    listener.updateDrawState(StringUtils.getString(mRegexList.get(finalI)), finalI, ds);
                                }
                            }
                        };
                        String regex = mRegexList.get(i);
                        SpannableString service = new SpannableString(regex);
                        service.setSpan(clickableSpan, 0, service.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        textView.append(service);
                    }
                }
                textView.setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                textView.setText(value.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            textView.setText(value.toString());
        }
    }


    //关键字排序处理
    private static List<String> getRegexList(Object value, List<String> regexList) {
        List<String> keys = new ArrayList<>();

        try {
            if (!StringUtils.isEmpty(StringUtils.getString(value)) && regexList != null && regexList.size() > 0) {
                List<String> mKey = new ArrayList<>();
                List<Integer> indexs = new ArrayList<>();

                for (String key : regexList) {
                    if (!mKey.contains(key)) {
                        mKey.add(key);
                    }
                }

                for (int i = 0; i < mKey.size(); i++) {
                    String str = mKey.get(i);
                    List<Integer> index = getIndex(StringUtils.getString(value), str);
                    for (int j = 0; j < index.size(); j++) {
                        indexs.add(index.get(j));
                        keys.add(str);
                    }
                }

                for (int i = 0; i < indexs.size() - 1; i++) {
                    for (int j = 0; j < indexs.size() - 1 - i; j++) {
                        if (indexs.get(j) < indexs.get(j + 1)) {
                            int big = indexs.get(j);
                            indexs.set(j, indexs.get(j + 1));
                            indexs.set(j + 1, big);

                            String str = keys.get(j);
                            keys.set(j, keys.get(j + 1));
                            keys.set(j + 1, str);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keys;
    }

    //关键字排序还原
    private static List<String> getRegexReductionList(Object value, List<String> regexList) {
        List<String> mKey = new ArrayList<>();

        try {
            if (!StringUtils.isEmpty(StringUtils.getString(value)) && regexList != null && regexList.size() > 0) {
                List<Integer> indexs = new ArrayList<>();
                mKey.addAll(regexList);

                for (int i = 0; i < mKey.size(); i++) {
                    String msg = StringUtils.getString(value);
                    int index = msg.indexOf(mKey.get(i));
                    if (indexs.contains(index)) {
                        index = msg.indexOf(mKey.get(i), index);
                        indexs.add(index);
                    } else {
                        indexs.add(index);
                    }
                }

                for (int j = 0; j < indexs.size() - 1; j++) {//外层循环控制排序次数
                    for (int i = 0; i < indexs.size() - 1 - j; i++) {
                        //判断相邻元素大小，如果前面的比后面的大，则交换位置
                        if (indexs.get(i) > indexs.get(i + 1)) {
                            int temp2 = indexs.get(i);
                            indexs.set(i, indexs.get(i + 1));
                            indexs.set(i + 1, temp2);

                            String temp3 = mKey.get(i);
                            mKey.set(i, mKey.get(i + 1));
                            mKey.set(i + 1, temp3);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mKey;
    }

    /**
     * 获取strings字符串中所有str字符所在的下标
     *
     * @param strings 母字符串
     * @param str     子字符串
     * @return 字符串在母字符串中下标集合，如果母字符串中不包含子字符串，集合长度为零
     */
    private static List<Integer> getIndex(String strings, String str) {
        List<Integer> list = new ArrayList<>();
        int flag = 0;
        while (strings.indexOf(str) != -1) {
            //截取包含自身在内的前边部分
            String aa = strings.substring(0, strings.indexOf(str) + str.length());
            flag = flag + aa.length();
            list.add(flag - str.length());
            strings = strings.substring(strings.indexOf(str) + str.length());
        }
        return list;
    }

    /**
     * 根据正则表达式  获取 匹配值数组
     *
     * @param s（数据源）
     * @param regex  (正则表达式)
     * @return
     */
    public static List<String> getMatchValue(String s, String regex) {
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            list.add(matcher.group(0));
        }
        return list;
    }
}
