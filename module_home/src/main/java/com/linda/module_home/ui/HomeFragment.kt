package com.linda.module_home.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseFragment
import com.linda.module_base.ui.BaseFragmentV2
import com.linda.module_home.R
import com.linda.module_home.adapter.HomeFragmentPagerAdapter
import com.linda.module_home.databinding.HomeFragmentHomeBinding
import kotlinx.android.synthetic.main.home_fragment_home.*

/**
 * 描述 :     首页fragment
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/25
 */
@Route(path = RouterPaths.HOME_FRAGMENT)
class HomeFragment : BaseFragmentV2<HomeFragmentHomeBinding>(R.layout.home_fragment_home) {

    private val tabNames: Array<String> = arrayOf("发现", "日报")

    override fun initView() {
        viewPager.adapter = HomeFragmentPagerAdapter(
            childFragmentManager,
            0, tabNames
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
