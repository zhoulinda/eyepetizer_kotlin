package com.linda.lib_net.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * 描述 :     RxJava 线程处理
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */
class RxScheduler {

    companion object {
        /**
         * 统一线程处理
         */
        fun <T> io_main(): FlowableTransformer<T, T> {
            return FlowableTransformer {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}