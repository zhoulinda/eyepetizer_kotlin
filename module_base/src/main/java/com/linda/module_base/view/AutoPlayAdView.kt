package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import kotlinx.android.synthetic.main.view_auto_play_ad.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/23
 */
class AutoPlayAdView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_auto_play_ad, this, true)
    }

    fun setData(data: ItemData) {

        Glide.with(context)
            .load(data.detail?.icon)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(portrait)

        titleBar.text = data.detail?.title
        description.text = data.detail?.description
    }
}