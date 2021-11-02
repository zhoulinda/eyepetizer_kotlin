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
import com.linda.module_base.constants.Constants
import com.linda.module_base.constants.RouterPaths
import com.linda.lib_common.utils.DisplayUtil
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
        data.run {
            titleBar.text = header?.title
            banner.run {
                adapter = object : VideoCollectionBannerImageAdapter(data.itemList!!) {
                    override fun onBindView(
                        holder: ItemBannerHolder?,
                        data: Card?,
                        position: Int,
                        size: Int
                    ) {
                        data?.let { holder?.itemView?.setData(it) }
                    }
                }
                setBannerRound(DisplayUtil.dip2px(3f).toFloat())
                setOnBannerListener { data, _ ->
                    ARouter.getInstance().build(RouterPaths.DETAIL_VIDEO_DETAIL_ACTIVITY)
                        .withInt(Constants.VIDEO_ID, (data as Card).data?.content?.data?.id!!)
                        .withString(Constants.RESOURCE_TYPE, data.data?.content?.type)
                        .navigation()
                }
                isAutoLoop(false)
            }
        }
    }
}