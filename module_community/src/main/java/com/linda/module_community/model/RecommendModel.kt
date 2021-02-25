package com.linda.module_community.model

import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.net.RetrofitManager
import com.linda.module_base.net.rx.RxScheduler
import com.linda.module_community.contract.RecommendContract
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     社区->推荐model
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
class RecommendModel : RecommendContract.Model {

    override fun getRecommendData(): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getCommunityRecommendData()
            .flatMap { it ->
                val items = it.itemList.filter { it.type == "communityColumnsCard" }
                it.itemList.clear()
                it.itemList.addAll(items)
                Flowable.just(it)
            }
            .compose(RxScheduler.io_main())
    }

    override fun getMoreRecommendData(url: String): Flowable<BaseListData<ItemData>> {
        return RetrofitManager.service.getMoreCommunityRecommendData(url)
            .flatMap { it ->
                val items = it.itemList.filter { it.type == "communityColumnsCard" }
                it.itemList.clear()
                it.itemList.addAll(items)
                Flowable.just(it)
            }
            .compose(RxScheduler.io_main())
    }
}