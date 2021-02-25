package com.linda.module_base.net.rx

import com.linda.module_base.bean.HttpResult
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.functions.Function
import org.reactivestreams.Publisher
import java.lang.Exception

/**
 * 描述 :     请求结果统一处理帮助类
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */
class RxHelper {

    /**
     * 统一处理Flowable
     */
    fun <T> handleResult(): FlowableTransformer<HttpResult<T>, T> {
        return FlowableTransformer { it ->
            it.flatMap(Function<HttpResult<T>, Publisher<T>> { t: HttpResult<T>? ->
                t?.data?.let { createData(it) }
            })
        }
    }


    /**
     * 生成Flowable
     */
    private fun <T> createData(t: T): Publisher<T> {
        return Flowable.create({ emitter ->
            try {
                emitter.onNext(t)
                emitter.onComplete()
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }, BackpressureStrategy.BUFFER)
    }
}