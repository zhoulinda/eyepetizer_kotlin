package com.linda.module_mine.contract

import com.linda.module_base.IModel
import com.linda.module_base.IPresenter
import com.linda.module_base.IView
import com.linda.module_base.bean.BaseListData
import com.linda.module_base.bean.ItemData
import com.linda.module_base.bean.VideoItem
import io.reactivex.rxjava3.core.Flowable

/**
 * 描述 :
 *
 * @author: linda
 * email:   zhoulinda@lexue.com
 * 创建日期: 2021/2/5
 */
interface PersonHomeContract {

    interface View : IView {

        fun onGetPersonHomeDataSuccess(data: BaseListData<ItemData>)

        fun onGetPersonHomeDataError()
    }

    interface Presenter : IPresenter {

        fun getPersonHomeData(targetUrl: String)
    }

    interface Model : IModel {

        fun getPersonHomeData(
            targetUrl: String
        ): Flowable<BaseListData<ItemData>>

        fun cancelRequest()
    }
}