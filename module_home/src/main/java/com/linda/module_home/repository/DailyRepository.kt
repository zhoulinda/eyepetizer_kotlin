package com.linda.module_home.repository

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager

/**
Describe:
Created by linda on 2021/10/8
Email:zhoulinda@songguo7.com
 */
class DailyRepository {

    suspend fun getDailyData(): BaseListData<ItemData> {
        return RetrofitManager.serviceV2.getDailyData()
    }

    suspend fun getMoreDailyData(url: String): BaseListData<ItemData> {
        return RetrofitManager.serviceV2.getMoreDailyData(url)
    }
}