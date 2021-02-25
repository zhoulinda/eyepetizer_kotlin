package com.linda.module_login.presenter

import com.linda.module_login.contract.LoginContract
import com.linda.module_login.model.LoginModel
import com.linda.module_login.ui.LoginActivity

/**
 * 描述 :     登录presenter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
class LoginPresenter(private val view: LoginActivity) : LoginContract.Presenter {

    private val loginModel: LoginModel by lazy {
        LoginModel()
    }

    override fun login(username: String, password: String) {
        loginModel.login(username, password).subscribe({
            view.onLoginSuccess(it)
        }, {
            view.onLoginError()
        })
    }

    override fun onDestoryPresenter() {

    }
}