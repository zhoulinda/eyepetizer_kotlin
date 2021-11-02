package com.linda.module_login.ui

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivity
import com.linda.module_login.R
import com.linda.module_login.databinding.LoginActivityRegisterBinding
import com.linda.module_login.model.RegisterViewModel
import com.linda.module_login.repository.RegisterRepository
import kotlinx.android.synthetic.main.login_activity_register.*

/**
 * 描述 :     注册页
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
@Route(path = RouterPaths.REGISTER_ACTIVITY)
class RegisterActivity :
    BaseActivity<LoginActivityRegisterBinding>(R.layout.login_activity_register) {

    private val viewModel by lazy { RegisterViewModel(RegisterRepository()) }

    override fun initView() {
        viewModel.run {
            verificationCodeData.observe(this@RegisterActivity, Observer {
                counterDownView.startCounterDown("获取验证码", 60 * 1000, 1000)
            })

            registerData.observe(this@RegisterActivity, Observer {
                if (it.error == 0) {
                    Toast.makeText(this@RegisterActivity, "注册成功", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@RegisterActivity, it.msg, Toast.LENGTH_SHORT).show()
                }
            })
        }
        setStatusBarColor()
        counterDownView.setOnClickListener {
            getVerificationCode()
        }

        register.setOnClickListener {
            register()
        }
    }

    override fun initData() {
    }

    private fun getVerificationCode() {
        val phone = phone.text.toString()
        when {
            TextUtils.isEmpty(phone) -> {
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show()
            }
            else -> {
                viewModel.getVerificationCode(phone)
            }
        }
    }

    private fun register() {
        val phone = phone.text.toString()
        val password = password.text.toString()
        val verificationCode = verificationCode.text.toString()
        when {
            TextUtils.isEmpty(phone) -> {
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(verificationCode) -> {
                Toast.makeText(this, "验证码不能为空", Toast.LENGTH_SHORT).show()
            }
            TextUtils.isEmpty(password) -> {
                Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show()
            }
            else -> {
                viewModel.register(phone, verificationCode, password)
            }
        }
    }

}