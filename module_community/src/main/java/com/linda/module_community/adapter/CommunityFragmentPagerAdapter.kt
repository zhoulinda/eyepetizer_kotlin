package com.linda.module_community.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.linda.module_base.constants.RouterPaths
import com.linda.module_community.CommunityFragmentFactory

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
class CommunityFragmentPagerAdapter(
    fm: FragmentManager,
    behavior: Int,
    private val tabNames: Array<String>
) :
    FragmentPagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return CommunityFragmentFactory().createFragment(position)
    }

    override fun getCount(): Int {
        return RouterPaths.COMMUNITY_FRAGMENTS.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabNames[position]
    }
}