package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.BaseApplication
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import com.linda.module_base.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_columns_card.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/20
 */
class CommunityColumnsCard : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_columns_card, this, true)
    }

    fun setData(data: ItemData) {
        val originWidth = data.content?.data?.width
        val originHeight = data.content?.data?.height
        val realWidth = (DisplayUtil.getScreenWidth(context) / 2) - DisplayUtil.dip2px(15f)
        val realHeight = realWidth * originHeight!! / originWidth!!
        val layoutParams = cover.layoutParams
        layoutParams.width = realWidth
        layoutParams.height = realHeight.toInt()
        cover.layoutParams = layoutParams

        val roundedCorners = RoundedCorners(DisplayUtil.dip2px(3f))
        val options = RequestOptions.bitmapTransform(roundedCorners)

        Glide.with(BaseApplication.getContext())
            .load(data.content.data.cover?.feed)
            .apply(options)
            .into(cover)

        Glide.with(BaseApplication.getContext())
            .load(data.header?.icon)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(portrait)

        description.text = data.content.data.description
        author.text = data.content.data.owner?.nickname
        storeCount.text = "" + data.content.data.consumption?.collectionCount
    }
}