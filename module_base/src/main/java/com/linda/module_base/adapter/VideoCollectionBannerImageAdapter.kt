package com.linda.module_base.adapter

import android.view.ViewGroup
import com.linda.module_base.bean.Card
import com.linda.module_base.view.VideoCollectionHorizontalScrollCardItemView
import com.youth.banner.adapter.BannerAdapter

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/22
 */
abstract class VideoCollectionBannerImageAdapter(cards: ArrayList<Card>) :
    BannerAdapter<Card, ItemBannerHolder>(cards) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): ItemBannerHolder {
        val view = VideoCollectionHorizontalScrollCardItemView(parent!!.context).apply {
            //注意，必须设置为match_parent，这个是viewpager2强制要求的
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return ItemBannerHolder(view)
    }
}