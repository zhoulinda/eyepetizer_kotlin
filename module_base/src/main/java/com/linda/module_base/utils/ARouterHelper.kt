package com.linda.module_base.utils

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * 描述 :     ARouter帮助类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class ARouterHelper {

    companion object {
        fun getFragment(path: String): Fragment {
            return ARouter.getInstance().build(path).navigation() as Fragment
        }
    }
}