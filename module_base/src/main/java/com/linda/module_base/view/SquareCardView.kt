package com.linda.module_base.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.linda.module_base.R
import com.linda.module_base.adapter.SquareCardAdapter
import com.linda.module_base.bean.ItemData
import com.linda.module_base.utils.DisplayUtil
import kotlinx.android.synthetic.main.view_special_square.view.*

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/21
 */
class SquareCardView : FrameLayout {

    private var adapter: SquareCardAdapter? = null

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
        LayoutInflater.from(context).inflate(R.layout.view_special_square, this, true)
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.HORIZONTAL, false)
        recyclerView.addItemDecoration(
            GridSpaceItemDecoration(
                DisplayUtil.dip2px(6f),
                DisplayUtil.dip2px(6f),
                RecyclerView.HORIZONTAL
            )
        )
        adapter = SquareCardAdapter()
        recyclerView.adapter = adapter
    }

    fun setData(data: ItemData) {
        titleBar.text = data.header?.title
        lookMore.text = data.header?.rightText
        lookMore.setOnClickListener {
        }
        data.itemList?.let {
            adapter?.setData(it)
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {

    }

}