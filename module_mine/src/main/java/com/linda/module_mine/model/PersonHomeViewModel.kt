package com.linda.module_mine.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_base.bean.mine.PersonMainData
import com.linda.module_mine.repository.PersonHomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class PersonHomeViewModel(private val personHomeRepository: PersonHomeRepository) : ViewModel() {

    val data: MutableLiveData<ArrayList<VideoItem<ItemData>>> = MutableLiveData()

    fun getPersonHomeData(url: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val homeData = withContext(Dispatchers.IO) {
                personHomeRepository.getPersonHomeData(
                    url
                )
            }
            data.value = homeData.itemList
        }
    }
}