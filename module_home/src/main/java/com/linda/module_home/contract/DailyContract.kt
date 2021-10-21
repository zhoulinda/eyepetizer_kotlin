package com.linda.module_home.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     首页->日报Contract
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
interface DailyContract {

    interface View : IView {

        fun onGetDailyDataSuccess(itemDataList: BaseListData<ItemData>)

        fun onGetDailyDataError()

        fun onGetDailyMoreDataSuccess(itemDataList: BaseListData<ItemData>)

        fun onGetDailyMoreDataError()

        fun finishRefresh()
    }

    interface Presenter : IPresenter {

        fun getDailyData()

        fun getMoreDailyData()
    }

    interface Model : IModel {

        fun getDailyData(): Flowable<BaseListData<ItemData>>

        fun getDailyMoreData(url: String): Flowable<BaseListData<ItemData>>

        fun cancelRequest()
    }

}