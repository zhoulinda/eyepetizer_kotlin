package com.linda.module_community.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_home.repository.AttentionRepository
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
class AttentionViewModel(private val attentionRepository: AttentionRepository) : ViewModel() {

    val data: MutableLiveData<UiModel> = MutableLiveData()
    var url: String = ""

    fun getAttentionData() {
        GlobalScope.launch(Dispatchers.Main) {
            val attentionData =
                withContext(Dispatchers.IO) { attentionRepository.getAttentionData() }
            url = attentionData.nextPageUrl
            data.value = UiModel(true, attentionData.itemList)
        }
    }

    fun getMoreAttentionData() {
        GlobalScope.launch(Dispatchers.Main) {
            val attentionData =
                withContext(Dispatchers.IO) { attentionRepository.getMoreAttentionData(url) }
            url = attentionData.nextPageUrl
            data.value = UiModel(false, attentionData.itemList)
        }
    }

    data class UiModel(val isRefresh: Boolean, val items: ArrayList<VideoItem<ItemData>>)
}