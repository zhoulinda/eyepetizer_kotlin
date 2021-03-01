package com.linda.lib_common.view.navigationbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.linda.lib_common.R
import kotlinx.android.synthetic.main.view_tab.view.*
import org.jetbrains.anko.backgroundResource

/**
 * 描述 :     主页底部导航栏的TabView
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class TabView : LinearLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        orientation = VERTICAL
        init(context)
    }

    fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.view_tab, this, true)
    }

    fun setTabData(tabData: TabData) {
        image.backgroundResource = tabData.icon
        text.text = tabData.text
        isSelected = tabData.isSelected
    }
}