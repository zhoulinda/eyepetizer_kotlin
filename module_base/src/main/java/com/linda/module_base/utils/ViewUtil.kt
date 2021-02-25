package com.linda.module_base.utils

import android.view.View
import com.linda.module_base.BaseApplication
import com.linda.module_base.view.HorizontalScrollCardView

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/19
 */
class ViewUtil {

    companion object {
        fun getCardView(type: String): View {
            return when (type) {
                "horizontalScrollCard" -> HorizontalScrollCardView(BaseApplication.getContext())

                else -> View(BaseApplication.getContext())
            }
        }
    }
}