package com.linda.module_community.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     社区->推荐contract
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/12
 */
interface AttentionContract {

    interface View : IView {
        fun onGetAttentionDataSuccess(recommends: BaseListData<ItemData>)

        fun onGetAttentionDataError()

        fun onGetMoreAttentionDataSuccess(recommends: BaseListData<ItemData>)

        fun onGetMoreAttentionDataError()

        fun finishRefresh()
    }

    interface Presenter : IPresenter {
        fun getAttentionData()

        fun getMoreAttentionData()
    }

    interface Model : IModel {
        fun getAttentionData(): Flowable<BaseListData<ItemData>>

        fun getMoreAttentionData(url: String): Flowable<BaseListData<ItemData>>
    }

}