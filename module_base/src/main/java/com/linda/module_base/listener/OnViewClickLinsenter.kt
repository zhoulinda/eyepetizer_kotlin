package com.linda.module_base.listener

import android.view.View

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/7
 */
interface OnViewClickListener<T> {

    fun onViewClick(view: View, data: T)
}