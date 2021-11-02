package com.linda.module_login.ui

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.lib_common.utils.PrefsUtil
import com.linda.module_base.bean.Member
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivity
import com.linda.module_login.R
import com.linda.module_login.databinding.LoginActivityLoginBinding
import com.linda.module_login.model.LoginViewModel
import com.linda.module_login.repository.LoginRepository
import kotlinx.android.synthetic.main.login_activity_login.*

/**
 * 描述 :     登录页
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
@Route(path = RouterPaths.LOGIN_ACTIVITY)
class LoginActivity : BaseActivity<LoginActivityLoginBinding>(R.layout.login_activity_login) {

    private var userData: Member by PrefsUtil(
        Constants.USER_DATA, Member(
            0, "", "", "", "", ""
        )

    )

    private val viewModel by lazy { LoginViewModel(LoginRepository()) }

    override fun initView() {
        viewModel.run {
            data.observe(this@LoginActivity, Observer {
                userData = it.member
            })
        }
        setStatusBarColor()
        login.setOnClickListener {
            login()
        }

        register.setOnClickListener {
            ARouter.getInstance().build(RouterPaths.REGISTER_ACTIVITY).navigation()
        }
    }

    override fun initData() {

    }

    private fun login() {
        val phone = phone.text.toString()
        val password = password.text.toString()
        when {
            TextUtils.isEmpty(phone) -> {
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            }
            else -> {
                viewModel.login(phone, password)
            }
        }
    }
}