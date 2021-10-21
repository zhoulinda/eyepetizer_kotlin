package com.linda.module_mine.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.module_base.bean.mine.PersonMainData
import com.linda.module_mine.repository.PersonMainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class PersonMainViewModel(private val personMainRepository: PersonMainRepository) : ViewModel() {

    val mainData: MutableLiveData<PersonMainData> = MutableLiveData()

    fun getPersonMainData(userId: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val videoDetail = withContext(Dispatchers.IO) {
                personMainRepository.getPersonMainData(
                    userId,
                    if (userId.toString().length > 4) "NORMAL" else "PGC"
                )
            }
            mainData.value = videoDetail
        }
    }
}