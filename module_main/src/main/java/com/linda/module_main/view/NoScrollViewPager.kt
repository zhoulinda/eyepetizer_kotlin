package com.linda.module_main.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 描述 :     禁止滚动的ViewPager
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/1
 */
class NoScrollViewPager : ViewPager {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }
}