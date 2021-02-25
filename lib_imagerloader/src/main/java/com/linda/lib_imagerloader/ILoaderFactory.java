package com.linda.lib_imagerloader;

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/10/20
 */
interface ILoaderFactory {

    IImageLoader create(@LoaderType int loaderType);
}
