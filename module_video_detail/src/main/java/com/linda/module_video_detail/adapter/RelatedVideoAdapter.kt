package com.linda.module_video_detail.adapter

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.linda.module_base.bean.RelatedVideo
import com.linda.module_base.bean.VideoItem
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.module_video_detail.R
import com.linda.module_video_detail.databinding.DetailItemRelatedVideoBinding
import org.jetbrains.anko.layoutInflater

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
                ItemHolder(
                    DataBindingUtil.inflate(
                        parent.context.layoutInflater,
                        R.layout.detail_item_related_video,
                        parent,
                        false
                    )
                )
            else ->
                ItemHolder(
                    DataBindingUtil.inflate(
                        parent.context.layoutInflater,
                        R.layout.view_empty,
                        parent,
                        false
                    )
                )
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemHolder) {
            val data = datas[position].data
            holder.bind(onMultiViewClickListener, data)
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

    class ItemHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            onMultiViewClickListener: OnMultiViewClickListener<RelatedVideo>?,
            data: RelatedVideo
        ) {
            if (binding is DetailItemRelatedVideoBinding) {
                binding.run {
                    video = data
                    listener = View.OnClickListener {
                        onMultiViewClickListener?.onViewClick(
                            position,
                            itemView,
                            data,
                            itemViewType
                        )
                    }
                }
            }
        }
    }

    fun setOnMultiViewClickListener(onMultiViewClickListener: OnMultiViewClickListener<RelatedVideo>) {
        this.onMultiViewClickListener = onMultiViewClickListener
    }
}