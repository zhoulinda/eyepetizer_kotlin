package com.linda.module_login.repository

import com.linda.module_base.bean.Login
import com.linda.module_base.bean.VerificationCode
import com.linda.module_base.config.AppConfig
import com.linda.module_base.net.RetrofitManager

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class RegisterRepository {

    suspend fun register(userName: String, verificationCode: String, password: String): Login {
        return RetrofitManager.service.register(
            AppConfig.LOGIN,
            userName,
            verificationCode,
            password
        )
    }

    suspend fun getVerificationCode(
        phone: String
    ): VerificationCode {
        return RetrofitManager.service.getVerificationCode(
            AppConfig.GET_VERIFICATION_CODE,
            phone,
            "sms"
        )
    }
}