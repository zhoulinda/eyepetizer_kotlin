package com.linda.module_video_detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.module_base.BaseApplication
import com.linda.module_base.bean.VideoDetail
import com.linda.module_video_detail.R
import kotlinx.android.synthetic.main.detail_view_picture_info.view.*

/**
 * 描述       图片信息View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/17
 */
class PictureInfoView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.detail_view_picture_info, this, true)
    }

    fun setData(videoDetail: VideoDetail) {
        Glide.with(CommonApplication.getContext())
            .load(videoDetail.owner.avatar)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(portrait)
        authorName.text = videoDetail.owner.nickname
        val tags = videoDetail.tags
        description.text = videoDetail.description
        label.text = tags[0].name
        storeCount.text = "" + videoDetail.consumption.collectionCount
        commentCount.text = "" + videoDetail.consumption.shareCount
    }
}