package com.linda.module_base.impl

import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
interface IFragmentFactory : IProvider {

    fun createFragment(position: Int): Fragment
}