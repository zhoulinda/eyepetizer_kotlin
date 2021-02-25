package com.linda.lib_imagerloader;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/10/20
 */
public class LoaderFactory implements ILoaderFactory {

    @Override
    public IImageLoader create(@LoaderType int loaderType) {
        IImageLoader imageLoader;
        switch (loaderType) {
            case LoaderType.TYPE_GLIDE:
                imageLoader = GlideLoader.getInstance();
                break;
            case LoaderType.TYPE_FRESCO:
                imageLoader = GlideLoader.getInstance();
                break;
            default:
                imageLoader = GlideLoader.getInstance();
                break;
        }
        return imageLoader;
    }
}
