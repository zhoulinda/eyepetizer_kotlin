package com.linda.module_video_detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.BaseApplication
import com.linda.module_base.bean.VideoDetail
import com.linda.module_video_detail.R
import kotlinx.android.synthetic.main.view_video_info.view.*
import kotlinx.android.synthetic.main.view_video_info.view.category
import kotlinx.android.synthetic.main.view_video_info.view.mShare
import kotlinx.android.synthetic.main.view_video_info.view.mVideoTitle

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
        LayoutInflater.from(context).inflate(R.layout.view_video_info, this, true)
    }

    fun setData(videoDetail: VideoDetail) {

        mVideoTitle.text = videoDetail.title
        category.text = "#" + videoDetail.category
        mVideoBrief.text = videoDetail.description
        mPraise.text = "" + videoDetail.consumption.collectionCount
        mShare.text = "" + videoDetail.consumption.shareCount
        mAuthorName.text = "" + videoDetail.author.name
        description.text = "" + videoDetail.author.description

        Glide.with(BaseApplication.getContext())
            .load(videoDetail.author.icon)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(mAvatar)


    }
}
