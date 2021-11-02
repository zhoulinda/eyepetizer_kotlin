package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_follow_card.view.*
import kotlinx.android.synthetic.main.view_follow_card.view.descriptionStr
import kotlinx.android.synthetic.main.view_follow_card.view.portrait

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/20
 */
class FollowCardView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_follow_card, this, true)
    }

    fun setData(data: ItemData) {
        data.run {
            titleBar.text = header?.title
            descriptionStr.text = header?.description

            val roundedCorners = RoundedCorners(DisplayUtil.dip2px(3f))
            val options = RequestOptions.bitmapTransform(roundedCorners)
            Glide.with(CommonApplication.getContext())
                .load(content?.data?.cover?.feed)
                .apply(options)
                .into(coverImage)

            Glide.with(CommonApplication.getContext())
                .load(header?.icon)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(portrait)
        }

    }
}