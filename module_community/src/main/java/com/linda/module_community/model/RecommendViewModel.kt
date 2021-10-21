package com.linda.module_community.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_home.repository.RecommendRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/8
Email:zhoulinda@songguo7.com
 */
class RecommendViewModel(private val recommendRepository: RecommendRepository) : ViewModel() {

    val data: MutableLiveData<UiModel> = MutableLiveData()
    var url: String = ""

    fun getCommunityRecommendData() {
        GlobalScope.launch(Dispatchers.Main) {
            val recommendData =
                withContext(Dispatchers.IO) { recommendRepository.getCommunityRecommendData() }
            url = recommendData.nextPageUrl
            data.value = UiModel(true, recommendData.itemList)
        }
    }

    fun getMoreCommunityRecommendData() {
        GlobalScope.launch(Dispatchers.Main) {
            val recommendData =
                withContext(Dispatchers.IO) { recommendRepository.getMoreCommunityRecommendData(url) }
            url = recommendData.nextPageUrl
            data.value = UiModel(true, recommendData.itemList)
        }
    }

    data class UiModel(val isRefresh: Boolean, val items: ArrayList<VideoItem<ItemData>>)
}