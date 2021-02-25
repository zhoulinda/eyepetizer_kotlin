package com.linda.module_base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/19
 */
abstract class BaseAdapter<T> :
    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    var datas: ArrayList<T> = ArrayList()
    private var mHeaderView: View? = null
    private var headerViewSize = 0
    private var mListener: OnItemClickListener<T>? = null

    fun setHeaderView(headerView: View?) {
        mHeaderView = headerView
        headerViewSize = 1
    }

    fun getHeaderView(): View? {
        return mHeaderView
    }

    fun addData(list: List<T>?) {
        list?.let { datas.addAll(it) }
        notifyDataSetChanged()
    }

    fun setData(list: List<T>?) {
        datas.clear()
        datas.addAll(list!!)
        notifyDataSetChanged()
    }

    fun getData(): List<T> {
        return datas
    }

    fun clear() {
        datas.clear()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        var type = TYPE_ITEM
        if (headerViewSize == 1 && position == 0) {
            type = TYPE_HEADER
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(mHeaderView!!)
            TYPE_ITEM -> onCreate(parent, viewType)
            else -> EmptyViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_HEADER) return
        if (getItemViewType(position) == TYPE_ITEM) {
            val pos = getRealPosition(viewHolder)
            val data = datas[pos]
            onBind(viewHolder, pos, data)
            viewHolder.itemView.setOnClickListener { v ->
                mListener?.onItemClick(v, position, data)
            }
        }
    }

    private fun getRealPosition(holder: RecyclerView.ViewHolder): Int {
        val position: Int = holder.layoutPosition
        return if (mHeaderView == null) position else position - 1
    }

    override fun getItemCount(): Int {
        return datas.size + headerViewSize
    }

    abstract fun onCreate(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBind(
        viewHolder: RecyclerView.ViewHolder,
        realPosition: Int,
        data: T
    )

    class EmptyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    open class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface OnItemClickListener<T> {
        fun onItemClick(v: View?, position: Int, data: T)
    }

    fun setOnItemClickListener(li: OnItemClickListener<T>?) {
        mListener = li
    }
}
