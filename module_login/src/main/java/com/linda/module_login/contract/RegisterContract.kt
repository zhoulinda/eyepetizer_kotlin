package com.linda.module_login.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.Login
import com.linda.module_base.bean.Register
import com.linda.module_base.bean.VerificationCode
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     注册contract
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
interface RegisterContract {
    interface View : IView {

        fun onRegisterSuccess(login: Login)

        fun onRegisterError()

        fun onGetVerificationSuccess(verificationCode: VerificationCode)

        fun onGetVerificationError()
    }

    interface Presenter : IPresenter {

        fun getVerificationCode(phone: String)

        fun register(phone: String, verificationCode: String, password: String)
    }

    interface Model : IModel {

        fun getVerification(
            phone: String
        ): Flowable<VerificationCode>

        fun register(
            username: String,
            verificationCode: String,
            password: String
        ): Flowable<Login>

        fun cancelRequest()
    }
}