package com.linda.module_base.view;

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/10/19
 */
class AdHelper : ConstraintHelper {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attributeSet: AttributeSet) : super(context, attributeSet)

    constructor(context: Context?, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)

        val views = getViews(container)

        for (view in views) {
            val anim = ViewAnimationUtils.createCircularReveal(view, 0, 0, 0f, view.width.toFloat())
            anim.duration = 5000
            anim.start()
        }
    }
}
