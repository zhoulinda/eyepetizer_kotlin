package com.linda.module_login.model

import com.linda.module_base.bean.Login
import com.linda.module_base.config.AppConfig
import com.linda.module_base.net.RetrofitManager
import com.linda.lib_net.rx.RxScheduler
import com.linda.module_login.contract.LoginContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     登录model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
class LoginModel : LoginContract.Model {

    override fun login(username: String, password: String): Flowable<Login> {
        return RetrofitManager.service.login(AppConfig.LOGIN, username, password)
            .compose(RxScheduler.io_main())
    }

    override fun cancelRequest() {
    }
}