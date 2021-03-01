package com.linda.module_video_detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.module_base.BaseApplication
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoItem
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.lib_common.utils.DisplayUtil
import com.linda.module_video_detail.R
import kotlinx.android.synthetic.main.detail_item_related_video.view.*

/**
 * 描述 :     详情页相关视频列表Adapter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/6
 */
class RelatedVideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onMultiViewClickListener: OnMultiViewClickListener<RelatedVideo>? =
        null

    private var datas: ArrayList<VideoItem<RelatedVideo>> = ArrayList()

    companion object {
        const val ITEM_TYPE_VIDEO_SMALL_CARD = 1 //videoSmallCard类型
        const val ITEM_TYPE_EMPTY_CARD = 2 //empty类型
    }

    fun setData(list: List<VideoItem<RelatedVideo>>) {
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
    }

    fun addDatas(list: List<VideoItem<RelatedVideo>>) {
        datas.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_VIDEO_SMALL_CARD ->
                ItemHolder(inflaterView(R.layout.detail_item_related_video, parent))
            else ->
                ItemHolder(View(parent.context))
        }
    }

    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = LayoutInflater.from(parent.context)?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
            val data = datas[position].data
            when (getItemViewType(position)) {
                ITEM_TYPE_VIDEO_SMALL_CARD ->
                    bindRelatedVideo(
                        holder.itemView,
                        position,
                        data,
                        ITEM_TYPE_VIDEO_SMALL_CARD
                    )
                else ->
                    bindEmptyView()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val itemData = datas[position]
        return when (itemData.type) {
            "videoSmallCard" ->
                ITEM_TYPE_VIDEO_SMALL_CARD
            else -> ITEM_TYPE_EMPTY_CARD
        }
    }

    private fun bindRelatedVideo(
        itemView: View,
        position: Int,
        relatedVideo: RelatedVideo,
        type: Int
    ) {
        val roundedCorners = RoundedCorners(DisplayUtil.dip2px(3f))
        val options = RequestOptions.bitmapTransform(roundedCorners)
        Glide.with(CommonApplication.getContext())
            .load(relatedVideo.cover.feed)
            .apply(options)
            .into(itemView.videoCover)

        itemView.setOnClickListener {
            onMultiViewClickListener?.onViewClick(position, itemView, relatedVideo, type)
        }

        itemView.videoTitle.text = relatedVideo.title
        itemView.category.text =
            "#" + relatedVideo.category + "/" + relatedVideo.author?.name
    }

    private fun bindEmptyView() {

    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setOnMultiViewClickListener(onMultiViewClickListener: OnMultiViewClickListener<RelatedVideo>) {
        this.onMultiViewClickListener = onMultiViewClickListener
    }

}