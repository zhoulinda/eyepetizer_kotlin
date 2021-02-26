package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import com.linda.module_base.utils.DateUtil
import kotlinx.android.synthetic.main.view_video_small_card.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/22
 */
class VideoCardView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_video_small_card, this, true)
    }

    fun setData(data: ItemData) {
        Glide.with(context)
            .load(data.cover?.feed)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(6)))
            .into(videoCover)

        duration.text = data.duration?.let { DateUtil.getDuration(it) }
        titleBar.text = data.title
        category.text = data.category
    }
}