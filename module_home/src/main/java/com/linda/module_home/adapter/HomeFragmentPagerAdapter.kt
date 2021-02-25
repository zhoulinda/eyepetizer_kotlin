package com.linda.module_home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.linda.module_base.constants.RouterPaths
import com.linda.module_home.HomeFragmentFactory

/**
 * 描述 :     首页PagerAdapter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/1
 */
class HomeFragmentPagerAdapter(
    fm: FragmentManager,
    behavior: Int,
    private val tabNames: Array<String>
) :
    FragmentPagerAdapter(
        fm,
        behavior
    ) {

    override fun getItem(position: Int): Fragment {
        return HomeFragmentFactory().createFragment(position)
    }

    override fun getCount(): Int {
        return RouterPaths.HOME_FRAGMENTS.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
}