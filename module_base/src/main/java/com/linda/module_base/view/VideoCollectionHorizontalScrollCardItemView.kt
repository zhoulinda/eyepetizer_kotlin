package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import kotlinx.android.synthetic.main.view_video_collection_item_card.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/22
 */
class VideoCollectionHorizontalScrollCardItemView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_video_collection_item_card, this, true)
    }

    fun setData(data: Card) {
        Glide.with(context)
            .load(data.data?.content?.data?.cover?.feed)
            .into(image)
        Glide.with(context)
            .load(data.data?.content?.data?.author?.icon)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(portrait)
        videoTitle.text = data.data?.header?.title
        description.text = data.data?.header?.description
    }
}