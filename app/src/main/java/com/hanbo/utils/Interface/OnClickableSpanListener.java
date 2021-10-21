package com.hanbo.utils.Interface;

import android.text.TextPaint;

import androidx.annotation.NonNull;

/**
 * @Author: han
 * 创建时间: 21/04/15 15:56
 * 描    述: 富文本监听
 */
public interface OnClickableSpanListener {
    void onClick(@NonNull CharSequence result);

    void updateDrawState(@NonNull CharSequence result, int position, TextPaint ds);
}
