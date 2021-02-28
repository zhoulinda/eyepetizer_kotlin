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
import com.linda.module_base.BaseApplication
import com.linda.module_base.R
import com.linda.module_base.bean.ItemData
import com.linda.module_base.listener.OnViewClickListener
import com.linda.module_base.utils.DateUtil
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
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context)
            .load(data.content?.data?.cover?.feed)
            .into(imageView)
        videoPlayer.thumbImageView = imageView
        videoPlayer.backButton.visibility = View.GONE
        videoPlayer.thumbImageViewLayout.visibility = View.VISIBLE

        Glide.with(BaseApplication.getContext())
            .load(data.header?.icon)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(portrait)
        name.text = data.content?.data?.author?.name
        date.text = data.content?.data?.date?.let { DateUtil.getFormatDate(it, "yyyy.MM.dd") }
        description.text = data.content?.data?.description
        storeCount.text = "" + data.content?.data?.consumption?.collectionCount
        commentCount.text = "" + data.content?.data?.consumption?.replyCount
    }


    private var onViewClickListener: OnViewClickListener<ItemData>? = null

    fun setOnViewClickListener(onViewClickListener: OnViewClickListener<ItemData>) {
        this.onViewClickListener = onViewClickListener
    }
}