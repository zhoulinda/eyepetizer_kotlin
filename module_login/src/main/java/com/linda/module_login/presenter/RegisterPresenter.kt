package com.linda.module_login.presenter

import com.linda.module_login.contract.RegisterContract
import com.linda.module_login.model.RegisterModel
import com.linda.module_login.ui.RegisterActivity

/**
 * 描述 :     注册presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
class RegisterPresenter(private val view: RegisterActivity) : RegisterContract.Presenter {

    private val registerModel: RegisterModel by lazy {
        RegisterModel()
    }

    override fun getVerificationCode(phone: String) {
        registerModel.getVerification(phone).subscribe({
            view.onGetVerificationSuccess(it)
        }, {
            view.onGetVerificationError()
        })
    }

    override fun register(phone: String, verificationCode: String, password: String) {
        registerModel.register(phone, verificationCode, password).subscribe({
            view.onRegisterSuccess(it)
        }, {
            view.onRegisterError()
        })
    }

    override fun onDestoryPresenter() {

    }
}