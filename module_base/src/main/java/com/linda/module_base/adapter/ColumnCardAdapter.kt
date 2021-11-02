package com.linda.module_base.adapter

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.linda.module_base.R
import com.linda.module_base.bean.Card
import com.linda.module_base.bean.DataBean
import com.linda.module_base.databinding.ItemColumnBinding
import org.jetbrains.anko.layoutInflater

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
            DataBindingUtil.inflate<ItemColumnBinding>(
                parent.context.layoutInflater,
                R.layout.item_column,
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

    class ItemHolder(private val binding: ItemColumnBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataBean?) {
            binding.data = data
        }
    }
}