package com.linda.module_notification

import com.alibaba.android.arouter.facade.annotation.Route
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseFragment

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/2
 */
@Route(path = RouterPaths.NOTIFICATION_FRAGMENT)
class NotificationFragment : BaseFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.notification_fragment_notificaiton
    }

    override fun initView() {
    }

    override fun initData() {
    }
}