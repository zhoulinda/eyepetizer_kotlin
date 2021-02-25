package com.linda.module_home.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :     首页->发现Contract
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2020/9/4
 */
interface DiscoverContract {

    interface View : IView {

        fun onGetDiscoverDataSuccess(baseListData: BaseListData<ItemData>)

        fun onGetDiscoverDataError()

        fun finishRefresh()
    }

    interface Presenter : IPresenter {

        fun getDiscoverData()
    }

    interface Model : IModel {

        fun getDiscoverData(): Flowable<BaseListData<ItemData>>
    }

}