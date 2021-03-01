package com.linda.lib_common.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * 描述 :     网络工具类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */

class NetworkUtil {

    companion object {

        /**
         * 网络是否可用
         */
        fun isNetworkAvailable(context: Context): Boolean {
            val manager =
                context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return info != null && info.isAvailable
        }
    }
}