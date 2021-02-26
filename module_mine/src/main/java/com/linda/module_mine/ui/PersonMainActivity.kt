package com.linda.module_mine.ui

import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.linda.module_base.bean.mine.PersonMainData
import com.linda.module_base.bean.mine.PgcInfo
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.ui.BaseActivity
import com.linda.module_mine.R
import com.linda.module_mine.adapter.PersonMainPagerAdapter
import com.linda.module_mine.contract.PersonMainContract
import com.linda.module_mine.presenter.PersonMainPresenter
import kotlinx.android.synthetic.main.mine_activity_person_main.*
import kotlinx.android.synthetic.main.mine_layout_person_main_header.*
import org.jetbrains.anko.imageResource
import kotlin.math.abs

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
@Route(path = RouterPaths.PERSON_MAIN_ACTIVITY)
class PersonMainActivity : BaseActivity(), PersonMainContract.View {

    @JvmField
    @Autowired(name = Constants.USER_ID)
    var userId = 0

    private var maxScrollHeight: Float = 0f

    override fun getLayoutResId(): Int {
        return R.layout.mine_activity_person_main
    }

    override fun initView() {
        setImmerseLayout(toolbar)
        ARouter.getInstance().inject(this)
    }

    override fun initData() {
        val presenter = PersonMainPresenter(this)
        presenter.getPersonMainData(userId)
    }

    override fun onGetPersonMainDataSuccess(data: PersonMainData) {
        setTabLayout(data)
        if (userId.toString().length > 4) {
            val pgcInfo = PgcInfo(
                data.userInfo.icon,
                data.userInfo.name,
                data.userInfo.brief,
                data.userInfo.description,
                data.userInfo.cover,
                data.userInfo.followCount,
                data.userInfo.videoCount,
                data.userInfo.myFollowCount
            )
            data.pgcInfo = pgcInfo
        }

        Glide.with(this)
            .load(data.pgcInfo.cover)
            .into(cover)

        Glide.with(this)
            .load(data.pgcInfo.icon)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .placeholder(R.drawable.ic_avatar)
            .error(R.drawable.ic_avatar)
            .into(portrait)

        name.text = data.pgcInfo.name
        brief.text = data.pgcInfo.brief
        description.text = data.pgcInfo.description
        artistCount.text = data.pgcInfo.videoCount.toString()
        attentionCount.text = data.pgcInfo.followCount.toString()
        fansCount.text = data.pgcInfo.myFollowCount.toString()

        viewPager.adapter = PersonMainPagerAdapter(
            supportFragmentManager,
            0, data.tabInfo.tabList
        )
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                tabLayout.getTabAt(position)?.select()
            }
        })

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (maxScrollHeight != 0f) {
                val scrollPercent: Float = abs(verticalOffset) / maxScrollHeight
                toolbar.alpha = scrollPercent
                if (abs(verticalOffset) >= maxScrollHeight) {
                    back.imageResource = R.drawable.ic_back_black
                    titleBar.alpha = scrollPercent
                } else {
                    back.imageResource = R.drawable.ic_back_white
                    titleBar.alpha = scrollPercent
                }
            }
        })
    }

    private fun setTabLayout(data: PersonMainData) {
        for (tabData in data.tabInfo.tabList) {
            tabLayout.addTab(tabLayout.newTab().setText(tabData.name))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
            }
        })
    }

    override fun onGetDailyDataError() {
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            maxScrollHeight = (mPersonMainHeader.measuredHeight - toolbar.measuredHeight).toFloat()
        }
    }
}