package com.linda.module_mine.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.bean.mine.TabData
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
class PersonMainPagerAdapter(
    fm: FragmentManager,
    behavior: Int, private val tabs: List<TabData>
) : FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return ARouter.getInstance().build(RouterPaths.PERSON_HOME_FRAGMENT)
            .withParcelable(Constants.TAB_DATA, tabs[position])
            .navigation() as Fragment
    }

    override fun getCount(): Int {
        return tabs.size
    }
}