package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.linda.module_base.R
import com.linda.module_base.adapter.ItemBannerHolder
import com.linda.module_base.adapter.VideoCollectionBannerImageAdapter
import com.linda.module_base.bean.Card
import com.linda.module_base.bean.ItemData
import com.linda.module_base.constants.RouterPaths
import com.linda.module_base.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_scroll_card.view.banner
import kotlinx.android.synthetic.main.view_video_collection_scroll_card.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/20
 */
class VideoCollectionHorizontalScrollCardView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_video_collection_scroll_card, this, true)
    }

    fun setData(data: ItemData) {
        titleBar.text = data.header?.title
        banner.adapter = object : VideoCollectionBannerImageAdapter(data.itemList!!) {
            override fun onBindView(
                holder: ItemBannerHolder?,
                data: Card?,
                position: Int,
                size: Int
            ) {
                data?.let { holder?.itemView?.setData(it) }
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