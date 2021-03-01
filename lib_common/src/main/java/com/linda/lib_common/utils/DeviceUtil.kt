package com.linda.lib_common.utils

import android.os.Build

/**
 * 描述 :     设备工具类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
class DeviceUtil {

    companion object {

        /**
         *  获取手机品牌
         */
        fun getMobileBrand(): String {
            var model: String? = Build.BRAND
            model = model?.trim { it <= ' ' } ?: ""
            return model
        }

        /**
         *  获取手机型号
         */
        fun getMobileModel(): String {
            var model: String? = Build.MODEL
            model = model?.trim { it <= ' ' } ?: ""
            return model
        }

        /**
         * 获取当前手机系统版本号
         *
         * @return  系统版本号
         */
        fun getSystemVersion(): String? {
            return Build.VERSION.RELEASE
        }
    }
}