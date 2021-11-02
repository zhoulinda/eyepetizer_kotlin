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
import com.linda.lib_common.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_column_card.view.*

/**
 * 描述 :     专题策划View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/22
 */
class ColumnCardView : FrameLayout {

    private val columnCardAdapter: ColumnCardAdapter by lazy { ColumnCardAdapter() }

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
        recyclerView.run {
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(
                GridSpaceItemDecoration(
                    DisplayUtil.dip2px(6f),
                    DisplayUtil.dip2px(6f),
                    RecyclerView.VERTICAL
                )
            )
            adapter = columnCardAdapter
        }
    }

    fun setData(data: ItemData) {
        data.run {
            titleBar.text = header?.title
            lookMore.text = header?.rightText
            itemList?.let { columnCardAdapter.setData(it) }
        }
    }
}