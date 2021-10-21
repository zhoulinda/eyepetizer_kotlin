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
import kotlinx.android.synthetic.main.detail_view_video_info.view.*

/**
 * 描述 :     视频简信息
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/8
 */
class VideoInfoView : FrameLayout {

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
        LayoutInflater.from(context).inflate(R.layout.detail_view_video_info, this, true)
    }

    fun setData(videoDetail: VideoDetail) {
        videoDetail.run {

            videoTitle.text = title
            categoryStr.text = "#$category"
            videoBrief.text = description
            praise.text = "" + consumption.collectionCount
            share.text = "" + consumption.shareCount
            authorName.text = "" + author.name
            descriptionStr.text = "" + author.description

            Glide.with(CommonApplication.getContext())
                .load(author.icon)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(portrait)
        }
    }
}
