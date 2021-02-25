package com.linda.module_community.model

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager
import com.linda.module_base.net.rx.RxScheduler
import com.linda.module_community.contract.AttentionContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     社区->推荐model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
class AttentionModel : AttentionContract.Model {

    override fun getAttentionData(): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getAttentionData()
            .compose(RxScheduler.io_main())
    }

    override fun getMoreAttentionData(url: String): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getMoreAttentionData(url)
            .compose(RxScheduler.io_main())
    }
}