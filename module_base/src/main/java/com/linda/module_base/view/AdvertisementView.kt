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
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_advertisement.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/22
 */
class AdvertisementView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_advertisement, this, true)
    }

    fun setData(data: ItemData) {
        data.run {
            titleBar.text = header?.title
            descriptionStr.text = header?.description
            labelStr.text = label?.text

            Glide.with(context)
                .load(image)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(DisplayUtil.dip2px(15f))))
                .into(coverImage)

            Glide.with(context)
                .load(header?.icon)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(DisplayUtil.dip2px(10f))))
                .into(portrait)
        }
    }
}