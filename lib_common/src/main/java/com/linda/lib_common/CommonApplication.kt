package com.linda.lib_common

import android.content.Context
import androidx.multidex.MultiDexApplication

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
abstract class CommonApplication : MultiDexApplication(), IBaseApplication {

    companion object {
        private var mContext: Context? = null
        fun getContext() = mContext!!
    }

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }
}