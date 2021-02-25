package com.linda.module_main

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.impl.IFragmentFactory

/**
 * 描述 :     主页Fragment工厂
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class MainFragmentFactory : IFragmentFactory {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 ->
                fragment =
                    ARouter.getInstance().build(RouterPaths.HOME_FRAGMENT).navigation() as Fragment
            1 ->
                fragment =
                    ARouter.getInstance().build(RouterPaths.COMMUNITY_FRAGMENT)
                        .navigation() as Fragment
            2 ->
                fragment =
                    ARouter.getInstance().build(RouterPaths.NOTIFICATION_FRAGMENT)
                        .navigation() as Fragment
            3 ->
                fragment =
                    ARouter.getInstance().build(RouterPaths.MINE_FRAGMENT).navigation() as Fragment
        }
        return fragment!!

    }

    override fun init(context: Context?) {
        TODO("Not yet implemented")
    }
}