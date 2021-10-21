package com.linda.module_main.ui

import com.linda.module_base.ui.BaseActivity
import com.linda.module_main.R
import com.linda.module_main.adapter.MainFragmentPagerAdapter
import kotlinx.android.synthetic.main.main_activity_main.*

/**
 * 描述 :     主页Activity
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class MainActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.main_activity_main
    }

    override fun initView() {
        setStatusBarColor()
        navigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    viewPager.currentItem = 0
                }
                R.id.community -> {
                    viewPager.currentItem = 1
                }
                R.id.notification -> {
                    viewPager.currentItem = 2
                }
                R.id.mine -> {
                    viewPager.currentItem = 3
                }
            }
            true
        }

        viewPager.offscreenPageLimit = 3
        val pagerAdapter = MainFragmentPagerAdapter(
            supportFragmentManager,
            0
        )
        viewPager?.adapter = pagerAdapter
    }

    override fun initData() {

    }
}
