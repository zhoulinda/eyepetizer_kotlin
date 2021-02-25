package com.linda.module_login.view

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.linda.module_base.listener.OnViewClickListener

/**
 * 描述 :     倒计时View
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/24
 */
class CounterDownView : AppCompatButton {

    private var countDownTimer: CountDownTimer? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
    }

    fun startCounterDown(
        originText: String,
        millisInFuture: Long,
        countDownInterval: Long
    ) {
        text = originText
        countDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
            override fun onFinish() {
                text = originText
            }

            override fun onTick(millisUntilFinished: Long) {
                val surplusTime = millisUntilFinished / 1000
                text = "$surplusTime" + "s"
            }
        }.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        countDownTimer?.cancel()
    }

    private var onViewClickListener: OnViewClickListener<Long>? = null

    fun setOnViewClickListener(onViewClickListener: OnViewClickListener<Long>) {
        this.onViewClickListener = onViewClickListener
    }
}