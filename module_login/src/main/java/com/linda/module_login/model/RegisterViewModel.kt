package com.linda.module_login.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.linda.lib_net.rx.RxScheduler
import com.linda.module_base.bean.Login
import com.linda.module_base.bean.VerificationCode
import com.linda.module_base.config.AppConfig
import com.linda.module_base.net.RetrofitManager
import com.linda.module_login.repository.RegisterRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
Describe:
Created by linda on 2021/10/15
Email:zhoulinda@songguo7.com
 */
class RegisterViewModel(private val registerRepository: RegisterRepository) : ViewModel() {

    val registerData: MutableLiveData<Login> = MutableLiveData()
    val verificationCodeData: MutableLiveData<VerificationCode> = MutableLiveData()

    fun getVerificationCode(phone: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val verificationCode = withContext(Dispatchers.IO) {
                registerRepository.getVerificationCode(phone)
            }
            verificationCodeData.value = verificationCode
        }
    }

    fun register(userName: String, verificationCode: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val register = withContext(Dispatchers.IO) {
                registerRepository.register(
                    userName,
                    verificationCode,
                    password
                )
            }
            registerData.value = register
        }
    }

}