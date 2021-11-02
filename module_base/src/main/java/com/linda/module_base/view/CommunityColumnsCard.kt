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
import com.linda.module_base.BaseApplication
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import com.linda.lib_common.utils.DisplayUtil
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
        data.run {
            val originWidth = content?.data?.width
            val originHeight = content?.data?.height
            val realWidth = (DisplayUtil.getScreenWidth(context) / 2) - DisplayUtil.dip2px(15f)
            val realHeight = realWidth * originHeight!! / originWidth!!

            coverImage.layoutParams = coverImage.layoutParams.apply {
                width = realWidth
                height = realHeight.toInt()
            }

            val roundedCorners = RoundedCorners(DisplayUtil.dip2px(3f))
            val options = RequestOptions.bitmapTransform(roundedCorners)

            Glide.with(CommonApplication.getContext())
                .load(content?.data?.cover?.feed)
                .apply(options)
                .into(coverImage)

            Glide.with(CommonApplication.getContext())
                .load(data.header?.icon)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(portrait)

            descriptionStr.text = content?.data?.description
            author.text = content?.data?.owner?.nickname
            storeCount.text = "" + content?.data?.consumption?.collectionCount
        }

    }
}