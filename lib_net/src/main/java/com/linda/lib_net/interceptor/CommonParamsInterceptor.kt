package com.linda.lib_net.interceptor

import com.linda.lib_common.CommonApplication
import com.linda.lib_common.utils.DeviceUtil
import com.linda.lib_common.utils.DisplayUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 描述 :     公共参数拦截器
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/3/1
 */
class CommonParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request: Request
        val modifiedUrl = originalRequest.url.newBuilder()
            .addQueryParameter("udid", "6a6fa0cffa894788983cce8dc7d33cd7c508f3bb")
            .addQueryParameter("vc", "6030071")
            .addQueryParameter("vn", "6.3.7")
            .addQueryParameter(
                "size",
                DisplayUtil.getScreenRatio(CommonApplication.getContext())
            )
            .addQueryParameter("deviceModel", DeviceUtil.getMobileModel())
            .addQueryParameter("first_channel", DeviceUtil.getMobileBrand())
            .addQueryParameter("last_channel", DeviceUtil.getMobileBrand())
            .addQueryParameter("system_version_code", DeviceUtil.getSystemVersion())
            .build()
        request = originalRequest.newBuilder().url(modifiedUrl).build()
        return chain.proceed(request)
    }
}