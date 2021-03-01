package com.linda.module_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.linda.lib_common.CommonApplication
import com.linda.module_base.BaseApplication
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import com.linda.module_base.bean.ItemData
import com.linda.module_base.listener.OnMultiViewClickListener
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_special.view.*

/**
 * 描述 :     热门分类adapter
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/21
 */
class SquareCardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onMultiViewClickListener: OnMultiViewClickListener<ItemData>? = null

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
            inflaterView(R.layout.view_special, parent)
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

        val recommend = datas[position].data

        if (holder is ItemHolder) {
            val roundedCorners = RoundedCorners(DisplayUtil.dip2px(3f))
            val options = RequestOptions.bitmapTransform(roundedCorners)
            Glide.with(CommonApplication.getContext())
                .load(recommend?.image)
                .apply(options)
                .into(holder.itemView.mCategoryImage)
            holder.itemView.mCategoryTitle.text = recommend?.title
        }
    }

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setOnItemClClickListener(onMultiViewClickListener: OnMultiViewClickListener<ItemData>) {
        this.onMultiViewClickListener = onMultiViewClickListener
    }
}