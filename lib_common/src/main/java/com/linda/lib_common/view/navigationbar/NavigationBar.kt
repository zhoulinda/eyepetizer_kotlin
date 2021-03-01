package com.linda.lib_common.view.navigationbar

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.linda.lib_common.R

/**
 * 描述 :     主页NavigationBar
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class NavigationBar : LinearLayout {

    private val tabImages = arrayOf(
        R.drawable.ic_tab_strip_icon_feed_selector,
        R.drawable.ic_tab_strip_icon_follow_selector,
        R.drawable.ic_tab_strip_icon_catogery_selector,
        R.drawable.ic_tab_strip_icon_profile_selector
    )

    private val tabText = arrayOf("首页", "社区", "通知", "我的")

    private val tabViews: MutableList<TabView>? = mutableListOf()
    private val tabDatas: MutableList<TabData>? = mutableListOf()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        init(context)
    }

    private fun init(context: Context) {
        for (index in tabImages.indices) {
            val tabData = TabData(
                tabImages[index],
                tabText[index],
                index == 0
            )
            val tabView = TabView(context)

            tabView.setOnClickListener {
                val childIndex = indexOfChild(tabView)
                if (tabDatas?.get(childIndex)?.isSelected!!) {
                    onViewClickListener?.onReSelected(childIndex)
                }
                setCurrentIndex(childIndex)
                onViewClickListener?.onSelected(childIndex)
            }

            tabView.setTabData(tabData)
            tabView.gravity = Gravity.CENTER
            tabViews?.add(tabView)
            tabDatas?.add(tabData)

            val layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            layoutParams.width = 0
            layoutParams.rightMargin = 20
            layoutParams.weight = 1.0f
            addView(tabView, layoutParams)
        }
    }

    /**
     *  选中指定tab
     */
    fun setCurrentIndex(position: Int) {
        for (index in tabViews!!.indices) {
            val tabView = tabViews[index]
            val tabData = tabDatas?.get(index)
            tabData?.isSelected = index == position
            tabView.setTabData(tabData!!)
        }
    }


    private var onViewClickListener: OnViewClickListener? = null

    interface OnViewClickListener {

        fun onSelected(position: Int)

        fun onReSelected(position: Int)
    }

    fun setOnViewClickListener(onViewClickListener: OnViewClickListener) {
        this.onViewClickListener = onViewClickListener
    }

}