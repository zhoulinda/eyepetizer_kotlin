package com.linda.module_login.model

import com.linda.module_base.bean.Login
import com.linda.module_base.bean.VerificationCode
import com.linda.module_base.config.AppConfig
import com.linda.module_base.net.RetrofitManager
import com.linda.module_base.net.rx.RxScheduler
import com.linda.module_login.contract.RegisterContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     注册model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
class RegisterModel : RegisterContract.Model {

    override fun getVerification(phone: String): Flowable<VerificationCode> {
        return RetrofitManager.service.getVerificationCode(
            AppConfig.GET_VERIFICATION_CODE,
            phone,
            "sms"
        ).compose(RxScheduler.io_main())
    }

    override fun register(
        username: String,
        verificationCode: String,
        password: String
    ): Flowable<Login> {
        return RetrofitManager.service.register(
            AppConfig.REGISTER,
            username,
            verificationCode,
            password
        ).compose(RxScheduler.io_main())
    }

    override fun cancelRequest() {

    }
}