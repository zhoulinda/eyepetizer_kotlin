package com.linda.module_mine.repository

import com.linda.module_base.bean.mine.PersonMainData
import com.linda.module_base.net.RetrofitManager

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class PersonMainRepository {

    suspend fun getPersonMainData(userId: Int, userType: String): PersonMainData {
        return RetrofitManager.serviceV2.getPersonMainData(userId, userType)
    }

}