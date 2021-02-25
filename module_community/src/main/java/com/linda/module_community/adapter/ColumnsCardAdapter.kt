package com.linda.module_community.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.linda.module_base.bean.Data
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import com.linda.module_community.R

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
class ColumnsCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var datas: ArrayList<VideoItem<ItemData>> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_columns_card, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is ItemHolder) {
//            val columnsCardData = datas[position].data.data
//            bindColumnsCardData(holder.itemView, columnsCardData)
//        }
    }

//    private fun bindColumnsCardData(itemView: View, columnsCardData: Data) {
//        Glide.with(itemView.context)
//            .load(columnsCardData.content.data.cover.feed)
//            .into(itemView.cover)
//
//        Glide.with(itemView.context)
//            .load(columnsCardData.header.icon)
//            .into(itemView.headPortrait)
//
//        itemView.description.text = columnsCardData.content.data.description
//        itemView.storeCount.text = "" + columnsCardData.content.data.consumption.collectionCount
//    }

    fun setData(recommends: List<VideoItem<ItemData>>) {
        datas.clear()
        datas.addAll(recommends)
        notifyDataSetChanged()
    }

    fun addData(recommends: List<VideoItem<ItemData>>) {
        datas.addAll(recommends)
        notifyDataSetChanged()
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}