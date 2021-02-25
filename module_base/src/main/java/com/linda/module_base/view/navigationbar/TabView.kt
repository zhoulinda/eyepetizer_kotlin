package com.linda.module_base.view.navigationbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.linda.module_base.R
import com.linda.module_base.view.navigationbar.TabData
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.textColor

/**
 * 描述 :     主页底部导航栏的TabView
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/7/24
 */
class TabView : LinearLayout {

    var image: ImageView? = null
    var text: TextView? = null

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
        val view = LayoutInflater.from(context).inflate(R.layout.view_tab, this, true)
        image = view.findViewById<ImageView>(R.id.image)
        text = view.findViewById<TextView>(R.id.text)
    }

    fun setTabData(tabData: TabData) {
        image?.backgroundResource = tabData.icon
        text?.text = tabData.text
        isSelected = tabData.isSelected
    }
}