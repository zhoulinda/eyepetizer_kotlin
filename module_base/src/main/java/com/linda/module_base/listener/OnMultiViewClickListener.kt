package com.linda.module_base.listener

import android.view.View

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/9
 */
interface OnMultiViewClickListener<T> {

    fun onViewClick(position: Int, view: View, data: T, type: Int)
}