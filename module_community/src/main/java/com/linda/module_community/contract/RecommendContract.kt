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
interface RecommendContract {

    interface View : IView {
        fun onGetRecommendDataSuccess(baseListData: BaseListData<ItemData>)

        fun onGetRecommendDataError()

        fun onGetMoreRecommendDataSuccess(baseListData: BaseListData<ItemData>)

        fun onGetMoreRecommendDataError()
    }

    interface Presenter : IPresenter {
        fun getRecommendData()

        fun getMoreRecommendData()
    }


    interface Model : IModel {
        fun getRecommendData(): Flowable<BaseListData<ItemData>>

        fun getMoreRecommendData(url: String): Flowable<BaseListData<ItemData>>
    }

}