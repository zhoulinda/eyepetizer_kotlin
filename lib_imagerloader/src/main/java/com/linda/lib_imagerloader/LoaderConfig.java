package com.linda.lib_imagerloader;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/10/20
 */
public class LoaderConfig {

    public static class Config {

        private Context context;

        public Config(Context context) {
            this.context = context;
        }

        private String url;
        private float thumbnail;
        private Uri uri;
        private File file;
        private int resId;
        private int scaleMode;
        private int blur;
        private int placeHolderResId;
        private int errorResId;
        private boolean isCircle;
        private boolean isGif;
        private int roundingRadius;
        private int overrideWidth;
        private int overrideHeight;
        private float alpha;
        private boolean isFirstResource;
        private boolean isNeedCacheStrategy;
        private ImageView targetView;

        /**
         * 图片url
         *
         * @param url
         * @return
         */
        public Config load(String url) {
            this.url = url;
            return this;
        }

        /**
         * 加载图片uri
         *
         * @param uri
         * @return
         */
        public Config load(Uri uri) {
            this.uri = uri;
            return this;
        }

        /**
         * 加载file
         *
         * @param file
         * @return
         */
        public Config load(File file) {
            this.file = file;
            return this;
        }

        /**
         * 加载drawable资源
         *
         * @param resId
         * @return
         */
        public Config load(int resId) {
            this.resId = resId;
            return this;
        }

        /**
         * 填充模式
         *
         * @param scaleMode
         * @return
         */
        public Config scaleType(int scaleMode) {
            this.scaleMode = scaleMode;
            return this;
        }

        /**
         * 高斯模糊
         *
         * @return
         */
        public Config blur(int blur) {
            this.blur = blur;
            return this;
        }

        /**
         * 占位图
         *
         * @return
         */
        public Config placeHolder(int resourceId) {
            this.placeHolderResId = resourceId;
            return this;
        }

        /**
         * 加载失败时显示的内容
         *
         * @param resourceId
         * @return
         */
        public Config error(int resourceId) {
            this.errorResId = resourceId;
            return this;
        }

        /**
         * 加载圆形图片
         *
         * @return
         */
        public Config asCircle() {
            this.isCircle = true;
            return this;
        }

        /**
         * 设置圆角
         *
         * @return
         */
        public Config rounderCorner(int roundingRadius) {
            this.roundingRadius = roundingRadius;
            return this;
        }


        /**
         * 设置宽高
         *
         * @param width
         * @param height
         * @return
         */
        public Config override(int width, int height) {
            this.overrideWidth = width;
            this.overrideHeight = height;
            return this;
        }


        /**
         * 设置透明度
         *
         * @return
         */
        public Config alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }


        /**
         * 加载缩略图
         *
         * @param thumbnail (取值0~1)
         * @return
         */
        public Config thumbnail(float thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        /**
         * 加载gif
         *
         * @param isGif 是否是gif
         * @return
         */
        public Config asGif(boolean isGif) {
            this.isGif = isGif;
            asGif(isGif, false);
            return this;
        }

        /**
         * 加载gif
         *
         * @param isGif           是否是gif
         * @param isFirstResource 是否只加载第一帧
         * @return
         */
        public Config asGif(boolean isGif, boolean isFirstResource) {
            this.isGif = isGif;
            this.isFirstResource = isFirstResource;
            return this;
        }


        /**
         * 是否设置磁盘缓存策略
         *
         * @param isNeedCacheStrategy
         * @return
         */
        public Config diskCacheStrategy(boolean isNeedCacheStrategy) {
            this.isNeedCacheStrategy = isNeedCacheStrategy;
            return this;
        }

        /**
         * 目标View
         *
         * @param targetView
         * @return
         */
        public ImageView into(ImageView targetView) {
            this.targetView = targetView;
            GlideLoader.getInstance().create(this);
            return targetView;
        }

        public Context getContext() {
            return context;
        }

        public String getUrl() {
            return url;
        }

        public Uri getUri() {
            return uri;
        }

        public File getFile() {
            return file;
        }

        public int getResId() {
            return resId;
        }

        public int getScaleMode() {
            return scaleMode;
        }

        public int getBlur() {
            return blur;
        }

        public int getPlaceHolder() {
            return placeHolderResId;
        }

        public int getErrorResId() {
            return errorResId;
        }

        public boolean isCircle() {
            return isCircle;
        }

        public int getRoundingRadius() {
            return roundingRadius;
        }

        public int getOverrideWidth() {
            return overrideWidth;
        }

        public int getOverrideHeight() {
            return overrideHeight;
        }

        public float getThumbnail() {
            return thumbnail;
        }

        public float getAlpha() {
            return alpha;
        }

        public boolean isGif() {
            return isGif;
        }

        public boolean isFirstResource() {
            return isFirstResource;
        }

        public boolean isNeedCacheStrategy() {
            return isNeedCacheStrategy;
        }

        public ImageView getTargetView() {
            return targetView;
        }

    }
}
