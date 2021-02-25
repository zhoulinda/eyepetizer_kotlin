package com.linda.lib_imagerloader;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.linda.lib_imagerloader.util.DisplayUtil;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/10/20
 */
public class GlideLoader implements IImageLoader {

    private RequestManager requestManager;

    public static GlideLoader getInstance() {
        return GlideLoaderHolder.instance;
    }

    private static class GlideLoaderHolder {
        private static final GlideLoader instance = new GlideLoader();
    }

    @Override
    public void create(LoaderConfig.Config config) {
        requestManager = Glide.with(config.getContext());
        load(config);
    }

    /**
     * 加载图片
     *
     * @param config
     */
    private void load(LoaderConfig.Config config) {

        RequestBuilder<Drawable> requestBuilder = null;

        RequestBuilder<GifDrawable> gifRequestBuilder = null;

        //图片url
        if (!TextUtils.isEmpty(config.getUrl())) {
            if (config.getUrl().endsWith("gif")) {
                gifRequestBuilder = requestManager.asGif().load(config.getUrl());
            } else {
                requestBuilder = requestManager.load(config.getUrl());
            }
        }

        //Drawable资源
        if (config.getResId() > 0) {
            if (config.getUrl().endsWith("gif")) {
                gifRequestBuilder = requestManager.asGif().load(config.getUrl());
            } else {
                requestBuilder = requestManager.load(config.getUrl());
            }
        }


        //width/height
        if (config.getOverrideWidth() > 0 && config.getOverrideHeight() > 0)
            requestBuilder.override(config.getOverrideWidth(), config.getOverrideHeight());

        //缩放比例
        if (config.getThumbnail() > 0) requestBuilder.thumbnail(config.getThumbnail());

        //圆形图片
        if (config.isCircle())
            requestBuilder.apply(RequestOptions.bitmapTransform(new CircleCrop()));

        //Gif


        //占位图
        if (config.getPlaceHolder() > 0) requestBuilder.placeholder(config.getPlaceHolder());

        //加载失败时的显示内容
        if (config.getErrorResId() > 0) requestBuilder.error(config.getErrorResId());

        //毛玻璃模糊度
        if (config.getBlur() > 0)
            requestBuilder.transform(new BlurTransformation(config.getBlur()));

        //矩形圆角
        if (DisplayUtil.dip2px(config.getContext(), config.getRoundingRadius()) > 0) {
            RoundedCorners roundedCorners = new RoundedCorners(DisplayUtil.dip2px(config.getContext(), config.getRoundingRadius()));
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            options.apply(requestBuilder);
        }

        //透明度
        if (config.getAlpha() > 0) {
            config.getTargetView().setAlpha(config.getAlpha());
        }

        //目标View
        if (config.getTargetView() != null) {
            requestBuilder.into(config.getTargetView());
        }
    }

    /**
     * 设置磁盘缓存路径
     */


    /**
     * 清除内存缓存
     */
    private void clearMemoryCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    /**
     * 清理磁盘缓存
     */
    private void setDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }
}
