package com.linda.module_community

import android.content.Context
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.impl.IFragmentFactory

/**
 * 描述 :     社区Fragment工厂
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/1
 */
class CommunityFragmentFactory : IFragmentFactory {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 ->
                fragment = ARouter.getInstance().build(RouterPaths.RECOMMEND_FRAGMENT)
                    .navigation() as Fragment
            1 ->
                fragment = ARouter.getInstance().build(RouterPaths.ATTENTION_FRAGMENT)
                    .navigation() as Fragment
        }
        return fragment!!
    }

    override fun init(context: Context?) {
        TODO("Not yet implemented")
    }
}