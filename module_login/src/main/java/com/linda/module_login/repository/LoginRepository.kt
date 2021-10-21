package com.linda.module_login.repository

import com.linda.module_base.bean.Login
import com.linda.module_base.config.AppConfig
import com.linda.module_base.net.RetrofitManager

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class LoginRepository {

    suspend fun login(userName: String, password: String): Login {
        return RetrofitManager.serviceV2.login(AppConfig.LOGIN, userName, password)
    }
}