package com.linda.module_base.utils

import android.util.Log
import com.linda.module_base.config.AppConfig

/**
 * 描述 :     日志工具类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
class MyLogger {

    companion object {

        fun d(tag: String, msg: String) {
            if (AppConfig.isDebug()) {
                Log.d(tag, msg)
            }
        }

        fun e(tag: String, msg: String) {
            if (AppConfig.isDebug()) {
                Log.e(tag, msg)
            }
        }

        fun v(tag: String, msg: String) {
            if (AppConfig.isDebug()) {
                Log.v(tag, msg)
            }
        }

        fun i(tag: String, msg: String) {
            if (AppConfig.isDebug()) {
                Log.i(tag, msg)
            }
        }

        fun w(tag: String, msg: String) {
            if (AppConfig.isDebug()) {
                Log.w(tag, msg)
            }
        }
    }
}