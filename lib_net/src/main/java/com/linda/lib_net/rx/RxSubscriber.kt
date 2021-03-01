package com.linda.lib_net.rx

import io.reactivex.rxjava3.subscribers.ResourceSubscriber

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/3
 */
class RxSubscriber<T>() : ResourceSubscriber<T>() {

    override fun onComplete() {
        TODO("Not yet implemented")
    }

    override fun onNext(t: T) {
        TODO("Not yet implemented")
    }

    override fun onError(t: Throwable?) {
        TODO("Not yet implemented")
    }
}