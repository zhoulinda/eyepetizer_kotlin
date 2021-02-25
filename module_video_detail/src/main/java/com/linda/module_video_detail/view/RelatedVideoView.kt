package com.linda.module_video_detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoItem
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_base.listener.OnViewClickListener
import com.linda.module_video_detail.R
import com.linda.module_video_detail.adapter.RelatedVideoAdapter
import kotlinx.android.synthetic.main.view_related_video.view.*

/**
 * 描述 :     相关视频列表
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/10
 */
class RelatedVideoView : FrameLayout {

    private var adapter: RelatedVideoAdapter? = null

    private var onViewClickListener: OnViewClickListener<Any>? = null

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
        LayoutInflater.from(context).inflate(R.layout.view_related_video, this, true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.isNestedScrollingEnabled = false
        adapter = RelatedVideoAdapter()
        recyclerView.adapter = adapter
        adapter?.setOnMultiViewClickListener(object : OnMultiViewClickListener<RelatedVideo> {
            override fun onViewClick(position: Int, view: View, data: RelatedVideo, type: Int) {
                onViewClickListener?.onViewClick(view, data)
            }
        })

        lookMore.setOnClickListener {
            onViewClickListener?.onViewClick(it, Any())
        }
    }

    fun setData(relatedVideoList: BaseListData<RelatedVideo>, isShowAll: Boolean) {
        var videoList = relatedVideoList.itemList as MutableList<VideoItem<RelatedVideo>>
        if (!isShowAll && videoList.size > 5) {
            videoList =
                videoList.subList(0, 6)
            lookMore.visibility = View.VISIBLE
        } else {
            lookMore.visibility = View.GONE
        }
        adapter?.setData(videoList)
    }

    fun setOnViewClickListener(onViewClickListener: OnViewClickListener<Any>) {
        this.onViewClickListener = onViewClickListener
    }

}