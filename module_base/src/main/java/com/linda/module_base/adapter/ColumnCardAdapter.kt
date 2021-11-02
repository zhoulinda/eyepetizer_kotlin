package com.linda.module_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.synthetic.main.item_column.view.*

/**
 * 描述 :     专题策划adapter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/22
 */
class ColumnCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var datas: ArrayList<Card> = ArrayList()

    fun setData(list: ArrayList<Card>) {
        datas.clear()
        datas.addAll(list)
        notifyDataSetChanged()
    }

    fun addDatas(list: ArrayList<Card>) {
        datas.addAll(list)
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<Card> {
        return datas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemHolder(
            inflaterView(R.layout.item_column, parent)
        )
    }

    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        val view = LayoutInflater.from(parent.context)?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = datas[position].data
        val roundedCorners = RoundedCorners(DisplayUtil.dip2px(6f))
        val options = RequestOptions.bitmapTransform(roundedCorners)
        if (holder is ItemHolder) {
            holder.itemView.run {
                Glide.with(context)
                    .load(data?.image)
                    .override(
                        (DisplayUtil.getScreenWidth(context) - DisplayUtil.dip2px(
                            24f
                        ) / 2), DisplayUtil.dip2px(100f)
                    )
                    .apply(options)
                    .into(mCategoryImage)
                mCategoryTitle.text = data?.title
            }
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}