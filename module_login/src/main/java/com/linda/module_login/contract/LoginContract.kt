package com.linda.module_login.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.Login
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     登录contract
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
interface LoginContract {

    interface View : IView {

        fun onLoginSuccess(login: Login)

        fun onLoginError()
    }

    interface Presenter : IPresenter {

        fun login(username: String, password: String)
    }

    interface Model : IModel {

        fun login(username: String, password: String): Flowable<Login>

        fun cancelRequest()
    }
}