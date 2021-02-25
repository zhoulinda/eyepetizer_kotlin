package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.linda.module_base.R
import com.linda.module_base.bean.Banner
import com.linda.module_base.utils.DisplayUtil

/**
 * 描述 :     日报列表中信息View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/7
 */
class InfoView : LinearLayout {

//    private var onViewClickListener: OnViewClickListener<DailyData.ItemBean.DataBean.BannerBean>? =
//        null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        orientation = VERTICAL
        setBackgroundColor(context.resources.getColor(R.color.cl_dddddd))
        setPadding(
            DisplayUtil.dip2px(10f),
            DisplayUtil.dip2px(10f),
            DisplayUtil.dip2px(10f),
            DisplayUtil.dip2px(10f)
        )
    }

    fun setData(dataList: List<Banner>) {
        removeAllViews()
        for (bannerBean in dataList) {
            val text = TextView(context)
            text.text = bannerBean.tag_name + "|" + bannerBean.title
            val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            layoutParams.topMargin = DisplayUtil.dip2px(5f)
            addView(text, layoutParams)
        }
    }

//    fun setOnViewClickListener(onViewClickListener: OnViewClickListener<DailyData.ItemBean.DataBean.BannerBean>) {
//        this.onViewClickListener = onViewClickListener
//    }
}