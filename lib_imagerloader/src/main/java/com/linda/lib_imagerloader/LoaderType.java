package com.linda.lib_imagerloader;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/10/20
 */
@IntDef({LoaderType.TYPE_GLIDE, LoaderType.TYPE_FRESCO})
@Retention(RetentionPolicy.CLASS)
public @interface LoaderType {

    /**
     * Glide
     */
    int TYPE_GLIDE = 1;

    /**
     * Fresco
     */
    int TYPE_FRESCO = 2;
}
