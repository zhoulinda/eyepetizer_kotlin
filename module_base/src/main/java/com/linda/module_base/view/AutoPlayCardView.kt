package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.module_base.BaseApplication
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import com.linda.module_base.listener.OnViewClickListener
import com.linda.lib_common.utils.DateUtil
import kotlinx.android.synthetic.main.view_auto_play_card.view.*


/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/20
 */
class AutoPlayCardView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.view_auto_play_card, this, true)
    }

    fun setData(position: Int, data: ItemData) {
        //增加封面
        data.run {
            val imageView = ImageView(context)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            Glide.with(context)
                .load(content?.data?.cover?.feed)
                .into(imageView)
            videoPlayer.run {
                thumbImageView = imageView
                backButton.visibility = View.GONE
                thumbImageViewLayout.visibility = View.VISIBLE
            }

            Glide.with(CommonApplication.getContext())
                .load(header?.icon)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(portrait)
            name.text = content?.data?.author?.name
            date.text = content?.data?.date?.let { DateUtil.getFormatDate(it, "yyyy.MM.dd") }
            descriptionStr.text = content?.data?.description
            storeCount.text = "" + content?.data?.consumption?.collectionCount
            commentCount.text = "" + content?.data?.consumption?.replyCount
        }
    }


    private var onViewClickListener: OnViewClickListener<ItemData>? = null

    fun setOnViewClickListener(onViewClickListener: OnViewClickListener<ItemData>) {
        this.onViewClickListener = onViewClickListener
    }
}