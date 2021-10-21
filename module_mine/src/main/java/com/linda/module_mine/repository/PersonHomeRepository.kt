package com.linda.module_mine.repository

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class PersonHomeRepository {

    suspend fun getPersonHomeData(url: String): BaseListData<ItemData> {
        return RetrofitManager.serviceV2.getPersonHomeData(url)
    }

}