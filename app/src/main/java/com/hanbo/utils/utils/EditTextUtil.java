package com.hanbo.utils.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.hanbo.utils.Interface.OnResultListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditTextUtil {
    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpace(EditText editText) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" ")) {
                    return "";
                } else {
                    return null;
                }
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    /**
     * 禁止EditText输入特殊字符
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpeChat(EditText editText) {

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#¥%……&*（）——+|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(source.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    public static void setEditTextNum(EditText editText, int maxNum) {
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxNum)});
    }

    public static void setEditTextChinese(EditText editText) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s1 = editText.getText().toString().trim();
                String s2 = filterChinese(editText.getText().toString().trim());
                if (!TextUtils.equals(s1, s2)) {
                    editText.setText(s2);
                    editText.setSelection(s2.length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("Chinese===", editText.getText().toString());
            }
        });
    }

    //是中文
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }


    public static String filterChinese(String str) {
        String regEx = "[^\\u4E00-\\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(str);
        return matcher.replaceAll("").trim();
    }

    /**
     * EditText输入监听
     *
     * @param editText
     */
    public static void addTextChangedListener(EditText editText, OnResultListener listener) {
        addTextChangedListener(editText, 800, listener);
    }

    public static void addTextChangedListener(EditText editText, long time, OnResultListener listener) {

        final MyHandler[] mHandler = {new MyHandler()};
        mHandler[0].setOnEnterResultsListener(listener);

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText.hasFocus() && listener != null) {
                    if (mHandler[0] == null) {
                        mHandler[0] = new MyHandler();
                        mHandler[0].setOnEnterResultsListener(listener);
                    }

                    mHandler[0].removeMessages(0x8989);

                    Message message = mHandler[0].obtainMessage();
                    message.what = 0x8989;
                    message.obj = s.toString();

                    mHandler[0].sendMessageDelayed(message, time);
                }
            }
        };

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.addTextChangedListener(tw);
            } else {
                editText.removeTextChangedListener(tw);
                if (mHandler[0] != null) {
                    mHandler[0].removeCallbacksAndMessages(0x8989);
                    mHandler[0] = null;
                }
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
                    ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    if (listener != null) {
                        if (mHandler[0] == null) {
                            mHandler[0] = new MyHandler();
                            mHandler[0].setOnEnterResultsListener(listener);
                        }

                        mHandler[0].removeMessages(0x8989);

                        Message message = mHandler[0].obtainMessage();
                        message.what = 0x8989;
                        message.obj = StringUtils.getString(editText.getText().toString());

                        mHandler[0].sendMessageDelayed(message, time);
                    }
                    return true;
                }
                return false;
            }
        });
    }


    static class MyHandler extends Handler {

        private OnResultListener mListener;

        public void setOnEnterResultsListener(OnResultListener mListener) {
            this.mListener = mListener;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x8989:
                    if (mListener != null) {
                        mListener.onResult(StringUtils.getString(msg.obj));
                    }
                    break;
            }
        }
    }
}
