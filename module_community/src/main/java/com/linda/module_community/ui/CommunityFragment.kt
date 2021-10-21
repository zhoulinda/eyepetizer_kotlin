package com.linda.module_community.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseFragment
import com.linda.module_base.ui.BaseFragmentV2
import com.linda.module_community.R
import com.linda.module_community.adapter.CommunityFragmentPagerAdapter
import com.linda.module_community.databinding.CommunityFragmentCommunityBinding
import kotlinx.android.synthetic.main.community_fragment_community.*

/**
 * 描述 :     社区Fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
@Route(path = RouterPaths.COMMUNITY_FRAGMENT)
class CommunityFragment :
    BaseFragmentV2<CommunityFragmentCommunityBinding>(R.layout.community_fragment_community) {

    private val tabNames: Array<String> = arrayOf("推荐", "关注")

    override fun initView() {

        viewPager.adapter = CommunityFragmentPagerAdapter(
            childFragmentManager,
            0,
            tabNames
        )

        tabLayout.run {
            setupWithViewPager(viewPager)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    viewPager?.currentItem = tab?.position!!
                }
            })
        }
    }

    override fun initData() {
    }
}