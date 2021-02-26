package com.linda.module_login.ui

import android.text.TextUtils
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.linda.module_base.bean.Login
import com.linda.module_base.bean.VerificationCode
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivity
import com.linda.module_login.R
import com.linda.module_login.contract.RegisterContract
import com.linda.module_login.presenter.RegisterPresenter
import kotlinx.android.synthetic.main.login_activity_register.*

/**
 * 描述 :     注册页
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/23
 */
@Route(path = RouterPaths.REGISTER_ACTIVITY)
class RegisterActivity : BaseActivity(), RegisterContract.View {

    private var presenter: RegisterPresenter? = null

    override fun getLayoutResId(): Int {
        return R.layout.login_activity_register
    }

    override fun initView() {
        setStatusBarColor()
        counterDownView.setOnClickListener {
            getVerificationCode()
        }

        register.setOnClickListener {
            register()
        }
    }

    override fun initData() {
        presenter = RegisterPresenter(this)
    }

    private fun getVerificationCode() {
        val phone = phone.text.toString()
        when {
            TextUtils.isEmpty(phone) -> {
                Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show()
            }
            else -> {
                presenter?.getVerificationCode(phone)
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
                presenter?.register(phone, verificationCode, password)
            }
        }
    }

    override fun onRegisterSuccess(login: Login) {
        if (login.error == 0) {
            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, login.msg, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRegisterError() {

    }

    override fun onGetVerificationSuccess(verificationCode: VerificationCode) {
        counterDownView.startCounterDown("获取验证码", 60 * 1000, 1000)
    }

    override fun onGetVerificationError() {
    }

}