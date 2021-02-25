package com.linda.lib_imagerloader;

import android.content.Context;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/6
 */
public class ImageLoader {

    public static LoaderConfig.Config init(Context context) {
        return new LoaderConfig.Config(context);
    }
}
