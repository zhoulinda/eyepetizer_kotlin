package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import com.linda.module_base.constants.RouterPaths
import com.linda.lib_common.utils.DisplayUtil
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.view_scroll_card.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/19
 */
class HorizontalScrollCardView : FrameLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_scroll_card, this, true)
    }

    fun setData(cards: ArrayList<Card>?) {
        banner.adapter = object : BannerImageAdapter<Card>(cards) {
            override fun onBindView(
                holder: BannerImageHolder?,
                data: Card?,
                position: Int,
                size: Int
            ) {
                Glide.with(context)
                    .load(data?.data?.image)
                    .into(holder!!.imageView)
            }
        }
        banner.setBannerRound(DisplayUtil.dip2px(3f).toFloat())
        banner.setOnBannerListener { data, _ ->
            ARouter.getInstance().build(RouterPaths.WEBVIEW_ACTIVITY)
                .withString("url", (data as Card).data?.actionUrl)
                .navigation()
        }
        banner.isAutoLoop(false)
    }
}