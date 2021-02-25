package com.linda.module_main.ui

import androidx.viewpager.widget.ViewPager
import com.linda.module_base.ui.BaseActivity
import com.linda.module_base.view.navigationbar.NavigationBar
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
        navigationBar.setOnViewClickListener(object : NavigationBar.OnViewClickListener {
            override fun onSelected(position: Int) {
                viewPager?.currentItem = position
            }

            override fun onReSelected(position: Int) {

            }
        })

        viewPager.offscreenPageLimit = 3
        val pagerAdapter = MainFragmentPagerAdapter(
            supportFragmentManager,
            0
        )
        viewPager?.adapter = pagerAdapter
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                navigationBar?.setCurrentIndex(position)
            }
        })
    }

    override fun initData() {

    }
}
