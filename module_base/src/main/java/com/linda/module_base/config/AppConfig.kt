package com.linda.module_base.config

import android.text.TextUtils
import com.linda.module_base.BuildConfig

/**
 * 描述 :     app相关配置
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/1
 */
class AppConfig {

    companion object {

        const val BASE_SERVER_URL = "http://baobab.kaiyanapp.com"

        const val GET_VERIFICATION_CODE = "https://account.kaiyanapp.com/v1/api/sms/initialization"

        const val REGISTER = "https://account.kaiyanapp.com/v2/api/register"

        const val LOGIN = "https://account.kaiyanapp.com/v1/api/login"

        fun isDebug(): Boolean {
            return !TextUtils.equals(BuildConfig.BUILD_TYPE, "release")
        }

    }
}