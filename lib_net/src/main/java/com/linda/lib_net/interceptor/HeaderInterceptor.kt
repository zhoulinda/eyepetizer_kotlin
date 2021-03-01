package com.linda.lib_net.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 描述 :     请求头拦截器
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/3/1
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header("model", "android")
            .method(originalRequest.method, originalRequest.body)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}