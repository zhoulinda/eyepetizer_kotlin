package com.linda.module_login.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.linda.module_base.bean.Login
import com.linda.module_login.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    val data: MutableLiveData<Login> = MutableLiveData()

    fun login(userName: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val login = withContext(Dispatchers.IO) {
                loginRepository.login(
                    userName,
                    password
                )
            }
            data.value = login
        }
    }

}