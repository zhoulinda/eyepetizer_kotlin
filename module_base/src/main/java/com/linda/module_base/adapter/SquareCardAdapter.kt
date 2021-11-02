package com.linda.module_base.adapter

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import com.linda.module_base.bean.DataBean
import com.linda.module_base.bean.ItemData
import com.linda.module_base.databinding.ViewSpecialBinding
import com.linda.module_base.listener.OnMultiViewClickListener
import org.jetbrains.anko.layoutInflater

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
            DataBindingUtil.inflate<ViewSpecialBinding>(
                parent.context.layoutInflater,
                R.layout.view_special,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = datas[position].data

        if (holder is ItemHolder) {
            holder.bind(data)
        }
    }

    class ItemHolder(private val binding: ViewSpecialBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataBean?) {
            binding.data = data
        }
    }

    fun setOnItemClClickListener(onMultiViewClickListener: OnMultiViewClickListener<ItemData>) {
        this.onMultiViewClickListener = onMultiViewClickListener
    }
}