package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linda.module_base.R
import com.linda.module_base.adapter.ColumnCardAdapter
import com.linda.module_base.bean.ItemData
import com.linda.module_base.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_column_card.view.*

/**
 * 描述 :     专题策划View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/22
 */
class ColumnCardView : FrameLayout {

    private var adapter: ColumnCardAdapter? = null

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
        LayoutInflater.from(context).inflate(R.layout.view_column_card, this, true)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.addItemDecoration(
            GridSpaceItemDecoration(
                DisplayUtil.dip2px(6f),
                DisplayUtil.dip2px(6f),
                RecyclerView.VERTICAL
            )
        )
        adapter = ColumnCardAdapter()
        recyclerView.adapter = adapter
    }

    fun setData(itemData: ItemData) {
        titleBar.text = itemData.header?.title
        lookMore.text = itemData.header?.rightText
        lookMore.setOnClickListener { }
        itemData.itemList?.let { adapter?.setData(it) }
    }
}