package com.linda.module_home.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
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
class DiscoverViewModel(private val discoverRepository: DiscoverRepository) : ViewModel() {

    val data: MutableLiveData<ArrayList<VideoItem<ItemData>>> = MutableLiveData()

    fun getDiscoverData() {
        GlobalScope.launch(Dispatchers.Main) {
            val discoverData = withContext(Dispatchers.IO) { discoverRepository.getDiscoverData() }
            data.value = discoverData.itemList
        }
    }
}