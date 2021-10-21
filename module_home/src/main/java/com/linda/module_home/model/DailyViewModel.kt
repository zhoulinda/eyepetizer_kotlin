package com.linda.module_home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_home.repository.DailyRepository
import com.linda.module_home.repository.DiscoverRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/8
Email:zhoulinda@songguo7.com
 */
class DailyViewModel(private val dailyRepository: DailyRepository) : ViewModel() {

    val data: MutableLiveData<UiModel> = MutableLiveData()
    var url: String = ""

    fun getDailyData() {
        GlobalScope.launch(Dispatchers.Main) {
            val dailyData = withContext(Dispatchers.IO) { dailyRepository.getDailyData() }
            url = dailyData.nextPageUrl;
            data.value = UiModel(true, dailyData.itemList)
        }
    }


    fun getMoreDailyData() {
        GlobalScope.launch(Dispatchers.Main) {
            val dailyData =
                withContext(Dispatchers.IO) { dailyRepository.getMoreDailyData(url) }
            url = dailyData.nextPageUrl
            data.value = UiModel(false, dailyData.itemList)
        }
    }

    data class UiModel(val isRefresh: Boolean, val items: ArrayList<VideoItem<ItemData>>)
}