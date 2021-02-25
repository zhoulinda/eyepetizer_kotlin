package com.linda.module_main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.linda.module_base.constants.RouterPaths
import com.linda.module_main.MainFragmentFactory

/**
 * 描述 :     主页PagerAdapter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class MainFragmentPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return MainFragmentFactory().createFragment(position)
    }

    override fun getCount(): Int {
        return RouterPaths.MAIN_FRAGMENTS.size
    }
}