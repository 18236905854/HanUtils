package com.hanbo.utils.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.hanbo.utils.R;

import static com.hanbo.utils.utils.GlideUtils.loadCircle;

/**
 * @description: 对glide进行封装的工具类
 */

public class GlideUtils {


    public static void load(Context context, Object url, ImageView iv) {
        load(context, url, R.mipmap.ic_launcher, iv);
    }

    public static void load(Context context, Object url, int resourceId, ImageView iv) {
        try {
            if (null != context && !((Activity) context).isFinishing()) {


                byte[] decode = null;
                if (isBase64Img(url)) {
                    decode = Base64.decode(StringUtils.getString(url).split(",")[1], Base64.DEFAULT);
                }

                RequestOptions options = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .placeholder(resourceId)
                        .error(resourceId);

                Glide.with(context)
                        .asBitmap()
                        .load(decode == null ? url : decode)
                        .apply(options)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                iv.setImageBitmap(resource);
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 圆形图片
     *
     * @param url
     * @param view
     */
    @SuppressLint("CheckResult")
    public static void loadCircle(Context context, Object url, @NonNull ImageView view) {
        loadCircle(context, url, R.mipmap.ic_launcher_round, view);
    }

    @SuppressLint("CheckResult")
    public static void loadCircle(Context context, Object url, int resourceId, @NonNull ImageView view) {
        try {
            if (null != context && !((Activity) context).isFinishing()) {

                RequestOptions options = new RequestOptions();
                RequestOptions
                        .bitmapTransform(new CircleCrop())
                        .placeholder(resourceId)
                        .error(resourceId);

                Glide.with(context)
                        .asBitmap()
                        .load(url)
                        .apply(options)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                //屏幕宽度
                                float width = getWindowWidth((Activity) context);
                                //缩放比例
                                float scale = width / resource.getWidth();
                                //缩放后的宽度和高度
                                int afterWidth = (int) (resource.getWidth() * scale);
                                int afterHeight = (int) (resource.getHeight() * scale);
                                ViewGroup.LayoutParams lp = view.getLayoutParams();
                                lp.width = afterWidth;
                                lp.height = afterHeight;
                                view.setLayoutParams(lp);

                                view.setImageBitmap(resource);
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 高斯模糊
     */
    public static void blurBgPic(Context context, String url, int resourceId, ImageView view) {
        if (context == null || view == null) {
            return;
        }
        if (null != context && !((Activity) context).isFinishing()) {

            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(resourceId)
                    .error(resourceId);

            if (StringUtils.isEmpty(url)) {
                view.setImageResource(resourceId);
            } else {
                Glide.with(context.getApplicationContext())
                        .asBitmap()
                        .load(url)
                        .apply(options)
                        .into(view);
            }
        }
    }

    public static boolean isBase64Img(Object url) {
        if (url instanceof String) {
            String imageUrl = StringUtils.getString(url);
            if (!TextUtils.isEmpty(imageUrl) && (imageUrl.startsWith("data:image/png;base64,")
                    || imageUrl.startsWith("data:image/*;base64,")
                    || imageUrl.startsWith("data:image/jpg;base64,")
            )) {
                return true;
            }
        }
        return false;
    }

    // 屏幕宽度（像素）
    public static int getWindowWidth(Activity context) {
        DisplayMetrics metric = new DisplayMetrics();
        try {
            context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metric.widthPixels;
    }
}